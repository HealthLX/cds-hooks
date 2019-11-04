package com.healthlx.cdshooks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FhirAuthorization {

  private String accessToken;
  private String tokenType;
  private Integer expiresIn;
  private String scope;
  private String subject;
}
