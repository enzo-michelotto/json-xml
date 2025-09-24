package xmltransformers;

import static com.example.xmltransformers.XsltXmlTransformer.xmlTransformer;
import static com.example.xmltransformers.XsltXmlTransformer.xmlTransformerWithParameter;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.errorhandler.ErrorHandler;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XsltTest {
  DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
  DocumentBuilder documentBuilder;

  {
    try {
      documentBuilder = documentBuilderFactory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void shouldPrintValidCurrent_Price() throws ErrorHandler {

    String XSLT_FILENAME = "src/main/resources/xslt/expensiveBooks.xslt";

    try (InputStream inputStream = new FileInputStream("src/main/resources/xml/books.xml")) {

      Document xmlDocument = documentBuilder.parse(inputStream);

      try (FileOutputStream output =
          new FileOutputStream("src/main/resources/xml/expensiveBooksXslt.xml")) {
        xmlTransformer(xmlDocument, XSLT_FILENAME, output);
      }

    } catch (IOException | SAXException | TransformerException e) {
      throw new ErrorHandler(e);
    }
    try (Stream<String> lines =
        Files.lines(Path.of("src/main/resources/xml/expensiveBooksXslt.xml")); ) {
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
    String XSLT_FILENAME = "src/main/resources/xslt/authors.xslt";

    try (InputStream inputStream = new FileInputStream("src/main/resources/xml/books.xml")) {

      Document xmlDocument = documentBuilder.parse(inputStream);

      try (FileOutputStream output =
          new FileOutputStream("src/main/resources/xml/authorXslt.xml")) {
        xmlTransformer(xmlDocument, XSLT_FILENAME, output);
      }

    } catch (IOException | SAXException | TransformerException e) {
      throw new ErrorHandler(e);
    }

    try (Stream<String> lines = Files.lines(Path.of("src/main/resources/xml/authorXslt.xml")); ) {
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
    String XSLT_FILENAME = "src/main/resources/xslt/authorsByName.xslt";

    try (InputStream inputStream = new FileInputStream("src/main/resources/xml/books.xml")) {

      Document xmlDocument = documentBuilder.parse(inputStream);

      try (FileOutputStream output =
          new FileOutputStream("src/main/resources/xml/authorsByNameXslt.xml")) {
        xmlTransformerWithParameter(
            xmlDocument, XSLT_FILENAME, output, "author_name", "Oscar Wilde");
      }

    } catch (IOException | SAXException | TransformerException e) {
      throw new ErrorHandler(e);
    }
    try (Stream<String> lines =
        Files.lines(Path.of("src/main/resources/xml/authorsByNameXslt.xml")); ) {
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
