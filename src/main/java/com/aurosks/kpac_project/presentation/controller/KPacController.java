package com.aurosks.kpac_project.presentation.controller;

import com.aurosks.kpac_project.domain.model.KPacCreateDto;
import com.aurosks.kpac_project.infrastracture.entity.KPac;
import com.aurosks.kpac_project.application.KPacService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class KPacController {
    private final KPacService kpacService;

    @GetMapping("/kpacs")
    public String get() {
        return "kpacs";
    }

    @Autowired
    public KPacController(KPacService kpacService) {
        this.kpacService = kpacService;
    }

    @GetMapping(value = "/api/kpacs")
    public @ResponseBody ResponseEntity<Iterable<KPac>> getData() {
        Iterable<KPac> loaded = kpacService.getAllKPacs();
        return ResponseEntity.ok(loaded);
    }

    @PostMapping(path = "/api/kpacs")
    public @ResponseBody ResponseEntity<Void> create(@Valid @RequestBody KPacCreateDto dto) {
        kpacService.saveKPac(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/api/kpacs/{id}")
    public @ResponseBody ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        kpacService.deleteKPac(id);
        return ResponseEntity.noContent().build();
    }
}
