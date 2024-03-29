package org.goit.HorbatkoIvan.JD15.HW18.exception;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class NoteNotFoundException extends RuntimeException {

  public NoteNotFoundException(String message) {
    super(message);
  }
}
