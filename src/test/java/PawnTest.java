import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class PawnTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        whitePawnValidMoves(),
        blackPawnValidMoves(),
        whitePawnInvalidMoves(),
        blackPawnInvalidMoves(),
        whitePawnValidCaptures(),
        blackPawnValidCaptures(),
        whitePawnInvalidCaptures(),
        blackPawnInvalidCaptures(),
        whitePawnBlockedByPiece(),
        blackPawnBlockedByPiece());
  }

  private static Arguments whitePawnValidMoves() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 1, 0),
        List.of(new Movement(2, 0, true), new Movement(3, 0, true)));
  }

  private static Arguments blackPawnValidMoves() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 6, 0),
        List.of(new Movement(5, 0, true), new Movement(4, 0, true)));
  }

  private static Arguments whitePawnInvalidMoves() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 1, 0),
        List.of(
            new Movement(1, 1, false),
            new Movement(1, 2, false),
            new Movement(0, 0, false),
            new Movement(4, 0, false)));
  }

  private static Arguments blackPawnInvalidMoves() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 6, 0),
        List.of(
            new Movement(6, 1, false),
            new Movement(6, 2, false),
            new Movement(7, 0, false),
            new Movement(3, 0, false)));
  }

  private static Arguments whitePawnValidCaptures() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 3, 3, List.of(new OtherPiece(4, 4, Color.BLACK))),
        List.of(new Movement(4, 4, true)));
  }

  private static Arguments blackPawnValidCaptures() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 4, 3, List.of(new OtherPiece(3, 4, Color.WHITE))),
        List.of(new Movement(3, 4, true)));
  }

  private static Arguments whitePawnInvalidCaptures() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 3, 3, List.of(new OtherPiece(4, 4, Color.WHITE))),
        List.of(new Movement(4, 4, false)));
  }

  private static Arguments blackPawnInvalidCaptures() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 4, 3, List.of(new OtherPiece(3, 4, Color.BLACK))),
        List.of(new Movement(3, 4, false)));
  }

  private static Arguments whitePawnBlockedByPiece() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 3, 3, List.of(new OtherPiece(4, 3, Color.BLACK))),
        List.of(new Movement(4, 3, false)));
  }

  private static Arguments blackPawnBlockedByPiece() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 4, 3, List.of(new OtherPiece(3, 3, Color.WHITE))),
        List.of(new Movement(3, 3, false)));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Pawn(color, initialRank, initialFile, new Board());
  }

  private static Supplier<Piece> pieceSupplier(
      Color color, int initialRank, int initialFile, List<OtherPiece> otherPieces) {
    return () -> {
      Board board = new Board();
      for (OtherPiece otherPiece : otherPieces) {
        new Pawn(otherPiece.color, otherPiece.rank, otherPiece.file, board);
      }
      return new Pawn(color, initialRank, initialFile, board);
    };
  }
}
