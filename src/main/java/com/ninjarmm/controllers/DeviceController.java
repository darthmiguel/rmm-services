package com.ninjarmm.controllers;

import com.ninjarmm.Constants;
import com.ninjarmm.entities.request.AddDeviceBody;
import com.ninjarmm.entities.Device;
import com.ninjarmm.entities.response.DeviceResponse;
import com.ninjarmm.entities.request.UpdateDeviceBody;
import com.ninjarmm.exceptions.DeviceException;
import com.ninjarmm.repositories.DeviceRepository;
import com.ninjarmm.services.DeviceService;
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

/***
 * Rest Controller that allows users to manage their devices
 * getDevices: Retrieves all user's devices
 * addDevice: Adds a device to a user
 * updateDevice: Updates the user's device name
 * deleteDevice: Removes a device
 */
@Component
@RestController
@RequestMapping("/device")
public class DeviceController {

  @Autowired
  private DeviceService deviceService;

  @Autowired
  private DeviceRepository deviceRepository;

  @RequestMapping(value = "/{login}/getDevices", method = RequestMethod.GET)
  @ApiOperation(value = "Retrieve all the devices for a given username")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device(s) retrieved") })
  public ResponseEntity<?> getDevices(
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
   ) throws DeviceException {
    //check if the user its working with its own data
    if(principal != null && !principal.getName().equals(login)) {
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //retrieves all devices
    List<DeviceResponse> devices;
    if(StringUtils.isNotEmpty(login)){
      devices = deviceService.getAll(login);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(devices, HttpStatus.FOUND);
  }

  @RequestMapping(value = "/{login}/addDevice", method = RequestMethod.POST)
  @ApiOperation(value = "Add device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device added") })
  public ResponseEntity<?> addDevice(
    @ApiParam(value = "JSON containing device type(device_type) and the name of the device(system_name)", required = true) @RequestBody AddDeviceBody deviceContext,
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  )throws DeviceException{
    //check if the user its working with its own data
    if(principal != null && !principal.getName().equals(login)) {
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //adds the device
    DeviceResponse device;
    try{
      device = deviceService.save(deviceContext, login);
    }
    catch (Exception e){
      throw new DeviceException("The device '" + deviceContext.getSystemName() + "' has already been added");
    }
    return new ResponseEntity<>(device, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{login}/updateDevice", method = RequestMethod.PUT)
  @ApiOperation(value = "Update device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device updated") })
  public ResponseEntity<?> updateDevice(
    @ApiParam(value = "JSON containing the id and the name(system_name) of the device", required = true) @RequestBody UpdateDeviceBody updateDeviceBody,
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ) throws DeviceException{
    //check if the user its working with its own data
    Device found = deviceRepository.findById(updateDeviceBody.getId());
    if(found != null && principal != null &&
      !(found.getCustomer().getLogin().equals(login) && principal.getName().equals(login))){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //updates the device
    DeviceResponse updatedDevice = deviceService.update(updateDeviceBody);
    return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
  }

  @RequestMapping(value = "/{login}/deleteDevice", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device deleted")}
  )
  public ResponseEntity<?> deleteDevice(
    @ApiParam(value = "Device id", required = true) @RequestParam(value = "id") Long id,
    @ApiParam(value = "username", required = true) @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ) throws DeviceException{
    //check if the user its working with its own data
    Device found = deviceRepository.findById(id);
    if(found != null && principal != null &&
      !(found.getCustomer().getLogin().equals(login) && principal.getName().equals(login))){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //removes the device
    deviceService.delete(id);
    return new ResponseEntity<>("The device with id " + id + " has been removed", HttpStatus.OK);
  }
}
