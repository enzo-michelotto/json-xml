package json.serialization;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.json.classes.Book;
import com.example.json.classes.Library;
import com.example.json.classes.LibraryContainer;
import com.example.json.classes.Publisher;
import com.example.json.serialization.JsonSerialization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SerializerTest {

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
    JsonSerialization jsonSerialization = new JsonSerialization();
    assertDoesNotThrow(
        () ->
            jsonSerialization.serializer(
                libraryContainer, "src/main/resources/serializedLibrary.json"));
  }
}
