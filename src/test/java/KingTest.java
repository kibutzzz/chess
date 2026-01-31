import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

class KingTest extends PieceTestTemplate {

  static Stream<Arguments> getTestCases() {
    return Stream.of(whiteKingOneSquareMovements(), blackKingCornerMovements());
  }

  private static Arguments whiteKingOneSquareMovements() {
    return Arguments.of(
        pieceSupplier(Color.WHITE, 4, 4),
        List.of(
            new Movement(5, 4, true),
            new Movement(5, 5, true),
            new Movement(4, 5, true),
            new Movement(3, 5, true),
            new Movement(3, 4, true),
            new Movement(3, 3, true),
            new Movement(4, 3, true),
            new Movement(5, 3, true),
            new Movement(6, 4, true),
            new Movement(5, 3, true),
            new Movement(4, 6, false)));
  }

  private static Arguments blackKingCornerMovements() {
    return Arguments.of(
        pieceSupplier(Color.BLACK, 0, 0),
        List.of(
            new Movement(1, 0, true),
            new Movement(1, 1, true),
            new Movement(0, 1, true),
            new Movement(2, 2, false),
            new Movement(2, 1, false)));
  }

  private static Supplier<Piece> pieceSupplier(Color color, int rank, int file) {
    return () -> new King(color, rank, file, new Board());
  }
}
