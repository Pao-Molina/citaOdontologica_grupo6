package grupo6.clinicaOdontologica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import grupo6.clinicaOdontologica.model.entity.Cita;
import grupo6.clinicaOdontologica.model.enums.EstadoCita;
import grupo6.clinicaOdontologica.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/citas")
@Tag(name = "Citas", description = "Gestión de citas odontológicas")

public class CitaController {

@Autowired

    private CitaService citaService;

    @Operation(summary = "Obtener todas las Citas registradas")
    @GetMapping
    public List<Cita> getAll() {
        return citaService.findAll();
    }

    @Operation(summary = "Obtener una cita por ID")
    @GetMapping("/{id}")
    public Cita getById(@PathVariable Long id) {
        return citaService.findById(id);
    }

    @Operation(summary = "Filtrar citas por estado")
    @GetMapping("/estado/{estado}")
    public List<Cita> getByEstado(@PathVariable EstadoCita estado) {
        return citaService.findByEstado(estado);
    }

    @Operation(summary = "Obtener citas por paciente")
    @GetMapping("/paciente/{pacienteId}")
    public List<Cita> getByPaciente(@PathVariable Long pacienteId) {
        return citaService.findByPaciente(pacienteId);
    }

    @Operation(summary = "Crear una cita", description = "Solo requiere paciente.id y odontologo.id en el body")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cita create(@RequestBody Cita request) {
        return citaService.agendarCita(request);
    }

    @Operation(summary = "Cambiar estado de una cita")
    @PatchMapping("/{id}/estado/{estado}")
    public Cita cambiarEstado(@PathVariable Long id, @PathVariable EstadoCita estado) {
        return citaService.cambiarEstado(id, estado);
    }

    @Operation(summary = "Eliminar una cita")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        citaService.delete(id);
    }
}
