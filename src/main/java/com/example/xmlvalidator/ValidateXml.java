package com.example.xmlvalidator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;

/** A class that validates XML. */
@Slf4j
public class ValidateXml {

  /**
   * Sets a schema for the xml to be validated against.
   *
   * @param xsdPath the path of the schema you wish to use.
   * @return a validator for the specified schema
   * @throws SAXException throws exception if not valid schema.
   */
  private Validator initValidator(String xsdPath) throws SAXException {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Source schemaFile = new StreamSource(getFile(xsdPath));
    Schema schema = factory.newSchema(schemaFile);
    return schema.newValidator();
  }

  private File getFile(String location) {
    return new File(getClass().getClassLoader().getResource(location).getFile());
  }

  /**
   * Validates the xml against the provided schema.
   *
   * @param xsdPath the path of the schema you wish to use.
   * @param xmlPath the path of the xml you wish to validate.
   * @return true if valid.
   * @throws IOException in case you're unable to make operations with the files.
   * @throws SAXException throws exception if not valid schema.
   */
  public boolean isValid(String xsdPath, String xmlPath) throws IOException, SAXException {
    Validator validator = initValidator(xsdPath);
    try {
      validator.validate(new StreamSource(getFile(xmlPath)));
      return true;
    } catch (SAXException e) {
      return false;
    }
  }
}
