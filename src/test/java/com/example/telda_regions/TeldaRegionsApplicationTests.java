package com.example.telda_regions;

import com.example.telda_regions.Region.dto.RegionResponseDto;
import com.example.telda_regions.Region.services.RegionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
class TeldaRegionsApplicationTests {

  @Autowired
  RegionService regionService;

  @Test
  public void whenRecordsInDatabase_shouldReturnWithGivenId() {
    RegionResponseDto regionEntity = regionService.findRegionById(1L);
    Assertions.assertNotNull(regionEntity);
    Assertions.assertEquals(regionEntity.getId(),1L);
    Assertions.assertEquals(regionEntity.getTitle(),"SaintPetersburg");
    Assertions.assertEquals(regionEntity.getShortTitle(),"spb");
  }

  @Test
  public void whenRecordsInDatabaseNotFound_shouldBeResponseStatusExceptionNotFound() {
    ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
      regionService.findRegionById(3L);
    });
    String expectedMessage = "404 NOT_FOUND \"Регион не найден\"";
    String actualMessage = exception.getMessage();

    Assertions.assertEquals(actualMessage, expectedMessage);
  }
}
