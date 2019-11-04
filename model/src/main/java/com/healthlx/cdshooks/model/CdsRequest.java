package com.healthlx.cdshooks.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
public class CdsRequest {

  @NotNull(message = "unsupported hook")
  private String hook;
  @NotNull
  private String hookInstance;
  private String fhirServer;
  private FhirAuthorization fhirAuthorization;
  @NotNull
  private String user;
  private String patient;
  @NotNull
  private Map<String, Object> context;
  private Map<String, Object> prefetch;
}