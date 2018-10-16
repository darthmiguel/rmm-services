package com.ninjarmm.entities.response;

import com.ninjarmm.entities.Service;

public class ServiceResponse {

  public ServiceResponse(Service service){
    this.id = service.getId();
    this.name = service.getServiceType().getName();
    this.cost = service.getCost();
  }

  private Long id;

  private String name;

  private double cost;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
