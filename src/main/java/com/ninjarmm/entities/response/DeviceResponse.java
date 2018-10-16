package com.ninjarmm.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ninjarmm.entities.Device;
import com.ninjarmm.entities.DeviceType;

public class DeviceResponse {

  public DeviceResponse(Device device){
    this.id = device.getId();
    this.systemName = device.getSystemName();
    this.deviceType = device.getDeviceType();
  }

  private Long id;

  @JsonProperty("system_name")
  private String systemName;

  @JsonProperty("device_type")
  private DeviceType deviceType;

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
