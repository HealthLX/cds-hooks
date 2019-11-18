package com.healthlx.cdshooks.spec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthlx.cdshooks.model.CdsServiceInformation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CdsServiceInformationSpecTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapperBuilder().build();
  }

  @Test
  public void cdsServiceInformationShouldFollowSpecification() throws IOException {

    String string = "{\n" + "  \"services\": [\n" + "    {\n" + "      \"hook\": \"patient-view\",\n"
        + "      \"title\": \"Static CDS Service Example\",\n"
        + "      \"description\": \"An example of a CDS Service that returns a static set of cards\",\n"
        + "      \"id\": \"static-patient-greeter\",\n" + "      \"prefetch\": {\n"
        + "        \"patientToGreet\": \"Patient/{{context.patientId}}\"\n" + "      }\n" + "    },\n" + "    {\n"
        + "      \"hook\": \"medication-prescribe\",\n" + "      \"title\": \"Medication Echo CDS Service\",\n"
        + "      \"description\": \"An example of a CDS Service that simply echos the medication being prescribed\",\n"
        + "      \"id\": \"medication-echo\",\n" + "      \"prefetch\": {\n"
        + "        \"patient\": \"Patient/{{context.patientId}}\",\n"
        + "        \"medications\": \"MedicationRequest?patient={{context.patientId}}\"\n" + "      }\n" + "    }\n"
        + "  ]\n" + "}";

    CdsServiceInformation actual = objectMapper.readValue(string, CdsServiceInformation.class);

    assertEquals("static-patient-greeter", actual.getServices()
        .get(0)
        .getId());
    assertEquals("patient-view", actual.getServices()
        .get(0)
        .getHook());
  }

  @Test
  public void cdsServiceInformationTitleShouldBeOptional() throws IOException {

    String string = "{\n" + "  \"services\": [\n" + "    {\n" + "      \"hook\": \"patient-view\",\n"
        + "      \"description\": \"An example of a CDS Service that returns a static set of cards\",\n"
        + "      \"id\": \"static-patient-greeter\",\n" + "      \"prefetch\": {\n"
        + "        \"patientToGreet\": \"Patient/{{context.patientId}}\"\n" + "      }\n" + "    },\n" + "    {\n"
        + "      \"hook\": \"medication-prescribe\",\n" + "      \"title\": \"Medication Echo CDS Service\",\n"
        + "      \"description\": \"An example of a CDS Service that simply echos the medication being prescribed\",\n"
        + "      \"id\": \"medication-echo\",\n" + "      \"prefetch\": {\n"
        + "        \"patient\": \"Patient/{{context.patientId}}\",\n"
        + "        \"medications\": \"MedicationRequest?patient={{context.patientId}}\"\n" + "      }\n" + "    }\n"
        + "  ]\n" + "}";

    CdsServiceInformation actual = objectMapper.readValue(string, CdsServiceInformation.class);

    assertNull(actual.getServices()
        .get(0)
        .getTitle());
  }

}
