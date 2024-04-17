package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.util.Arrays;

public class DisjointSet {
    int[] parent;

    public DisjointSet(int size) {
        parent = new int[size + 1];
        Arrays.fill(parent, -1);
    }

    public int find(int x) {
        if (parent[x] < 0) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (parent[rootX] < parent[rootY]) {
                parent[rootX] += parent[rootY];
                parent[rootY] = rootX;
            } else {
                parent[rootY] += parent[rootX];
                parent[rootX] = rootY;
            }
        }
    }
}
