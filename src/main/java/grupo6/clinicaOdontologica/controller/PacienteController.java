package grupo6.clinicaOdontologica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import grupo6.clinicaOdontologica.model.entity.Paciente;
import grupo6.clinicaOdontologica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes")

public class PacienteController {

@Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Obtener lista de  los pacientes")
    @GetMapping
    public List<Paciente> getAll() {
        // Usamos el nombre exacto de tu Service
        return pacienteService.getAllPacientes();
    }

    @Operation(summary = "Obtener un paciente por ID")
    @GetMapping("/{id}")
    public Paciente getById(@PathVariable Long id) {
        // Usamos findEntity para que lance la excepción si no existe
        return pacienteService.findEntity(id);
    }

    @Operation(summary = "Crear un nuevo paciente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente create(@RequestBody Paciente request) {
        return pacienteService.save(request);
    }

    @Operation(summary = "Actualizar datos de un paciente")
    @PutMapping("/{id}")
    public Paciente update(@PathVariable Long id, @RequestBody Paciente request) {
        return pacienteService.update(id, request);
    }

    @Operation(summary = "Eliminar un paciente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pacienteService.delete(id);
    }
}




