package com.example.telda_regions.Region.services;

import com.example.telda_regions.Region.dto.RegionRequestDto;
import com.example.telda_regions.Region.dto.RegionResponseDto;
import com.example.telda_regions.Region.dtoMapper.RegionDtoMapper;
import com.example.telda_regions.Region.entity.RegionEntity;
import com.example.telda_regions.Region.mapper.RegionMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegionServiceImpl implements RegionService{

  private final RegionMapper regionMapper;
  private final RegionDtoMapper regionDtoMapper;

  public RegionServiceImpl(RegionMapper regionMapper, RegionDtoMapper regionDtoMapper) {
    this.regionMapper = regionMapper;
    this.regionDtoMapper = regionDtoMapper;
  }

  @Override
  @Cacheable(value = "regionById", key = "#id")
  public RegionResponseDto findRegionById(Long id) {
    RegionEntity regionEntity = regionMapper
        .findRegionById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    return regionDtoMapper.toDTO(regionEntity);
  }

  @Override
  @Cacheable(cacheNames = "allRegion")
  public List<RegionResponseDto> findAllRegion() {
    System.out.println("1");
    List<RegionEntity> regionEntities = regionMapper.findAllRegions();
    return regionEntities.stream().map(regionDtoMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  @CacheEvict(cacheNames = "allRegion", allEntries = true)
  public void deleteRegionById(Long id) {
    RegionEntity region = regionMapper
        .findRegionById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));
    regionMapper.deleteById(id);
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = "allRegion", allEntries = true)
  public RegionResponseDto createRegion(RegionRequestDto region) {

    RegionEntity regionEntity = regionDtoMapper.toEntity(region);

    regionMapper.insert(regionEntity);

    RegionEntity newRegionEntity = regionMapper.getLastCreatedRegion();

    return regionDtoMapper.toDTO(newRegionEntity);
  }

  @Override
  @Transactional
  @CachePut(value = {"allRegion", "regionById"}, key = "#id")
  public RegionResponseDto updateRegionById(Long id, RegionRequestDto region) {

    RegionEntity regionEntity = regionDtoMapper.toEntity(region);

    regionMapper.updateById(id, regionEntity);

    RegionEntity updatedRegionEntity = regionMapper
        .findRegionById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseExceptionMessage.NOT_FOUND.getMessage()));

    return regionDtoMapper.toDTO(updatedRegionEntity);
  }

}
