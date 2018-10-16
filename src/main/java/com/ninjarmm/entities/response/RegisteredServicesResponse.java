package com.ninjarmm.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ninjarmm.entities.Device;

import java.util.List;
import java.util.stream.Collectors;

public class RegisteredServicesResponse {

  public RegisteredServicesResponse(Device device){
    this.device = new DeviceResponse(device);
    this.serviceList = device.getServiceList().stream().map(
      service -> new ServiceResponse(service)
    ).collect(Collectors.toList());
  }

  DeviceResponse device;

  @JsonProperty("service_list")
  List<ServiceResponse> serviceList;

  public DeviceResponse getDevice() {
    return device;
  }

  public void setDevice(DeviceResponse device) {
    this.device = device;
  }

  public List<ServiceResponse> getServiceList() {
    return serviceList;
  }

  public void setServiceList(List<ServiceResponse> serviceList) {
    this.serviceList = serviceList;
  }
}
