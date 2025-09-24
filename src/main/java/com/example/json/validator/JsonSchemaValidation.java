package com.example.json.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/** Provides methods for validating a JSON document against a JSON schema. */
public class JsonSchemaValidation {
  /**
   * Validates a JSON document against a JSON schema.
   *
   * @param jsonSchemaPath The path to the JSON schema file.
   * @param jsonPath The path to the JSON document file.
   * @return A set of validation messages representing any validation errors or warnings.
   * @throws IOException If an I/O error occurs while reading the JSON schema or document files.
   */
  public static Set<ValidationMessage> validate(String jsonSchemaPath, String jsonPath)
      throws IOException {
    try (InputStream jsonSchemaStream = new FileInputStream(jsonSchemaPath);
        InputStream jsonStream = new FileInputStream(jsonPath)) {
      JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
      JsonSchema jsonSchema = factory.getSchema(jsonSchemaStream);
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(jsonStream);
      return jsonSchema.validate(jsonNode);
    }
  }
}
