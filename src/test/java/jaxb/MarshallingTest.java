package jaxb;

import static com.example.jaxb.marshaller.JaxbMarshaller.createXmlGregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.errorhandler.ErrorHandler;
import com.example.jaxb.classes.client.Client;
import com.example.jaxb.classes.listorderrequest.ListOrderRequest;
import com.example.jaxb.classes.listorderresponse.ListOrderResponse;
import com.example.jaxb.classes.order.Order;
import com.example.jaxb.classes.product.Product;
import com.example.jaxb.marshaller.JaxbMarshaller;
import com.example.xmlvalidator.ValidateXml;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class MarshallingTest {
  ValidateXml validateXml = new ValidateXml();

  @BeforeAll
  public static void setUp() throws ErrorHandler, DatatypeConfigurationException {

    XMLGregorianCalendar dateOfBirth;

    {
      try {
        dateOfBirth = createXmlGregorianCalendar("2023-06-26T15:07:54-03:00");
      } catch (DatatypeConfigurationException e) {
        throw new DatatypeConfigurationException(e);
      }
    }

    Client client = new Client("string", dateOfBirth, 10, new BigDecimal("99.00"), "MALE");
    Product product = new Product("AAAAA0000", "good", new BigDecimal("99.00"), "SHOES");
    Product product2 = new Product("AAAAA0000", "good", new BigDecimal("99.00"), "SHOES");

    ListOrderRequest listOrderRequest = new ListOrderRequest();
    listOrderRequest.setClient(client);

    List<Product> productList = new ArrayList<>();
    productList.add(product);
    productList.add(product2);
    Order order = new Order(10, client, productList);

    List<Order> orderArrayList = new ArrayList<Order>();
    orderArrayList.add(order);
    ListOrderResponse listOrderResponse = new ListOrderResponse();
    listOrderResponse.setOrders(orderArrayList);

    JaxbMarshaller jaxbMarshaller = new JaxbMarshaller();

    jaxbMarshaller.jaxbObjectToXml(
        product, new File("src/main/resources/jaxbMarshallingOutput/Product.xml"));
    jaxbMarshaller.jaxbObjectToXml(
        listOrderResponse,
        new File("src/main/resources/jaxbMarshallingOutput/ListOrderResponse.xml"));
    jaxbMarshaller.jaxbObjectToXml(
        client, new File("src/main/resources/jaxbMarshallingOutput/Client.xml"));
    jaxbMarshaller.jaxbObjectToXml(
        listOrderRequest,
        new File("src/main/resources/jaxbMarshallingOutput/ListOrderRequest.xml"));
    jaxbMarshaller.jaxbObjectToXml(
        order, new File("src/main/resources/jaxbMarshallingOutput/Order.xml"));
  }

  @Test
  public void shouldReturnValidListOrderXMl() throws IOException, SAXException {
    assertTrue(validateXml.isValid("xsd/order.xsd", "jaxbMarshallingOutput/Order.xml"));
  }

  @Test
  public void shouldReturnValidProductXMl() throws IOException, SAXException {
    assertTrue(validateXml.isValid("xsd/product.xsd", "jaxbMarshallingOutput/Product.xml"));
  }

  @Test
  public void shouldReturnValidOrderResponseXMl() throws IOException, SAXException {
    assertTrue(
        validateXml.isValid(
            "xsd/listOrderResponse.xsd", "jaxbMarshallingOutput/ListOrderResponse.xml"));
  }

  @Test
  public void shouldReturnValidClientXMl() throws IOException, SAXException {
    assertTrue(validateXml.isValid("xsd/client.xsd", "jaxbMarshallingOutput/Client.xml"));
  }

  @Test
  public void shouldReturnValidListOrderRequestXMl() throws IOException, SAXException {
    assertTrue(
        validateXml.isValid(
            "xsd/listOrderRequest.xsd", "jaxbMarshallingOutput/ListOrderRequest.xml"));
  }
}
