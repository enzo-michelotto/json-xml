package com.example.json.classes;

/** A class to represent an object that contains library objects. */
public class LibraryContainer {
  Library library;

  public void setLibrary(Library library) {
    this.library = new Library(library);
  }

  public Library getLibrary() {
    return new Library(library);
  }

  @Override
  public String toString() {

    return library.getPublisher().toString();
  }
}
