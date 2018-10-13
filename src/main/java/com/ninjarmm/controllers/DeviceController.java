package com.ninjarmm.controllers;

import com.ninjarmm.entities.Device;
import com.ninjarmm.entities.DeviceContext;
import com.ninjarmm.exceptions.DeviceException;
import com.ninjarmm.services.DeviceService;
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
@RequestMapping("/device")
public class DeviceController {

  @Autowired
  private DeviceService deviceService;

  @RequestMapping(value = "/getByLogin", method = RequestMethod.GET)
  @ApiOperation(value = "retrieve all the devices for a given login")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device(s) retrieved") })
  public ResponseEntity<?> getByLogin(
    @ApiParam(value = "user login") @RequestParam(value = "login") String login
  ) throws DeviceException {
    List<Device> devices = null;
    if(StringUtils.isNotEmpty(login)){
      devices = deviceService.getAll(login);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(devices, HttpStatus.FOUND);
  }

  @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
  @ApiOperation(value = "add device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device added") })
  public ResponseEntity<?> addDevice(
    @ApiParam(value = "device") @RequestBody DeviceContext deviceContext) throws DeviceException{
    Device device = deviceService.save(deviceContext);
    if(device != null){
      return new ResponseEntity<>(device, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(deviceContext, HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/updateDevice", method = RequestMethod.PUT)
  @ApiOperation(value = "Update device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device updated") })
  public ResponseEntity<?> updateDevice(
    @ApiParam(value = "device") @RequestBody DeviceContext deviceContext) throws DeviceException{
    Device updatedDevice = deviceService.update(deviceContext);
    if(updatedDevice != null){
      return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }
    return new ResponseEntity<>(deviceContext, HttpStatus.NOT_MODIFIED);
  }

  @RequestMapping(value = "/deleteDevice", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete device")
  @ApiResponses({ @ApiResponse(code = 200, message = "Device deleted")})
  public ResponseEntity<?> deleteDevice(
    @ApiParam(value = "device id") @RequestParam(value = "id") Long id){
    boolean result = deviceService.delete(id);
    if(result){
      return new ResponseEntity<>(result, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
  }


}
