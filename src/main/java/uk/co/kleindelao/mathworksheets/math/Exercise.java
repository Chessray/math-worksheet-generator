package uk.co.kleindelao.mathworksheets.math;

import static java.util.Objects.requireNonNull;
import static uk.co.kleindelao.mathworksheets.math.Operator.TO_THE_POWER_OF;

import com.lowagie.text.Cell;

public record Exercise(int index, int firstOperand, int secondOperand, Operator operator) {
  public Exercise {
    requireNonNull(operator);
  }

  private String toPureOperationString() {
    if (operator == TO_THE_POWER_OF) {
      return String.format("%d%s", firstOperand,superscripted(secondOperand));
    } else {
    return String.format("%d %s %d", firstOperand, operator.textualRepresentation, secondOperand);
    }
  }

  private String superscripted(final int operand) {
    final var superscriptBuilder = new StringBuilder();
    appendSuperscriptedMinusIfRequired(superscriptBuilder, operand);
    appendSuperscriptedDigits(superscriptBuilder, operand);
    return superscriptBuilder.toString();
  }

  private void appendSuperscriptedDigits(final StringBuilder superscriptBuilder,
                                         final int operand) {
    for (char digit: String.valueOf(Math.abs(operand)).toCharArray()) {
      superscriptBuilder.append(superscriptedChar(digit));
    }
  }

  private void appendSuperscriptedMinusIfRequired(final StringBuilder superscriptBuilder,
                                                  final int operand) {
    if (operand < 0) {
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
    System.out.printf("%d.) %d %s %d%n", index, firstOperand, operator.textualRepresentation,
        secondOperand);
    return String.format("%d.) %s\n\n\n\n", index, toPureOperationString());
  }

  public Cell toCell() {
    return new Cell(toString());
  }
}
