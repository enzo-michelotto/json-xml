package com.example.errorhandler;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXParseException;

/** A class that receives exceptions and logs based on which has happened. */
@Slf4j
public class ErrorHandler extends RuntimeException {

  /**
   * Logs the exception.
   *
   * @param e the given exception.
   */
  public ErrorHandler(Exception e) {

    if (e instanceof SAXParseException) {
      log.info("Exception of class: {} with problem on: {}", e.getClass(), e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    } else if (e instanceof IOException) {
      log.info("Exception of class: {} with problem on: {}", e.getClass(), e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    } else {
      log.info("Exception of class: {} with problem on: {}", e.getClass(), e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
