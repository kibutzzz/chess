import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class RookTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        Arguments.of(
            pieceSupplier(Color.WHITE, 3, 3),
            List.of(
                new Movement(5, 3, true),
                new Movement(6, 3, true),
                new Movement(6, 0, true),
                new Movement(0, 0, true),
                new Movement(2, 4, false),
                new Movement(4, 5, false))),
        Arguments.of(
            pieceSupplier(Color.BLACK, 2, 4),
            List.of(
                new Movement(2, 7, true),
                new Movement(5, 7, true),
                new Movement(5, 2, true),
                new Movement(0, 2, true),
                new Movement(3, 3, false),
                new Movement(4, 5, false))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 4, 4, 4, 6, Color.WHITE),
            List.of(new Movement(4, 6, false))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 4, 4, 4, 6, Color.BLACK), List.of(new Movement(4, 6, true))),
        Arguments.of(
            pieceSupplier(Color.BLACK, 5, 5, 2, 5, Color.BLACK),
            List.of(new Movement(2, 5, false))),
        Arguments.of(
            pieceSupplier(Color.BLACK, 5, 5, 2, 5, Color.WHITE), List.of(new Movement(2, 5, true))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 3, 3, 3, 5, Color.WHITE),
            List.of(new Movement(3, 6, false))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 3, 3, 3, 5, Color.BLACK),
            List.of(new Movement(3, 6, false))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 3, 3, 5, 3, Color.WHITE),
            List.of(new Movement(6, 3, false))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 3, 3, 5, 3, Color.BLACK),
            List.of(new Movement(6, 3, false))));
  }

  private static Supplier<Piece> pieceSupplier(
      Color color,
      int initialRank,
      int initialFile,
      int otherPieceRank,
      int otherPieceFile,
      Color otherPieceColor) {
    return () -> {
      final var board = new Board();
      final var piece = new Pawn(otherPieceColor, otherPieceRank, otherPieceFile, board);
      board.placePiece(piece);
      return new Rook(color, initialRank, initialFile, board);
    };
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Rook(color, initialRank, initialFile, new Board());
  }
}
