import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disk {

	private double x;
	private double y;
	private double radius;
	private Circle circle;

	public Disk(double x, double y, double radius, Circle c) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.circle = c;

	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;

	}

	public void setX(double x) {
		this.x = x;

	}

	public double getX() {
		return x;
	}

	public void setColor(Color color) {
		circle.setFill(color);
	}

	public void draw() {
		circle.setRadius(radius);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
	}
}
