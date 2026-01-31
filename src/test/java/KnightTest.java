import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class KnightTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        whiteKnightValidMoves(),
        blackKnightValidMoves(),
        whiteKnightInvalidMoves(),
        blackKnightInvalidMoves(),
        whiteKnightValidCaptures(),
        blackKnightValidCaptures(),
        whiteKnightInvalidCaptures(),
        blackKnightInvalidCaptures());
  }

  private static Arguments whiteKnightValidMoves() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 3, 3),
        List.of(
            new Movement(5, 2, true),
            new Movement(3, 3, true),
            new Movement(5, 4, true),
            new Movement(3, 3, true),
            new Movement(4, 5, true),
            new Movement(3, 3, true),
            new Movement(2, 5, true),
            new Movement(3, 3, true),
            new Movement(1, 4, true),
            new Movement(3, 3, true),
            new Movement(1, 2, true),
            new Movement(3, 3, true),
            new Movement(2, 1, true),
            new Movement(3, 3, true),
            new Movement(4, 1, true),
            new Movement(3, 3, true)));
  }

  private static Arguments blackKnightValidMoves() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 5, 5),
        List.of(
            new Movement(7, 4, true),
            new Movement(5, 5, true),
            new Movement(7, 6, true),
            new Movement(5, 5, true),
            new Movement(6, 7, true),
            new Movement(5, 5, true),
            new Movement(4, 7, true),
            new Movement(5, 5, true),
            new Movement(3, 6, true),
            new Movement(5, 5, true),
            new Movement(3, 4, true),
            new Movement(5, 5, true),
            new Movement(4, 3, true),
            new Movement(5, 5, true),
            new Movement(6, 3, true),
            new Movement(5, 5, true)));
  }

  private static Arguments whiteKnightInvalidMoves() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 2, 2),
        List.of(
            new Movement(2, 2, false),
            new Movement(2, 3, false),
            new Movement(3, 2, false),
            new Movement(1, 1, false),
            new Movement(4, 4, false)));
  }

  private static Arguments blackKnightInvalidMoves() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 6, 1),
        List.of(
            new Movement(6, 1, false),
            new Movement(6, 2, false),
            new Movement(7, 1, false),
            new Movement(5, 0, false),
            new Movement(3, 3, false)));
  }

  private static Arguments whiteKnightValidCaptures() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 3, 3, List.of(new OtherPiece(5, 2, Color.BLACK))),
        List.of(new Movement(5, 2, true)));
  }

  private static Arguments blackKnightValidCaptures() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 4, 4, List.of(new OtherPiece(6, 3, Color.WHITE))),
        List.of(new Movement(6, 3, true)));
  }

  private static Arguments whiteKnightInvalidCaptures() {
    return Arguments.of(
        pieceSupplier(
            Color.WHITE,
            4,
            4,
            List.of(
                new OtherPiece(6, 3, Color.WHITE),
                new OtherPiece(6, 5, Color.WHITE),
                new OtherPiece(2, 3, Color.WHITE),
                new OtherPiece(2, 5, Color.WHITE))),
        List.of(
            new Movement(6, 3, false),
            new Movement(6, 5, false),
            new Movement(2, 3, false),
            new Movement(2, 5, false)));
  }

  private static Arguments blackKnightInvalidCaptures() {
    return Arguments.of(
        pieceSupplier(
            Color.BLACK,
            3,
            3,
            List.of(
                new OtherPiece(5, 2, Color.BLACK),
                new OtherPiece(5, 4, Color.BLACK),
                new OtherPiece(1, 2, Color.BLACK),
                new OtherPiece(1, 4, Color.BLACK))),
        List.of(
            new Movement(5, 2, false),
            new Movement(5, 4, false),
            new Movement(1, 2, false),
            new Movement(1, 4, false)));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Knight(color, initialFile, initialRank, new Board());
  }

  private static Supplier<Piece> pieceSupplier(
      Color color, int initialRank, int initialFile, List<OtherPiece> otherPieces) {
    return () -> {
      Board board = new Board();
      for (OtherPiece otherPiece : otherPieces) {
        new Pawn(otherPiece.color, otherPiece.rank, otherPiece.file, board);
      }
      return new Knight(color, initialFile, initialRank, board);
    };
  }
}
