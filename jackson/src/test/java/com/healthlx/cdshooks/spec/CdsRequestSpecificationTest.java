package com.healthlx.cdshooks.spec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthlx.cdshooks.model.CdsRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CdsRequestSpecificationTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapperBuilder().build();
  }

  @Test
  public void cdsRequestShouldFollowSpecification() throws IOException {

    String string = "{\n" + "   \"hookInstance\" : \"d1577c69-dfbe-44ad-ba6d-3e05e953b2ea\",\n"
        + "   \"fhirServer\" : \"http://hooks.smarthealthit.org:9080\",\n" + "   \"hook\" : \"patient-view\",\n"
        + "   \"fhirAuthorization\" : {\n" + "     \"access_token\" : \"some-opaque-fhir-access-token\",\n"
        + "     \"token_type\" : \"Bearer\",\n" + "     \"expires_in\" : 300,\n"
        + "     \"scope\" : \"patient/Patient.read patient/Observation.read\",\n"
        + "     \"subject\" : \"cds-service4\"\n" + "   },\n" + "   \"context\" : {\n"
        + "       \"userId\" : \"Practitioner/example\",\n" + "       \"patientId\" : \"1288992\",\n"
        + "       \"encounterId\" : \"89284\"\n" + "   },\n" + "   \"prefetch\" : {\n"
        + "      \"patientToGreet\" : {\n" + "         \"resourceType\" : \"Patient\",\n"
        + "         \"gender\" : \"male\",\n" + "         \"birthDate\" : \"1925-12-23\",\n"
        + "         \"id\" : \"1288992\",\n" + "         \"active\" : true\n" + "      }\n" + "   }\n" + "}";

    CdsRequest actual = objectMapper.readValue(string, CdsRequest.class);

    assertEquals("http://hooks.smarthealthit.org:9080", actual.getFhirServer());
    assertEquals("some-opaque-fhir-access-token", actual.getFhirAuthorization()
        .getAccessToken());
    assertEquals("patient/Patient.read patient/Observation.read", actual.getFhirAuthorization()
        .getScope());
    assertEquals(new Integer(300), actual.getFhirAuthorization()
        .getExpiresIn());
    assertEquals("1288992", ((Map) actual.getPrefetch()
        .get("patientToGreet")).get("id"));
    assertTrue((Boolean) ((Map) actual.getPrefetch()
        .get("patientToGreet")).get("active"));
  }

}
