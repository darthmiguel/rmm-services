package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "device_type", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "name_index", columnList = "name", unique = true)
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeviceType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  @JsonIgnore
  private long id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
