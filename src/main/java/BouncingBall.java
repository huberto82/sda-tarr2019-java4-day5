import javafx.AnimationThread;
import javafx.Counter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;


public class BouncingBall extends Application {
    private static final double RADIUS = 30;
    private Circle ball = new Circle(RADIUS, Color.VIOLET);
    private static AnimationThread thread;
    static final Counter tick = new Counter();
    private static final int TICKS = 10;
    private Text clockInfo = new Text(10,50,""+TICKS);

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        root.getChildren().add(ball);
        root.getChildren().add(clockInfo);
        Text info = new Text(10,100,"Licznik: ");
        info.setFont(Font.font("Arial", 18));
        root.getChildren().add(info);
        Counter counter = new Counter();
        Button stopBtn = new Button("Stop");
        root.getChildren().add(stopBtn);
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (stopBtn.getText().equals("Stop")) {
                    thread.interrupt();
                    stopBtn.setText("Run");
                } else {
                    thread = new AnimationThread(ball, scene, 2, 2);
                    thread.start();
                    stopBtn.setText("Stop");
                }
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double diffX = ball.getCenterX() - event.getX();
                double diffY = ball.getCenterY() - event.getY();
                double distance = diffX*diffX + diffY*diffY;
                if (ball.getRadius()*ball.getRadius() >= distance && tick.get() < TICKS){
                    counter.inc();
                    info.setText("Licznik: " + counter.get());
                }
            }
        });
        primaryStage.show();
        Random random = new Random();
        ball.setCenterX(
                getRandom(random, scene.getWidth())
        );
        ball.setCenterY(
                getRandom(random, scene.getHeight()));

        thread = new AnimationThread(ball, scene,4, 4);
        thread.start();
        for(int i = 0; i < 10; i++){
            Circle ball = new Circle(RADIUS, Color.VIOLET);
            ball.setCenterX(getRandom(random, scene.getWidth()));
            ball.setCenterY(getRandom(random, scene.getHeight()));
            root.getChildren().add(ball);
            new AnimationThread(ball, scene, random.nextDouble()*6, random.nextDouble()*6).start();
        }
        Thread clock = new Thread(() -> {
            while(tick.get() < TICKS){
                clockInfo.setText(""+(TICKS - tick.get()));
                tick.inc();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clockInfo.setText("Koniec");
        });
        clock.start();
    }

    private double getRandom(Random random, double width) {
        return RADIUS + random.nextDouble() * (width - RADIUS);
    }
}
