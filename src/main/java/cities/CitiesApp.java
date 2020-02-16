package cities;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CitiesApp extends Application {
    TableView<City> citiesView = new TableView();
    Button loadBtn = new Button("Load");
    Label fileName = new Label("");
    Dao<City> cities = new CitiesDao();

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.add(fileName,1,0);
        root.add(loadBtn,0, 0);
        root.add(citiesView,0, 1);
        Scene scene = new Scene(root,800,600, Color.ANTIQUEWHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cities finder");
        primaryStage.show();
    }

    public void setButtonsHandlers(){
        loadBtn.setOnAction((event) -> {
            citiesView.getItems().addAll(cities.findAll());
        });
    }
}
