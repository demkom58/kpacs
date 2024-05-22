package com.aurosks.kpac_project.infrastracture.repository;

import com.aurosks.kpac_project.infrastracture.entity.KPac;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KPacRepository extends CrudRepository<KPac, Long> {
}
