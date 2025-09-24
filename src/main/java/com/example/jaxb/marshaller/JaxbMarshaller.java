package com.example.jaxb.marshaller;

import com.example.errorhandler.ErrorHandler;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.extern.slf4j.Slf4j;

/**
 * The JaxbMarshaller class provides methods for converting Java objects to XML using JAXB (Java
 * Architecture for XML Binding).
 */
@Slf4j
public class JaxbMarshaller {

  /**
   * Converts a Java object to XML using JAXB and saves the XML to a outputFile.
   *
   * @param object the Java object to be converted to XML
   * @param outputFile the output outputFile
   */
  public void jaxbObjectToXml(Object object, File outputFile) throws ErrorHandler {

    try {

      JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      jaxbMarshaller.marshal(object, outputFile);

    } catch (JAXBException e) {
      throw new ErrorHandler(e);
    }
  }

  /**
   * Creates an XMLGregorianCalendar object from a date string.
   *
   * @param dateString the date string to be converted
   * @return the XMLGregorianCalendar object representing the specified date
   * @throws DatatypeConfigurationException if a DatatypeFactory cannot be created
   */
  public static XMLGregorianCalendar createXmlGregorianCalendar(String dateString)
      throws DatatypeConfigurationException {

    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
    return datatypeFactory.newXMLGregorianCalendar(dateString);
  }
}
