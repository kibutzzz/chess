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
    board[rank][file] = piece;
    piece.moveTo(rank, file);
    return capturedPiece;
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
}
