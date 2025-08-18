package uk.co.kleindelao.mathworksheets.math;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.iterator;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static uk.co.kleindelao.mathworksheets.math.Operator.MINUS;
import static uk.co.kleindelao.mathworksheets.math.Operator.PLUS;
import static uk.co.kleindelao.mathworksheets.math.Operator.TO_THE_POWER_OF;

import org.apache.commons.lang3.math.Fraction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openpdf.text.Cell;
import org.openpdf.text.Element;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;

public class ExerciseTest {
  @Nested
  class Constructor {
    @Test
    void shouldThrowNullPointerExceptionIfOperatorIsNull() {
      thenThrownBy(() -> new Exercise<>(12, 23, 42, null)).isInstanceOf(NullPointerException.class);
    }
  }

  @Nested
  class ToString {
    @ParameterizedTest
    @EnumSource(names = "TO_THE_POWER_OF", mode = EXCLUDE)
    void shouldReturnSingleParagraphContainingConcatenatedElements(final Operator operator) {
      // Given
      final var exercise = new Exercise<>(10, 23, 42, operator);

      // When
      final var exerciseString = exercise.toString();

      // Then
      then(exerciseString).isEqualTo("10.) 23 " + operator.textualRepresentation + " 42\n\n\n\n");
    }

    @Nested
    class ToThePowerOf {
      @Test
      void shouldReturnSecondOperandAsSuperscript() {
        // Given
        final var exercise = new Exercise<>(2, 3, -134, TO_THE_POWER_OF);

        // When
        final var exerciseString = exercise.toString();

        // Then
        then(exerciseString).isEqualTo("2.) 3\u207b\u00b9\u00b3\u2074\n\n\n\n");
      }

      @Test
      void shouldReturnSixAsSuperscript() {
        // Given
        final var exercise = new Exercise<>(1, 2, 6, TO_THE_POWER_OF);

        // When
        final var exerciseString = exercise.toString();

        // Then
        then(exerciseString).isEqualTo("1.) 2\u2076\n\n\n\n");
      }
    }
  }

  @Nested
  class ToCell {
    @Test
    void shouldReturnCellWithSingleParagraphContainingConcatenatedElements() {
      // Given
      final var exercise = new Exercise<>(12, 23, 42, PLUS);

      // When
      final var exerciseCell = exercise.toCell();

      // Then
      then(exerciseCell)
          .isNotNull()
          .extracting(Cell::getElements)
          .asInstanceOf(iterator(Element.class))
          .toIterable()
          .hasSize(1)
          .allSatisfy(
              element ->
                  then(element)
                      .isInstanceOf(Paragraph.class)
                      .asInstanceOf(type(Paragraph.class))
                      .extracting(Phrase::getContent)
                      .isEqualTo("12.) 23 + 42\n\n\n\n"));
    }

    @Test
    void shouldReturnCellWithReporesentationForFractions() {
      // Given
      final var exercise = new Exercise<>(2, Fraction.getFraction(1, 2),
          Fraction.getFraction(5, 3), MINUS);

      // When
      final var exerciseCell = exercise.toCell();

      // Then
      then(exerciseCell)
          .isNotNull()
          .extracting(Cell::getElements)
          .asInstanceOf(iterator(Element.class))
          .toIterable()
          .hasSize(1)
          .allSatisfy(
              element ->
                  then(element)
                      .isInstanceOf(Paragraph.class)
                      .asInstanceOf(type(Paragraph.class))
                      .extracting(Phrase::getContent)
                      .isEqualTo("2.) 1/2 - 5/3\n\n\n\n"));
    }
  }
}
