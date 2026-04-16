package grupo6.clinicaOdontologica.model.entity;

import java.time.LocalDateTime;

import grupo6.clinicaOdontologica.model.enums.EstadoCita;
import grupo6.clinicaOdontologica.model.enums.MotivoCita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "citas")

public class Cita extends BaseEntity {

@Column(name = "fecha_hora", nullable = false)
private LocalDateTime fechaHora;

@Enumerated(EnumType.STRING)
@Column(name = "motivo", nullable = false, length = 100)
private MotivoCita motivo;

@Column(name = "costo", nullable = false)  
private float costo;

@Enumerated(EnumType.STRING)
@Column(name = "estado", nullable = false, length = 20)
private EstadoCita estado;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "odontolodo_id", nullable = false)
private Odontologo odontologo;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "paciente_id", nullable = false)
private Paciente paciente;

public String getNombreAcudiente() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNombreAcudiente'");
}

public void setCosto(double costoFinal) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setCosto'");
}

}
