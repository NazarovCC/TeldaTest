package com.example.telda_regions.Region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/regions")
public class RegionControllers {

  @Autowired
  private final RegionService regionService;

  public RegionControllers(RegionService regionService) {
    this.regionService = regionService;
  }

  @GetMapping
  public List<RegionEntity> getAllRegions() {
    return regionService.findAllRegion();
  }

  @GetMapping("/{id}")
  public RegionEntity getRegionById(@PathVariable Long id) {
    return regionService.findRegionById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRegion(@PathVariable("id") Long id) {
    regionService.deleteRegionById(id);
  }

  @PostMapping
  public RegionEntity createRegion(@RequestBody RegionEntity regionEntity) {
    return regionService.createRegion(regionEntity);

  }

  @PutMapping("/{id}")
  public RegionEntity updateRegionById(@PathVariable("id") Long id, @RequestBody RegionEntity regionEntity) {
    return regionService.updateRegionById(id, regionEntity);
  }

}
