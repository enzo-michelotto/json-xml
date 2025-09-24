package com.example.json.classes;

import lombok.Getter;
import lombok.Setter;

/** A class that is for representing book objects. */
@Getter
@Setter
public class Book {
  /**
   * A constructor for the Book class.
   *
   * @param author the author of the book.
   * @param title of the book.
   * @param price of the book.
   */
  public Book(String author, String title, Double price) {
    this.author = author;
    this.title = title;
    this.price = price;
  }

  public Book() {}

  private String author;
  private String title;
  private Double price;

  @Override
  public String toString() {
    return "Book{"
        + "author='"
        + author
        + '\''
        + ", title='"
        + title
        + '\''
        + ", price ="
        + price
        + "}";
  }
}
