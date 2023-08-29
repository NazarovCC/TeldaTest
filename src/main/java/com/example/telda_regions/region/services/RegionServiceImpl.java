package com.example.telda_regions.region.services;

import com.example.telda_regions.region.dto.RegionRequestDto;
import com.example.telda_regions.region.dto.RegionResponseDto;
import com.example.telda_regions.region.dtoMapper.RegionDtoMapper;
import com.example.telda_regions.region.entity.RegionEntity;
import com.example.telda_regions.region.mapper.RegionMapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.telda_regions.region.util.ResponseExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegionServiceImpl implements RegionService {

  private static final Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

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
    logger.info("Requesting region by id: " + id);
    RegionEntity regionEntity = regionMapper
      .findById(id)
      .orElseThrow(() -> {
        logger.info("Not found region by id: " + id);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage());
      });
    logger.info("Found: " + regionEntity);
    return regionDtoMapper.toDTO(regionEntity);
  }

  @Override
  @Cacheable(cacheNames = ALL_REGION)
  public List<RegionResponseDto> findAllRegion(String title, String shortTitle) {
    logger.info("Requesting all regions with parameters: title = " + title + ", shortTitle = " + shortTitle);
    List<RegionEntity> regionEntities = regionMapper.findAll(title, shortTitle);
    logger.info("Found: " + regionEntities.size() + " regions");
    return regionEntities.stream().map(regionDtoMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
  public void deleteRegionById(Long id) {
    logger.info("Requesting region by id: " + id);
    RegionEntity regionEntity = regionMapper
      .findById(id)
      .orElseThrow(() ->
      {
        logger.info("Not found region by id: " + id);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage());
      });
    regionMapper.deleteById(id);
    logger.info("Delete: " + regionEntity);
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
  public RegionResponseDto createRegion(RegionRequestDto region) {
    logger.info("Creating region");
    RegionEntity regionEntity = regionDtoMapper.toEntity(region);
    regionMapper.insert(regionEntity);
    RegionEntity newRegionEntity = regionMapper.getLastCreatedRegion();
    logger.info("Create: " + newRegionEntity);
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
    logger.info("Updating region by id: " + id);
    RegionEntity regionEntity = regionDtoMapper.toEntity(region);
    regionMapper.updateById(id, regionEntity);
    RegionEntity updatedRegionEntity = regionMapper
      .findById(id)
      .orElseThrow(() -> {
        logger.info("Not found region by id: " + id);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage());
      });
    logger.info("Update: " + updatedRegionEntity);
    return regionDtoMapper.toDTO(updatedRegionEntity);
  }
}
