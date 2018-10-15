package com.ninjarmm.entities;

public class Bill {

  private String serviceName;

  private double subtotal;

  public Bill(Object rawBill) {
    Object [] rawBillArray = (Object[]) rawBill;
    this.serviceName = (String) rawBillArray[0];
    this.subtotal = (double) rawBillArray[1];
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double total) {
    this.subtotal = total;
  }
}
