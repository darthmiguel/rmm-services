package com.ninjarmm.controllers;

import com.ninjarmm.entities.AvailableServiceDevice;
import com.ninjarmm.entities.AvailableServiceDeviceContext;
import com.ninjarmm.exceptions.ServiceException;
import com.ninjarmm.services.AvailableServiceDeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/service")
public class AvailableServiceDeviceController {

  @Autowired
  private AvailableServiceDeviceService availableServiceDeviceService;

  @RequestMapping(value = "/getByLogin", method = RequestMethod.GET)
  @ApiOperation(value = "retrieve all the devices for a given login")
  @ApiResponses({ @ApiResponse(code = 200, message = "Services per login retrieved") })
  public ResponseEntity<?> getByLogin(
    @ApiParam(value = "user login") @RequestParam(value = "login") String login
  ) throws ServiceException {
    List<AvailableServiceDevice> services = null;
    if(StringUtils.isNotEmpty(login)){
      services = availableServiceDeviceService.getAllByLogin(login);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(services, HttpStatus.FOUND);
  }

  @RequestMapping(value = "/addService", method = RequestMethod.POST)
  @ApiOperation(value = "add service to a device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Services per login retrieved") })
  public ResponseEntity<?> addService(
    @ApiParam(value = "add service to a device") @RequestBody AvailableServiceDeviceContext service) throws ServiceException {
    AvailableServiceDevice availableServiceDevice = availableServiceDeviceService.addService(service);
    if(availableServiceDevice != null){
      return new ResponseEntity<>(availableServiceDevice, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(service, HttpStatus.NOT_MODIFIED);
  }
}
