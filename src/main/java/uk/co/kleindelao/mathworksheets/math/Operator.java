package uk.co.kleindelao.mathworksheets.math;

public enum Operator {
  PLUS("+"),
  MINUS("-"),
  TIMES("×"),
  DIVIDED_BY("÷"),
  TO_THE_POWER_OF("^");

  public final String textualRepresentation;

  Operator(final String textualRepresentation) {
    this.textualRepresentation = textualRepresentation;
  }
}
