package com.ninjarmm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ninjarmm.repositories"})
@ImportResource("classpath:jpa.cfg.xml")
public class NinjarmmJpaConfig {
}
