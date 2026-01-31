public class Rook extends Piece {

  public Rook(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {

    final var rankDifference = rank - getCurrentRank();
    final var fileDifference = file - getCurrentFile();

    if (isNotMovingInLine(rankDifference, fileDifference)) {
      return false;
    }

    if (getBoard().containsPieceBetween(getCurrentRank(), getCurrentFile(), rank, file)) {
      return false;
    }

    return isEmptyOrOpponentColor(rank, file);
  }

  private static boolean isNotMovingInLine(int rankDifference, int fileDifference) {
    return rankDifference != 0 && fileDifference != 0;
  }

  @Override
  public String getSymbol() {
    return "R";
  }
}
