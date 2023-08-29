package com.example.telda_regions.region.dtoMapper;

import com.example.telda_regions.region.dto.RegionRequestDto;
import com.example.telda_regions.region.dto.RegionResponseDto;
import com.example.telda_regions.region.entity.RegionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionDtoMapper {

  RegionResponseDto toDTO(RegionEntity region);

  RegionEntity toEntity(RegionRequestDto dto);
}
