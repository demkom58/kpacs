package com.aurosks.kpac_project.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class KPacCreateDto {
    @Size(min = 1, max = 250, message = "${msg.kpac.title.size}")
    @NotNull
    private String title;
    @Size(max = 2000, message = "${msg.kpac.description.size}")
    @NotNull
    private String description;

    public KPacCreateDto() {
    }

    public KPacCreateDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
