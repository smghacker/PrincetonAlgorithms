package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int countOpen = 0;
  private int size;
  private boolean[][] grid;
  private WeightedQuickUnionUF weightedQuickUnionUF;
  private WeightedQuickUnionUF weightedQuickUnionUFIsFull;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    weightedQuickUnionUFIsFull = new WeightedQuickUnionUF(n * n + 1);
    this.grid = new boolean[n][n];
    this.size = n;
  }

  public void open(int row, int col) {
    if (isOpen(row, col)) {
      return;
    }
    grid[--row][--col] = true;
    countOpen++;

    int current = row * size + col + 1;

    int upperCell = (row - 1) == -1 ? 0 : current - size;
    if (upperCell == 0 || isOpen(row, col + 1)) {
      weightedQuickUnionUF.union(current, upperCell);
      weightedQuickUnionUFIsFull.union(current, upperCell);
    }

    int lowerCell = (row + 1) == size ? size * size + 1 : current + size;
    if (lowerCell == size * size + 1 || isOpen(row + 2, col + 1)) {
      weightedQuickUnionUF.union(current, lowerCell);
    }

    if(lowerCell != size * size + 1 && isOpen(row + 2, col + 1)) {
      weightedQuickUnionUFIsFull.union(current, lowerCell);
    }

    if (col - 1 >= 0 && isOpen(row + 1, col)) {
      weightedQuickUnionUF.union(current, current - 1);
      weightedQuickUnionUFIsFull.union(current, current - 1);
    }

    if (col + 1 < size && isOpen(row + 1, col + 2)) {
      weightedQuickUnionUF.union(current, current + 1);
      weightedQuickUnionUFIsFull.union(current, current + 1);
    }
  }

  public boolean isOpen(int row, int col) {
    checkBounds(row, col);
    return grid[--row][--col];
  }

  public boolean isFull(int row, int col) {
    return isOpen(row, col) &&
        weightedQuickUnionUFIsFull.find((row - 1) * size + col) == weightedQuickUnionUFIsFull.find(0);
  }

  public int numberOfOpenSites() {
    return countOpen;
  }

  public boolean percolates() {
    return weightedQuickUnionUF.find(size * size + 1) == weightedQuickUnionUF.find(0);
  }

  private void checkBounds(int row, int col) {
    if (row <= 0 || row > size || col <= 0 || col > size) {
      throw new IllegalArgumentException();
    }
  }

  public static void main(String[] args) {
    Percolation p = new Percolation(4);
    p.open(1,1);
    p.open(2,1);
    p.open(2, 2);
    p.open(2, 3);
    p.open(3,3);
    p.open(4,3);
    p.open(4, 1);
    System.out.println(p.isFull(4,1));
  }
}
