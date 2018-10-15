package com.ninjarmm.repositories;

import com.ninjarmm.entities.DeviceType;
import org.springframework.data.repository.CrudRepository;

public interface DeviceTypeRepository extends CrudRepository<DeviceType, Integer>{

  DeviceType findById(Integer id);
}
