package pt.ipp.isep.dei.esoft.project.domain.MATDISC_class_resolution;

public class FileInfo {
    private String fileName;
    private int totalLines;
    private double totalCost;
    private int totalNumberOfVertices;
    private long executionTime;

    public FileInfo(String fileName, int totalLines, long executionTime, double totalCost, int totalNumberOfVertices) {
        this.fileName = fileName;
        this.totalLines = totalLines;
        this.executionTime = executionTime;
        this.totalCost = totalCost;
        this.totalNumberOfVertices = totalNumberOfVertices;
    }

    public FileInfo(String fileName, int totalLines, long executionTime) {
        this.fileName = fileName;
        this.totalLines = totalLines;
        this.executionTime = executionTime;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "File Name: " + fileName.substring(fileName.lastIndexOf("\\") + 1) +
                "\nGraph Dimension: " + totalLines +
                "\nGraph Order: " + totalNumberOfVertices +
                "\nMinimum Cost: " + totalCost +
                "\nExecution Time: " + executionTime + "ms";
    }


    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
