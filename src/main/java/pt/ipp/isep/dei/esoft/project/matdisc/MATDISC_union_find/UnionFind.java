package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_union_find;
public class UnionFind {
    private int[] parent;
    private int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * dizer o que o método faz em conjunto com o método connected e union
     * @param p
     * @return
     */
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // Otimização de caminho
            p = parent[p];
        }
        return p;
    }

    /**
     * dizer o que o método faz em conjunto com o método find e union
     * @param p
     * @return
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * dizer o que o método faz em conjunto com o método connected e find
     * @param p
     * @return
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // Mantém a árvore balanceada
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }
}
