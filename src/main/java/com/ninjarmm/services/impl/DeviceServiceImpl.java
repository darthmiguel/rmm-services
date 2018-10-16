package com.ninjarmm.services.impl;

import com.ninjarmm.Constants;
import com.ninjarmm.entities.*;
import com.ninjarmm.entities.request.AddDeviceBody;
import com.ninjarmm.entities.request.UpdateDeviceBody;
import com.ninjarmm.entities.response.DeviceResponse;
import com.ninjarmm.exceptions.DeviceException;
import com.ninjarmm.repositories.*;
import com.ninjarmm.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class DeviceServiceImpl implements DeviceService {

  @Autowired
  private DeviceRepository deviceRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private DeviceTypeRepository deviceTypeRepository;

  @Autowired
  private ServiceRepository serviceRepository;

  @Autowired
  private RegisterServiceToDeviceRepository registerServiceToDeviceRepository;

  @Override
  @Transactional(readOnly = true)
  public List<DeviceResponse> getAll(String login) throws DeviceException {
    List<Device> devices = deviceRepository.findByLogin(login);
    if(devices == null || devices.size() == 0){
      throw new DeviceException("There are no devices for user " + login);
    }
    return devices.stream().map(device -> new DeviceResponse(device)).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public DeviceResponse save(AddDeviceBody deviceContext, String login) throws DeviceException{

    Customer customer = customerRepository.findByLogin(login);
    if(customer == null){
      throw new DeviceException("Customer with login " + login + " was not found");
    }
    DeviceType deviceType = deviceTypeRepository.findById(deviceContext.getType());
    if(deviceType == null){
      throw new DeviceException("Device type with id  " + deviceContext.getType() + " was not found");
    }
    if(deviceContext.getSystemName() == null) {
      throw new DeviceException("Field 'system_name' is required");
    }
    Device device = new Device();
    device.setSystemName(deviceContext.getSystemName());
    device.setCustomer(customer);
    device.setDeviceType(deviceType);
    //retrieves the service that monitors the device
    Service deviceService = serviceRepository.findByServiceTypeAndDeviceType(Constants.SERVICE_TYPE_DEVICE, deviceContext.getType());
    if(deviceService == null){
      throw new DeviceException("There price for device type " + device.getDeviceType().getName() + " cannot be determined, therefore the device was not added");
    }
    ServiceDevice monitoringService = new ServiceDevice();
    monitoringService.setDevice(device);
    monitoringService.setService(deviceService);
    Device savedDevice = deviceRepository.save(device);
    //registers the monthly service cost to the device
    registerServiceToDeviceRepository.save(monitoringService);
    return new DeviceResponse(savedDevice);
  }

  @Override
  public DeviceResponse update(UpdateDeviceBody updateDeviceBody) throws DeviceException {
    if(updateDeviceBody.getSystemName() == null || updateDeviceBody.getId() == null){
      throw new DeviceException("Fields 'id' and 'system_name' are required");
    }
    Device deviceUpdate = deviceRepository.findById(updateDeviceBody.getId());
    if(deviceUpdate == null){
        throw new DeviceException("Device with id " + updateDeviceBody.getId() + " was not found");
    }
    deviceUpdate.setSystemName(updateDeviceBody.getSystemName());
    deviceRepository.save(deviceUpdate);
    return new DeviceResponse(deviceUpdate);
  }

  @Override
  public void delete(Long id) throws DeviceException{
    try{
      deviceRepository.delete(id);
    }catch (EmptyResultDataAccessException e){
      throw new DeviceException("The device with id " + id + " does not exist");
    }
  }
}
