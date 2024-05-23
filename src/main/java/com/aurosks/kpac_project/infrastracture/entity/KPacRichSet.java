package com.aurosks.kpac_project.infrastracture.entity;

import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("kpac_sets")
public class KPacRichSet extends KPacSet {
    @MappedCollection(idColumn = "kpac_set_id", keyColumn = "kpac_id")
    public Set<SetKPacRef> pacs;

    public Set<SetKPacRef> getKPacs() {
        return pacs;
    }

    public void setKPacs(Set<SetKPacRef> pacs) {
        this.pacs = pacs;
    }
}
