package uk.co.kleindelao.mathworksheets.output;

import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static uk.co.kleindelao.mathworksheets.math.Operator.TO_THE_POWER_OF;

import org.apache.commons.lang3.math.Fraction;
import uk.co.kleindelao.mathworksheets.math.Exercise;
import uk.co.kleindelao.mathworksheets.math.Operator;

public class ExerciseGenerator {
  private static <O extends Number> O operand(final boolean fractionPreferred) {
    return (O) (fractionPreferred ? fractionOperand() : integerOperand());
  }

  private static Fraction fractionOperand() {
    return Fraction.getFraction(singleDigit() + 1, singleDigit() + 1);
  }

  private static Integer integerOperand() {
    return nextInt(0, 1999) - 999;
  }

  private static Integer singleDigit() {
    return nextInt(0, 9);
  }

  private static <O extends Number> O operand(final Operator operator, final boolean fractionPreferred) {
    return (O) (operator == TO_THE_POWER_OF ? singleDigit() : operand(fractionPreferred));
  }

  public <L extends Number, R extends Number> Exercise<L, R> create(
      final int index, final Operator operator) {
    final var isFraction = nextBoolean();
    return new Exercise<>(
        index, operand(operator, isFraction), operand(operator, isFraction), operator);
  }
}
