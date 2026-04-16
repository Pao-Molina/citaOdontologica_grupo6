package grupo6.clinicaOdontologica.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo6.clinicaOdontologica.model.entity.Paciente;
import grupo6.clinicaOdontologica.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
    
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Paciente save(Paciente request) {
        return pacienteRepository.save(request);
    }

    @Transactional
    public Paciente update(Long id, Paciente request) {
        Paciente paciente = findById(id);
        paciente.setNombreCompleto(request.getNombreCompleto());
        paciente.setEdad(request.getEdad());
        paciente.setTieneSeguro(request.getTieneSeguro());
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void delete(Long id) {
        findEntity(id);
        pacienteRepository.deleteById(id);
    }

    public Paciente findEntity(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado con id: " + id));
    }


}


	

