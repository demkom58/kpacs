package com.aurosks.kpac_project.domain.mapper;

import com.aurosks.kpac_project.domain.model.KPacCreateDto;
import com.aurosks.kpac_project.infrastracture.entity.KPac;
import org.springframework.stereotype.Component;

@Component
public class KPacMapper {
    public KPac toEntity(KPacCreateDto kpacCreateDto) {
        KPac kpac = new KPac();
        kpac.setTitle(kpacCreateDto.getTitle());
        kpac.setDescription(kpacCreateDto.getDescription());
        return kpac;
    }
}
