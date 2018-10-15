package com.ninjarmm.services;

import com.ninjarmm.entities.ServiceDevice;
import com.ninjarmm.entities.ServiceDeviceContext;
import com.ninjarmm.exceptions.ServiceException;

import java.util.List;

public interface AvailableServiceDeviceService {

  List<ServiceDevice> getAllByLogin(String login) throws ServiceException;

  ServiceDevice addService(ServiceDeviceContext service) throws ServiceException;
}
