package com.healthlx.cdshooks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link {

  private String label;
  private String url;
  private String type;

  private String appContext;
}
