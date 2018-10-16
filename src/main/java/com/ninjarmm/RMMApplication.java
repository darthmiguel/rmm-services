package com.ninjarmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.ninjarmm")
@EntityScan("com.ninjarmm.entities")
@EnableJpaRepositories("com.ninjarmm.repositories")
public class RMMApplication {


  public static void main(String[] args) {
    SpringApplication.run(RMMApplication.class, args);
  }
}
