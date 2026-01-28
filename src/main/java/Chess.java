import java.util.HashSet;
import java.util.Set;

public class Chess {
  private final Board board;
  private Color currentTurn;

  private final Set<Piece> capturedPieces = new HashSet<>();

  public Chess() {
    this.board = new Board();
    this.currentTurn = Color.WHITE;
  }

  public void move(int originRank, int originFile, int destinationRank, int destinationFile) {
    final var piece = board.getPiece(originRank, originFile);
    if (piece == null) {
      throw new IllegalArgumentException("No piece at the origin position.");
    }

    if (!piece.getColor().equals(currentTurn)) {
      throw new IllegalArgumentException("It's not your turn.");
    }

    final var capturedPiece = board.movePiece(destinationRank, destinationFile, piece);
    if (capturedPiece != null) {
      this.capturedPieces.add(capturedPiece);
    }

    passTurn();
  }

  private void passTurn() {
    currentTurn = currentTurn.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
  }
}
