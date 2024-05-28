package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import pt.ipp.isep.dei.esoft.project.controller.AddNewEntryToDoController;
import pt.ipp.isep.dei.esoft.project.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListGreenSpacesUI implements Initializable {

    private final ListGreenSpacesController controller;

    private final List<GreenSpaceDTO> greenSpaceDTOList;

    @FXML
    private Text title;
    @FXML
    private TableView<GreenSpaceDTO> tbGreenSpaces;
    @FXML
    private TableColumn<GreenSpaceDTO, String> greenSpaceType, greenSpaceName, greenSpaceAddress, greenSpaceAreaInHectares;

    public ListGreenSpacesUI() {
        this.controller = new ListGreenSpacesController();
        this.greenSpaceDTOList = this.controller.getManagerGreenSpaceDTOList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText("Green Spaces managed by " + ApplicationSession.getInstance().getCurrentSession().getUserId());
        greenSpaceType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type.toString()));
        greenSpaceName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name));
        greenSpaceAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().addressToString()));
        greenSpaceAreaInHectares.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().totalArea)));
        fillGreenSpacesTable();
    }

    private void fillGreenSpacesTable() {
        tbGreenSpaces.getItems().addAll(greenSpaceDTOList);
    }
}
