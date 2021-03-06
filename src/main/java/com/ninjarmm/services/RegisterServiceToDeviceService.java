package com.ninjarmm.services;

import com.ninjarmm.entities.request.ServiceDeviceBody;
import com.ninjarmm.entities.response.BillResponse;
import com.ninjarmm.entities.response.RegisteredServicesResponse;
import com.ninjarmm.entities.response.ServiceResponse;
import com.ninjarmm.exceptions.ServiceException;

import java.util.List;

public interface RegisterServiceToDeviceService {

  List<RegisteredServicesResponse> getServices(String login) throws ServiceException;

  ServiceResponse addService(ServiceDeviceBody service) throws ServiceException;

  void delete(ServiceDeviceBody serviceDeviceBody) throws ServiceException;

  BillResponse getBill(String login) throws ServiceException;

}
