package com.healthlx.cdshooks.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.healthlx.cdshooks.model.Card;
import com.healthlx.cdshooks.model.IndicatorEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IndicatorEnumCombinedSerializerTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(IndicatorEnum.class, new IndicatorEnumCombinedSerializer.IndicatorEnumSerializer());
    simpleModule.addDeserializer(IndicatorEnum.class, new IndicatorEnumCombinedSerializer.IndicatorEnumDeserializer());
    objectMapper.registerModule(simpleModule);
  }

  @Test
  public void indicatorShouldBeSerializable() throws IOException {
    IndicatorEnum expected = IndicatorEnum.HARD_STOP;
    String string = objectMapper.writeValueAsString(expected);
    IndicatorEnum actual = objectMapper.readValue(string, IndicatorEnum.class);
    assertEquals(expected, actual);
  }

  @Test
  public void dependentClassesShouldBeWorking() throws IOException {
    Card expected = new Card();
    expected.setSummary("summary");
    expected.setIndicator(IndicatorEnum.HARD_STOP);
    String string = objectMapper.writeValueAsString(expected);
    Card actual = objectMapper.readValue(string, Card.class);
    assertEquals(expected.getSummary(), actual.getSummary());
    assertEquals(expected.getIndicator(), actual.getIndicator());
  }
}