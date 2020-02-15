package javafx;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public class AnimationThread extends Thread {
    private Circle ball;
    private Scene scene;
    private double speedX;
    private double speedY;

    public AnimationThread(Circle ball, Scene scene, double speedX, double speedY) {
        this.ball = ball;
        this.scene = scene;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void run() {
        double x = 0;
        double y = 0;
        while(!this.isInterrupted()){
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                this.interrupt();
            }
            x = ball.getCenterX();
            y = ball.getCenterY();
            double radius = ball.getRadius();
            if (x + speedX < radius){
                speedX = - speedX;
            }
            if (x + speedX > scene.getWidth() - radius){
                speedX = - speedX;
            }
            if (y + speedY < radius){
                speedY = -speedY;
            }
            if (y + speedY > scene.getHeight() - radius){
                speedY = -speedY;
            }
            double xx = x + speedX;
            double yy = y + speedY;
            Platform.runLater(() -> {
                ball.setCenterX(xx);
                ball.setCenterY(yy);
            });

        }
    }
}
