package wit.comp1050.mastermind;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Peg extends Circle {

    PegColor color;
    Circle circle;

    public Peg(PegColor color, double radius){
        this.color = color;
        this.circle = new Circle(radius);

        this.circle.setStroke(Color.BLACK);
        this.circle.setStrokeWidth(2);
        this.circle.setFill(Color.TRANSPARENT);
    }

}
