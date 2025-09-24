package com.example.jaxb.classes.order;

import static java.util.List.copyOf;

import com.example.jaxb.classes.client.Client;
import com.example.jaxb.classes.product.Product;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** A class that provides Order objects. */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

  private int orderNumber;

  private Client client;

  @XmlElement(name = "product", namespace = "http://www.example.com/order")
  private List<Product> products;

  /** A constructor for Order objects. */
  public Order() {
    super();
  }

  /**
   * Constructs a new Order object with the specified order number, client, and products.
   *
   * @param orderNumber the order number associated with the order
   * @param client the client associated with the order
   * @param products the products included in the order
   */
  public Order(int orderNumber, Client client, List<Product> products) {
    this.client = client;
    this.orderNumber = orderNumber;
    this.products = copyOf(products);
  }

  public int getOrderNumber() {
    return orderNumber;
  }
}
