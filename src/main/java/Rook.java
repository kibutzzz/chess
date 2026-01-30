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

    if (foundPieceAtIntermediatePosition(rank, file, rankDifference, fileDifference)) {
      return false;
    }

    return isEmptyOrOpponentColor(rank, file);
  }

  private boolean foundPieceAtIntermediatePosition(
      int rank, int file, int rankDifference, int fileDifference) {
    final var rankStep = Integer.signum(rankDifference);
    final var fileStep = Integer.signum(fileDifference);
    var intermediateRank = Integer.sum(getCurrentRank(), rankStep);
    var intermediateFile = Integer.sum(getCurrentFile(), fileStep);
    while (notYetOnTarget(rank, file, intermediateRank, intermediateFile)) {
      final var pieceAtIntermediate = getBoard().getPiece(intermediateRank, intermediateFile);

      if (pieceIsFound(pieceAtIntermediate)) {
        return true;
      }

      intermediateRank += rankStep;
      intermediateFile += fileStep;
    }
    return false;
  }

  private boolean isEmptyOrOpponentColor(int rank, int file) {
    final var piece = getBoard().getPiece(rank, file);
    return !pieceIsFound(piece) || piece.getColor() != getColor();
  }

  private static boolean pieceIsFound(Piece pieceAtIntermediate) {
    return pieceAtIntermediate != null;
  }

  private static boolean notYetOnTarget(
      int rank, int file, int intermediateRank, int intermediateFile) {
    return intermediateRank != rank || intermediateFile != file;
  }

  private static boolean isNotMovingInLine(int rankDifference, int fileDifference) {
    return rankDifference != 0 && fileDifference != 0;
  }
}
