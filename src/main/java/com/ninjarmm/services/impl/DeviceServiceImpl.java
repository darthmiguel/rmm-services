package com.ninjarmm.services.impl;

import com.ninjarmm.entities.Customer;
import com.ninjarmm.entities.Device;
import com.ninjarmm.entities.DeviceContext;
import com.ninjarmm.entities.DeviceType;
import com.ninjarmm.exceptions.DeviceException;
import com.ninjarmm.repositories.CustomerRepository;
import com.ninjarmm.repositories.DeviceRepository;
import com.ninjarmm.repositories.DeviceTypeRepository;
import com.ninjarmm.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

  @Autowired
  private DeviceRepository deviceRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private DeviceTypeRepository deviceTypeRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Device> getAll(String login) throws DeviceException {
    List<Device> devices = deviceRepository.findByLogin(login);
    if(devices == null || devices.size() == 0){
      throw new DeviceException("There are no devices for user " + login);
    }
    return devices;
  }

  @Override
  public Device save(DeviceContext deviceContext) throws DeviceException{
    if(deviceContext.getSystemName() == null || deviceContext.getLogin() == null || deviceContext.getType() == null){
      throw new DeviceException("Please provide all the fields: 'system_name', 'login' and 'device_type'");
    }
    Customer customer = customerRepository.findByLogin(deviceContext.getLogin());
    if(customer == null){
      throw new DeviceException("Customer with login " + deviceContext.getLogin() + " was not found");
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
    device.setType(deviceType);
    try{
      return deviceRepository.save(device);
    }catch (DataIntegrityViolationException d){
      throw new DeviceException("Device '" + deviceContext.getSystemName() + "' has been already added for user " + deviceContext.getLogin());
    }
  }

  @Override
  public Device update(DeviceContext deviceContext) throws DeviceException {
    if(deviceContext.getSystemName() == null || deviceContext.getId() == null){
      throw new DeviceException("Fields 'id' and 'system_name' are required");
    }
    Device deviceUpdate = deviceRepository.findById(deviceContext.getId());
    if(deviceUpdate == null){
        throw new DeviceException("Device with id " + deviceContext.getId() + " was not found");
    }
    deviceUpdate.setSystemName(deviceContext.getSystemName());
    return deviceRepository.save(deviceUpdate);
  }

  @Override
  public boolean delete(Long id) {
    deviceRepository.delete(id);
    return true;
  }
}
