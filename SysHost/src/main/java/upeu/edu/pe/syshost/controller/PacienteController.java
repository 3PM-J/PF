package upeu.edu.pe.syshost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.syshost.model.Paciente;
import upeu.edu.pe.syshost.service.PacienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;
    
    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodos() {
        try {
            List<Paciente> pacientes = pacienteService.obtenerTodos();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", pacientes);
            response.put("message", "Pacientes obtenidos exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener pacientes: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    // Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Paciente> paciente = pacienteService.obtenerPorId(id);
            Map<String, Object> response = new HashMap<>();
            
            if (paciente.isPresent()) {
                response.put("success", true);
                response.put("data", paciente.get());
                response.put("message", "Paciente encontrado");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Paciente no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener paciente: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    // Crear nuevo paciente
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Paciente paciente) {
        try {
            Map<String, Object> response = new HashMap<>();
            
            // Validar DNI único
            if (pacienteService.existeDni(paciente.getDni())) {
                response.put("success", false);
                response.put("message", "Ya existe un paciente con este DNI");
                return ResponseEntity.badRequest().body(response);
            }
            
            Paciente nuevoPaciente = pacienteService.guardar(paciente);
            response.put("success", true);
            response.put("data", nuevoPaciente);
            response.put("message", "Paciente creado exitosamente");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al crear paciente: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteActualizado = pacienteService.actualizar(id, paciente);
            Map<String, Object> response = new HashMap<>();
            
            if (pacienteActualizado != null) {
                response.put("success", true);
                response.put("data", pacienteActualizado);
                response.put("message", "Paciente actualizado exitosamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Paciente no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al actualizar paciente: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    // Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        try {
            boolean eliminado = pacienteService.eliminar(id);
            Map<String, Object> response = new HashMap<>();
            
            if (eliminado) {
                response.put("success", true);
                response.put("message", "Paciente eliminado exitosamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Paciente no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al eliminar paciente: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    // Buscar pacientes por nombre
    @GetMapping("/buscar")
    public ResponseEntity<Map<String, Object>> buscarPorNombre(@RequestParam String nombre) {
        try {
            List<Paciente> pacientes = pacienteService.buscarPorNombre(nombre);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", pacientes);
            response.put("message", "Búsqueda completada");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error en la búsqueda: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}