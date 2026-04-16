package grupo6.clinicaOdontologica.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo6.clinicaOdontologica.model.entity.Odontologo;
import grupo6.clinicaOdontologica.repository.OdontologoRepository;

@Service
public class OdontologoService {

@Autowired
private OdontologoRepository odontologoRepository;

public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }

    public Odontologo findById(Long id) {
        return odontologoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Odontologo save(Odontologo request) {
        return odontologoRepository.save(request);
    }

    @Transactional
    public Odontologo update(Long id, Odontologo request){
        Odontologo odontologo = findById(id);
        odontologo.setNombreCompleto(request.getNombreCompleto());
        odontologo.setEspecialidad(request.getEspecialidad());
        odontologo.setTarjetaProfesional(request.getTarjetaProfesional());
        return odontologoRepository.save(odontologo);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        odontologoRepository.deleteById(id);
    }

    public Odontologo findEntity(Long id) {
        return odontologoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Odontólogo no encontrado con id: " + id));
    }
}