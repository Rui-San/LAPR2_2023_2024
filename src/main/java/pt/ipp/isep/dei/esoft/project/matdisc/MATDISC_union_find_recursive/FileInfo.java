package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_union_find_recursive;

/**
 * The FileInfo class represents information about a file processed by the application.
 *
 * This Class will be used when print all the information gathered about the analysed CSV file.
 *
 * It contains details such as the file name, total number of lines, total cost, total number of vertices, and execution time.
 *
 */
public class FileInfo {

    /**
     * The name of the csv file.
     */
    private String fileName;

    /**
     * The total number of lines in the csv file.
     */
    private int totalLines;

    /**
     * The total cost(distance) of the Minimal Spanning Tree of the file.
     */
    private double totalCost;

    /**
     * The total number of vertices in the graph of the csv file.
     */
    private int totalNumberOfVertices;

    /**
     * The total execution time of processing the algorithm.
     */
    private long executionTime;

    /**
     * Constructs a new {@code FileInfo} object with the specified file name, total lines, execution time, total cost, and total number of vertices.
     *
     * @param fileName             The name of the csv file.
     * @param totalLines           The total number of lines in the csv file.
     * @param executionTime        The total execution time of processing the algorithm.
     * @param totalCost            The total cost(distance) of the Minimal Spanning Tree of the file.
     * @param totalNumberOfVertices The total number of vertices in the graph of the csv file.
     */
    public FileInfo(String fileName, int totalLines, long executionTime, double totalCost, int totalNumberOfVertices) {
        this.fileName = fileName;
        this.totalLines = totalLines;
        this.executionTime = executionTime;
        this.totalCost = totalCost;
        this.totalNumberOfVertices = totalNumberOfVertices;
    }

    /**
     * Constructs a new {@code FileInfo} object with the specified file name, total lines, and execution time.
     *
     * @param fileName      The name of the csv file.
     * @param totalLines    The total number of lines in the csv file.
     * @param executionTime The total execution time of processing the algorithm.
     */
    public FileInfo(String fileName, int totalLines, long executionTime) {
        this.fileName = fileName;
        this.totalLines = totalLines;
        this.executionTime = executionTime;
    }

    /**
     * Returns the name of the file.
     *
     * @return the name of the file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the name of the file.
     *
     * @param fileName the name of the file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the total number of lines in the file.
     *
     * @return the total number of lines in the file
     */
    public int getTotalLines() {
        return totalLines;
    }

    /**
     * Sets the total number of lines in the file.
     *
     * @param totalLines the total number of lines in the file
     */
    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    /**
     * Returns the total cost associated with the file.
     *
     * @return the total cost associated with the file
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the total cost associated with the file.
     *
     * @param totalCost the total cost associated with the file
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Returns the execution time of processing the file.
     *
     * @return the execution time of processing the file
     */
    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * Sets the execution time of processing the file.
     *
     * @param executionTime the execution time of processing the file
     */
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * Returns a string representation of the file information.
     *
     * @return a string representation of the file information
     */
    @Override
    public String toString() {
        return "File Name: " + fileName.substring(fileName.lastIndexOf("\\") + 1) +
                "\nGraph Dimension: " + totalLines +
                "\nGraph Order: " + totalNumberOfVertices +
                "\nMinimum Cost: " + totalCost +
                "\nExecution Time: " + executionTime + "ms";
    }

}