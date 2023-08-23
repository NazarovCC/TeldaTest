package com.example.telda_regions.Region;

import com.example.telda_regions.Region.mapper.RegionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService{

  private final RegionMapper regionMapper;

  public RegionServiceImpl(RegionMapper regionMapper) {
    this.regionMapper = regionMapper;
  }

  @Override
  public RegionEntity findRegionById(Long id) {
    return regionMapper.findRegionById(id);
  }

  @Override
  public List<RegionEntity> findAllRegion() {
    return regionMapper.findAllRegions();
  }

  @Override
  public void deleteRegionById(Long id) {
    regionMapper.deleteById(id);
  }

  @Override
  public RegionEntity createRegion(RegionEntity region) {
    return regionMapper.insert(region);
  }

  @Override
  public RegionEntity updateRegionById(Long id, RegionEntity region) {
    return regionMapper.updateById(id, region);
  }

}
