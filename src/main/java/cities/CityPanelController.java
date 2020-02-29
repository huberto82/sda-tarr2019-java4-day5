package cities;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class CityPanelController {

    @FXML
    public TableColumn<City, Integer> columnId;

    @FXML
    public TableColumn<City, String> columnName;

    @FXML
    public TableColumn<City, Double> columnLatitude;

    @FXML
    public TableColumn<City, Double> columnLongitude;

    @FXML
    public TableColumn<City, Double> columnCountryCode;

    @FXML
    public TableColumn<City, Integer> columnPopulation;

    @FXML
    public TableView<City> citiesView;

    @FXML
    public CheckBox enableFilterByCountryCodeCheckBox;

    @FXML
    public ComboBox<String> countryCodeCombo;

    @FXML
    TextField fileNameField;

    Dao<City> cities;

    public void initialize(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        columnLongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        columnCountryCode.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        columnPopulation.setCellValueFactory(new PropertyValueFactory<>("population"));
    }

    public void loadFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        //getSource zwraca obiekt typu Object, ale wiemy, że źródłem jest klasa Button
        Button button = (Button) actionEvent.getSource();
        //dostęp do okna, w którym znajduje się nasz panel, scena itd.
        Window window = button.getParent().getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            fileNameField.setText(file.getAbsolutePath());
            CitiesDao citiesDao = new CitiesDao(file.getAbsolutePath());
            cities = citiesDao;
            citiesView.setItems(FXCollections.observableArrayList(cities.findAll()));
            countryCodeCombo.setItems(FXCollections.observableArrayList(createCountryCodeList()));
        }
    }

    public void enableFilterByCountryCode(ActionEvent actionEvent) {
        boolean enable = enableFilterByCountryCodeCheckBox.isSelected();
        countryCodeCombo.setDisable(!enable);
        enableFilterByCountryCodeCheckBox.setText(enable ? "Wyłącz filtr" : "Włącz filtr");

    }

    public void filterByCountryCode(ActionEvent actionEvent) {
        String code = countryCodeCombo.getValue();
        List<City> result = cities.findAll().stream().filter(city -> city.getCountryCode().equals(code)).sorted().collect(Collectors.toList());
        citiesView.setItems(FXCollections.observableArrayList(result));
    }

    List<String> createCountryCodeList(){
        return cities.findAll().stream().map(City::getCountryCode).distinct().sorted().collect(Collectors.toList());
    }
}
