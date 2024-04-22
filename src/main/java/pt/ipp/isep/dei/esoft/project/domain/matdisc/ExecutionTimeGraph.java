package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExecutionTimeGraph extends JFrame {
    private List<FileInfo> fileInfoList;

    public ExecutionTimeGraph(List<FileInfo> fileInfoList) {
        super("Execution Time Graph");
        this.fileInfoList = fileInfoList;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Execution Time vs Graph Dimension", // Chart title
                "Graph Dimension", // X-Axis label
                "Execution Time (ms)", // Y-Axis label
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Customize chart...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Create a series for the data
        XYSeries series = new XYSeries("Execution Time vs Graph Dimension");

        // Populate the series with data from the FileInfo list
        for (FileInfo fileInfo : fileInfoList) {
            series.add(fileInfo.getTotalLines(), fileInfo.getExecutionTime());
        }

        dataset.addSeries(series);

        return dataset;
    }

    public static void main(String[] args) {
        // Assuming you have a list of FileInfo objects called fileInfoList
        List<FileInfo> fileInfoList = new ArrayList<>();
        // Populate fileInfoList with your data...

        SwingUtilities.invokeLater(() -> {
            ExecutionTimeGraph example = new ExecutionTimeGraph(fileInfoList);
            example.setVisible(true);
        });
    }
}

