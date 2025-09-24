package com.example.json.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/** An implementation of the JsonOperations class for LibraryContainer instances. */
public class JsonSerialization {

  /**
   * A deserializer for an abstract class T.
   *
   * @param filePath the path for the file you wish to deserialize.
   * @param tclass the concrete class you'll use.
   * @return the deserialized object.
   * @throws IOException thrown if any problems occur with the file.
   */
  public static <T> Object deserializer(String filePath, Class<T> tclass) throws IOException {

    String jsonString = Files.readString(Paths.get(filePath));

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(jsonString, tclass);
  }

  /**
   * Serializes the provided object to JSON and writes it to the specified output file.
   *
   * @param object The object to be serialized to JSON.
   * @param outputFile The path of the output file where the JSON will be written.
   * @throws IOException If an I/O error occurs while writing the JSON to the file.
   */
  public static void serializer(Object object, String outputFile) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    try (FileWriter fileWriter = new FileWriter(outputFile, StandardCharsets.UTF_8)) {
      fileWriter.write(objectMapper.writeValueAsString(object));
    }
  }
}
