package com.healthlx.cdshooks.spec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthlx.cdshooks.model.CdsService;
import com.healthlx.cdshooks.model.CdsServiceInformation;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CdsServiceInformationSpecTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapperBuilder().build();
  }

  @Test
  public void shouldFollowSpecification() throws IOException {

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
  public void titleShouldBeOptional() throws IOException {

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

  @Test
  public void prefetchShouldBeSerializable() throws IOException {

    CdsServiceInformation information = new CdsServiceInformation();
    Map<String,Object> prefetch = new HashMap<>();
    prefetch.put("key1", Collections.singletonMap("key2","Test?where={{test.context.data}}"));
    prefetch.put("key3", Collections.singletonMap("key4","AnotherData"));
    prefetch.put("key5","Flat data");
    information.addServicesItem(new CdsService("1","test-hook","test","test description",prefetch));

    String string = objectMapper.writeValueAsString(information);

    assertTrue(StringUtils.contains(string, "Test?where={{test.context.data}}"));
  }

  @Test
  public void prefetchShouldBeDeserializable() throws IOException {

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
    String patientToGreet = (String) actual.getServices()
        .get(0)
        .getPrefetch()
        .get("patientToGreet");

    assertTrue(StringUtils.contains(patientToGreet, "{{context.patientId}}"));
  }
}
