package unionfind;

/*
  Union-find with specific canonical element. Add a method
  find() to the union-find data type so that find(i) returns the largest element in the connected
  component containing i. The operations, union(), connected(), and find() should all take
  logarithmic time or better.

  For example, if one of the connected components is {1,2,6,9}, then the find() method should
  return 9 for each of the four elements in the connected components.
*/
public class UnionFindWithSpecificCanonicalElement {

  private int[] biggest;
  private int[] parents;

  public UnionFindWithSpecificCanonicalElement(int n) {
    parents = new int[n];
    biggest = new int[n];
    for (int i = 0; i < n; i++) {
      parents[i] = i;
      biggest[i] = i;
    }
  }

  public void union(int p, int q) {
    int parentP = parent(p);
    int parentQ = parent(q);
    if (parentP != parentQ) {
      if (biggest[parentP] < biggest[parentQ]) {
        biggest[parentP] = biggest[parentQ];
        parents[parentP] = parents[parentQ];
      } else {
        biggest[parentQ] = biggest[parentP];
        parents[parentQ] = parents[parentP];
      }
    }
  }

  public int find(int s) {
    return biggest[parent(s)];
  }

  private int parent(int p) {
    if (p == parents[p]) {
      return p;
    }

    return parents[p] = parent(parents[p]);
  }

  public static void main(String[] args) {
    UnionFindWithSpecificCanonicalElement uf = new UnionFindWithSpecificCanonicalElement(10);
    uf.union(0, 1);
    uf.union(0, 2);
    uf.union(3, 1);
    uf.union(5, 0);
    uf.union(5, 7);
    System.out.println(uf.find(0));
  }
}
