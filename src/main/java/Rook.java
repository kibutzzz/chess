public class Rook extends Piece {

  public Rook(Color color, int initialRank, int initialFile, Board board) {
    super(color, initialFile, initialRank, board);
  }

  @Override
  protected boolean canMoveTo(int rank, int file) {

    final var rankDifference = rank - getCurrentRank();
    final var fileDifference = file - getCurrentFile();

    if (rankDifference != 0 && fileDifference != 0) {
      return false;
    }

    final var rankStep = Integer.signum(rankDifference);
    final var fileStep = Integer.signum(fileDifference);

    var intermediateRank = getCurrentRank() + rankStep;
    var intermediateFile = getCurrentFile() + fileStep;
    while (intermediateRank != rank || intermediateFile != file) {
      final var pieceAtIntermediate = getBoard().getPiece(intermediateRank, intermediateFile);

      if (pieceAtIntermediate != null) {
        return false;
      }

      intermediateRank += rankStep;
      intermediateFile += fileStep;
    }

    return getBoard().getPiece(rank, file) == null
        || getBoard().getPiece(rank, file).getColor() != getColor();
  }
}
