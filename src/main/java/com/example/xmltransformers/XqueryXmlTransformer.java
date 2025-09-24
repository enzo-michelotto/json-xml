package com.example.xmltransformers;

import com.example.errorhandler.ErrorHandler;
import java.io.File;
import java.io.IOException;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmNode;

/** A class that uses xquery to make xml transformations. */
public class XqueryXmlTransformer {

  /**
   * A method that receives a xml and outputs another based on the xquery.
   *
   * @param xqueryPath the path to the xquery you'll use to transformFileWithXquery.
   * @param inputFilePath the file you wish to transformFileWithXquery.
   * @param outputFile the transformed file.
   */
  public static void transformFileWithXquery(
      String xqueryPath, String inputFilePath, String outputFile) throws ErrorHandler {
    try {
      Processor processor = new Processor(true);
      XQueryCompiler compiler = processor.newXQueryCompiler();

      XQueryExecutable executable = compiler.compile(new File(xqueryPath));

      XQueryEvaluator evaluator = executable.load();

      XdmNode inputXml = processor.newDocumentBuilder().build(new File(inputFilePath));

      evaluator.setSource(inputXml.asSource());

      Serializer serializer = processor.newSerializer(new File(outputFile));
      serializer.setOutputProperty(Serializer.Property.INDENT, "yes");
      evaluator.setDestination(serializer);

      evaluator.run();

      evaluator.close();

    } catch (SaxonApiException | IOException e) {
      throw new ErrorHandler(e);
    }
  }

  /**
   * A method that receives a xml and outputs another based on the xquery.
   *
   * @param xqueryPath the path to the xquery you'll use to transformFileWithXquery.
   * @param inputFilePath the file you wish to transformFileWithXquery.
   * @param outputFile the transformed file.
   * @param author of the book you wish to search.
   */
  public static void transformFileWithXquery(
      String xqueryPath, String inputFilePath, String outputFile, String author)
      throws ErrorHandler {
    try {
      Processor processor = new Processor(true);
      XQueryCompiler compiler = processor.newXQueryCompiler();

      XQueryExecutable executable = compiler.compile(new File(xqueryPath));

      XQueryEvaluator evaluator = executable.load();

      QName authorVariable = new QName("author");
      evaluator.setExternalVariable(authorVariable, new XdmAtomicValue(author));

      XdmNode inputXml = processor.newDocumentBuilder().build(new File(inputFilePath));

      evaluator.setSource(inputXml.asSource());

      Serializer serializer = processor.newSerializer(new File(outputFile));
      serializer.setOutputProperty(Serializer.Property.INDENT, "yes");
      evaluator.setDestination(serializer);

      evaluator.run();

      evaluator.close();

    } catch (SaxonApiException | IOException e) {
      throw new ErrorHandler(e);
    }
  }
}
