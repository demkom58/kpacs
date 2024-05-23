package com.aurosks.kpac_project.application;


import com.aurosks.kpac_project.application.exception.MessageException;
import com.aurosks.kpac_project.domain.mapper.KPacSetMapper;
import com.aurosks.kpac_project.domain.model.KPacRichSetDto;
import com.aurosks.kpac_project.domain.model.KPacSetCreateDto;
import com.aurosks.kpac_project.infrastracture.entity.KPac;
import com.aurosks.kpac_project.infrastracture.entity.KPacRichSet;
import com.aurosks.kpac_project.infrastracture.entity.KPacSet;
import com.aurosks.kpac_project.infrastracture.entity.SetKPacRef;
import com.aurosks.kpac_project.infrastracture.repository.KPacRepository;
import com.aurosks.kpac_project.infrastracture.repository.KPacRichSetRepository;
import com.aurosks.kpac_project.infrastracture.repository.KPacSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KPacSetService {
    private final KPacSetMapper setMapper;
    private final KPacRepository pacsRepository;
    private final KPacSetRepository setRepository;
    private final KPacRichSetRepository richSetRepository;

    @Autowired
    public KPacSetService(KPacSetMapper setMapper,
                          KPacRepository pacsRepository,
                          KPacSetRepository setRepository,
                          KPacRichSetRepository richSetRepository) {
        this.setMapper = setMapper;
        this.pacsRepository = pacsRepository;
        this.setRepository = setRepository;
        this.richSetRepository = richSetRepository;
    }

    public Iterable<KPacSet> getAllKPacSets() {
        return setRepository.findAll();
    }

    public void saveKPacSet(KPacSetCreateDto createDto) {
        try {
            KPacSet entity = setMapper.toEntity(createDto);
            setRepository.save(entity);
        } catch (
                DbActionExecutionException e) {
            if (e.getCause() instanceof DuplicateKeyException) {
                throw new MessageException("msg.kpacSet.title.duplicate", e);
            }
        }
    }

    public void deleteKPacSet(long id) {
        setRepository.deleteById(id);
    }

    public Optional<KPacRichSetDto> getKPacRichSet(long id) {
        Optional<KPacRichSet> result = richSetRepository.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        KPacRichSet richSet = result.get();
        List<Long> referenced = richSet.getKPacs().stream().map(SetKPacRef::getKpacId).toList();
        Iterable<KPac> pacs = pacsRepository.findAllById(referenced);

        return Optional.of(setMapper.toDto(richSet, pacs));
    }
}