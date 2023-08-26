package com.example.telda_regions.Region.services;

import com.example.telda_regions.Region.dto.RegionRequestDto;
import com.example.telda_regions.Region.dto.RegionResponseDto;
import com.example.telda_regions.Region.dtoMapper.RegionDtoMapper;
import com.example.telda_regions.Region.entity.RegionEntity;
import com.example.telda_regions.Region.mapper.RegionMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

  public RegionServiceImpl(RegionMapper regionMapper, RegionDtoMapper regionDtoMapper) {
    this.regionMapper = regionMapper;
    this.regionDtoMapper = regionDtoMapper;
  }

  @Override
  @Cacheable(value = REGION_BY_ID, key = "#id")
  public RegionResponseDto findRegionById(Long id) {
    logger.info("get по id запрос выполняется");
    RegionEntity regionEntity = regionMapper
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    logger.info("get по id запрос выполнился");
    return regionDtoMapper.toDTO(regionEntity);
  }

  @Override
  @Cacheable(cacheNames = ALL_REGION)
  public List<RegionResponseDto> findAllRegion(String title, String shortTitle) {
    logger.info("get запрос выполняется");
    List<RegionEntity> regionEntities = regionMapper.findAll(title, shortTitle);
    logger.info("get запрос выполнился");
    return regionEntities.stream().map(regionDtoMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
  public void deleteRegionById(Long id) {
    logger.info("delete запрос выполняется");
    regionMapper
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    regionMapper.deleteById(id);
    logger.info("delete запрос выполнился");
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = ALL_REGION, allEntries = true)
  public RegionResponseDto createRegion(RegionRequestDto region) {
    logger.info("post запрос выполняется");
    RegionEntity regionEntity = regionDtoMapper.toEntity(region);
    regionMapper.insert(regionEntity);
    RegionEntity newRegionEntity = regionMapper.getLastCreatedRegion();
    logger.info("post запрос выполнился");
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
    logger.info("put по id запрос выполняется");
    RegionEntity regionEntity = regionDtoMapper.toEntity(region);
    regionMapper.updateById(id, regionEntity);
    RegionEntity updatedRegionEntity = regionMapper
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    logger.info("put по id запрос выполнился");
    return regionDtoMapper.toDTO(updatedRegionEntity);
  }

}
