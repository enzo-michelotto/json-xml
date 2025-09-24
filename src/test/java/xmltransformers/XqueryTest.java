package xmltransformers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.errorhandler.ErrorHandler;
import com.example.xmltransformers.XqueryXmlTransformer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class XqueryTest {

  @Test
  public void shouldPrintValidCurrent_Price() throws ErrorHandler {

    XqueryXmlTransformer.transformFileWithXquery(
        "src/main/resources/xquery/expensiveBooks.xq",
        "src/main/resources/xml/books.xml",
        "target/xml/expensiveBooksXquery.xml");

    try (Stream<String> lines = Files.lines(Path.of("target/xml/expensiveBooksXquery.xml")); ) {
      Boolean[] bool = new Boolean[1];
      bool[0] = false;

      lines.forEach(
          line -> {
            if (line.contains("<mr:current_price>25</mr:current_price>")) {
              bool[0] = true;
            }
          });
      assertTrue(bool[0]);
    } catch (IOException e) {
      throw new ErrorHandler(e);
    }
  }

  @Test
  public void shouldContainValidTitleOnPrint() throws ErrorHandler {

    XqueryXmlTransformer.transformFileWithXquery(
        "src/main/resources/xquery/authors.xq",
        "src/main/resources/xml/books.xml",
        "target/xml/authorsXquery.xml");

    try (Stream<String> lines = Files.lines(Path.of("target/xml/authorsXquery.xml")); ) {
      Boolean[] bool = new Boolean[1];
      bool[0] = false;

      lines.forEach(
          line -> {
            if (line.contains("<mr:name>J. D. Salinger</mr:name>")) {
              bool[0] = true;
            }
          });
      assertTrue(bool[0]);
    } catch (IOException e) {
      throw new ErrorHandler(e);
    }
  }

  @Test
  public void shouldPrintValidTitle() throws ErrorHandler {
    XqueryXmlTransformer.transformFileWithXquery(
        "src/main/resources/xquery/authorsByName.xq",
        "src/main/resources/xml/books.xml",
        "target/xml/authorsByNameXquery.xml",
        "Oscar Wilde");

    try (Stream<String> lines = Files.lines(Path.of("target/xml/authorsByNameXquery.xml")); ) {
      Boolean[] bool = new Boolean[1];
      bool[0] = false;

      lines.forEach(
          line -> {
            if (line.contains("<mr:name>Oscar Wilde</mr:name>")) {
              bool[0] = true;
            }
          });
      assertTrue(bool[0]);
    } catch (IOException e) {
      throw new ErrorHandler(e);
    }
  }
}
