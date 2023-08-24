package com.example.telda_regions.Region.mapper;

import com.example.telda_regions.Region.RegionEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RegionMapper {

  @Select("select * from region where id = #{id}")
  @Results(id = "regionEntityById", value = {
      @Result(property = "id", column = "id",  id = true),
      @Result(property = "title", column = "title"),
      @Result(property = "shortTitle", column = "short_title")
  })
  RegionEntity findRegionById(@Param("id") Long id);

  @Select("select * from region")
  @Results(id = "regionEntityAll", value = {
      @Result(property = "id", column = "id",  id = true),
      @Result(property = "title", column = "title"),
      @Result(property = "shortTitle", column = "short_title")
  })
  List<RegionEntity> findAllRegions();

  @Delete("delete from region where id=#{id}")
  void deleteById(@Param("id") Long id);

  @Insert("insert into region(title, short_title) values (#{title}, #{shortTitle})")
  void insert(RegionEntity regionEntity);

  @Update("update region set title=#{regionEntity.title}, short_title=#{regionEntity.shortTitle} where id=#{id}")
  void updateById(@Param("id") Long id, RegionEntity regionEntity);

  @Select("select * from region order by id DESC limit 1")
  @Results(id = "regionEntityLimit1", value = {
      @Result(property = "id", column = "id",  id = true),
      @Result(property = "title", column = "title"),
      @Result(property = "shortTitle", column = "short_title")
  })
  RegionEntity getLastCreatedRegion();
}
