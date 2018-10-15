package com.ninjarmm.controllers;

import com.ninjarmm.Constants;
import com.ninjarmm.entities.*;
import com.ninjarmm.exceptions.ServiceException;
import com.ninjarmm.repositories.DeviceRepository;
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
public class RegisteredServicesToDeviceController {

  @Autowired
  private RegisterServiceToDeviceService registerServiceToDeviceService;

  @Autowired DeviceRepository deviceRepository;

  @RequestMapping(value = "/{login}/getServices", method = RequestMethod.GET)
  @ApiOperation(value = "Retrieve all the services per device for a given username")
  @ApiResponses({ @ApiResponse(code = 200, message = "Services per login retrieved") })
  public ResponseEntity<?> getServices(
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ) throws ServiceException {
    //check if the user its working with its own data
    if( !principal.getName().equals(login)) {
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }
    List<RegisteredServicesResponse> registeredServices;
    if(StringUtils.isNotEmpty(login)){
      registeredServices = registerServiceToDeviceService.getServices(login);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(registeredServices, HttpStatus.FOUND);
  }

  @RequestMapping(value = "/{login}/addService", method = RequestMethod.POST)
  @ApiOperation(value = "Add service to a device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Services per login retrieved") })
  public ResponseEntity<?> addService(
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "JSON containing the id of the service(service) and the id of the device(device)", required = true) @RequestBody ServiceDeviceBody service,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ) throws ServiceException {
    //check if the user its working with its own data
    Device found = deviceRepository.findById(service.getDevice());
    if(found != null &&
      !(found.getCustomer().getLogin().equals(login) && principal.getName().equals(login))){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }
    ServiceResponse availableServiceDevice = registerServiceToDeviceService.addService(service);
    if(availableServiceDevice != null){
      return new ResponseEntity<>(availableServiceDevice, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(service, HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/{login}/deleteService", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device deleted")}
  )
  public ResponseEntity<?> deleteDevice(
    @ApiParam(value = "Registered service id", required = true) @RequestParam(value = "id") Long id,
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  )throws ServiceException{
    //check if the user its working with its own data
    Device found = deviceRepository.findById(id);
    if(found != null &&
      !(found.getCustomer().getLogin().equals(login) && principal.getName().equals(login))){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //removes the device
    boolean result = registerServiceToDeviceService.delete(id);
    if(result){
      return new ResponseEntity<>("The registered device with id " + id + " has been removed", HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/{login}/getBill", method = RequestMethod.GET)
  @ApiOperation(value = "Gets the monthly bill")
  @ApiResponses({ @ApiResponse(code = 200, message = "Bill retrieved")}
  )
  public ResponseEntity<?> getBill(
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  )throws ServiceException{
    //check if the user its working with its own data
    if( !principal.getName().equals(login)){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    BillResponse billResponse;
    if(StringUtils.isNotEmpty(login)){
      billResponse = registerServiceToDeviceService.getBill(login);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(billResponse, HttpStatus.FOUND);
  }


}
