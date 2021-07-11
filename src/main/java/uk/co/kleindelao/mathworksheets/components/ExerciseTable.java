package uk.co.kleindelao.mathworksheets.components;

import com.lowagie.text.Table;

public final class ExerciseTable extends Table {
  public ExerciseTable() {
    super(4);
    setBorder(NO_BORDER);
    getDefaultCell().setBorder(NO_BORDER);
  }
}
