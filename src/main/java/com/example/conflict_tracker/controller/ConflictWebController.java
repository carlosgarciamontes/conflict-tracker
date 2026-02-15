package com.example.conflict_tracker.controller;

import com.example.conflict_tracker.service.ConflictService;
import org.springframework.stereotype.Controller; // Ojo: NO es RestController
import org.springframework.ui.Model; // Interface para pasar datos a la vista
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/conflicts")
public class ConflictWebController {

    private final ConflictService conflictService;

    public ConflictWebController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public String listConflicts(Model model) {
        // 1. Obtenemos los datos del servicio (igual que en la API)
        // 2. Añadimos los datos al modelo con una clave "conflicts"
        model.addAttribute("conflicts", conflictService.getAllConflicts());

        // 3. Retornamos el nombre del archivo HTML (sin .html) que está en /templates
        return "conflict-list";
    }
}