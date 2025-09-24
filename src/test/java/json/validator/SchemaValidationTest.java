package json.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.json.validator.JsonSchemaValidation;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class SchemaValidationTest {
  @Test
  public void shouldBeEmptyList() throws IOException {
    assertTrue(
        JsonSchemaValidation.validate(
                "src/main/resources/booksSchema.json", "src/main/resources/books.json")
            .isEmpty());
  }
}
