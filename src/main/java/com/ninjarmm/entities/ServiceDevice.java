package com.ninjarmm.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_device", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "service_device_index", columnList = "device_id, service_id", unique = true)
})
public class ServiceDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id", nullable = false, referencedColumnName = "id")
  private Device device;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "service_id", nullable = false, referencedColumnName = "id")
  private Service service;

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

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }
}
