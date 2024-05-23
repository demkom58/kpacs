package com.aurosks.kpac_project.infrastracture.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Table("kpac_sets")
public class KPacSet {
    @Id
    private Long id;
    private String title;

    public KPacSet() {
    }

    public KPacSet(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public @Nullable Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KPacSet kPacSet = (KPacSet) o;
        return Objects.equals(id, kPacSet.id)
                && Objects.equals(title, kPacSet.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "KPacSet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
