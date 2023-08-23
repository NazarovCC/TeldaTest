package com.example.telda_regions.Region;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService{

  @Autowired
  private final RegionMapper regionMapper;

  public RegionServiceImpl(RegionMapper regionMapper) {
    this.regionMapper = regionMapper;
  }

  public RegionEntity findRegionById(Long id) {
    return regionMapper.findRegionById(id);
  }

  public List<RegionEntity> findAllRegion() {
    return regionMapper.findAllRegions();
  }

  public void deleteRegionById(Long id) {
    regionMapper.deleteById(id);
  }

  public RegionEntity createRegion(RegionEntity region) {
    return regionMapper.insert(region);
  }

  public RegionEntity updateRegionById(Long id, RegionEntity region) {
    return regionMapper.updateById(id, region);
  }
}
