package com.example.jaxb.unmarshaller;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

/**
 * The JaxbUnmarshaller class provides a method for unmarshalling XML files to Java objects using.
 * JAXB (Java Architecture for XML Binding).
 */
@Slf4j
public class JaxbUnmarshaller {

  /**
   * Unmarshalls an XML file to a Java object using JAXB.
   *
   * @param filePath the path to the XML file.
   * @param object the object of the class to be unmarshalled.
   * @return the unmarshalled Java object.
   * @throws JAXBException when having a problem with the file structure.
   */
  public static Object unmarshall(String filePath, Object object) throws JAXBException {

    JAXBContext jaxbContext;
    File xmlFile = new File(filePath);

    try {
      jaxbContext = JAXBContext.newInstance(object.getClass());

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      return jaxbUnmarshaller.unmarshal(xmlFile);
    } catch (JAXBException e) {
      throw new JAXBException(e);
    }
  }
}
