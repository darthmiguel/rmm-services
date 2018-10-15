package com.ninjarmm.repositories;

import com.ninjarmm.entities.ServiceDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegisterServiceToDeviceRepository extends CrudRepository<ServiceDevice, Long>{

  ServiceDevice findById(Long id);

  @Query(value = "select st.name, sum(s.cost) "+
    "from service_type st, service s, service_device sd, device d, customer c " +
    "where " +
      "sd.service_id = s.id " +
      "and s.service_type_id = st.id " +
      "and sd.device_id = d.id " +
      "and d.customer_id = c.id " +
      "and c.login = :login " +
    "group by st.name"
  , nativeQuery = true)
  List<Object> getBill(@Param("login") String login);
}
