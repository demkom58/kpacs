package com.aurosks.kpac_project.domain.model;

import com.aurosks.kpac_project.infrastracture.entity.KPac;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.Set;

public class KPacRichSetDto {
    private Long id;
    private String title;
    private Iterable<KPac> pacs;

    public KPacRichSetDto() {
    }

    public KPacRichSetDto(Long id, String title, Iterable<KPac> pacs) {
        this.id = id;
        this.title = title;
        this.pacs = pacs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Iterable<KPac> getPacs() {
        return pacs;
    }

    public void setPacs(Iterable<KPac> pacs) {
        this.pacs = pacs;
    }
}
