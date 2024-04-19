package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.util.ArrayList;
import java.util.List;

/**
 * The UnionFind class is responsible for, when calling the method union with 2 diferent vertex,
 * it checks if they belong to the same set. If they do, it means connecting them would create a
 * cycle, so nothing is done. If they are in different sets, the union method joins them, vertex2
 * going to the same set as vertex1.
 */

public class UnionFind {

    private final List<String> parent;

    public UnionFind() {
        parent = new ArrayList<>();
    }

    /**
     * Used to unite 2 diferent sets, each set with one vertex
     * Receiving 2 diferent verifies if they belong to the same set.
     * If they belong to the same set, nothing is done
     * The union is done by changing the parent of the vertex2, pointing it to the vertex1 instead
     *
     * @param vertex1
     * @param vertex2
     * @return
     */
    public boolean union(String vertex1, String vertex2) {
        int root1 = findRoot(vertex1);
        int root2 = findRoot(vertex2);

        if (root1 != root2) {
            parent.set(root2, String.valueOf(root1)); // Correção aqui
            return true; // União bem-sucedida
        }
        return false; // União criaria um ciclo
    }

    /**
     * this is used to find the root that the vertex belongs
     * Searching algorithm that navigates through the parents of the vertices until it finds the root.
     *
     * @param vertex
     * @return the index of list parent that corresponds to the root of that set
     */
    public int findRoot(String vertex) {
        if (!parent.contains(vertex)) {
            parent.add(vertex); // Adiciona o vértice ao conjunto
            return parent.size() - 1;
        }

        int idx = parent.indexOf(vertex);
        while (!vertex.equals(parent.get(idx))) {
            idx = Integer.parseInt(parent.get(idx)); // Atualiza o índice para o próximo elemento na cadeia
        }
        return idx;
    }
}
