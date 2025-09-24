package com.example.json.classes;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

/** A class for representing library objects. */
@Setter
public class Library {
  private List<Publisher> publisher = new ArrayList<>();

  public Library() {}

  public Library(Library library) {
    this.publisher = List.copyOf(library.getPublisher());
  }

  public List<Publisher> getPublisher() {
    return List.copyOf(publisher);
  }

  public void setPublisher(List<Publisher> publishers) {
    this.publisher = List.copyOf(publishers);
  }
}
