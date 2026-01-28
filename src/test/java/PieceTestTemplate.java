import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.Value;

abstract class PieceTestTemplate {

  private static final String INVALID_MOVE_MESSAGE = "Invalid move for the piece.";
  protected Piece cut;

  @Value
  static class Position {
    int rank;
    int file;
  }

  @Value
  static class Movement {
    Position target;
    boolean expectedToBeValid;

    Movement(int rank, int file, boolean expectedToBeValid) {
      this.target = new Position(rank, file);
      this.expectedToBeValid = expectedToBeValid;
    }
  }

  @ParameterizedTest
  @MethodSource("getTestCases")
  void moves(Supplier<Piece> pieceSupplier, List<Movement> movements) {
    givenPiece(pieceSupplier.get());
    int currentRank = cut.getCurrentRank();
    int currentFile = cut.getCurrentFile();

    for (Movement movement : movements) {
      if (movement.expectedToBeValid) {
        whenMovingTo(movement.target.rank, movement.target.file);
        thenPositionIs(movement.target.rank, movement.target.file);
        currentRank = movement.target.rank;
        currentFile = movement.target.file;
      } else {
        final var exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> whenMovingTo(movement.target.rank, movement.target.file));
        assertEquals(INVALID_MOVE_MESSAGE, exception.getMessage());
        thenPositionIs(currentRank, currentFile);
      }
    }
  }

  private void givenPiece(Piece piece) {
    this.cut = piece;
  }

  private void whenMovingTo(int rank, int file) {
    cut.moveTo(rank, file);
  }

  private void thenPositionIs(int expectedRank, int expectedFile) {
    assertEquals(expectedRank, cut.getCurrentRank());
    assertEquals(expectedFile, cut.getCurrentFile());
  }
}
