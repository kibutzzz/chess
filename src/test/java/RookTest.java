import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class RookTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        basicMovementWhiteRook(),
        basicMovementBlackRook(),
        sameColorBlocking(),
        differentColorCapture());
  }

  private static Arguments basicMovementWhiteRook() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 3, 3, List.of()),
        List.of(
            new Movement(5, 3, true),
            new Movement(6, 3, true),
            new Movement(6, 0, true),
            new Movement(0, 0, true),
            new Movement(2, 4, false),
            new Movement(4, 5, false)));
  }

  private static Arguments basicMovementBlackRook() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 2, 4, List.of()),
        List.of(
            new Movement(2, 7, true),
            new Movement(5, 7, true),
            new Movement(5, 2, true),
            new Movement(0, 2, true),
            new Movement(3, 3, false),
            new Movement(4, 5, false)));
  }

  private static Arguments sameColorBlocking() {
    return Arguments.of(
        pieceSupplier(
            Color.WHITE,
            4,
            4,
            List.of(new OtherPiece(4, 6, Color.WHITE), new OtherPiece(6, 4, Color.WHITE))),
        List.of(
            new Movement(4, 6, false),
            new Movement(4, 7, false),
            new Movement(6, 4, false),
            new Movement(7, 4, false),
            new Movement(4, 2, true),
            new Movement(4, 0, true)));
  }

  private static Arguments differentColorCapture() {
    return Arguments.of(
        pieceSupplier(
            Color.BLACK,
            5,
            5,
            List.of(new OtherPiece(5, 3, Color.WHITE), new OtherPiece(3, 5, Color.WHITE))),
        List.of(
            new Movement(5, 2, false),
            new Movement(5, 3, true),
            new Movement(5, 2, true),
            new Movement(3, 2, true),
            new Movement(3, 7, false),
            new Movement(3, 5, true),
            new Movement(3, 7, true),
            new Movement(1, 7, true)));
  }

  private static Supplier<Piece> pieceSupplier(
      Color rookColor, int rookRank, int rookFile, List<OtherPiece> otherPieces) {
    return () -> {
      final var board = new Board();

      for (OtherPiece otherPiece : otherPieces) {
        new Pawn(otherPiece.color, otherPiece.rank, otherPiece.file, board);
      }

      return new Rook(rookColor, rookRank, rookFile, board);
    };
  }
}
