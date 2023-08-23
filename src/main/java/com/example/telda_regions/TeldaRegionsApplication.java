package com.example.telda_regions;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.telda_regions.Region")
@SpringBootApplication
public class TeldaRegionsApplication {

  public static void main(String[] args) {
    SpringApplication.run(TeldaRegionsApplication.class, args);
  }

}
