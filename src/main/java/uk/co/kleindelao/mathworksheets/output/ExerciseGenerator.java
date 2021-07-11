package uk.co.kleindelao.mathworksheets.output;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static uk.co.kleindelao.mathworksheets.math.Operator.DIVIDED_BY;
import static uk.co.kleindelao.mathworksheets.math.Operator.TO_THE_POWER_OF;

import uk.co.kleindelao.mathworksheets.math.Exercise;
import uk.co.kleindelao.mathworksheets.math.Operator;

public class ExerciseGenerator {
  private static int operand() {
    return nextInt(0, 1999) - 999;
  }

  private static int singleDigit() {
    return nextInt(0, 9);
  }

  private static int operand(final Operator operator) {
    return operator == TO_THE_POWER_OF ? singleDigit() : operand();
  }

  public Exercise create(final int index, final Operator operator) {
    var firstOperand = operand(operator);
    var secondOperand = operand(operator);
    if (operator == DIVIDED_BY) {
      firstOperand *= secondOperand;
    }
    return new Exercise(index, firstOperand, secondOperand, operator);
  }
}
