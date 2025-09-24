package xmlvalidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.xmlvalidator.ValidateXml;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class ValidateXmlTest {

  ValidateXml validateXML = new ValidateXml();

  @Test
  public void shouldBeValidOrderRequest() {
    try {
      assertTrue(
          validateXML.isValid(
              "xsd/listOrderResponse.xsd", "jaxbMarshallingOutput/ListOrderResponse.xml"));
    } catch (IOException | SAXException e) {
      throw new RuntimeException(e);
    }
  }
}
