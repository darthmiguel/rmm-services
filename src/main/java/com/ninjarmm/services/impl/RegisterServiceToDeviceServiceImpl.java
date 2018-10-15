package com.ninjarmm.services.impl;

import com.ninjarmm.entities.Service;
import com.ninjarmm.entities.ServiceDevice;
import com.ninjarmm.entities.ServiceDeviceContext;
import com.ninjarmm.entities.Device;
import com.ninjarmm.exceptions.ServiceException;
import com.ninjarmm.repositories.RegisterServiceToDeviceRepository;
import com.ninjarmm.repositories.ServiceRepository;
import com.ninjarmm.repositories.DeviceRepository;
import com.ninjarmm.services.AvailableServiceDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@org.springframework.stereotype.Service
public class AvailableServiceServiceImpl implements AvailableServiceDeviceService {


  @Autowired
  RegisterServiceToDeviceRepository availableServiceDeviceRepository;

  @Autowired
  ServiceRepository availableServiceRepository;

  @Autowired
  DeviceRepository deviceRepository;

  @Override
  public List<ServiceDevice> getAllByLogin(String login) throws ServiceException {
    List<ServiceDevice> availableServices = availableServiceDeviceRepository.getAllByLogin(login);
    if(availableServices == null || availableServices.size() == 0){
      throw new ServiceException("There are no devices for user " + login);
    }
    return availableServices;
  }

  @Override
  public ServiceDevice addService(ServiceDeviceContext service) throws ServiceException {
    Service availableService = availableServiceRepository.findById(service.getService());
    if(availableService == null){
      throw new ServiceException("The available service with id " + service.getService() + " does not exist");
    }
    Device device = deviceRepository.findById(service.getDevice());
    if(device == null){
      throw new ServiceException("The device with id " + service.getDevice() + " does not exist");
    }
    ServiceDevice availableServiceDevice = new ServiceDevice();
    availableServiceDevice.setDevice(device);
    availableServiceDevice.setService(availableService);
    try{
      return availableServiceDeviceRepository.save(availableServiceDevice);
    }catch (DataIntegrityViolationException d){
      throw new ServiceException("The service " + availableService.getName() + " has been already added to device " + device.getSystemName());
    }
  }
}
