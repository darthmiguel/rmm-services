package com.ninjarmm.repositories;

import com.ninjarmm.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
  Customer findByLogin(String login);
}
