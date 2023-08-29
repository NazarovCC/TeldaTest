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
@Tag(name = "REST для регионов")
public class RegionControllers {

  private final RegionService regionService;

  public RegionControllers(RegionService regionService) {
    this.regionService = regionService;
  }

  @GetMapping
  @Operation(summary = "Получение всех регионов")
  public List<RegionResponseDto> getAllRegions(
    @RequestParam(required = false) @Parameter(description = "для поиска регионов по title") String title,
    @RequestParam(required = false) @Parameter(description = "для поиска регионов по shortTitle") String shortTitle) {
    return regionService.findAllRegion(title, shortTitle);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Получение региона по id")
  public RegionResponseDto getRegionById(
    @PathVariable @Parameter(description = "id для поиска региона") Long id) {
    return regionService.findRegionById(id);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Удаление региона")
  public void deleteRegion(
    @PathVariable("id") @Parameter(description = "id для удаления региона") Long id) {
    regionService.deleteRegionById(id);
  }

  @PostMapping
  @Operation(summary = "Создание региона")
  public RegionResponseDto createRegion(@RequestBody RegionRequestDto regionDto) {
    return regionService.createRegion(regionDto);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Обновление региона")
  public RegionResponseDto updateRegionById(
    @PathVariable("id") @Parameter(description = "id для обновления региона") Long id,
    @RequestBody RegionRequestDto regionDto) {
    return regionService.updateRegionById(id, regionDto);
  }
}
