package uk.co.kleindelao.mathworksheets.math;

import java.util.List;
import uk.co.kleindelao.mathworksheets.components.ExerciseTable;

public record ExerciseSheet(List<Exercise> exercises) {
  public ExerciseTable toTable() {
    final var table = new ExerciseTable();
    exercises.stream().map(Exercise::toCell).forEach(table::addCell);
    return table;
  }
}
