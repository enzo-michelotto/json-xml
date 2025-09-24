package json.serialization;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.json.classes.LibraryContainer;
import com.example.json.serialization.JsonSerialization;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DeserializerTest {
  @Test
  public void shouldDeserialize() throws IOException {

    JsonSerialization jsonSerialization = new JsonSerialization();
    try (Stream<String> lines = Files.lines(Path.of("src/main/resources/app.log")); ) {
      Boolean[] bool = new Boolean[1];
      bool[0] = false;
      log.info(
          jsonSerialization
              .deserializer("src/main/resources/books.json", LibraryContainer.class)
              .toString());

      lines.forEach(
          line -> {
            if (line.contains("Dune Messiah")) {
              bool[0] = true;
            }
          });
      assertTrue(bool[0]);
    }
  }
}
