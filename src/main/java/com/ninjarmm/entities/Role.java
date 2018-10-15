package com.ninjarmm.entities;

import javax.persistence.*;

@Entity
@Table(name = "role", indexes = {
  @Index(name = "username_role_index", unique = true, columnList = "customer_id, role_name")
})
public class Role {

  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id")
  private Customer customer;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
