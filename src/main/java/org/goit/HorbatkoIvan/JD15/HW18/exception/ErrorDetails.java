package org.goit.HorbatkoIvan.JD15.HW18.exception;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("checkstyle:MissingJavadocType")
@Getter
@Setter
public class ErrorDetails {

  private String title;
  private String message;
  private String details;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public ErrorDetails(String title, String message, String details) {
    this.title = title;
    this.message = message;
    this.details = details;
  }

}
