import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepresentationCalculator {
  private static final String BLACK_TEXT = "\u001B[30m";
  private static final String DARK_SQUARE_BG = "\u001B[42m";
  private static final String WHITE_SQUARE_BG = "\u001B[47m";
  private static final String ANSI_RESET = "\u001B[0m";

  private static final String SQUARE_TEMPLATE = "%s%s%s %s";

  private final int ranksSize;
  private final int filesSize;
  private final Piece[][] board;

  public String calculate() {
    final var sb = new StringBuilder();
    for (int rank = ranksSize - 1; rank >= 0; rank--) {
      sb.append(rank + 1).append(" ");
      for (int file = 0; file < filesSize; file++) {
        final var piece = board[rank][file];
        final var isDarkSquare = (rank + file) % 2 == 0;

        sb.append(getSquareString(piece, isDarkSquare));
      }
      sb.append("\n");
    }
    sb.append("   a  b  c  d  e  f  g  h\n");
    return sb.toString();
  }

  private String getSquareString(final Piece piece, final boolean isDarkSquare) {
    if (piece == null) {
      return SQUARE_TEMPLATE.formatted(
          isDarkSquare ? DARK_SQUARE_BG : WHITE_SQUARE_BG, " ", " ", ANSI_RESET);
    }
    final var pieceColor = piece.getColor();
    final var pieceSymbol = piece.getSymbol();
    final var background = isDarkSquare ? DARK_SQUARE_BG : WHITE_SQUARE_BG;
    final var colorCode = pieceColor == Color.BLACK ? BLACK_TEXT : "";
    return SQUARE_TEMPLATE.formatted(background, colorCode, " " + pieceSymbol, ANSI_RESET);
  }
}
