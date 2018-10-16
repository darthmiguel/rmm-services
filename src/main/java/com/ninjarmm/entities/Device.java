package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "system_name_index", columnList = "system_name, customer_id", unique = true)
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  @JsonIgnore
  private Long id;

  @Column(name = "system_name", nullable = false)
  @JsonProperty("system_name")
  private String systemName;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "device_type_id", nullable = false, referencedColumnName = "id", updatable = false)
  @JsonProperty("device_type")
  private DeviceType deviceType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id", updatable = false)
  @JsonIgnore
  private Customer customer;

  @ManyToMany(cascade =
    {
      CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.PERSIST
    }, fetch=FetchType.EAGER, targetEntity = Service.class)
  @JoinTable(
    name = "service_device", joinColumns = {@JoinColumn(name = "device_id")},
    inverseJoinColumns = {@JoinColumn(name = "service_id")}
  )
  private List<Service> serviceList;

  public Long getId() {
    return id;
  }

  public String getSystemName() {
    return systemName;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public void setDeviceType(DeviceType type) {
    this.deviceType = type;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Service> getServiceList() {
    return serviceList;
  }

  public void setServiceList(List<Service> serviceList) {
    this.serviceList = serviceList;
  }
}
