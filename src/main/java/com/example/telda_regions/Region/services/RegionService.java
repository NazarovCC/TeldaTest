package com.example.telda_regions.Region.services;

import com.example.telda_regions.Region.dto.RegionRequestDto;
import com.example.telda_regions.Region.dto.RegionResponseDto;
import java.util.List;

public interface RegionService {
  RegionResponseDto findRegionById(Long id);

  List<RegionResponseDto> findAllRegion();

  void deleteRegionById(Long id);

  RegionResponseDto createRegion(RegionRequestDto region);

  RegionResponseDto updateRegionById(Long id, RegionRequestDto region);

}
