package com.healthlx.cdshooks.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.healthlx.cdshooks.model.CdsResponse;
import com.healthlx.cdshooks.model.FhirAuthorization;
import com.healthlx.cdshooks.model.IndicatorEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class CdsResponseSpecificationTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(FhirAuthorization.class,
        new FhirAutorizationCombinedSerializer.FhirAutorizationSerializer());
    simpleModule.addDeserializer(FhirAuthorization.class,
        new FhirAutorizationCombinedSerializer.FhirAutorizationDeserializer());

    simpleModule.addSerializer(IndicatorEnum.class, new IndicatorEnumCombinedSerializer.IndicatorEnumSerializer());
    simpleModule.addDeserializer(IndicatorEnum.class, new IndicatorEnumCombinedSerializer.IndicatorEnumDeserializer());

    objectMapper.registerModule(simpleModule);
  }

  @Test
  public void emptyCdsResponseShouldFollowSpecification() throws IOException {
    String string = "{\n" + "  \"cards\": []\n" + "}";
    CdsResponse actual = objectMapper.readValue(string, CdsResponse.class);
    assertTrue(actual.getCards()
        .isEmpty());
  }

  @Test
  public void cdsResponseShouldFollowSpecification() throws IOException {
    String string = "{\n" + "  \"cards\": [\n" + "    {\n" + "      \"summary\": \"Example Card\",\n"
        + "      \"indicator\": \"info\",\n" + "      \"detail\": \"This is an example card.\",\n"
        + "      \"source\": {\n" + "        \"label\": \"Static CDS Service Example\",\n"
        + "        \"url\": \"https://example.com\",\n"
        + "        \"icon\": \"https://example.com/img/icon-100px.png\"\n" + "      },\n" + "      \"links\": [\n"
        + "        {\n" + "          \"label\": \"Google\",\n" + "          \"url\": \"https://google.com\",\n"
        + "          \"type\": \"absolute\"\n" + "        },\n" + "        {\n" + "          \"label\": \"Github\",\n"
        + "          \"url\": \"https://github.com\",\n" + "          \"type\": \"absolute\"\n" + "        },\n"
        + "        {\n" + "          \"label\": \"SMART Example App\",\n"
        + "          \"url\": \"https://smart.example.com/launch\",\n" + "          \"type\": \"smart\",\n"
        + "          \"appContext\": \"{\\\"session\\\":3456356,\\\"settings\\\":{\\\"module\\\":4235}}\"\n"
        + "        }\n" + "      ]\n" + "    },\n" + "    {\n" + "      \"summary\": \"Another card\",\n"
        + "      \"indicator\": \"warning\",\n" + "      \"source\": {\n"
        + "        \"label\": \"Static CDS Service Example\"\n" + "      }\n" + "    }\n" + "  ]\n" + "}";

    CdsResponse mappedResponse = objectMapper.readValue(string, CdsResponse.class);
    assertTrue(mappedResponse.getCards().isEmpty());
  }

}
