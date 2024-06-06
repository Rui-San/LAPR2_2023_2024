package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;
import pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_class_resolution.Edge;
import pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_class_resolution.GraphUS;
import pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_class_resolution.MainUS13;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ObtainRoutesUI implements Initializable {

    @FXML
    private TableView<Edge> tableView;
    @FXML
    private TableColumn<Edge, String> vertex1Column;
    @FXML
    private TableColumn<Edge, String> vertex2Column;
    @FXML
    private TableColumn<Edge, Double> costColumn;
    @FXML
    private Label totalCostLabel;

    private Stage stage;

    @FXML
    public void handleOpenCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            GraphUS graph = MainUS13.readCsvFile(file.getAbsolutePath());
            if (graph != null) {
                String fileName = file.getAbsolutePath();
                List<Edge> minimalSpanningTree = graph.getMinimalSpanningTree();
                tableView.setItems(FXCollections.observableArrayList(minimalSpanningTree));
                double totalCost = MainUS13.obtainTotalCost(minimalSpanningTree);
                totalCostLabel.setText("Total cost: " + totalCost);
                String csvName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
                MainUS13.exportDataToCsv(minimalSpanningTree, fileName, totalCost);
                Graph g = MainUS13.highlightGraph(MainUS13.drawGraph((ArrayList<Edge>) graph.getEdges(), "Minimum Spanning Tree"), (ArrayList<Edge>) minimalSpanningTree);
                MainUS13.addTextToGraph(g, fileName, totalCost);
                MainUS13.generateGraphViz( graph.getEdges(), minimalSpanningTree, csvName, totalCost);

            } else {
                showAlert("Error", "Failed to load the graph from the CSV file.");
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vertex1Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSource()));
        vertex2Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
        costColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDistance()).asObject());
    }
}
