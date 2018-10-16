package com.ninjarmm.services;

import com.ninjarmm.entities.request.AddDeviceBody;
import com.ninjarmm.entities.response.DeviceResponse;
import com.ninjarmm.entities.request.UpdateDeviceBody;
import com.ninjarmm.exceptions.DeviceException;

import java.util.List;

public interface DeviceService {

  List<DeviceResponse> getAll(String login) throws DeviceException;

  DeviceResponse save(AddDeviceBody addDeviceBody, String login) throws DeviceException;

  DeviceResponse update(UpdateDeviceBody updateDeviceBody) throws DeviceException;

  void delete(Long id) throws DeviceException;

}
