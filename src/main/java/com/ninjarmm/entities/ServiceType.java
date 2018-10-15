package com.ninjarmm.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_type")
public class ServiceType {

  @Id
  private Integer id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
