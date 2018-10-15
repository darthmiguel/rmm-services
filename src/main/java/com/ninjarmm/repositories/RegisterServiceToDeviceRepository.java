package com.ninjarmm.repositories;

import com.ninjarmm.entities.ServiceDevice;
import org.springframework.data.repository.CrudRepository;

public interface RegisterServiceToDeviceRepository extends CrudRepository<ServiceDevice, Long>{

  ServiceDevice findById(Long id);
}
