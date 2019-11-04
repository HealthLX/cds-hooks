package com.healthlx.cdshooks.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.IntNode;
import com.healthlx.cdshooks.model.FhirAuthorization;

import java.io.IOException;

public class FhirAutorizationCombinedSerializer {

  public static class FhirAutorizationSerializer extends JsonSerializer<FhirAuthorization> {

    @Override
    public void serialize(FhirAuthorization value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeStartObject();
      gen.writeStringField("access_token", value.getAccessToken());
      gen.writeStringField("token_type", value.getTokenType());
      gen.writeNumberField("expires_in", value.getExpiresIn());
      gen.writeStringField("scope", value.getScope());
      gen.writeStringField("subject", value.getSubject());
      gen.writeEndObject();
    }
  }

  public static class FhirAutorizationDeserializer extends JsonDeserializer<FhirAuthorization> {

    @Override
    public FhirAuthorization deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      JsonNode treeNode = p.getCodec()
          .readTree(p);

      String accessToken = treeNode.get("access_token")
          .asText();
      String tokenType = treeNode.get("token_type")
          .asText();
      Integer expiresIn = (Integer) ((IntNode) treeNode.get("expires_in")).numberValue();
      String scope = treeNode.get("scope")
          .asText();
      String subject = treeNode.get("subject")
          .asText();

      FhirAuthorization fhirAuthorization = new FhirAuthorization();
      fhirAuthorization.setAccessToken(accessToken);
      fhirAuthorization.setTokenType(tokenType);
      fhirAuthorization.setExpiresIn(expiresIn);
      fhirAuthorization.setScope(scope);
      fhirAuthorization.setSubject(subject);
      return fhirAuthorization;
    }
  }

}

