package com.aurosks.kpac_project.domain.mapper;

import com.aurosks.kpac_project.domain.model.KPacRichSetDto;
import com.aurosks.kpac_project.domain.model.KPacSetCreateDto;
import com.aurosks.kpac_project.infrastracture.entity.KPac;
import com.aurosks.kpac_project.infrastracture.entity.KPacSet;
import org.springframework.stereotype.Component;

@Component
public class KPacSetMapper {

    public KPacSet toEntity(KPacSetCreateDto createDto) {
        KPacSet set = new KPacSet();
        set.setTitle(createDto.getTitle());
        return set;
    }

    public KPacRichSetDto toDto(KPacSet set, Iterable<KPac> pacs) {
        KPacRichSetDto dto = new KPacRichSetDto();
        dto.setId(set.getId());
        dto.setTitle(set.getTitle());
        dto.setPacs(pacs);
        return dto;
    }
}
