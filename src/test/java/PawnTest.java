import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class PawnTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        whitePawnSingleStepFromStart(),
        whitePawnDoubleStepFromStart(),
        whitePawnConsecutiveMoves(),
        whitePawnCannotMoveSideways(),
        whitePawnCannotMoveTwoSquaresSideways(),
        whitePawnCannotMoveBackward(),
        whitePawnCannotMoveThreeSquares(),
        whitePawnCannotMoveDoubleAfterFirstMove(),
        blackPawnSingleStepFromStart(),
        blackPawnDoubleStepFromStart(),
        blackPawnConsecutiveMoves(),
        blackPawnCannotMoveSideways(),
        blackPawnCannotMoveTwoSquaresSideways(),
        blackPawnCannotMoveBackward(),
        blackPawnCannotMoveThreeSquares(),
        blackPawnCannotMoveDoubleAfterFirstMove());
  }

  private static Arguments whitePawnSingleStepFromStart() {
    return Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(2, 0, true)));
  }

  private static Arguments whitePawnDoubleStepFromStart() {
    return Arguments.of(pieceSupplier(Color.WHITE, 2, 0), List.of(new Movement(4, 0, true)));
  }

  private static Arguments whitePawnConsecutiveMoves() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 1, 0),
        List.of(new Movement(2, 0, true), new Movement(3, 0, true)));
  }

  private static Arguments whitePawnCannotMoveSideways() {
    return Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(1, 1, false)));
  }

  private static Arguments whitePawnCannotMoveTwoSquaresSideways() {
    return Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(1, 2, false)));
  }

  private static Arguments whitePawnCannotMoveBackward() {
    return Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(0, 0, false)));
  }

  private static Arguments whitePawnCannotMoveThreeSquares() {
    return Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(4, 0, false)));
  }

  private static Arguments whitePawnCannotMoveDoubleAfterFirstMove() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 1, 0),
        List.of(new Movement(3, 0, true), new Movement(5, 0, false)));
  }

  private static Arguments blackPawnSingleStepFromStart() {
    return Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(5, 0, true)));
  }

  private static Arguments blackPawnDoubleStepFromStart() {
    return Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(4, 0, true)));
  }

  private static Arguments blackPawnConsecutiveMoves() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 6, 0),
        List.of(new Movement(5, 0, true), new Movement(4, 0, true)));
  }

  private static Arguments blackPawnCannotMoveSideways() {
    return Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(6, 1, false)));
  }

  private static Arguments blackPawnCannotMoveTwoSquaresSideways() {
    return Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(6, 2, false)));
  }

  private static Arguments blackPawnCannotMoveBackward() {
    return Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(7, 0, false)));
  }

  private static Arguments blackPawnCannotMoveThreeSquares() {
    return Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(3, 0, false)));
  }

  private static Arguments blackPawnCannotMoveDoubleAfterFirstMove() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 6, 0),
        List.of(new Movement(4, 0, true), new Movement(2, 0, false)));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Pawn(color, initialRank, initialFile, new Board());
  }
}
