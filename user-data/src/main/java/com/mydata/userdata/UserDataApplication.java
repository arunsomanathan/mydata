package com.mydata.userdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/** Main method class for starting the application. */
@SpringBootApplication
@EnableAutoConfiguration
@ConfigurationPropertiesScan
public class UserDataApplication {

  /**
   * The main method of the User Data application
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(UserDataApplication.class, args);
  }
}
