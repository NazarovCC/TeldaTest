package com.example.telda_regions.region.util;

public enum ResponseExceptionMessage {

  NOT_FOUND("Регион не найден");

  private final String message;

  ResponseExceptionMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
