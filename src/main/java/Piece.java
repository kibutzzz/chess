import lombok.Getter;

@Getter
public abstract class Piece {
  private final Color color;
  protected int currentFile;
  protected int currentRank;
  private final Board board;

  protected Piece(Color color, int currentFile, int currentRank, Board board) {
    this.color = color;
    this.currentFile = currentFile;
    this.currentRank = currentRank;
    this.board = board;
    this.board.placePiece(this);
  }

  protected abstract boolean canMoveTo(int rank, int file);

  protected void moveTo(int rank, int file) {
    if (!canMoveTo(rank, file)) {
      throw new IllegalArgumentException(
          "Invalid move from %dx%d to %dx%d".formatted(currentRank, currentFile, rank, file));
    }
    this.currentRank = rank;
    this.currentFile = file;
    afterMove();
  }

  protected boolean isEmptyOrOpponentColor(int rank, int file) {
    final var piece = getBoard().getPiece(rank, file);
    return !pieceIsFound(piece) || piece.getColor() != getColor();
  }

  private static boolean pieceIsFound(Piece piece) {
    return piece != null;
  }

  protected void afterMove() {}

  public abstract String getSymbol();
}
