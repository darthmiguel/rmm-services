package com.ninjarmm.repositories;

import com.ninjarmm.entities.AvailableService;
import org.springframework.data.repository.CrudRepository;

public interface AvailableServiceRepository extends CrudRepository<AvailableService, Long> {

  AvailableService findById(Long id);
}
