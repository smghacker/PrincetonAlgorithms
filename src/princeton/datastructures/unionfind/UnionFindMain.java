package princeton.datastructures.unionfind;

public class UnionFindMain {
	public static void main(String[] args){
		int n = 10;
		UnionFind uf = new UnionFindWeighted(n);
		for(int i = 0; i < n/2; i++){
			uf.unite(i, i+n/2);
			System.out.println(uf.isSame(i, i+n/2) + " " + uf.findRoot(i+n/2));
		}
	}
}
