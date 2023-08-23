package com.example.telda_regions;

import com.example.telda_regions.Region.RegionEntity;
import com.example.telda_regions.Region.RegionMapper;
import com.example.telda_regions.Region.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TeldaRegionsApplicationTests {

  @Autowired
  RegionService regionService;

  @Test
  public void whenRecordsInDatabase_shouldReturnWithGivenId() {
    RegionEntity regionEntity = regionService.findRegionById(1L);




  }

}
