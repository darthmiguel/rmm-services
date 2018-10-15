package com.ninjarmm.repositories;

import com.ninjarmm.entities.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {

  @Query(value = "select service from Service service where service.serviceType.id = ?1 and service.deviceType.id = ?2")
  Service findByServiceTypeAndDeviceType(Integer serviceType, Integer deviceType);

  Service findById(Long id);
}
