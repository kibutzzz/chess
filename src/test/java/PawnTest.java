import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class PawnTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(2, 0, true))),
        Arguments.of(pieceSupplier(Color.WHITE, 2, 0), List.of(new Movement(4, 0, true))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 1, 0),
            List.of(new Movement(2, 0, true), new Movement(3, 0, true))),
        Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(1, 1, false))),
        Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(1, -1, false))),
        Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(0, 0, false))),
        Arguments.of(pieceSupplier(Color.WHITE, 1, 0), List.of(new Movement(4, 0, false))),
        Arguments.of(
            pieceSupplier(Color.WHITE, 1, 0),
            List.of(new Movement(3, 0, true), new Movement(5, 0, false))),
        Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(5, 0, true))),
        Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(4, 0, true))),
        Arguments.of(
            pieceSupplier(Color.BLACK, 6, 0),
            List.of(new Movement(5, 0, true), new Movement(4, 0, true))),
        Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(6, 1, false))),
        Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(6, -1, false))),
        Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(7, 0, false))),
        Arguments.of(pieceSupplier(Color.BLACK, 6, 0), List.of(new Movement(3, 0, false))),
        Arguments.of(
            pieceSupplier(Color.BLACK, 6, 0),
            List.of(new Movement(4, 0, true), new Movement(2, 0, false))));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Pawn(color, initialRank, initialFile, new Board());
  }
}
