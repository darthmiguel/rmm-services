package com.ninjarmm.controllers;

import com.ninjarmm.Constants;
import com.ninjarmm.entities.Device;
import com.ninjarmm.entities.AddDeviceBody;
import com.ninjarmm.entities.DeviceResponse;
import com.ninjarmm.entities.UpdateDeviceBody;
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
  @ApiOperation(value = "retrieve all the devices for a given login")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device(s) retrieved") })
  public ResponseEntity<?> getByLogin(
    @ApiParam(value = "username") @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
   ) throws DeviceException {
    //check if the user its working with its own data
    if( !principal.getName().equals(login)) {
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
  @ApiOperation(value = "add device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device added") })
  public ResponseEntity<?> addDevice(
    @ApiParam(value = "device") @RequestBody AddDeviceBody deviceContext,
    @ApiParam(value = "username") @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  )throws DeviceException{
    //check if the user its working with its own data
    if( !principal.getName().equals(login)) {
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //adds the device
    DeviceResponse device;
    try{
      device =deviceService.save(deviceContext, login);
    }
    catch (Exception e){
      throw new DeviceException("The device '" + deviceContext.getSystemName() + "' has already been added");
    }
    if(device != null){
      return new ResponseEntity<>(device, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(deviceContext, HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/{login}/updateDevice", method = RequestMethod.PUT)
  @ApiOperation(value = "Update device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device updated") })
  public ResponseEntity<?> updateDevice(
    @ApiParam(value = "device") @RequestBody UpdateDeviceBody updateDeviceBody,
    @ApiParam(value = "username") @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ) throws DeviceException{
    //check if the user its working with its own data
    Device found = deviceRepository.findById(updateDeviceBody.getId());
    if(found != null &&
      !(found.getCustomer().getLogin().equals(login) && principal.getName().equals(login))){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //updates the device
    DeviceResponse updatedDevice = deviceService.update(updateDeviceBody);
    if(updatedDevice != null){
      return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }
    return new ResponseEntity<>(updateDeviceBody, HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/{login}/deleteDevice", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device deleted")}
  )
  public ResponseEntity<?> deleteDevice(
    @ApiParam(value = "device id") @RequestParam(value = "id") Long id,
    @ApiParam(value = "username") @PathVariable("login") String login,
    @ApiParam(value = "authenticated user") @AuthenticationPrincipal Principal principal
  ){
    //check if the user its working with its own data
    Device found = deviceRepository.findById(id);
    if(found != null &&
      !(found.getCustomer().getLogin().equals(login) && principal.getName().equals(login))){
      return new ResponseEntity<>(Constants.FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    //removes the device
    boolean result = deviceService.delete(id);
    if(result){
      return new ResponseEntity<>("The device with id " + id + " has been removed", HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
  }
}
