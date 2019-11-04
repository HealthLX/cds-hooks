package com.healthlx.cdshooks.model;

public enum IndicatorEnum {
  INFO("info"),

  WARNING("warning"),

  HARD_STOP("hard-stop");

  private String value;

  IndicatorEnum(String value) {
    this.value = value;
  }

  /**
   * Create the enum value from a string. Needed because the values have illegal java chars.
   *
   * @param value One of the enum values.
   * @return indicatorEnum
   */
  public static IndicatorEnum fromValue(String value) {
    for (IndicatorEnum indicatorEnum : IndicatorEnum.values()) {
      if (indicatorEnum.toString().equals(value)) {
        return indicatorEnum;
      }
    }
    return null;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
