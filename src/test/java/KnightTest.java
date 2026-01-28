import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class KnightTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(6,3, true))),
        Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(6,5, true))),
        Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(5,6, true))),
        Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(3,6, true))),
        Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(2,5, true))),
        Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(2,3, true))),
        Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(3,2, true))),
        Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(5,2, true))),
        Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(4,4, false))),
        Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(4,5, false))),
        Arguments.of(pieceSupplier(Color.WHITE, 4, 4), List.of(new Movement(5,4, false))),
        Arguments.of(pieceSupplier(Color.BLACK, 4, 4), List.of(new Movement(4,3, false))));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int initialRank, int initialFile) {
    return () -> new Knight(color, initialFile, initialRank, new Board());
  }
}
