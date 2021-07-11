package uk.co.kleindelao.mathworksheets.components;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class ExerciseTableTest {
  private ExerciseTable table = new ExerciseTable();

  @Test
  void shouldHaveFourColumns() {
    then(table.getColumns()).isEqualTo(4);
  }
}