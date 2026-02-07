public class Bishop extends Piece {

  public Bishop(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {
    if (Math.abs(currentFile - file) != Math.abs(currentRank - rank)) {
      return false;
    }
    return !this.getBoard().containsPieceBetween(getCurrentRank(), getCurrentFile(), rank, file);
  }

  @Override
  public char getSymbol() {
    return 'B';
  }
}
