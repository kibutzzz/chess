import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.function.Supplier;

import java.util.stream.Stream;

class QueenTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(
        Arguments.of(
            pieceSupplier(Color.WHITE, 4, 4),
            List.of(
                new Movement(4, 7, true),
                new Movement(7, 7, true),
                new Movement(8, 8,true),
                new Movement(1, 1, true),
                new Movement(5, 6, false),
                new Movement(3, 5, false)
            )),
        Arguments.of(
            pieceSupplier(Color.BLACK, 0, 0),
            List.of(
                new Movement(0, 5, true),
                new Movement(5, 5, true),
                new Movement(5, 0, true),
                new Movement(3, 2, true),
                new Movement(2, 4, false),
                new Movement(1, 3, false)
            ))
    );
  }

  private static Supplier<Piece> pieceSupplier(Color color, int rank, int file) {
    return () -> new Queen(color, rank, file, new Board());
  }

}