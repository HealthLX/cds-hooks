package com.healthlx.cdshooks.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.healthlx.cdshooks.model.CdsService;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

public class CdsServiceCombinedSerializer {

  public static class CdsServiceSerializer extends JsonSerializer<CdsService> {

    @Override
    public void serialize(CdsService value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeStartObject();
      gen.writeStringField("id", value.getId());
      gen.writeStringField("hook", value.getHook());
      if (StringUtils.isNotEmpty(value.getTitle())) {
        gen.writeStringField("title", value.getTitle());
      }
      gen.writeStringField("description", value.getDescription());

      gen.writeFieldName("prefetch");
      gen.writeObject(value.getPrefetch());

      gen.writeEndObject();
    }
  }

  public static class CdsServiceDeserializer extends JsonDeserializer<CdsService> {

    @Override
    public CdsService deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode treeNode = p.getCodec()
          .readTree(p);

      String id = treeNode.get("id")
          .asText();
      String hook = treeNode.get("hook")
          .asText();
      String description = treeNode.get("description")
          .asText();
      JsonNode titleNode = treeNode.get("title");

      JsonParser prefetchParser = treeNode.get("prefetch")
          .traverse();
      prefetchParser.nextToken();
      Map prefetch = ctxt.readValue(prefetchParser, Map.class);

      return new CdsService(id, hook, titleNode != null ? titleNode.asText() : null, description, prefetch);
    }
  }
}
