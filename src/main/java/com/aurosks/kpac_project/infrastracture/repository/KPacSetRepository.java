package com.aurosks.kpac_project.infrastracture.repository;

import com.aurosks.kpac_project.infrastracture.entity.KPacSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KPacSetRepository  extends CrudRepository<KPacSet, Long> {

}
