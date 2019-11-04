package com.healthlx.cdshooks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Action {

  private TypeEnum type;
  private String description;
  private Object resource;

  public enum TypeEnum {
    create,
    update,
    delete
  }
}
