import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Piece {
  private final Color color;
  protected int currentFile;
  protected int currentRank;
  private final Board board;

  protected abstract boolean canMoveTo(int rank, int file);

  protected void moveTo(int rank, int file) {
    if (!canMoveTo(rank, file)) {
      throw new IllegalArgumentException(
          "Invalid move from %dx%d to %dx%d".formatted(currentRank, currentFile, rank, file));
    }
    this.currentRank = rank;
    this.currentFile = file;
    afterMove();
  }

  protected void afterMove() {}

  public abstract String getSymbol();
}
