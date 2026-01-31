public class Pawn extends Piece {

  private boolean isFirstMove = true;

  public Pawn(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int destinationRank, int destinationFile) {

    final int direction = getColor() == Color.WHITE ? 1 : -1;
    final int rankDifference = (destinationRank - getCurrentRank()) * direction;
    if (destinationFile != getCurrentFile()) {
      final var fileDiff = Math.abs(destinationFile - getCurrentFile());
      if (fileDiff != 1) {
        return false;
      }

      return rankDifference == 1 && isOpponentPieceAt(destinationRank, destinationFile);
    }

    if (rankDifference <= 0) {
      return false;
    }

    if (rankDifference > 2) {
      return false;
    }

    if (rankDifference == 2 && isFirstMove) {
      return true;
    }

    return rankDifference == 1 && !isOpponentPieceAt(destinationRank, destinationFile);
  }

  @Override
  public void afterMove() {
    isFirstMove = false;
  }

  @Override
  public String getSymbol() {
    return "P";
  }
}
