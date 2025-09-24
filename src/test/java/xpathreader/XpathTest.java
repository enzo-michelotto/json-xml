package xpathreader;

import static com.example.xpathreader.XpathReader.xpathNodeExtractor;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.errorhandler.ErrorHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class XpathTest {

  @Test
  public void shouldOutputPygmalion() throws ErrorHandler {

    xpathNodeExtractor(
        new File("src/main/resources/xml/books.xml"),
        "//library/publisher/book[author='Oscar Wilde']/title/text()");

    try (Stream<String> lines = Files.lines(Path.of("src/main/resources/app.log")); ) {
      Boolean[] bool = new Boolean[1];
      bool[0] = false;

      lines.forEach(
          line -> {
            if (line.contains("Pygmalion")) {
              bool[0] = true;
            }
          });
      assertTrue(bool[0]);
    } catch (IOException e) {
      throw new ErrorHandler(e);
    }
  }

  @Test
  public void shouldThrowRuntimeExceptionDueToSax() {
    assertThrows(
        RuntimeException.class,
        () -> {
          xpathNodeExtractor(
              new File("src/main/resources/xml/authorsByNameXslt.xml"),
              "//author[name='Oscar Wilde]/title/text()");
        });
  }

  @Test
  public void shouldThrowRuntimeExceptionDueToXpathExpression() {

    assertThrows(
        RuntimeException.class,
        () -> {
          xpathNodeExtractor(
              new File("src/main/resources/xml/notifyActivationWithoutElements.xml"),
              "//ItemInvolvesResource[SpecifiedBy[ID='Splitter Ports']/DescribedBy/value");
        });
  }

  @Test
  public void shouldThrowRuntimeExceptionDueToIo() {

    assertThrows(
        RuntimeException.class,
        () -> {
          xpathNodeExtractor(new File("notifyActivati.xml"), "//MessageHeader/priority/text()");
        });
  }
}
