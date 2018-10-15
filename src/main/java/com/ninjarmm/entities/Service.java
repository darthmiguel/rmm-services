package com.ninjarmm.entities;

import javax.persistence.*;

@Entity
@Table(name = "service", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "name_type_index", columnList = "name, type_id", unique = true)
})
public class AvailableService {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
  private DeviceType type;

  @Column(name = "cost")
  private double cost;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DeviceType getType() {
    return type;
  }

  public void setType(DeviceType type) {
    this.type = type;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}
