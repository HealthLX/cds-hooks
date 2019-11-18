package com.healthlx.cdshooks.spec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthlx.cdshooks.model.CdsResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CdsResponseSpecificationTest {

  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapperBuilder().build();
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

    CdsResponse actual = objectMapper.readValue(string, CdsResponse.class);

    assertEquals("https://example.com", actual.getCards().get(0).getSource().getUrl());
    assertEquals("{\"session\":3456356,\"settings\":{\"module\":4235}}",
        actual.getCards().get(0).getLinks().get(2).getAppContext());
    assertEquals("Static CDS Service Example", actual.getCards().get(1).getSource().getLabel());
  }

}
