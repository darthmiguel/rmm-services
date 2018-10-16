package com.ninjarmm.controllers;

import com.ninjarmm.TestConstants;
import com.ninjarmm.entities.Bill;
import com.ninjarmm.entities.request.ServiceDeviceBody;
import com.ninjarmm.entities.response.BillResponse;
import com.ninjarmm.entities.response.RegisteredServicesResponse;
import com.ninjarmm.entities.response.ServiceResponse;
import com.ninjarmm.exceptions.ServiceException;
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
public class RegisteredServicesToDeviceControllerTest {

  @Autowired
  private RegisteredServicesToDeviceController registeredServicesToDeviceController;

  @Test
  public void getServicesBadRequest() throws Exception {
    ResponseEntity<?> responseEntity = registeredServicesToDeviceController
      .getServices("", null);
    Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test(expected = ServiceException.class)
  public void getServicesNotFound() throws Exception {
    registeredServicesToDeviceController
      .getServices("ppanama", null);
  }

  @Test
  public void getService() throws Exception {
    ResponseEntity<?> responseEntity = registeredServicesToDeviceController
      .getServices("ifaier", null);
    Assert.assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    List<RegisteredServicesResponse> serviceResponseList = (List<RegisteredServicesResponse>)responseEntity.getBody();
    Assert.assertEquals(1, serviceResponseList.size());
  }

  /***
   * if one of the ServiceBody fields is null then throw ServiceException
   */
  @Test(expected = ServiceException.class)
  public void addServiceNullFields() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(null);
    registeredServicesToDeviceController
      .addService("mmarmol", TestConstants.getServiceDeviceBody(), null);
  }

  /**
   * service id does not exist
   */
  @Test(expected = ServiceException.class)
  public void addServiceServiceNotFound() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(100L);
    registeredServicesToDeviceController
      .addService("mmarmol", serviceDeviceBody, null);
  }

  /**
   * device id does not exist
   */
  @Test(expected = ServiceException.class)
  public void addServiceDeviceNotFound() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setService(1L);
    registeredServicesToDeviceController
      .addService("mmarmol", serviceDeviceBody, null);
  }

  /**
   * Service device type does not match the type of the device
   */
  @Test(expected = ServiceException.class)
  public void addServiceDeviceTypeMismatch() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(100L);
    serviceDeviceBody.setService(2L);
    registeredServicesToDeviceController
      .addService("mmarmol", serviceDeviceBody, null);
  }

  /***
   * The service has already been registered to the device
   */
  @Test(expected = ServiceException.class)
  public void addServiceTwice() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(100L);
    serviceDeviceBody.setService(1L);
    registeredServicesToDeviceController
      .addService("mmarmol", serviceDeviceBody, null);
  }

  /***
   * The service 'Antivirus' will be added to the device
   */
  @Test
  public void addService() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(100L);
    serviceDeviceBody.setService(4L);
    ResponseEntity<?> responseEntity = registeredServicesToDeviceController
      .addService("mmarmol", serviceDeviceBody, null);
    Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    ServiceResponse serviceResponse = (ServiceResponse) responseEntity.getBody();
    Assert.assertEquals( "Antivirus", serviceResponse.getName());
  }

  /**
   * Correctly removes service from device
   */
  @Test
  public void deleteService() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(102L);
    serviceDeviceBody.setService(4L);
    ResponseEntity<?> responseEntity = registeredServicesToDeviceController
      .deleteDevice(serviceDeviceBody, "ifaier",  null);
    Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  /**
   * Fails to remove the service representing the monthly cost
   */
  @Test(expected = ServiceException.class)
  public void deleteServiceRemoveDeviceService() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(102L);
    serviceDeviceBody.setService(1L);
    registeredServicesToDeviceController
      .deleteDevice(serviceDeviceBody, "ifaier",  null);
  }

  /**
   * Fails to remove the service for it does not exist
   */
  @Test(expected = ServiceException.class)
  public void deleteServiceServiceNotFound() throws Exception {
    ServiceDeviceBody serviceDeviceBody = TestConstants.getServiceDeviceBody();
    serviceDeviceBody.setDevice(102L);
    serviceDeviceBody.setService(19L);
    registeredServicesToDeviceController
      .deleteDevice(serviceDeviceBody, "ifaier",  null);
  }

  @Test
  public void getBillBadRequest() throws Exception{
    ResponseEntity<?> responseEntity = registeredServicesToDeviceController
      .getBill("", null);
    Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test(expected = ServiceException.class)
  public void getBillNoDevices() throws Exception{
    registeredServicesToDeviceController
      .getBill("ppanama", null);
  }


  @Test
  public void getBill() throws Exception{
    ResponseEntity<?> responseEntity = registeredServicesToDeviceController
      .getBill("billUser", null);
    Assert.assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    BillResponse billResponse = (BillResponse) responseEntity.getBody();
    Assert.assertEquals(71.0, billResponse.getTotal(), 0.01);
    for(Bill bill : billResponse.getBillList()){
      switch (bill.getServiceName()) {
        case "Device":
          Assert.assertEquals(20, bill.getSubtotal(), 0.01);
          break;
        case "Antivirus":
          Assert.assertEquals(31, bill.getSubtotal(), 0.01);
          break;
        case "Cloudberry":
          Assert.assertEquals(15, bill.getSubtotal(), 0.01);
          break;
        case "TeamViewer":
          Assert.assertEquals(5, bill.getSubtotal(), 0.01);
          break;
      }
    }
  }

}
