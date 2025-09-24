package com.example.xpathreader;

import com.example.errorhandler.ErrorHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** A class for operations on xml files. */
@Slf4j
public class XpathReader {

  /**
   * Receives a xml file and returns the found text with the current date.
   *
   * @param file you wish to find something on.
   * @param xpathExpression the element you wish to look for and return from.
   */
  public static void xpathNodeExtractor(File file, String xpathExpression) throws ErrorHandler {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    try (FileInputStream fileInputStream = new FileInputStream(file)) {

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document xmlDocument = documentBuilder.parse(fileInputStream);
      XPath xpath = XPathFactory.newInstance().newXPath();
      NodeList nodeList =
          (NodeList) xpath.compile(xpathExpression).evaluate(xmlDocument, XPathConstants.NODESET);
      log.info(
          "Node: {}, currentTime: {}",
          nodeList.item(0).toString(),
          currentDateTime.format(formatter));
    } catch (ParserConfigurationException
        | SAXException
        | IOException
        | XPathExpressionException e) {
      throw new ErrorHandler(e);
    }
  }
}
