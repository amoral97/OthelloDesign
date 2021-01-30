import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disk extends GameController{

	public int column;
	public int row;
	private double radius;
	public Circle circle;

	public Disk(int column, int row, double radius, Circle c) {
		this.column = column;
		this.row = row;
		this.radius = radius;
		this.circle = c;
	}

	public void setColor(Color color) {
		circle.setFill(color);
	}

	public void draw() {
		circle.setRadius(radius);
		circle.setTranslateX(column * rectangleSize + (rectangleSize/2));
		circle.setTranslateY(row * rectangleSize + (rectangleSize/2));
	}

	public int getColumn() {
		return this.column;
	}

	public int getRow() {
		return this.row;
	}
	
	
}
