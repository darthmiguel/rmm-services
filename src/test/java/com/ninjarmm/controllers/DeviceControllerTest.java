package com.ninjarmm.controllers;

import com.ninjarmm.TestConstants;
import com.ninjarmm.entities.request.AddDeviceBody;
import com.ninjarmm.entities.response.DeviceResponse;
import com.ninjarmm.entities.request.UpdateDeviceBody;
import com.ninjarmm.exceptions.DeviceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class DeviceControllerTest {

  @Autowired
  private DeviceController deviceController;

  /***
   * Bad request when calling getDevices endpoint
   */
  @Test
  public void getDevicesBadRequest() throws Exception {
    ResponseEntity<?> responseEntity = deviceController.getDevices("", null);
    Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  /***
   * No devices found for a given user
   */
  @Test(expected = DeviceException.class)
  public void getDevicesNotFound() throws Exception {
    deviceController.getDevices("emestanza", null);
  }

  /***
   * Endpoint getDevices retrieves the devices for a given user
   */
  @Test
  public void getDevices () throws Exception {
    ResponseEntity<?> responseEntity = deviceController.getDevices("mmarmol", null);
    Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
    List<DeviceResponse> deviceResponseList = (List<DeviceResponse>)responseEntity.getBody();
    Assert.assertEquals(1, deviceResponseList.size());
    Assert.assertEquals("Device Test", deviceResponseList.get(0).getSystemName());
  }

  /***
   * Correctly updates device's name
   */
  @Test
  public void updateDevice() throws Exception {
    ResponseEntity<?> responseEntity = deviceController
      .updateDevice(
        TestConstants.getUpdateDeviceBody(), "mmarmol", null);
    Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    DeviceResponse deviceResponse = (DeviceResponse) responseEntity.getBody();
    Assert.assertEquals(TestConstants.getUpdateDeviceBody().getSystemName(), deviceResponse.getSystemName());
  }

  /**
   * The given id does not belong to any device
   */
  @Test(expected = DeviceException.class)
  public void updateDeviceNotFound() throws Exception {
    UpdateDeviceBody updateDeviceBody = TestConstants.getUpdateDeviceBody();
    updateDeviceBody.setId(121L);
    deviceController.updateDevice(updateDeviceBody, "mmarmol", null);
  }

  /**
   * If system_name or id are null a exception is thrown
   */
  @Test(expected = DeviceException.class)
  public void updateDeviceEmptyFields() throws Exception {
    UpdateDeviceBody updateDeviceBody = TestConstants.getUpdateDeviceBody();
    updateDeviceBody.setId(null);
    deviceController.updateDevice(updateDeviceBody, "mmarmol", null);
  }

  /***
   * Device was not removed because its id does not belong to any existing record
   */
  @Test(expected = DeviceException.class)
  public void deleteDeviceNotFound() throws Exception{
    deviceController.deleteDevice(122L, "mmarmol", null);
  }

  /***
   * Correctly removes a device given its id
   */
  @Test
  public void deleteDevice() throws Exception{
    ResponseEntity<?> responseEntity = deviceController.deleteDevice(101L, "emestanza", null);
    Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  /***
   * Cannot add a device with the same name and type
   */
  @Test(expected = DeviceException.class)
  public void addDuplicateDevice() throws Exception{
    deviceController.addDevice(TestConstants.getAddDeviceBody(), "ifaier", null);
  }

  /***
   * The customer was not found
   */
  @Test(expected = DeviceException.class)
  public void addDeviceCustomerNotFound() throws Exception{
    deviceController.addDevice(TestConstants.getAddDeviceBody(), "testUser", null);
  }

  /***
   * the device type does not exist
   */
  @Test(expected = DeviceException.class)
  public void addDeviceDeviceTypeNotFound() throws Exception{
    AddDeviceBody addDeviceBody = TestConstants.getAddDeviceBody();
    addDeviceBody.setType(10);
    deviceController.addDevice(addDeviceBody, "mmarmol", null);
  }

  /***
   * Device's name is mandatory
   */
  @Test(expected = DeviceException.class)
  public void addDeviceSystemNameNull() throws Exception{
    AddDeviceBody addDeviceBody = TestConstants.getAddDeviceBody();
    addDeviceBody.setSystemName(null);
    deviceController.addDevice(addDeviceBody, "mmarmol", null);
  }

  /***
   * Correctly adds a device to a customer
   */
  @Test
  public void addDevice() throws Exception{
    AddDeviceBody addDeviceBody = TestConstants.getAddDeviceBody();
    addDeviceBody.setSystemName("New Device");
    ResponseEntity<?> responseEntity = deviceController.addDevice(addDeviceBody, "mmarmol", null);
    Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
  }


}
