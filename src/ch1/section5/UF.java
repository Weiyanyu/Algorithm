package ch1.section5;

public class UF {
    private int[] id;
    private int count;
    private int[] sz;

    public UF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            sz[i] = 1;
        }

    }

    public int count() {
        return this.count;
    }

    /* quick-find time complexity max is (N+3)(N-1), so it's close N²
    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qId = find(q);

        if (pID == qId) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qId;
        }
        count--;
    }*/


    /*  version 2, quick-union , use tree struct help union to be faster.
        And the time complexity is linear level in the best situation .
    public int find(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }*/


    //version 3, this version is fastest in three version.
    public int find(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public boolean connect(int p, int q) {
        return find(p) == find(q);
    }

}
