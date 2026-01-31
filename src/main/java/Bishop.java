public class Bishop extends Piece {

  public Bishop(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {
    return Math.abs(currentFile - file) == Math.abs(currentRank - rank);
  }

  @Override
  public String getSymbol() {
    return "B";
  }
}
