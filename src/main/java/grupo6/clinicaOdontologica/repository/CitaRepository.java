package grupo6.clinicaOdontologica.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo6.clinicaOdontologica.model.entity.Cita;
import grupo6.clinicaOdontologica.model.enums.EstadoCita;


public interface CitaRepository extends JpaRepository <Cita, Long> {

    long countByPacienteIdAndEstado(Long pacienteId, EstadoCita estado);//Quiero contar cuántas citas tiene un paciente con un estado Pendiente    
    
    long countByOdontologoIdAndEstado(Long odontologoId, EstadoCita estado); //Quiero contar cuántas citas pendientes tiene un un odontologo

    List<Cita> findByOdontologoIdAndEstado(Long odontologoId, EstadoCita estado);

    boolean existsByOdontologoIdAndFechaHora(Long id, LocalDateTime horario);

    long countByPacienteIdAndEstadoAndFechaHoraBetween(Long id, EstadoCita pendiente, LocalDateTime inicioSemana,
            LocalDateTime finSemana);

    List<Cita> findByEstado(EstadoCita estado);

    List<Cita> findByPacienteId(Long pacienteId);

}
