package com.aurosks.kpac_project.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class KPacSetCreateDto {
    @Size(min = 1, max = 250, message = "${kpacSet.title.size}")
    @NotNull
    private String title;

    public KPacSetCreateDto() {
    }

    public KPacSetCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
