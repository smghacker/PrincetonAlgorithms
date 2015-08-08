package princeton.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private static final short BLACK_CELL = 0;
	private static final short WHITE_CELL = 1;
	private static final short BLUE_CELL = 2;
	private static final int NUM_DIRS = 4;
	private static final int[] DIR_ROW = { 1, 0, -1, 0 };
	private static final int[] DIR_COL = { 0, 1, 0, -1 };

	private int n;
	private short[][] grid;

	private WeightedQuickUnionUF unionFind;

	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException("The size of the grid must be positive number");
		}

		n = N;
		grid = new short[n + 1][n];
		for (int i = 0; i < n; i++) {
			grid[0][i] = BLUE_CELL;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = BLACK_CELL;
			}
		}
		unionFind = new WeightedQuickUnionUF(N * (N + 1));
	} // create N-by-N grid, with all sites blocked

	public void open(int i, int j) {
		if (!isInside(i, j)) {
			throw new IndexOutOfBoundsException(
					String.format("The row and column must be inside the grid(in the interval[$1%s, $2%s])", n, n));
		}
		int cellEncoded = cellEncode(i, j);
		if (grid[i][j] != WHITE_CELL) {
			grid[i][j] = WHITE_CELL;

			for (int k = 0; k < NUM_DIRS; k++) {
				int newRow = i + DIR_ROW[k];
				int newCol = j + DIR_COL[k];

				uniteWithNeighbourCell(i, j, cellEncoded, newRow, newCol);
			}
		}
	} // open site (row i, column j) if it is not open already

	public boolean isOpen(int i, int j) {
		if (!isInsideExtended(i, j)) {
			throw new IndexOutOfBoundsException(
					String.format("The row and column must be inside the grid(in the interval[$1%s, $2%s])", n, n));
		}
		return grid[i][j] != BLACK_CELL;
	} // is site (row i, column j) open?

	public boolean isFull(int i, int j) {
		if (!isInside(i, j)) {
			throw new IndexOutOfBoundsException(
					String.format("The row and column must be inside the grid(in the interval[$1%s, $2%s])", n, n));
		}
		return isRootCellFull(i, j);
	} // is site (row i, column j) full?

	public boolean percolates() {
		boolean result = false;
		for(int i = 0; i < n; i++){
			if(grid[n][i] == BLUE_CELL){
				result = true;
				break;
			}
			if(grid[n][i] == WHITE_CELL){
				if(isRootCellFull(n, i)){
					result = true;
					break;
				}
			}
		}
		
		return result;
	} // does the system percolate?

	private int cellEncode(int row, int col) {
		return row * n + col;
	}

	private int cellRowDecode(int encodedCell) {
		return encodedCell / n;
	}

	private int cellColDecode(int encodedCell) {
		return encodedCell % n;
	}

	private boolean isInside(int i, int j) {
		return i > 0 && i < (n + 1) && j >= 0 && j < n;
	}

	private boolean isInsideExtended(int i, int j) {
		return i >= 0 && i < (n + 1) && j >= 0 && j < n;
	}

	private boolean isRootCellFull(int row, int col) {
		int cellEncoded = cellEncode(row, col);
		int rootCellEncoded = unionFind.find(cellEncoded);
		int rowRootCell = cellRowDecode(rootCellEncoded);
		int colRootCell = cellColDecode(rootCellEncoded);
		return grid[rowRootCell][colRootCell] == BLUE_CELL;
	}

	private void floodComponentAfterUnion(int cellEncoded) {
		int rootCellEncoded = unionFind.find(cellEncoded);
		int rowOfRootCell = cellRowDecode(rootCellEncoded);
		int colOfRootCell = cellColDecode(rootCellEncoded);
		grid[rowOfRootCell][colOfRootCell] = BLUE_CELL;
	}

	private void floodIfItIsOnFirstRow(int row, int col, int rowRootCell, int colRootCell) {
		for (int i = 0; i < NUM_DIRS; i++) {
			int neighbourRow = row + DIR_ROW[i];
			int neighbourCol = col + DIR_COL[i];
			if (isInsideExtended(neighbourRow, neighbourCol) && grid[neighbourRow][neighbourCol] == BLUE_CELL) {
				grid[row][col] = BLUE_CELL;
				grid[rowRootCell][colRootCell] = BLUE_CELL;
			}
		}
	}
	
	private void uniteWithNeighbourCell(int openedCellRow, int openedCellCol, int cellEncoded, int neighbourCellRow,
			int neighbourCellCol) {
		if (isInsideExtended(neighbourCellRow, neighbourCellCol)) {
			if (isRootCellFull(neighbourCellRow, neighbourCellCol) || isOpen(neighbourCellRow, neighbourCellCol)) {
				int neighbourCellEncoded = cellEncode(neighbourCellRow, neighbourCellCol);
				unionFind.union(cellEncoded, neighbourCellEncoded);

				boolean isNeighbourComponentFlooded = isRootCellFull(neighbourCellRow, neighbourCellCol);

				int rootCellEncoded = unionFind.find(cellEncoded);
				int rowRootCell = cellRowDecode(rootCellEncoded);
				int colRootCell = cellColDecode(rootCellEncoded);

				floodIfItIsOnFirstRow(openedCellRow, openedCellCol, rowRootCell, colRootCell);
				boolean isCurrentComponentFlooded = isRootCellFull(openedCellRow, openedCellCol);

				if (isNeighbourComponentFlooded && !isCurrentComponentFlooded) {
					floodComponentAfterUnion(cellEncoded);
				} else if (!isNeighbourComponentFlooded && isCurrentComponentFlooded) {
					floodComponentAfterUnion(neighbourCellEncoded);
				} else if (isRootCellFull(neighbourCellRow, neighbourCellCol)) {
					floodComponentAfterUnion(cellEncoded);
					floodComponentAfterUnion(neighbourCellEncoded);
				}
			}
		}
	}

	public static void main(String[] args) {
		Percolation perc = new Percolation(5);
		perc.open(5, 4);
		perc.open(4, 4);
		perc.open(3, 4);
		perc.open(2, 4);
		perc.isFull(3, 4);
		System.out.println(perc.isOpen(4, 4));
		perc.open(1, 4);
		System.out.println(perc.isFull(3, 4));
		System.out.println(perc.isFull(4, 4));
		System.out.println(perc.isFull(1, 4));
		System.out.println(perc.percolates());
	}

}
