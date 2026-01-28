import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.function.Supplier;

import java.util.stream.Stream;
class BishopTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        Arguments.of(
            pieceSupplier(Color.WHITE, 3, 3),
            List.of(
                new Movement(5, 5, true),
                new Movement(6, 6, true),
                new Movement(0, 0, true),
                new Movement(1, 1, true),
                new Movement(2, 4, false),
                new Movement(4, 5, false))),
        Arguments.of(
            pieceSupplier(Color.BLACK, 2, 4),
            List.of(
                new Movement(5, 7, true),
                new Movement(0, 2, true),
                new Movement(1, 3, true),
                new Movement(4, 6, true),
                new Movement(3, 3, false),
                new Movement(4, 5, false))));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int rank, int file) {
    return () -> new Bishop(color, rank, file, new Board());
  }

}