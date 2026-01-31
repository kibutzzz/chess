import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class KnightTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        whiteKnightUpRightLongMove(),
        blackKnightUpRightShortMove(),
        whiteKnightUpLeftMove(),
        blackKnightUpLeftMove(),
        whiteKnightDownLeftMove(),
        blackKnightDownLeftMove(),
        whiteKnightDownRightMove(),
        blackKnightDownRightMove(),
        knightCannotStayInPlace(),
        knightCannotMoveOneSquareHorizontally(),
        knightCannotMoveOneSquareVertically(),
        knightCannotMoveOneSquareBackward());
  }

  private static Arguments whiteKnightUpRightLongMove() {
    return Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(6, 3, true)));
  }

  private static Arguments blackKnightUpRightShortMove() {
    return Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(6, 5, true)));
  }

  private static Arguments whiteKnightUpLeftMove() {
    return Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(5, 6, true)));
  }

  private static Arguments blackKnightUpLeftMove() {
    return Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(3, 6, true)));
  }

  private static Arguments whiteKnightDownLeftMove() {
    return Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(2, 5, true)));
  }

  private static Arguments blackKnightDownLeftMove() {
    return Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(2, 3, true)));
  }

  private static Arguments whiteKnightDownRightMove() {
    return Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(3, 2, true)));
  }

  private static Arguments blackKnightDownRightMove() {
    return Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(5, 2, true)));
  }

  private static Arguments knightCannotStayInPlace() {
    return Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(4, 4, false)));
  }

  private static Arguments knightCannotMoveOneSquareHorizontally() {
    return Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(4, 5, false)));
  }

  private static Arguments knightCannotMoveOneSquareVertically() {
    return Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(5, 4, false)));
  }

  private static Arguments knightCannotMoveOneSquareBackward() {
    return Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(4, 3, false)));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Knight(color, initialFile, initialRank, new Board());
  }
}
