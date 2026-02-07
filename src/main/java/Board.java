public class Board {
  public static final int FILES_SIZE = 8;
  public static final int RANKS_SIZE = 8;

  private final Piece[][] board;
  private final BoardRepresentationCalculator boardRepresentationCalcualtor;

  public Board() {
    this.board = new Piece[RANKS_SIZE][FILES_SIZE];
    this.boardRepresentationCalcualtor = new BoardRepresentationCalculator(RANKS_SIZE, FILES_SIZE, board);
  }

  public void setup() {
    // Setup pawns
    for (int file = 0; file < FILES_SIZE; file++) {
      new Pawn(Color.WHITE, 1, file, this);
      new Pawn(Color.BLACK, 6, file, this);
    }

    // Setup rooks
    new Rook(Color.WHITE, 0, 0, this);
    new Rook(Color.WHITE, 0, 7, this);
    new Rook(Color.BLACK, 7, 0, this);
    new Rook(Color.BLACK, 7, 7, this);

    // Setup knights
    new Knight(Color.WHITE, 0, 1, this);
    new Knight(Color.WHITE, 0, 6, this);
    new Knight(Color.BLACK, 7, 1, this);
    new Knight(Color.BLACK, 7, 6, this);

    // Setup bishops
    new Bishop(Color.WHITE, 0, 2, this);
    new Bishop(Color.WHITE, 0, 5, this);
    new Bishop(Color.BLACK, 7, 2, this);
    new Bishop(Color.BLACK, 7, 5, this);

    // Setup queens
    new Queen(Color.WHITE, 0, 3, this);
    new Queen(Color.BLACK, 7, 3, this);

    // Setup kings
    new King(Color.WHITE, 0, 4, this);
    new King(Color.BLACK, 7, 4, this);
  }

  public Piece getPiece(int rank, int file) {
    return board[rank][file];
  }

  public Piece movePiece(int rank, int file, Piece piece) {
    if (isOutOfBounds(rank, file)) {
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
    if (isOutOfBounds(rank, file)) {
      throw new IllegalArgumentException("Placement out of board bounds.");
    }

    if (board[rank][file] != null) {
      throw new IllegalArgumentException("Square already occupied.");
    }

    board[rank][file] = piece;
  }

  private boolean isOutOfBounds(int rank, int file) {
    return rank < 0 || rank >= RANKS_SIZE || file < 0 || file >= FILES_SIZE;
  }

  @Override
  public String toString() {
    return boardRepresentationCalcualtor.calculate();
  }
}
