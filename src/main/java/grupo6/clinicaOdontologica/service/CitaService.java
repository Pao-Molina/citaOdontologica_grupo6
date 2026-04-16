package grupo6.clinicaOdontologica.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo6.clinicaOdontologica.model.entity.Cita;
import grupo6.clinicaOdontologica.model.entity.Odontologo;
import grupo6.clinicaOdontologica.model.entity.Paciente;
import grupo6.clinicaOdontologica.model.enums.EstadoCita;
import grupo6.clinicaOdontologica.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    private static final double descuentoSeguro = 0.30;
    private static final double costoCita = 100000; 

    @Transactional

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Cita findById(Long id) {
        return findEntity(id);
    }

    public Cita agendarCita(Cita request) {
        Odontologo odontologo = odontologoService.findEntity(request.getOdontologo().getId());
        Paciente paciente = pacienteService.findEntity(request.getPaciente().getId());

        // 1. Validar disponibilidad del odontólogo
        validarDisponibilidadOdontologo(odontologo, request.getFechaHora());

        // 2. Validar acudiente si es menor de edad
        validarAcudienteSiEsMenor(paciente, request.getNombreAcudiente());

        // 3. Validar límite de citas pendientes por semana
        validarLimiteCitasSemanales(paciente, request.getFechaHora());

        // 4. Calcular costo con descuento si aplica
        double costoFinal = calcularCosto(paciente.getTieneSeguro());
        request.setCosto(costoFinal);

        request.setOdontologo(odontologo);
        request.setPaciente(paciente);
        request.setEstado(EstadoCita.PENDIENTE);

        return citaRepository.save(request);
    }

    public List<Cita> findByEstado(EstadoCita estado) {
        return citaRepository.findByEstado(estado); 
    }

    public List<Cita> findByPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    @Transactional
    public void delete(Long id) {
        Cita cita = findEntity(id);
        citaRepository.delete(cita);
    }

    @Transactional
    public Cita cambiarEstado(Long id, EstadoCita nuevoEstado) {
        Cita cita = findEntity(id);
        cita.setEstado(nuevoEstado);
        return citaRepository.save(cita);
    }

    @Transactional
    public Cita cancelarCita(Long id) {
        Cita cita = findEntity(id);

        // 5. Validar tiempo mínimo para cancelación (2 horas)
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.plusHours(2).isAfter(cita.getFechaHora())) {
            throw new IllegalStateException("No se puede cancelar con menos de 2 horas de antelación.");
        }

        cita.setEstado(EstadoCita.CANCELADA);
        return citaRepository.save(cita);
    }

    

    private void validarDisponibilidadOdontologo(Odontologo odontologo, LocalDateTime horario) {
        
        boolean ocupado = citaRepository.existsByOdontologoIdAndFechaHora(odontologo.getId(), horario);
        if (ocupado) {
            throw new IllegalArgumentException("El odontólogo ya tiene una cita programada en ese horario.");
        }
    }

    private void validarAcudienteSiEsMenor(Paciente paciente, String nombreAcudiente) {
        if (Integer.parseInt(paciente.getEdad()) < 18 && (nombreAcudiente == null || nombreAcudiente.trim().isEmpty())) {
            throw new IllegalArgumentException("Los pacientes menores de edad requieren el nombre de un acudiente.");
        }
    }

    private void validarLimiteCitasSemanales(Paciente paciente, LocalDateTime fechaCita) {
        LocalDateTime inicioSemana = fechaCita.with(DayOfWeek.MONDAY).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime finSemana = inicioSemana.plusDays(7);

        long citasPendientes = citaRepository.countByPacienteIdAndEstadoAndFechaHoraBetween(
                paciente.getId(), 
                EstadoCita.PENDIENTE, 
                inicioSemana, 
                finSemana
        );

        if (citasPendientes >= 2) {
            throw new IllegalArgumentException("El paciente no puede tener más de 2 citas pendientes en la misma semana.");
        }
    }

    private double calcularCosto(boolean tieneSeguro) {
        if (tieneSeguro) {
            return costoCita * (1 - descuentoSeguro);
        }
        return costoCita;
    }

    private Cita findEntity(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada con id: " + id));
    }
}



