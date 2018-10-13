package com.ninjarmm.repositories;

import com.ninjarmm.entities.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, Long>{

  @Query(value = "select d from Device d where d.customer.login = ?1")
  List<Device> findByLogin(String login);

  Device findById(Long id);



}
