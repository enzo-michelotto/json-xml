package jaxb;

import static com.example.jaxb.unmarshaller.JaxbUnmarshaller.unmarshall;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.jaxb.classes.client.Client;
import com.example.jaxb.classes.listorderrequest.ListOrderRequest;
import com.example.jaxb.classes.listorderresponse.ListOrderResponse;
import com.example.jaxb.classes.order.Order;
import com.example.jaxb.classes.product.Product;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

public class UnmarshallerTest {

  Order order = new Order();
  Client client = new Client();
  ListOrderRequest listOrderRequest = new ListOrderRequest();
  ListOrderResponse listOrderResponse = new ListOrderResponse();
  Product product = new Product();

  @Test
  public void shouldReturnValidOrderFromFile() throws JAXBException {
    order = (Order) unmarshall("src/main/resources/jaxbMarshallingOutput/Order.xml", order);
    assertEquals(10, order.getOrderNumber());
  }

  @Test
  public void shouldReturnValidClientFromFile() throws JAXBException {
    client = (Client) unmarshall("src/main/resources/jaxbMarshallingOutput/Client.xml", client);
    assertEquals("string", client.getName());
  }

  @Test
  public void shouldReturnValidProductFromFile() throws JAXBException {
    product = (Product) unmarshall("src/main/resources/jaxbMarshallingOutput/Product.xml", product);
    assertEquals("AAAAA0000", product.getProductCode());
  }

  @Test
  public void shouldReturnValidListRequestFromFile() throws JAXBException {
    listOrderRequest =
        (ListOrderRequest)
            unmarshall(
                "src/main/resources/jaxbMarshallingOutput/ListOrderRequest.xml", listOrderRequest);
    assertEquals("string", listOrderRequest.getClient().getName());
  }

  @Test
  public void shouldReturnValidListResponseFromFile() throws JAXBException {
    listOrderResponse =
        (ListOrderResponse)
            unmarshall(
                "src/main/resources/jaxbMarshallingOutput/ListOrderResponse.xml",
                listOrderResponse);
    assertEquals(10, listOrderResponse.getOrders().get(0).getOrderNumber());
  }

  @Test
  public void jaxbObjectToXml_ShouldThrowJAXBException() {

    assertThrows(
        JAXBException.class,
        () -> {
          unmarshall("src/main/resources/xml/ListOrderRequestFail.xml", ListOrderRequest.class);
        });
  }
}
