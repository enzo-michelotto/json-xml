package com.example.jaxb.classes.listorderresponse;

import com.example.jaxb.classes.order.Order;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** A class that provides ListOrderResponse objects. */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListOrderResponse {
  @XmlElement(name = "order")
  private List<Order> orders;

  public void setOrders(List<Order> orders) {
    this.orders = List.copyOf(orders);
  }

  public List<Order> getOrders() {
    return List.copyOf(orders);
  }
}
