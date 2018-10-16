package com.ninjarmm.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateDeviceBody {

  private Long id;

  @JsonProperty("system_name")
  private String systemName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
}
