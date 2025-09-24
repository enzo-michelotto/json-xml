package com.example.json.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/** A class that contains publisher objects. */
public class Publisher {

  @JsonProperty("@name")
  private String name;

  @JsonProperty("book")
  private List<Book> book = new ArrayList<>();

  public void setName(String name) {
    this.name = name;
  }

  public void setBook(List<Book> book) {
    this.book = List.copyOf(book);
  }

  public String getName() {
    return name;
  }

  public List<Book> getBook() {
    return List.copyOf(book);
  }

  @Override
  public String toString() {
    return "Publisher{" + "name='" + name + '\'' + ", books=" + book + '}';
  }
}
