import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {
	// @FXML allows scene builder to recognize indicated variables and methods.
	@FXML
	public Pane pane;
	
	@FXML
	public Pane bottomPane;
	
	@FXML
	public Label whiteDiskCount;
	@FXML
	public Label blackDiskCount;
	@FXML
	public Label finishedLabel;
	
	private int size = 400;
	private int tiles = 8;
	public int rectangleSize = size / tiles;
	
	private Board boardObj;
	
	private Color turn = Color.BLACK; 
	
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
		
		//Board Initialize
		boardObj = new Board(size , tiles, pane, bottomPane, whiteDiskCount, blackDiskCount, finishedLabel);
		
		bottomPane.setStyle("-fx-background-color: grey;");
		
		//Initial valid moves check
		boardObj.nextToDiskSpots();	
		boardObj.lineOfSightCheck(turn);
		
		//Add disks and indicate valid move spots
		boardObj.populateBoard();
		
		mouseClickChecker();
	}
	
	private void mouseClickChecker()
	{
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
		   		int x = (int)event.getSceneX()/rectangleSize;
    			int y = (int)event.getSceneY()/rectangleSize;
    		
    			if(boardObj.guessCounter != 0) turnTry(x, y);
		   	}
		});
	}
	
	private void turnTry(int x, int y)
	{
		if(boardObj.validMoveCheck(x, y, turn))
    	{
    		if(turn == Color.WHITE)
    		{
    			boardObj.setBoardSpot(x, y, 1);
    			boardObj.diskFlipper(Color.WHITE, x, y);
    		}
    		if(turn == Color.BLACK)
    		{
    			boardObj.setBoardSpot(x, y, 2);
    			boardObj.diskFlipper(Color.BLACK, x, y);
    		}
    		//Turn Swap
    		if(turn == Color.BLACK) turn = Color.WHITE;
    		else turn = Color.BLACK;
    	}
        else {System.out.println("Invalid Move"); boardObj.debugDisplayBoard();}
		
    	//Redraw Board and show valid move for next player
		boardObj.nextToDiskSpots();
		boardObj.lineOfSightCheck(turn);
		boardObj.populateBoard();
	}
}
