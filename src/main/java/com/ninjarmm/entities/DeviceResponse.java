package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceResponse {

  public DeviceResponse(Device device){
    this.systemName = device.getSystemName();
    this.deviceType = device.getDeviceType();
  }

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
}
