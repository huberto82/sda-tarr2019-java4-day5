package javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShapeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShapeApp");
        primaryStage.show();
        Rectangle rect = new Rectangle(10,10, 100, 100);
        rect.setFill(new Color(1,0,0,1));
        rect.setStrokeWidth(2);
        rect.setStroke(Color.BLACK);
        root.getChildren().add(rect);
        Circle circle = new Circle(50,50,50);
        circle.setFill(Color.VIOLET);
        root.getChildren().add(circle);
        Line line = new Line(100, 100, 200, 200);
        root.getChildren().add(line);
        Path path = new Path();
        path.getElements().addAll(
          new MoveTo(100,100),
          new LineTo(200,100),
          new LineTo(150,150),
          new LineTo(100,100)
        );
        path.setStroke(Color.STEELBLUE);
        path.setStrokeWidth(4);
        path.setFill(Color.LIGHTGREEN);
        root.getChildren().add(path);
        Path arrow = Arrow.create(0,100,200);
        root.getChildren().add(arrow);
        Text text = new Text(100,100,"Hello");
        text.setFont(Font.font("Arial", 24));
        text.setFill(Color.TOMATO);
        root.getChildren().add(text);
    }
}
