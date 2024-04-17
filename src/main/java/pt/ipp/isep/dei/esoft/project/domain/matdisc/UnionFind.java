package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.util.ArrayList;
import java.util.List;

public class UnionFind {

    private final List<String> parent;

    public UnionFind() {
        parent = new ArrayList<>();
    }

    public boolean union(String vertex1, String vertex2) {
        int root1 = findRoot(vertex1);
        int root2 = findRoot(vertex2);

        if (root1 != root2) {
            parent.set(root2, String.valueOf(root1)); // Correção aqui
            return true; // União bem-sucedida
        }
        return false; // União criaria um ciclo
    }

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
