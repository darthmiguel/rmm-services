package com.ninjarmm.services;

import com.ninjarmm.entities.*;
import com.ninjarmm.exceptions.ServiceException;

import java.util.List;

public interface RegisterServiceToDeviceService {

  List<RegisteredServicesResponse> getServices(String login) throws ServiceException;

  ServiceResponse addService(ServiceDeviceBody service) throws ServiceException;

  boolean delete(Long id) throws ServiceException;

  BillResponse getBill(String login) throws ServiceException;

}
