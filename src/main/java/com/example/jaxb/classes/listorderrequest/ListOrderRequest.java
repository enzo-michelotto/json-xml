package com.example.jaxb.classes.listorderrequest;

import com.example.jaxb.classes.client.Client;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** A class that provides ListOrderRequest objects. */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListOrderRequest {
  @XmlElement(name = "client")
  private Client client;

  public Client getClient() {
    return new Client(client);
  }

  public void setClient(Client client) {
    this.client = new Client(client);
  }
}
