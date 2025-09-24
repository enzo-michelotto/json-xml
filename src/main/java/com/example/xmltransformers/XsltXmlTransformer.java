package com.example.xmltransformers;

import java.io.File;
import java.io.OutputStream;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

/** A class that makes transformation operations. */
public class XsltXmlTransformer {

  static TransformerFactory transformerFactory = TransformerFactory.newInstance();

  /**
   * Transforms a xml into another using xslt.
   *
   * @param xmlDocument the received xml document to modify.
   * @param xsltFilename the xmlTransformer to use.
   * @param output where the generated file will be outputted.
   * @throws TransformerException in case something goes wrong while transforming.
   */
  public static void xmlTransformer(Document xmlDocument, String xsltFilename, OutputStream output)
      throws TransformerException {

    javax.xml.transform.Transformer transformer =
        transformerFactory.newTransformer(new StreamSource(new File(xsltFilename)));

    transformer.transform(new DOMSource(xmlDocument), new StreamResult(output));
  }

  /**
   * Transforms a xml into another using xslt.
   *
   * @param xmlDocument the received xml document to modify.
   * @param xsltFilename the xmlTransformer to use.
   * @param output where the generated file will be outputted.
   * @param parameter the name of the parameter you're passing a value into.
   * @param parameterValue the value being passed.
   * @throws TransformerException in case something goes wrong while transforming.
   */
  public static void xmlTransformerWithParameter(
      Document xmlDocument,
      String xsltFilename,
      OutputStream output,
      String parameter,
      String parameterValue)
      throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();

    javax.xml.transform.Transformer transformer =
        transformerFactory.newTransformer(new StreamSource(new File(xsltFilename)));

    transformer.setParameter(parameter, parameterValue);
    transformer.transform(new DOMSource(xmlDocument), new StreamResult(output));
  }
}
