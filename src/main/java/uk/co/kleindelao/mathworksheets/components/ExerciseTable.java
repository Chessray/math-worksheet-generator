package uk.co.kleindelao.mathworksheets.components;

import org.openpdf.text.Table;

public final class ExerciseTable extends Table {
  public ExerciseTable() {
    super(4);
    setBorder(NO_BORDER);
    getDefaultCell().setBorder(NO_BORDER);
  }
}
