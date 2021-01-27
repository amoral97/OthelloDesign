import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameController {
	// @FXML allows scene builder to recognize indicated variables and methods.
	@FXML
	Pane pane;
	
	private int size = 400;
	private int tiles = 8;
	private int rectangleSize = size / tiles;
	
	private ArrayList<Disk> whiteDisks;
	private ArrayList<Disk> blackDisks;
	
	@FXML
	public void initialize()	{
		// Draws a green gridpane on the javafx pane by getting the size, amount of tiles, and the dividend of the two to draw the individual tiles.
		for (int i = 0; i < size; i += rectangleSize) {
			for (int j = 0; j < size; j += rectangleSize) {
				Rectangle r = new Rectangle(i, j, rectangleSize, rectangleSize);
				
				r.setFill(Color.LIMEGREEN);
				r.setStroke(Color.BLACK);
				pane.getChildren().add(r);
			}
		}
		// Creates and sets white disks on specific x y coordinates
		whiteDisks = new ArrayList<Disk>();
		for (int i = 0; i < tiles; i++) {
			Circle c = new Circle();
			c.setFill(Color.WHITE);
			c.setStroke(Color.BLACK);
			double radius = rectangleSize / 3.0;
			int x = 3 * rectangleSize + (rectangleSize/2);
			int y = 3 * rectangleSize + (rectangleSize/2);
			
			Disk d = new Disk(x, y, radius, c);
			
			whiteDisks.add(d);
			pane.getChildren().add(c);
			d.draw();
		}
		// Creates and sets black disks on specific x y coordinates
		blackDisks = new ArrayList<Disk>();
		for (int i = 0; i < tiles; i ++) {
			Circle c = new Circle ();
			c.setFill(Color.BLACK);
			c.setStroke(Color.BLACK);
			double radius = rectangleSize / 3.0;
			int x = 4 * rectangleSize + (rectangleSize/2);
			int y = 3 * rectangleSize + (rectangleSize/2);
			
			Disk d = new Disk(x, y, radius, c);
			
			blackDisks.add(d);
			pane.getChildren().add(c);
			d.draw();
		}
		
	}
}
