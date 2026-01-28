public class Rook extends Piece {

  public Rook(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {
    return currentFile == file || currentRank == rank;
  }
}
