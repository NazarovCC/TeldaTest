package com.example.telda_regions.Region;

import java.util.List;

public interface RegionService {
  RegionEntity findRegionById(Long id);

  List<RegionEntity> findAllRegion();

  void deleteRegionById(Long id);

  RegionEntity createRegion(RegionEntity region);

  RegionEntity updateRegionById(Long id, RegionEntity region);

}
