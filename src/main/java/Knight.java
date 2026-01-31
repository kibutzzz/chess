public class Knight extends Piece {

  public Knight(Color color, int currentFile, int currentRank, Board board) {
    super(color, currentFile, currentRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {
    int rankDiff = Math.abs(rank - getCurrentRank());
    int fileDiff = Math.abs(file - getCurrentFile());

    if (rankDiff == 2 && fileDiff == 1) {
      return true;
    }

    return rankDiff == 1 && fileDiff == 2;
  }

  @Override
  public String getSymbol() {
    return "N";
  }
}
