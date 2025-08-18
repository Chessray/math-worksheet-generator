package uk.co.kleindelao.mathworksheets.math;

import static java.util.Objects.requireNonNull;
import static uk.co.kleindelao.mathworksheets.math.Operator.TO_THE_POWER_OF;

import org.openpdf.text.Cell;


public record Exercise<L extends Number, R extends Number>(int index, L firstOperand, R secondOperand, Operator operator) {
  public Exercise {
    requireNonNull(operator);
  }

  private String toPureOperationString() {
    if (operator == TO_THE_POWER_OF) {
      return String.format("%s%s", firstOperand, superscriptedSecondOperand());
    } else {
    return String.format("%s %s %s", firstOperand, operator.textualRepresentation, secondOperand);
    }
  }

  private String superscriptedSecondOperand() {
    final var superscriptBuilder = new StringBuilder();
    appendSuperscriptedMinusIfSecondOperandIsNegative(superscriptBuilder);
    appendSecondOperandSuperscriptedDigits(superscriptBuilder);
    return superscriptBuilder.toString();
  }

  private void appendSecondOperandSuperscriptedDigits(final StringBuilder superscriptBuilder) {
    for (char digit: String.valueOf(Math.abs(secondOperand.intValue())).toCharArray()) {
      superscriptBuilder.append(superscriptedChar(digit));
    }
  }

  private void appendSuperscriptedMinusIfSecondOperandIsNegative(final StringBuilder superscriptBuilder) {
    if (secondOperand.intValue() < 0) {
      superscriptBuilder.append('\u207b');
    }
  }

  private char superscriptedChar(final char digit) {
    return switch (digit) {
      case '1' -> '\u00b9';
      case '2' -> '\u00b2';
      case '3' -> '\u00b3';
      default -> (char) ('\u2070' + digit - '0');
    };
  }

  @Override
  public String toString() {
    System.out.printf("%s.) %s %s %s%n", index, firstOperand, operator.textualRepresentation,
        secondOperand);
    return String.format("%d.) %s\n\n\n\n", index, toPureOperationString());
  }

  public Cell toCell() {
    return new Cell(toString());
  }
}
