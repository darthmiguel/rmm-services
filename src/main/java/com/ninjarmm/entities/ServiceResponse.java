package com.ninjarmm.entities;

public class ServiceResponse {

  public ServiceResponse(Service service){
    this.name = service.getServiceType().getName();
    this.cost = service.getCost();
  }

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
}
