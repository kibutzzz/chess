public class Queen extends Piece {

  public Queen(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {
    boolean isDiagonalMove = Math.abs(currentFile - file) == Math.abs(currentRank - rank);
    boolean isStraightMove = currentFile == file || currentRank == rank;
    return isDiagonalMove || isStraightMove;
  }
}
