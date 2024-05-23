package com.aurosks.kpac_project.infrastracture.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("kpac_set_kpacs")
public class SetKPacRef {
    @Id
    @Column("kpac_set_id")
    private Long kpacSetId;
    @Column("kpac_id")
    private Long kpacId;

    public SetKPacRef() {
    }

    public Long getKpacSetId() {
        return kpacSetId;
    }

    public void setKpacSetId(Long kpacSetId) {
        this.kpacSetId = kpacSetId;
    }

    public Long getKpacId() {
        return kpacId;
    }

    public void setKpacId(Long kpacId) {
        this.kpacId = kpacId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetKPacRef that = (SetKPacRef) o;
        return Objects.equals(kpacSetId, that.kpacSetId) && Objects.equals(kpacId, that.kpacId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kpacSetId, kpacId);
    }

    @Override
    public String toString() {
        return "SetKPacRef{" +
                "kpacSetId=" + kpacSetId +
                ", kpacId=" + kpacId +
                '}';
    }
}
