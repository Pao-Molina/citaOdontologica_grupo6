package grupo6.clinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo6.clinicaOdontologica.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository <Paciente, Long> {

}
