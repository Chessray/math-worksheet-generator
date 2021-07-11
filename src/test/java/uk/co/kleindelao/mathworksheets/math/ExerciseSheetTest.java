package uk.co.kleindelao.mathworksheets.math;

import static org.assertj.core.api.BDDAssertions.then;
import static uk.co.kleindelao.mathworksheets.math.Operator.MINUS;
import static uk.co.kleindelao.mathworksheets.math.Operator.PLUS;

import java.util.ArrayList;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

class ExerciseSheetTest {
  @Test
  void shouldGenerateExerciseTable() {
    // Given
    final var exerciseList = new ArrayList<Exercise>();
    for (int i = 1; i <= 40; i++) {
      exerciseList.add(new Exercise(i, operand(), operand(), RandomUtils.nextBoolean() ? PLUS :
          MINUS));
    }
    final var sheet = new ExerciseSheet(exerciseList);

    // When
    final var table = sheet.toTable();

    // Then
    then(table).isNotNull();
  }

  private static int operand() {
    return RandomUtils.nextInt(0, 1999) - 999;
  }
}