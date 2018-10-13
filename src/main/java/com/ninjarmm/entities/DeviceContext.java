package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceContext {

  private String login;

  @JsonProperty("device_type")
  private Long type;

  @JsonProperty("system_name")
  private String systemName;

  private Long id;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}

