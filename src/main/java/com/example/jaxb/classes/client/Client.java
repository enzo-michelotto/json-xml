package com.example.jaxb.classes.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/** A class that provides Client objects. */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {

  protected String name;
  protected XMLGregorianCalendar dateOfBirth;
  protected String gender;
  protected long documentId;
  protected BigDecimal availableAccountCredit;

  /** A constructor for Client objects. */
  public Client() {
    super();
  }

  /**
   * Constructs a new Client object with the specified name, date of birth, document ID, available
   * account credit, and gender.
   *
   * @param name the name of the client
   * @param dateOfBirth the date of birth of the client
   * @param documentId the document ID of the client
   * @param availableAccountCredit the available account credit for the client
   * @param gender the gender of the client
   */
  public Client(
      String name,
      XMLGregorianCalendar dateOfBirth,
      long documentId,
      BigDecimal availableAccountCredit,
      String gender) {
    this.availableAccountCredit = availableAccountCredit;
    this.gender = gender;
    this.name = name;
    this.dateOfBirth = returnCopy(dateOfBirth);
    this.documentId = documentId;
  }

  /**
   * A constructor method for the Client class.
   *
   * @param client the client you wish to copy.
   */
  public Client(Client client) {
    this(
        client.getName(),
        client.getDateOfBirth(),
        client.getDocumentId(),
        client.getAvailableAccountCredit(),
        client.getGender());
  }

  public String getName() {
    return name;
  }

  public XMLGregorianCalendar returnCopy(XMLGregorianCalendar xmlGregorianCalendar) {
    XMLGregorianCalendar date = xmlGregorianCalendar;
    return date;
  }

  public XMLGregorianCalendar getDateOfBirth() {
    return returnCopy(dateOfBirth);
  }

  public long getDocumentId() {
    return documentId;
  }

  public BigDecimal getAvailableAccountCredit() {
    return availableAccountCredit;
  }

  public String getGender() {
    return gender;
  }
}
