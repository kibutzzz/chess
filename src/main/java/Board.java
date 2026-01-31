public class Board {
  public static final int FILES_SIZE = 8;
  public static final int RANKS_SIZE = 8;
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
        sb.append(piece != null ? piece.getSymbol() : ".").append(" ");
      }
      sb.append("\n");
    }
    sb.append("  a b c d e f g h\n");
    return sb.toString();
  }
}
