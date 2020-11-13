package element;

import globalVar.AppConstants;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class RectNode {

    private int value;
    private Color color;
    private Rectangle rectangle;
    private Label text;
    private StackPane view = new StackPane();

    public RectNode(int value) {

        this.value = value;

        this.color = getRandomColor(value);
        rectangle = new Rectangle(AppConstants.WIDTH_UNIT, value * AppConstants.HEIGHT_UNIT);
        rectangle.setFill(color);
        text = new Label(value + "\n\n");
        text.setTextFill(Color.WHITE);
        text.setStyle("-fx-font-family: Arial");
        view.setAlignment(Pos.BOTTOM_CENTER);
        view.getChildren().addAll(rectangle, text);
    }

    public RectNode() {
        this(1);
    }


    public void setValue(int value) {
        this.value = value;
        this.color = getRandomColor(value);
        rectangle.setFill(color);
    }

    public void setColor(Color color) {
        this.color = color;
        rectangle.setFill(color);
    }


    public int getValue() {
        return value;
    }

    public int getHeight() {
        return (int) rectangle.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Color getColor() {
        return color;
    }

    public StackPane getView() {
        return view;
    }

    public Color getRandomColor(int value) {
        final float hue = (float)value * 4;
        final float saturation = 0.8f;
        final float luminance = 0.5f;
        return Color.hsb(hue, saturation, luminance);
    }

    public void setOriginalColor() {
        setColor(getRandomColor(this.value));
    }


}
