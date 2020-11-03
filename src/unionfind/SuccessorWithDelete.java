package unionfind;

/*
  Given a set of nn integers S={0,1,...,n−1} and a sequence of requests of the following form:

  Remove x from S
  Find the successor of x: the smallest y in S such that y>=x
  design a data type so that all operations (except construction) take
  logarithmic time or better in the worst case.
*/
public class SuccessorWithDelete {

  private int[] successors;

  public SuccessorWithDelete(int n) {
    successors = new int[n];
    for (int i = 0; i < n; i++) {
      successors[i] = i;
    }
  }

  public void delete(int p) {
    successors[p] = successor(p + 1);
  }

  private int successor(int p) {
    if (p == successors[p]) {
      return p;
    }

    return successors[p] = successor(successors[p]);
  }

  public static void main(String[] args) {
    SuccessorWithDelete successor = new SuccessorWithDelete(8);
    successor.delete(3);
    System.out.println(successor.successor(3));
    successor.delete(5);
    System.out.println(successor.successor(5));
    successor.delete(4);
    System.out.println(successor.successor(4));
    successor.delete(2);
    System.out.println(successor.successor(2));

    System.out.println(successor.successor(3));
  }
}
