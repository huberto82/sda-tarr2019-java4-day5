package javafx;

import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Arrow {
    private Arrow(){
    }

    public static Path create(double x, double y, double size){
        Path shape = new Path();
        shape.getElements().addAll(
            new MoveTo(x, y),
            new LineTo(x + size, y),
            new MoveTo(x, y),
            new LineTo(x + size/4, y - size/4),
            new MoveTo(x,y),
            new LineTo(x + size/4, y + size/4)
        );
        return shape;
    }
}
