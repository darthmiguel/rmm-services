package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "service", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "service_type_device_type_index", columnList = "service_type_id, device_type_id", unique = true)
})
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  @JsonIgnore
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "service_type_id", nullable = false, referencedColumnName = "id")
  private ServiceType serviceType;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "device_type_id", nullable = false, referencedColumnName = "id")
  private DeviceType deviceType;

  @Column(name = "cost")
  private double cost;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(DeviceType type) {
    this.deviceType = type;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}
