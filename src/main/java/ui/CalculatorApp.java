package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    TextField numberOne = new TextField("0");
    TextField numberTwo = new TextField("0");
    Label result = new Label("0.");
    Button addBtn = new Button("+");
    Button diffBtn = new Button("-");
    Button mulBtn = new Button("*");
    Button divBtn = new Button("/");

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        Scene scene = new Scene(root,400,200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Kalkulator TARR2020");
        primaryStage.show();

        root.setPadding(new Insets(10));
        root.setSpacing(10);

        HBox buttons = new HBox(
                addBtn,
                diffBtn,
                mulBtn,
                divBtn
        );
        addBtn.setPrefWidth(80);
        diffBtn.setPrefWidth(80);
        mulBtn.setPrefWidth(80);
        divBtn.setPrefWidth(80);

        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
          result,
          numberOne,
          numberTwo,
          buttons
        );
        setButtonsHandlers();
    }

    void setButtonsHandlers(){
        addBtn.setOnAction((event) -> {
           String str1 = numberOne.getText();
           String str2 = numberTwo.getText();
           if (isNumber(str1) && isNumber(str2)){
               result.setText((toNumber(str1)+toNumber(str2))+"");
           }
        });
        diffBtn.setOnAction((event)->{
            String str1 = numberOne.getText();
            String str2 = numberTwo.getText();
            if (isNumber(str1) && isNumber(str2)){
                result.setText((toNumber(str1)-toNumber(str2))+"");
            }
        });
        mulBtn.setOnAction((event)->{
            String str1 = numberOne.getText();
            String str2 = numberTwo.getText();
            if (isNumber(str1) && isNumber(str2)){
                result.setText((toNumber(str1)*toNumber(str2))+"");
            }
        });
        divBtn.setOnAction((event)->{
            String str1 = numberOne.getText();
            String str2 = numberTwo.getText();
            if (isNumber(str1) && isNumber(str2)){
                result.setText((toNumber(str1)/toNumber(str2))+"");
            }
        });
    }

    double toNumber(String str){
        return Double.parseDouble(str);
    }

    boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
