package com.example.telda_regions.Region.entity;

import java.util.Objects;

public class RegionEntity {
  private Long id;
  private String title;
  private String shortTitle;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getShortTitle() {
    return shortTitle;
  }

  public void setShortTitle(String shortTitle) {
    this.shortTitle = shortTitle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegionEntity that = (RegionEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(title, that.title)
        && Objects.equals(shortTitle, that.shortTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, shortTitle);
  }

  @Override
  public String toString() {
    return "RegionEntity{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", shortTitle='" + shortTitle + '\'' +
        '}';
  }
}

