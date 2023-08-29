package com.example.telda_regions.region.providers;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class RegionSqlBuilder implements ProviderMethodResolver {

  public static String regionsByParams(String title, String shortTitle) {
    return new SQL() {{
      SELECT("*");
      FROM("region");
      if (title != null) {
        WHERE("title like #{title}");
      }
      if (shortTitle != null) {
        WHERE("short_title like #{shortTitle}");
      }
    }}.toString();
  }
}
