package princeton.datastructures.unionfind;

public class UnionFindWeighted implements UnionFind {

	private int elements[];
	private int rank[];
	private int numberOfComponents;
	
	public UnionFindWeighted(int n){
		numberOfComponents = n;
		elements = new int[n];
		rank = new int[n];
		
		init(n);
	}
	
	@Override
	public void unite(int x, int y) {
		int rootX = findRoot(x);
		int rootY = findRoot(y);
		if(!isSame(rootX, rootY)){
			if(rank[rootX] >= rank[rootY]){
				elements[rootY] = elements[rootX];
				rank[rootX] += rank[rootY];
			}else{
				elements[rootX] = elements[rootY];
				rank[rootY] += rank[rootX];
			}
		}
		numberOfComponents--;
	}

	@Override
	public boolean isSame(int x, int y) {
		return findRoot(x) == findRoot(y);
	}

	@Override
	public int findRoot(int x) {
		while(elements[x] != x){
			elements[x] = elements[elements[x]];
			x = elements[x];
		}
		
		return x;
	}

	@Override
	public int count() {
		return numberOfComponents;
	}

	private void init(int n){
		for(int i = 0; i < n; i++){
			elements[i] = i;
			rank[i] = 1;
		}
	}
}
