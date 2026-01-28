import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTest {

  public static final String INVALID_MOVE_MESSAGE = "Invalid move for the piece.";
  private Pawn cut;

  @BeforeEach
  void setup() {
    this.cut = new Pawn(Color.WHITE, 1, 0, new Board());
  }

  @Test
  void firstMoveForwardOneSquare() {
    whenMovingTo(2, 0);

    thenPositionIs(2, 0);
  }

  @Test
  void firstMoveForwardTwoSquares() {
    whenMovingTo(3, 0);

    thenPositionIs(3, 0);
  }

  @Test
  void secondMoveForwardOneSquare() {
    whenMovingTo(2, 0);
    whenMovingTo(3, 0);

    thenPositionIs(3, 0);
  }

  @Test
  void moveLeft() {
    final var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              whenMovingTo(1, 1);
            });

    assertEquals("Invalid move for the piece.", exception.getMessage());

    thenPositionIs(1, 0);
  }

  @Test
  void moveRight() {
    final var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              whenMovingTo(1, -1);
            });
    assertEquals(INVALID_MOVE_MESSAGE, exception.getMessage());
  }

  @Test
  void moveBackward() {
    final var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              whenMovingTo(0, 0);
            });
    assertEquals("Invalid move for the piece.", exception.getMessage());
  }

  @Test
  void moveMoreThanTwoSquaresForwardOnFirstMove() {
    final var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              whenMovingTo(4, 0);
            });
    assertEquals("Invalid move for the piece.", exception.getMessage());
  }

  private void whenMovingTo(int rank, int file) {
    cut.moveTo(rank, file);
  }

  private void thenPositionIs(int expectedRank, int expectedFile) {
    assertEquals(expectedRank, cut.getCurrentRank());
    assertEquals(expectedFile, cut.getCurrentFile());
  }
}
