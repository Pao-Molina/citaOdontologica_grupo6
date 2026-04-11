package grupo6.clinicaOdontologica.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odontologos")

public class Odontologo extends BaseEntity {

  @Column(name = "nombre", nullable = false, length = 80)  
  private String nombre;

  @Column(name = "especialidad", nullable = false, length = 100)
  private String especialidad;

  @Column(name = "tarjetaProfesional", nullable = false, length = 20)
  private String tarjetaProfesional;

}
