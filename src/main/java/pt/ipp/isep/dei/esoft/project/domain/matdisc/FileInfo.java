package pt.ipp.isep.dei.esoft.project.domain.matdisc;

public class FileInfo {
    private String fileName;
    private int totalLines;
    private long executionTime;

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

    @Override
    public String toString() {
        return
                "File Name: '" + fileName + '\'' +
                        ", Total Lines: " + totalLines +
                        ", Execution Time: " + executionTime + "ms";
    }


    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
