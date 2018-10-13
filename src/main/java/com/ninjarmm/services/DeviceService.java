package com.ninjarmm.services;

import com.ninjarmm.entities.Device;
import com.ninjarmm.entities.DeviceContext;
import com.ninjarmm.exceptions.DeviceException;

import java.util.List;

public interface DeviceService {

  List<Device> getAll(String login) throws DeviceException;

  Device save(DeviceContext deviceContext) throws DeviceException;

  Device update(DeviceContext deviceContext) throws DeviceException;

  boolean delete(Long id);

}
