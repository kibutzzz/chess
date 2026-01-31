public class King extends Piece {

  public King(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {
    int rankDiff = Math.abs(currentRank - rank);
    int fileDiff = Math.abs(currentFile - file);
    return (rankDiff <= 1 && fileDiff <= 1);
  }

  @Override
  public String getSymbol() {
    return "K";
  }
}
