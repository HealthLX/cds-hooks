package com.healthlx.cdshooks.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class CdsService {

  /**
   * The {id} portion of the URL to this service which is available at {baseUrl}/cds-services/{id}. REQUIRED
   */
  private String id;

  /**
   * The hook this service should be invoked on. REQUIRED
   */
  private String hook;

  /**
   * The human-friendly name of this service. RECOMMENDED
   */
  private String title;

  /**
   * The description of this service. REQUIRED
   */
  private String description;

  /**
   * The FHIR data that was prefetched by the CDS Client
   */
  private Map<String, Object> prefetch;

  public CdsService(String id, String hook, String title, String description, Map<String, Object> prefetch) {
    if (id == null) {
      throw new IllegalArgumentException("CDSService id cannot be null.");
    }
    if (hook == null) {
      throw new IllegalArgumentException("CDSService hook cannot be null.");
    }
    if (description == null) {
      throw new IllegalArgumentException("CDSService description cannot be null.");
    }
    this.id = id;
    this.hook = hook;
    this.title = title;
    this.description = description;
    this.prefetch = prefetch;
  }
}
