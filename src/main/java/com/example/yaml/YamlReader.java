package com.example.yaml;

import static com.example.json.serialization.JsonSerialization.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/** A class for Yaml file operations. */
public class YamlReader {

  /**
   * A method to transform yaml files into json files.
   *
   * @param filePath the path to the yamlFile.
   * @param outputFile the path to the outputFIle.
   */
  public static <T> void yamlToJson(String filePath, String outputFile, Class<T> tclass) {
    try {

      serializer(yamlDeserializer(filePath, tclass), outputFile);

    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * A method to serialize objects into yaml files.
   *
   * @param object the one to be serialized.
   * @param outputFile where the yaml content will be placed.
   * @throws IOException if it has a problem in writing the file.
   */
  public static void yamlSerializer(Object object, String outputFile) throws IOException {
    ObjectMapper mapper = new ObjectMapper(new JsonFactory());
    try (FileWriter fileWriter = new FileWriter(outputFile, StandardCharsets.UTF_8); ) {
      fileWriter.write(mapper.writeValueAsString(object));
    }
  }

  /**
   * A method to deserialize yaml files into java objects.
   *
   * @param yamlFilePath the yaml file path.
   * @param tclass the class you wish to deserialize for.
   * @return the deserialized object.
   * @throws IOException if it has a problem in writing the file.
   */
  public static <T> Object yamlDeserializer(String yamlFilePath, Class<T> tclass)
      throws IOException {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    return mapper.readValue(
        new String(Files.readAllBytes(Paths.get(yamlFilePath)), StandardCharsets.UTF_8), tclass);
  }

  /**
   * A method for reading a yaml file.
   *
   * @param filePath of the file.
   * @return yamlObject the object from the yaml file.
   */
  public String yamlFileToString(String filePath) throws IOException {
    return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
  }
}
