package com.healthlx.cdshooks.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.healthlx.cdshooks.model.IndicatorEnum;

import java.io.IOException;

public class IndicatorEnumCombinedSerializer {

  public static class IndicatorEnumSerializer extends JsonSerializer<IndicatorEnum> {

    @Override
    public void serialize(IndicatorEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.getValue());
    }
  }

  public static class IndicatorEnumDeserializer extends JsonDeserializer<IndicatorEnum> {

    @Override
    public IndicatorEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      return IndicatorEnum.fromValue(p.getText());
    }
  }

}
