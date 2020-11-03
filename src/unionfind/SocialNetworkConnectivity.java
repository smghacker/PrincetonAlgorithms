package unionfind;

import java.util.Arrays;

/*
  Social network connectivity. Given a social network containing n members and a log file containing
  m timestamps at which times pairs of members formed friendships, design an algorithm to determine
  the earliest time at which all members are connected (i.e., every member is a friend of a friend
  of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship
  is an equivalence relation. The running time of your algorithm should be mlogn or better
  and use extra space proportional to n.
*/
public class SocialNetworkConnectivity {

  private int n;
  private int[] sz;
  private int[] friends;

  public SocialNetworkConnectivity(int n) {
    this.n = n;
    friends = new int[n];
    for (int i = 0; i < n; i++) {
      friends[i] = i;
    }

    sz = new int[n];
    for (int i = 0; i < n; i++) {
      sz[i] = 1;
    }
  }

  public boolean union(int p, int q) {
    int parentP = parent(p);
    int parentQ = parent(q);
    if (parentP != parentQ) {
      if (sz[parentP] >= sz[parentQ]) {
        friends[parentQ] = parentP;
        sz[parentP] += sz[parentQ];
        return sz[parentP] == n;
      } else {
        friends[parentP] = parentQ;
        sz[parentQ] += sz[parentP];
        return sz[parentQ] == n;
      }
    }
    return false;
  }

  private int parent(int p) {
    if (p == friends[p]) {
      return p;
    }

    return friends[p] = parent(friends[p]);
  }

  public static void main(String[] args) {
    SocialNetworkConnectivity snc = new SocialNetworkConnectivity(5);
    System.out.println(snc.union(2, 1));
    Arrays.stream(snc.friends).forEach(System.out::print);
    System.out.println();
    System.out.println(snc.union(4, 3));
    Arrays.stream(snc.friends).forEach(System.out::print);
    System.out.println();
    System.out.println(snc.union(0, 1));
    Arrays.stream(snc.friends).forEach(System.out::print);
    System.out.println();
    System.out.println(snc.union(2, 1));
    Arrays.stream(snc.friends).forEach(System.out::print);
    System.out.println();
    System.out.println(snc.union(1, 2));
    Arrays.stream(snc.friends).forEach(System.out::print);
    System.out.println();
    System.out.println(snc.union(2, 3));

    Arrays.stream(snc.friends).forEach(System.out::print);
  }
}
