public class Board {
  public static final int FILES_SIZE = 8;
  public static final int RANKS_SIZE = 8;

  // ANSI color codes
  private static final String ANSI_BLACK_TEXT = "\u001B[30m";
  private static final String ANSI_GREEN_BG = "\u001B[42m";
  private static final String ANSI_WHITE_BG = "\u001B[47m";
  private static final String ANSI_RESET = "\u001B[0m";

  private final Piece[][] board;

  public Board() {
    this.board = new Piece[RANKS_SIZE][FILES_SIZE];
  }

  public Piece getPiece(int rank, int file) {
    return board[rank][file];
  }

  public Piece movePiece(int rank, int file, Piece piece) {
    if (!isWithinBounds(rank, file)) {
      throw new IllegalArgumentException("Move out of board bounds.");
    }
    Piece capturedPiece = board[rank][file];
    final var oldRank = piece.getCurrentRank();
    final var oldFile = piece.getCurrentFile();
    piece.moveTo(rank, file);
    board[oldRank][oldFile] = null;
    board[rank][file] = piece;
    return capturedPiece;
  }

  public boolean containsPieceBetween(int startRank, int startFile, int endRank, int endFile) {
    int rankStep = Integer.signum(endRank - startRank);
    int fileStep = Integer.signum(endFile - startFile);
    int currentRank = startRank + rankStep;
    int currentFile = startFile + fileStep;

    while (currentRank != endRank || currentFile != endFile) {
      if (board[currentRank][currentFile] != null) {
        return true;
      }
      currentRank += rankStep;
      currentFile += fileStep;
    }
    return false;
  }

  public void placePiece(Piece piece) {
    final var rank = piece.getCurrentRank();
    final var file = piece.getCurrentFile();
    if (!isWithinBounds(rank, file)) {
      throw new IllegalArgumentException("Placement out of board bounds.");
    }

    if (board[rank][file] != null) {
      throw new IllegalArgumentException("Square already occupied.");
    }

    board[rank][file] = piece;
  }

  private boolean isWithinBounds(int rank, int file) {
    return rank >= 0 && rank < RANKS_SIZE && file >= 0 && file < FILES_SIZE;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int rank = RANKS_SIZE - 1; rank >= 0; rank--) {
      sb.append(rank + 1).append(" ");
      for (int file = 0; file < FILES_SIZE; file++) {
        Piece piece = board[rank][file];
        boolean isDarkSquare = (rank + file) % 2 == 1;

        if (piece != null) {
          String symbol = piece.getSymbol();
          if (isDarkSquare) {
            if (piece.getColor() == Color.BLACK) {
              sb.append(ANSI_GREEN_BG)
                  .append(ANSI_BLACK_TEXT)
                  .append(" ")
                  .append(symbol)
                  .append(" ")
                  .append(ANSI_RESET);
            } else {
              sb.append(ANSI_GREEN_BG).append(" ").append(symbol).append(" ").append(ANSI_RESET);
            }
          } else {
            if (piece.getColor() == Color.BLACK) {
              sb.append(ANSI_WHITE_BG)
                  .append(ANSI_BLACK_TEXT)
                  .append(" ")
                  .append(symbol)
                  .append(" ")
                  .append(ANSI_RESET);
            } else {
              sb.append(ANSI_WHITE_BG).append(" ").append(symbol).append(" ").append(ANSI_RESET);
            }
          }
        } else {
          if (isDarkSquare) {
            sb.append(ANSI_GREEN_BG).append("   ").append(ANSI_RESET);
          } else {
            sb.append(ANSI_WHITE_BG).append("   ").append(ANSI_RESET);
          }
        }
      }
      sb.append("\n");
    }
    sb.append("   a  b  c  d  e  f  g  h\n");
    return sb.toString();
  }
}
