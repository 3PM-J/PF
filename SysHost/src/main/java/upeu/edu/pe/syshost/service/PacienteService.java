package upeu.edu.pe.syshost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.syshost.model.Paciente;
import upeu.edu.pe.syshost.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    // Obtener todos los pacientes
    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }
    
    // Obtener paciente por ID
    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }
    
    // Guardar paciente
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    
    // Actualizar paciente
    public Paciente actualizar(Long id, Paciente pacienteActualizado) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(id);
        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setDni(pacienteActualizado.getDni());
            paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
            paciente.setTelefono(pacienteActualizado.getTelefono());
            paciente.setDireccion(pacienteActualizado.getDireccion());
            paciente.setSexo(pacienteActualizado.getSexo());
            paciente.setCorreo(pacienteActualizado.getCorreo());
            paciente.setCaso(pacienteActualizado.getCaso());
            paciente.setEstatura(pacienteActualizado.getEstatura());
            paciente.setPeso(pacienteActualizado.getPeso());
            paciente.setTipoSangre(pacienteActualizado.getTipoSangre());
            paciente.setEnfermedadGenetica(pacienteActualizado.getEnfermedadGenetica());
            return pacienteRepository.save(paciente);
        }
        return null;
    }
    
    // Eliminar paciente (soft delete)
    public boolean eliminar(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            Paciente p = paciente.get();
            p.setActivo(false);
            pacienteRepository.save(p);
            return true;
        }
        return false;
    }
    
    // Buscar por nombre
    public List<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    // Buscar por DNI
    public Optional<Paciente> buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }
    
    // Verificar si existe DNI
    public boolean existeDni(String dni) {
        return pacienteRepository.existsByDni(dni);
    }
    
    // Obtener pacientes activos
    public List<Paciente> obtenerActivos() {
        return pacienteRepository.findByActivoTrue();
    }
}