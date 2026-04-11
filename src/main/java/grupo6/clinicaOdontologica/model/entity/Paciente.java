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
@Table(name = "pacientes")

public class Paciente extends BaseEntity {

 @Column(name = "nombre", nullable = false, length = 80)  
  private String nombre;

  @Column(name = "edad", nullable = false)
  private String edad;

  @Column(name = "tieneSeguro", nullable = false)
  private Boolean tieneSeguro;

}
