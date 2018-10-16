package com.ninjarmm.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddDeviceBody {

  @JsonProperty("device_type")
  private Integer type;

  @JsonProperty("system_name")
  private String systemName;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
}

