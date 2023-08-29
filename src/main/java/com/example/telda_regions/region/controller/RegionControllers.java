package com.example.telda_regions.region.controller;

import com.example.telda_regions.region.dto.RegionRequestDto;
import com.example.telda_regions.region.dto.RegionResponseDto;
import com.example.telda_regions.region.services.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/regions")
@Tag(name = "REST for regions")
public class RegionControllers {

  private final RegionService regionService;

  public RegionControllers(RegionService regionService) {
    this.regionService = regionService;
  }

  @GetMapping
  @Operation(summary = "Getting all regions")
  public List<RegionResponseDto> getAllRegions(
    @RequestParam(required = false) @Parameter(description = "param title") String title,
    @RequestParam(required = false) @Parameter(description = "param shortTitle") String shortTitle) {
    return regionService.findAllRegion(title, shortTitle);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Getting region by id")
  public RegionResponseDto getRegionById(
    @PathVariable @Parameter(description = "id for getting region") Long id) {
    return regionService.findRegionById(id);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deleting region")
  public void deleteRegion(
    @PathVariable("id") @Parameter(description = "id for deleting region") Long id) {
    regionService.deleteRegionById(id);
  }

  @PostMapping
  @Operation(summary = "Creating region")
  public RegionResponseDto createRegion(@RequestBody RegionRequestDto regionDto) {
    return regionService.createRegion(regionDto);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Updating region")
  public RegionResponseDto updateRegionById(
    @PathVariable("id") @Parameter(description = "id for updating region") Long id,
    @RequestBody RegionRequestDto regionDto) {
    return regionService.updateRegionById(id, regionDto);
  }
}
