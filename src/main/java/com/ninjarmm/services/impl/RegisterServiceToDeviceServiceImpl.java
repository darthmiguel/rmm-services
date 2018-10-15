package com.ninjarmm.services.impl;

import com.ninjarmm.Constants;
import com.ninjarmm.entities.*;
import com.ninjarmm.exceptions.ServiceException;
import com.ninjarmm.repositories.DeviceRepository;
import com.ninjarmm.repositories.RegisterServiceToDeviceRepository;
import com.ninjarmm.repositories.ServiceRepository;
import com.ninjarmm.services.RegisterServiceToDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class RegisterServiceToDeviceServiceImpl implements RegisterServiceToDeviceService {

  @Autowired
  DeviceRepository deviceRepository;

  @Autowired
  ServiceRepository serviceRepository;

  @Autowired
  RegisterServiceToDeviceRepository registerServiceToDeviceRepository;

  @Override
  public List<RegisteredServicesResponse> getServices(String login) throws ServiceException {
    List<Device> deviceList = deviceRepository.findByLogin(login);
    List<RegisteredServicesResponse> registeredServices = deviceList.stream().map(
      device -> new RegisteredServicesResponse(device)).collect(Collectors.toList());

    if(registeredServices == null || registeredServices.size() == 0){
      throw new ServiceException("There are no services registered for user " + login);
    }
    return registeredServices;
  }

  @Override
  public ServiceResponse addService(ServiceDeviceContext serviceDeviceContext) throws ServiceException {
    if(serviceDeviceContext.getDevice() == null || serviceDeviceContext.getService() == null){
      throw new ServiceException("Please provide all the fields: 'device' and 'service'");
    }
    Service service = serviceRepository.findById(serviceDeviceContext.getService());
    if(service == null){
      throw new ServiceException("The service with id " + serviceDeviceContext.getService() + " does not exist");
    }
    Device device = deviceRepository.findById(serviceDeviceContext.getDevice());
    if(device == null){
      throw new ServiceException("The device with id " + serviceDeviceContext.getDevice() + " does not exist");
    }
    if( !service.getDeviceType().equals(device.getDeviceType())){
      throw new ServiceException("The service '" + service.getServiceType().getName() + "' does not match the device type '" +
      device.getDeviceType().getName() + "'");
    }
    ServiceDevice registeredServiceToDevice = new ServiceDevice();
    registeredServiceToDevice.setDevice(device);
    registeredServiceToDevice.setService(service);
    ServiceDevice newRegisteredService;
    try{
      newRegisteredService = registerServiceToDeviceRepository.save(registeredServiceToDevice);
    }catch (DataIntegrityViolationException d){
      throw new ServiceException("The service has already been added to the device");
    }
    return new ServiceResponse(newRegisteredService.getService());
  }

  @Override
  public boolean delete(Long id) throws ServiceException{
    ServiceDevice registeredService = registerServiceToDeviceRepository.findById(id);
    if(registeredService != null && registeredService.getService().getServiceType().getId().equals(Constants.SERVICE_TYPE_DEVICE)){
      throw new ServiceException("It is not possible to remove the monthly cost of the device");
    }
    try{
      registerServiceToDeviceRepository.delete(id);
    }
    catch (EmptyResultDataAccessException e){
      throw new ServiceException("The registered service with id " + id + " does not exist");
    }
    return true;
  }
}
