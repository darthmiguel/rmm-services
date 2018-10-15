package com.ninjarmm.controllers;

import com.ninjarmm.Constants;
import com.ninjarmm.entities.ServiceDevice;
import com.ninjarmm.entities.ServiceDeviceContext;
import com.ninjarmm.exceptions.ServiceException;
import com.ninjarmm.services.RegisterServiceToDeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Component
@RestController
@RequestMapping("/service")
public class AvailableServiceDeviceController {

  @Autowired
  private RegisterServiceToDeviceService availableServiceDeviceService;

  @RequestMapping(value = "/{login}/getServices", method = RequestMethod.GET)
  @ApiOperation(value = "retrieve all the devices for a given login")
  @ApiResponses({ @ApiResponse(code = 200, message = "Services per login retrieved") })
  public ResponseEntity<?> getByLogin(
    @ApiParam(value = "username") @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ) throws ServiceException {
    //check if the user its working with its own data
    if( !principal.getName().equals(login)) {
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }
    List<ServiceDevice> services;
    if(StringUtils.isNotEmpty(login)){
      services = availableServiceDeviceService.getAllByLogin(login);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(services, HttpStatus.FOUND);
  }

  @RequestMapping(value = "/{login}/addService", method = RequestMethod.POST)
  @ApiOperation(value = "add service to a device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Services per login retrieved") })
  public ResponseEntity<?> addService(
    @ApiParam(value = "add service to a device") @RequestBody ServiceDeviceContext service) throws ServiceException {
    ServiceDevice availableServiceDevice = availableServiceDeviceService.addService(service);
    if(availableServiceDevice != null){
      return new ResponseEntity<>(availableServiceDevice, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(service, HttpStatus.NOT_MODIFIED);
  }
}
