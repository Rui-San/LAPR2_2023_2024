package pt.ipp.isep.dei.esoft.project.domain.matdisc;

public class Subset {

    private int parent;
    private int rank;

    public Subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
