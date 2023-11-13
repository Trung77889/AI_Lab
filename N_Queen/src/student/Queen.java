package student;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
	  this.row++;
	  if (this.row >= Node.N) {
	    this.row = 0; 
	  }
	}

	public boolean isConflict(Queen q) {
	  if (this.row == q.row || this.column == q.column) {
	    return true;
	  }
	  if (Math.abs(this.column - q.column) == Math.abs(this.row - q.row)) {
	    return true;
	  }
	  return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
