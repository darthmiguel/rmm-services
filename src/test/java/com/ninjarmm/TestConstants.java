package com.ninjarmm;

import com.ninjarmm.entities.request.AddDeviceBody;
import com.ninjarmm.entities.request.ServiceDeviceBody;
import com.ninjarmm.entities.request.UpdateDeviceBody;

public class TestConstants {

  public static UpdateDeviceBody getUpdateDeviceBody(){
    UpdateDeviceBody updateDeviceBody = new UpdateDeviceBody();
    updateDeviceBody.setSystemName("Win XP");
    updateDeviceBody.setId(100L);
    return updateDeviceBody;
  }

  public static AddDeviceBody getAddDeviceBody(){
    AddDeviceBody addDeviceBody = new AddDeviceBody();
    addDeviceBody.setSystemName("Device Test");
    addDeviceBody.setType(1);
    return addDeviceBody;
  }

  public static ServiceDeviceBody getServiceDeviceBody(){
    ServiceDeviceBody serviceDeviceBody = new ServiceDeviceBody();
    serviceDeviceBody.setDevice(129L);
    serviceDeviceBody.setService(120L);
    return serviceDeviceBody;
  }
}
