package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BillResponse {


  public BillResponse(List<Bill> bill){
    this.billList = bill;
  }

  @JsonProperty("detail")
  private List<Bill> billList;

  private double total;

  public List<Bill> getBillList() {
    return billList;
  }

  public void setBillList(List<Bill> billList) {
    this.billList = billList;
  }

  public double getTotal() {
    return billList.stream().mapToDouble(bill -> bill.getSubtotal()).sum();
  }

  public void setTotal(double total) {
    this.total = total;
  }
}
