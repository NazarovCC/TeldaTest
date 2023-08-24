package com.example.telda_regions.Region.dtoMapper;

import com.example.telda_regions.Region.dto.RegionRequestDto;
import com.example.telda_regions.Region.dto.RegionResponseDto;
import com.example.telda_regions.Region.entity.RegionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionDtoMapper {
  RegionResponseDto toDTO(RegionEntity region);

  RegionEntity toEntity(RegionRequestDto dto);
}
