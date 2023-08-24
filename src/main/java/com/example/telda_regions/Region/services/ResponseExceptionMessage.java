package com.example.telda_regions.Region.services;

public enum ResponseExceptionMessage {
  NOT_FOUND("Регион не найден");

  private final String message;

  private ResponseExceptionMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
