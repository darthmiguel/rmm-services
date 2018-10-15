package com.ninjarmm.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_customer", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "device_service_index", columnList = "device_id, service_id", unique = true)
})
public class AvailableServiceDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id", nullable = false, referencedColumnName = "id")
  private Device device;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_id", nullable = false, referencedColumnName = "id")
  private AvailableService service;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  public AvailableService getService() {
    return service;
  }

  public void setService(AvailableService service) {
    this.service = service;
  }
}
