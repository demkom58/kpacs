package com.aurosks.kpac_project.presentation.controller;


import com.aurosks.kpac_project.application.KPacSetService;
import com.aurosks.kpac_project.domain.model.KPacRichSetDto;
import com.aurosks.kpac_project.infrastracture.entity.KPacRichSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KPacSetController {
    private final KPacSetService setService;

    @Autowired
    public KPacSetController(KPacSetService setService) {
        this.setService = setService;
    }

    @GetMapping("/set/{id}")
    public String getPage(@PathVariable(name = "id") long id, Model model){
        model.addAttribute("setId", id);
        return "set";
    }

    @DeleteMapping("/api/set/{id}")
    public @ResponseBody ResponseEntity<Void> deleteKPacSet(@PathVariable(name = "id") long id) {
        setService.deleteKPacSet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/set/{id}")
    public @ResponseBody ResponseEntity<KPacRichSetDto> getKPacSet(@PathVariable(name = "id") long id) {
        return setService.getKPacRichSet(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}