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

import grupo6.clinicaOdontologica.model.entity.Odontologo;
import grupo6.clinicaOdontologica.service.OdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/odontologos")
@Tag(name = "Odontólogos", description = "Gestión de odontólogos")

public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @Operation(summary = "Obtener todos los odontólogos")
    @GetMapping
    public List<Odontologo> getAll() {
        // En tu service este método se llama findAll()
        return odontologoService.findAll();
    }

    @Operation(summary = "Obtener un odontólogo por ID")
    @GetMapping("/{id}")
    public Odontologo getById(@PathVariable Long id) {
        // Usamos findEntity para manejar el error si no existe
        return odontologoService.findEntity(id);
    }

    @Operation(summary = "Registrar un nuevo odontólogo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Odontologo create(@RequestBody Odontologo request) {
        return odontologoService.save(request);
    }

    @Operation(summary = "Actualizar información de un odontólogo")
    @PutMapping("/{id}")
    public Odontologo update(@PathVariable Long id, @RequestBody Odontologo request) {
        return odontologoService.update(id, request);
    }

    @Operation(summary = "Eliminar un odontólogo")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        odontologoService.delete(id);
    }
}

