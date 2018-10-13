package com.ninjarmm.repositories;

import com.ninjarmm.entities.AvailableServiceDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvailableServiceDeviceRepository extends CrudRepository<AvailableServiceDevice, Long>{
    @Query(value = "select sd from AvailableServiceDevice sd where sd.device.customer.login = ?1")
  List<AvailableServiceDevice> getAllByLogin(String login);
}
