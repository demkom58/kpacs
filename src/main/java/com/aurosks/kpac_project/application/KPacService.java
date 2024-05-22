package com.aurosks.kpac_project.application;

import com.aurosks.kpac_project.application.exception.MessageException;
import com.aurosks.kpac_project.domain.mapper.KPacMapper;
import com.aurosks.kpac_project.domain.model.KPacCreateDto;
import com.aurosks.kpac_project.infrastracture.entity.KPac;
import com.aurosks.kpac_project.infrastracture.repository.KPacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Service
public class KPacService {
    private final KPacMapper kpacMapper;
    private final KPacRepository kpacRepository;

    @Autowired
    public KPacService(KPacMapper kpacMapper, KPacRepository kpacRepository) {
        this.kpacMapper = kpacMapper;
        this.kpacRepository = kpacRepository;
    }

    public Iterable<KPac> getAllKPacs() {
        return kpacRepository.findAll();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void saveKPac(KPacCreateDto kpac) {
        try {
            KPac mapped = kpacMapper.toEntity(kpac);
            mapped.setCreationDate(new Date());
            kpacRepository.save(mapped);
        } catch (DbActionExecutionException e) {
            if (e.getCause() instanceof DuplicateKeyException) {
                throw new MessageException("msg.kpac.title.duplicate", e);
            }
        }
    }

    public void deleteKPac(long id) {
        kpacRepository.deleteById(id);
    }
}