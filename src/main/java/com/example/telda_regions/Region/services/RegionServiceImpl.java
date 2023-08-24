package com.example.telda_regions.Region.services;

import com.example.telda_regions.Region.dto.RegionRequestDto;
import com.example.telda_regions.Region.dto.RegionResponseDto;
import com.example.telda_regions.Region.dtoMapper.RegionDtoMapper;
import com.example.telda_regions.Region.entity.RegionEntity;
import com.example.telda_regions.Region.mapper.RegionMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegionServiceImpl implements RegionService{

  private final RegionMapper regionMapper;
  private final RegionDtoMapper regionDtoMapper;
  private final static String REGION_BY_ID = "regionById";
  private final static String ALL_REGION = "allRegion";

  public RegionServiceImpl(RegionMapper regionMapper, RegionDtoMapper regionDtoMapper) {
    this.regionMapper = regionMapper;
    this.regionDtoMapper = regionDtoMapper;
  }

  @Override
  @Cacheable(value = REGION_BY_ID, key = "#id")
  public RegionResponseDto findRegionById(Long id) {
    RegionEntity regionEntity = regionMapper
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    return regionDtoMapper.toDTO(regionEntity);
  }

  @Override
  @Cacheable(cacheNames = ALL_REGION)
  public List<RegionResponseDto> findAllRegion(String title, String shortTitle) {
    List<RegionEntity> regionEntities = regionMapper.findAll(title, shortTitle);
    return regionEntities.stream().map(regionDtoMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
  public void deleteRegionById(Long id) {
    regionMapper
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    regionMapper.deleteById(id);
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
  public RegionResponseDto createRegion(RegionRequestDto region) {

    RegionEntity regionEntity = regionDtoMapper.toEntity(region);

    regionMapper.insert(regionEntity);

    RegionEntity newRegionEntity = regionMapper.getLastCreatedRegion();

    return regionDtoMapper.toDTO(newRegionEntity);
  }

  @Override
  @Transactional
  @Caching(
      put = {
          @CachePut(value = REGION_BY_ID, key = "#id"),
      },
      evict = {
          @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
      }
  )
  public RegionResponseDto updateRegionById(Long id, RegionRequestDto region) {

    RegionEntity regionEntity = regionDtoMapper.toEntity(region);

    regionMapper.updateById(id, regionEntity);

    RegionEntity updatedRegionEntity = regionMapper
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));

    return regionDtoMapper.toDTO(updatedRegionEntity);
  }

}
