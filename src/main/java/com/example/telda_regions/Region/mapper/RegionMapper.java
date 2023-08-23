package com.example.telda_regions.Region.mapper;

import com.example.telda_regions.Region.RegionEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RegionMapper {

  @Select("select * from region where id = #{id}")
  @ResultType(RegionEntity.class)
  RegionEntity findRegionById(@Param("id") Long id);

  @Select("select * from region")
  @ResultType(RegionEntity.class)
  List<RegionEntity> findAllRegions();

  @Delete("delete from region where id=#{id}")
  void deleteById(@Param("id") Long id);

  @Insert("insert into region(title, short_title) values (#{title}, #{shortTitle})")
  @SelectKey(statement = "call identity()", keyProperty = "id", keyColumn = "id", before=false, resultType = Long.class)
  @ResultType(RegionEntity.class)
  RegionEntity insert(RegionEntity regionEntity);

  @Update("update region set title=#{title} short_title=#{shortTitle} where id=#{id}")
  @ResultType(RegionEntity.class)
  RegionEntity updateById(@Param("id") Long id, RegionEntity regionEntity);
}
