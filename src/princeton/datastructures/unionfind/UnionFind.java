package princeton.datastructures.unionfind;

public interface UnionFind {	
	void unite(int x, int y);
	boolean isSame(int x, int y);
	int findRoot(int x);
	int count();
}
