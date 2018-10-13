package com.ninjarmm.services;

import com.ninjarmm.entities.AvailableServiceDevice;
import com.ninjarmm.entities.AvailableServiceDeviceContext;
import com.ninjarmm.exceptions.ServiceException;

import java.util.List;

public interface AvailableServiceDeviceService {

  List<AvailableServiceDevice> getAllByLogin(String login) throws ServiceException;

  AvailableServiceDevice addService(AvailableServiceDeviceContext service) throws ServiceException;
}
