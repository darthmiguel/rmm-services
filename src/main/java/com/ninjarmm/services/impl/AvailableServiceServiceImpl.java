package com.ninjarmm.services.impl;

import com.ninjarmm.entities.AvailableService;
import com.ninjarmm.entities.AvailableServiceDevice;
import com.ninjarmm.entities.AvailableServiceDeviceContext;
import com.ninjarmm.entities.Device;
import com.ninjarmm.exceptions.ServiceException;
import com.ninjarmm.repositories.AvailableServiceDeviceRepository;
import com.ninjarmm.repositories.AvailableServiceRepository;
import com.ninjarmm.repositories.DeviceRepository;
import com.ninjarmm.services.AvailableServiceDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableServiceServiceImpl implements AvailableServiceDeviceService {


  @Autowired
  AvailableServiceDeviceRepository availableServiceDeviceRepository;

  @Autowired
  AvailableServiceRepository availableServiceRepository;

  @Autowired
  DeviceRepository deviceRepository;

  @Override
  public List<AvailableServiceDevice> getAllByLogin(String login) throws ServiceException {
    List<AvailableServiceDevice> availableServices = availableServiceDeviceRepository.getAllByLogin(login);
    if(availableServices == null || availableServices.size() == 0){
      throw new ServiceException("There are no devices for user " + login);
    }
    return availableServices;
  }

  @Override
  public AvailableServiceDevice addService(AvailableServiceDeviceContext service) throws ServiceException {
    AvailableService availableService = availableServiceRepository.findById(service.getService());
    if(availableService == null){
      throw new ServiceException("The available service with id " + service.getService() + " does not exist");
    }
    Device device = deviceRepository.findById(service.getDevice());
    if(device == null){
      throw new ServiceException("The device with id " + service.getDevice() + " does not exist");
    }
    AvailableServiceDevice availableServiceDevice = new AvailableServiceDevice();
    availableServiceDevice.setDevice(device);
    availableServiceDevice.setService(availableService);
    try{
      return availableServiceDeviceRepository.save(availableServiceDevice);
    }catch (DataIntegrityViolationException d){
      throw new ServiceException("The service " + availableService.getName() + " has been already added to device " + device.getSystemName());
    }
  }
}
