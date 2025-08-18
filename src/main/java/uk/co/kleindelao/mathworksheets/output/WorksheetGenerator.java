package uk.co.kleindelao.mathworksheets.output;

import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.openpdf.text.Document;
import org.openpdf.text.pdf.PdfWriter;
import uk.co.kleindelao.mathworksheets.math.Exercise;
import uk.co.kleindelao.mathworksheets.math.ExerciseSheet;
import uk.co.kleindelao.mathworksheets.math.Operator;

public class WorksheetGenerator {
  private final ExerciseGenerator exerciseGenerator;

  public WorksheetGenerator() {
    exerciseGenerator = new ExerciseGenerator();
  }

  public void run(final Path outputPath) throws IOException {
    final var document = new Document();
    Files.createDirectories(outputPath.getParent());
    final var pdfWriter =
        PdfWriter.getInstance(document, new FileOutputStream(outputPath.toFile()));
    document.open();
    document.add(generateSheet().toTable());
    document.close();
    pdfWriter.close();
  }

  private ExerciseSheet generateSheet() {
    final var exercises = new ArrayList<Exercise>();
    final var operators = Operator.values();
    for (int i = 1; i <= 40; i++) {
      exercises.add(nextExercise(i, operators[nextInt(0, operators.length)]));
    }
    return new ExerciseSheet(List.copyOf(exercises));
  }

  private Exercise nextExercise(final int index, final Operator operator) {
    return exerciseGenerator.create(index, operator);
  }

  public static void main(String[] args) throws IOException {
    new WorksheetGenerator().run(new File("./worksheet.pdf").toPath());
  }
}
