package com.healthlx.cdshooks.spec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.healthlx.cdshooks.jackson.CdsServiceCombinedSerializer;
import com.healthlx.cdshooks.jackson.FhirAutorizationCombinedSerializer;
import com.healthlx.cdshooks.jackson.IndicatorEnumCombinedSerializer;
import com.healthlx.cdshooks.model.CdsService;
import com.healthlx.cdshooks.model.FhirAuthorization;
import com.healthlx.cdshooks.model.IndicatorEnum;

public class ObjectMapperBuilder {

  /**
   * Initializes ObjectMapper with all available serializers
   *
   * @return initialized @{ling {@link ObjectMapper}}
   */
  ObjectMapper build() {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(FhirAuthorization.class,
        new FhirAutorizationCombinedSerializer.FhirAutorizationSerializer());
    simpleModule.addDeserializer(FhirAuthorization.class,
        new FhirAutorizationCombinedSerializer.FhirAutorizationDeserializer());

    simpleModule.addSerializer(IndicatorEnum.class, new IndicatorEnumCombinedSerializer.IndicatorEnumSerializer());
    simpleModule.addDeserializer(IndicatorEnum.class, new IndicatorEnumCombinedSerializer.IndicatorEnumDeserializer());

    simpleModule.addSerializer(CdsService.class, new CdsServiceCombinedSerializer.CdsServiceSerializer());
    simpleModule.addDeserializer(CdsService.class, new CdsServiceCombinedSerializer.CdsServiceDeserializer());

    return objectMapper.registerModule(simpleModule);
  }
}
