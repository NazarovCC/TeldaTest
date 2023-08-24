package com.example.telda_regions.Region;

import com.example.telda_regions.Region.mapper.RegionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public RegionEntity createRegion(RegionEntity region) {

    regionMapper.insert(region);

    return regionMapper.getLastCreatedRegion();
  }

  @Override
  @Transactional
  public RegionEntity updateRegionById(Long id, RegionEntity region) {
    regionMapper.updateById(id, region);

    return regionMapper.findRegionById(id);
  }

}
