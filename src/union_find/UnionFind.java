package union_find;

public class UnionFind {
    private int[] id;
    private int componentsCount;

    public UnionFind(int N) {
        // initialize N sites with integer names (0 to N - 1)
        componentsCount = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    int findComponent(int siteIndex) {
        // component identifier or p (0 to N - 1)
        // returns an integer component identifier for a given site.

        if (siteIndex < 0 || siteIndex >= id.length) {
            throw new IllegalArgumentException("index " + siteIndex + " is not between 0 and " + (id.length - 1));
        }
        return id[siteIndex];
    }

    void union(int p, int q) {
        // add connection between p and q;
        // merges two components if the two sites are in different components

        int pComponentID = findComponent(p);
        int qComponentID = findComponent(q);
        // Nothing to do if p and q are already in the same component.
        if (pComponentID == qComponentID) return;
        // Rename p’s component to q’s name.
        for (int i = 0; i < id.length; i++)
            if (id[i] == pComponentID) id[i] = qComponentID;
        componentsCount--;
    }

    int findComponentQuickUnion(int siteIndex) {
        // component identifier or p (0 to N - 1)
        // returns an integer component identifier for a given site.

        if (siteIndex < 0 || siteIndex >= id.length) {
            throw new IllegalArgumentException("index " + siteIndex + " is not between 0 and " + (id.length - 1));
        }

        while (siteIndex != id[siteIndex]) {
            siteIndex = id[siteIndex];
        }
        return siteIndex;
    }

    public void unionQuickUnion(int p, int q) {
        int pRoot = findComponentQuickUnion(p);
        int qRoot = findComponentQuickUnion(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        componentsCount--;
    }

    boolean connected(int p, int q) {
        // return true if p and q are in the same component
        return findComponent(p) == findComponent(q);
    }

    int count() {
        return componentsCount;
    }

    public static void main(String[] args) {
        UnionFind myUF = new UnionFind(10);

        int p = 3;
        int q = 100;

        if (myUF.connected(p, q)) return;

        myUF.union(p, q);
        System.out.println(p + " " + q);
        System.out.println(myUF.count() + " components");
    }
}
