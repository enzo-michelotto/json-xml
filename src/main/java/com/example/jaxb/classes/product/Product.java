package com.example.jaxb.classes.product;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** A class that provides Product objects. */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
  private String productCode;
  private String description;
  private BigDecimal price;
  private String category;

  /** A constructor for Product objects. */
  public Product() {
    super();
  }

  /**
   * Constructs a new Product object with the specified product code, description, price, and
   * category.
   *
   * @param productCode the product code associated with the product
   * @param description the description of the product
   * @param price the price of the product
   * @param category the category of the product
   */
  public Product(String productCode, String description, BigDecimal price, String category) {
    super();
    this.description = description;
    this.productCode = productCode;
    this.price = price;
    this.category = category;
  }

  public String getProductCode() {
    return productCode;
  }
}
