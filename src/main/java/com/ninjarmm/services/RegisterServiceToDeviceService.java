package com.ninjarmm.services;

import com.ninjarmm.entities.RegisteredServicesResponse;
import com.ninjarmm.entities.ServiceDevice;
import com.ninjarmm.entities.ServiceDeviceContext;
import com.ninjarmm.entities.ServiceResponse;
import com.ninjarmm.exceptions.ServiceException;

import java.util.List;

public interface RegisterServiceToDeviceService {

  List<RegisteredServicesResponse> getServices(String login) throws ServiceException;

  ServiceResponse addService(ServiceDeviceContext service) throws ServiceException;

  boolean delete(Long id) throws ServiceException;
}
