package grupo6.clinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupo6.clinicaOdontologica.model.entity.Odontologo;

public interface OdontologoRepository extends JpaRepository <Odontologo, Long> {

}
