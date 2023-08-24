package com.example.telda_regions;

import com.example.telda_regions.Region.RegionEntity;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(RegionEntity.class)
@MapperScan("com.example.telda_regions.Region.mapper")
@SpringBootApplication
public class TeldaRegionsApplication {

  public static void main(String[] args) {
    SpringApplication.run(TeldaRegionsApplication.class, args);
  }

}
