package upeu.edu.pe.syshost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import upeu.edu.pe.syshost.service.PacienteService;

@Controller
public class WebController {
    
    @Autowired
    private PacienteService pacienteService;
    
    // Página principal
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    // Página de registro de pacientes
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
    
    // Página de gestión de pacientes
    @GetMapping("/pacientes")
    public String pacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.obtenerTodos());
        return "pacientes";
    }
}