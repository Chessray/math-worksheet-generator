package uk.co.kleindelao.mathworksheets.output;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.BDDAssertions.then;
import static uk.co.kleindelao.mathworksheets.math.Operator.TO_THE_POWER_OF;

import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import uk.co.kleindelao.mathworksheets.math.Exercise;
import uk.co.kleindelao.mathworksheets.math.Operator;

class ExerciseGeneratorTest {
  final ExerciseGenerator generator = new ExerciseGenerator();

  @ParameterizedTest
  @EnumSource
  void shouldCreateExerciseWithIndexAndOperator(final Operator operator) {
    // Given
    final var index = nextInt();

    // When
    final var exercise = generator.create(index, operator);

    // Then
    then(exercise).isNotNull().extracting(Exercise::index).isEqualTo(index);
    then(exercise).extracting(Exercise::operator).isEqualTo(operator);
  }

  @Nested
  class ToThePowerOf {
    @Test
    void shouldCreateOnlySingleDigitNaturalOperands() {
      // Given
      final var operator = TO_THE_POWER_OF;

      // When
      final Exercise<Integer, Integer> exercise = generator.create(nextInt(), operator);

      // Then
      final var singleDigitNaturals = Range.between(0, 9);
      then(exercise.firstOperand())
          .matches(singleDigitNaturals::contains, "is single natural digit");
      then(exercise.secondOperand())
          .matches(singleDigitNaturals::contains, "is single natural digit");
    }
  }
}
