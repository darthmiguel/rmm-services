package com.ninjarmm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "customer", indexes = {
  @Index(name = "id_index", columnList = "id", unique = true),
  @Index(name = "login_index", columnList = "login", unique = true)
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "login", nullable = false, unique = true)
  private String login;

  @Column(name = "password", nullable = false)
  @JsonIgnore
  private String password;

  @Column(name = "name", nullable = false)
  private String name;

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
