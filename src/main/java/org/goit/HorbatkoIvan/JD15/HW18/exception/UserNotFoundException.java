package org.goit.HorbatkoIvan.JD15.HW18.exception;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
