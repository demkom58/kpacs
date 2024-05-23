package com.aurosks.kpac_project.presentation.controller;


import com.aurosks.kpac_project.application.KPacSetService;
import com.aurosks.kpac_project.domain.model.KPacSetCreateDto;
import com.aurosks.kpac_project.infrastracture.entity.KPacSet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KPacSetsController {
    private final KPacSetService setService;

    @Autowired
    public KPacSetsController(KPacSetService setService) {
        this.setService = setService;
    }

    @GetMapping("/sets")
    public String getPage() {
        return "sets";
    }

    @GetMapping("/api/sets")
    public @ResponseBody ResponseEntity<Iterable<KPacSet>> getData() {
        Iterable<KPacSet> loaded = setService.getAllKPacSets();
        return ResponseEntity.ok(loaded);
    }

    @PostMapping("/api/sets")
    public @ResponseBody ResponseEntity<Void> addKPacSet(@Valid KPacSetCreateDto createDto) {
        setService.saveKPacSet(createDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/sets/{id}")
    public @ResponseBody ResponseEntity<Void> deleteKPacSet(@PathVariable(name = "id") long id) {
        setService.deleteKPacSet(id);
        return ResponseEntity.noContent().build();
    }
}