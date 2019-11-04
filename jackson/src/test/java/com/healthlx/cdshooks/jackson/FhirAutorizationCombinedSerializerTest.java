package com.healthlx.cdshooks.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.healthlx.cdshooks.model.FhirAuthorization;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FhirAutorizationCombinedSerializerTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(FhirAuthorization.class,
        new FhirAutorizationCombinedSerializer.FhirAutorizationSerializer());
    simpleModule.addDeserializer(FhirAuthorization.class,
        new FhirAutorizationCombinedSerializer.FhirAutorizationDeserializer());
    objectMapper.registerModule(simpleModule);
  }

  @Test
  public void authorizationShouldBeSerializable() throws IOException {
    FhirAuthorization expected = new FhirAuthorization();
    expected.setAccessToken("test/token");
    expected.setSubject("test//subject");
    expected.setScope("test/scope");
    expected.setExpiresIn(42);
    expected.setTokenType("test/token.type");

    String string = objectMapper.writeValueAsString(expected);
    FhirAuthorization actual = objectMapper.readValue(string, FhirAuthorization.class);

    assertEquals(expected.getAccessToken(), actual.getAccessToken());
    assertEquals(expected.getSubject(), actual.getSubject());
    assertEquals(expected.getScope(), actual.getScope());
    assertEquals(expected.getExpiresIn(), actual.getExpiresIn());
    assertEquals(expected.getTokenType(), actual.getTokenType());
  }

  @Test
  public void authorizationShouldFollowSpecification() throws IOException {

    String string =
        "{\n" + "     \"access_token\" : \"some-opaque-fhir-access-token\",\n" + "     \"token_type\" : \"Bearer\",\n"
            + "     \"expires_in\" : 300,\n" + "     \"scope\" : \"patient/Patient.read patient/Observation.read\",\n"
            + "     \"subject\" : \"cds-service4\"\n" + "   }";
    FhirAuthorization actual = objectMapper.readValue(string, FhirAuthorization.class);

    assertEquals("some-opaque-fhir-access-token", actual.getAccessToken());
    assertEquals("cds-service4", actual.getSubject());
    assertEquals("patient/Patient.read patient/Observation.read", actual.getScope());
    assertEquals(new Integer(300), actual.getExpiresIn());
    assertEquals("Bearer", actual.getTokenType());
  }

}