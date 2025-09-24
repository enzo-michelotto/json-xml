package yaml;

import static com.example.yaml.YamlReader.yamlDeserializer;
import static com.example.yaml.YamlReader.yamlSerializer;
import static com.example.yaml.YamlReader.yamlToJson;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.json.classes.Book;
import com.example.json.classes.Library;
import com.example.json.classes.LibraryContainer;
import com.example.json.classes.Publisher;
import com.example.json.serialization.JsonSerialization;
import com.example.json.validator.JsonSchemaValidation;
import com.example.yaml.YamlReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class YamlReaderTest {

  JsonSerialization jsonSerialization = new JsonSerialization();

  @Test
  public void shouldReturnValidString() throws IOException { // check the app.log!!!
    YamlReader yamlReader = new YamlReader();

    try (Stream<String> lines = Files.lines(Path.of("src/main/resources/app.log")); ) {
      Boolean[] bool = new Boolean[1];
      bool[0] = false;
      log.info(yamlReader.yamlFileToString("src/main/resources/yaml/books.yaml"));

      lines.forEach(
          line -> {
            if (line.contains("Frank Herbert")) {
              bool[0] = true;
            }
          });
      assertTrue(bool[0]);
    }
  }

  @Test
  public void shouldSerialize() throws IOException {
    Book book = new Book("Enzo", "Chelsea", 18.0);
    List<Book> bookList = new ArrayList<>();
    Publisher publisher = new Publisher();
    bookList.add(book);
    publisher.setBook(List.copyOf(bookList));
    publisher.setName("Galileu");
    List<Publisher> publishers = new ArrayList<>();
    publishers.add(publisher);
    Library library = new Library();
    LibraryContainer libraryContainer = new LibraryContainer();
    library.setPublisher(publishers);
    libraryContainer.setLibrary(library);
    assertDoesNotThrow(
        () -> yamlSerializer(libraryContainer, "src/main/resources/yaml/generatedBook.yaml"));
  }

  @Test
  public void shouldDeserialize() throws IOException {
    assertTrue(
        yamlDeserializer("src/main/resources/yaml/books.yaml", LibraryContainer.class)
            instanceof LibraryContainer);
  }

  @Test
  public void shouldReturnAllBooks() throws IOException {

    LibraryContainer libraryContainer =
        (LibraryContainer)
            yamlDeserializer("src/main/resources/yaml/books.yaml", LibraryContainer.class);
    libraryContainer
        .getLibrary()
        .getPublisher()
        .forEach(
            publisher -> {
              for (Book book : publisher.getBook()) {
                assertTrue(publisher.toString().contains(book.getTitle()));
              }
            });
  }

  @Test
  public void shouldMakeValidJson() {

    assertDoesNotThrow(
        () ->
            yamlToJson(
                "src/main/resources/yaml/books.yaml",
                "src/main/resources/yaml/generatedBook.json",
                LibraryContainer.class));
  }

  @Test
  public void shouldBeEmptyList() throws IOException {
    assertTrue(
        JsonSchemaValidation.validate(
                "src/main/resources/booksSchema.json", "src/main/resources/yaml/generatedBook.json")
            .isEmpty());
  }
}
