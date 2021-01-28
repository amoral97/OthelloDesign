import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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
	public int rectangleSize = size / tiles;
	
	private ArrayList<Disk> diskArray;
	private int[][] board;
	
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
		

		//Disk Object Array initialize
		diskArray = new ArrayList<Disk>();
		
		//Board Array Initialize
		board = new int[tiles][tiles];
		for(int i = 0; i < tiles; i++)
		{
			for(int z = 0; z < tiles; z++)
			{
				board[z][i] = 0;
			}
		}
		
		//Initial board disks
		createDisk(Color.BLACK, 4, 3);
		createDisk(Color.BLACK, 3, 4);
		createDisk(Color.WHITE, 3, 3);
		createDisk(Color.WHITE, 4, 4);
		
		mouseClickChecker();
		
	}
	
	//Create a disk based on color and board coordinates
	private void createDisk(Color color, int column, int row)
	{
		Circle c = new Circle ();
		c.setFill(color);
		c.setStroke(Color.BLACK);
		double radius = rectangleSize / 3.0;
		
		Disk d = new Disk(column, row, radius, c);
		
		diskArray.add(d);
		
		//Board Array Key: 0 = blank, 1 = white, 2 = black
		if (color == Color.WHITE) board[column][row] = 1;
		if (color == Color.BLACK) board[column][row] = 2;
		
		System.out.println(row);
		
		pane.getChildren().add(c);
		d.draw();
	}
	
	
	
	//Temporary, Seeing how to handle mouse clicks and placing disks on the board.
	//Works so far but not made to interact with much else
	private void mouseClickChecker()
	{
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	if(validMoveCheck((int)event.getSceneX()/rectangleSize, (int)event.getSceneY()/rectangleSize)) createDisk(Color.BLACK, (int)event.getSceneX()/rectangleSize, (int)event.getSceneY()/rectangleSize);
		        else {System.out.println("Invalid Move"); debugDisplayBoard();}
		    }
		});
	}
	
	private boolean validMoveCheck(int column, int row)
	{
		boolean validMove = true;
		
		//Decide valid disk spots
		//This is gonna look really gross
		for(int i = 0; i < tiles; i++)
		{
			for(int z = 0; z < tiles; z++)
			{
				if(board[z][i] == 0)
				{
					if(i == 0 && z == 0)
					{
						if(board[z+1][i] > 0)board[z][i] = -1;
						if(board[z+1][i+1] > 0)board[z][i] = -1;
						if(board[z][i+1] > 0)board[z][i] = -1;
					}
					else if(i == 0 && z < 7)
					{
						if(board[z+1][i] > 0)board[z][i] = -1;
						if(board[z-1][i] > 0)board[z][i] = -1;
						if(board[z][i+1] > 0)board[z][i] = -1;
						if(board[z+1][i+1] > 0)board[z][i] = -1;
						if(board[z-1][i+1] > 0)board[z][i] = -1;
					}
					else if(i == 0 && z == 7)
					{
						if(board[z-1][i] > 0)board[z][i] = -1;
						if(board[z][i+1] > 0)board[z][i] = -1;
						if(board[z-1][i+1] > 0)board[z][i] = -1;
					}
					else if(i < 7 && z == 7)
					{
						if(board[z-1][i] > 0)board[z][i] = -1;
						if(board[z][i+1] > 0)board[z][i] = -1;
						if(board[z][i-1] > 0)board[z][i] = -1;
						if(board[z-1][i-1] > 0)board[z][i] = -1;
						if(board[z-1][i+1] > 0)board[z][i] = -1;
					}
					else if(i == 7 && z == 0)	
					{
						if(board[z+1][i] > 0)board[z][i] = -1;
						if(board[z][i-1] > 0)board[z][i] = -1;
						if(board[z+1][i-1] > 0)board[z][i] = -1;
					}
					else if(i < 7 && z == 0)
					{
						if(board[z+1][i] > 0)board[z][i] = -1;
						if(board[z][i+1] > 0)board[z][i] = -1;
						if(board[z][i-1] > 0)board[z][i] = -1;
						if(board[z+1][i+1] > 0)board[z][i] = -1;
						if(board[z+1][i-1] > 0)board[z][i] = -1;
					}
					else if(i == 7 && z == 7)
					{
						if(board[z-1][i] > 0)board[z][i] = -1;
						if(board[z][i-1] > 0)board[z][i] = -1;
						if(board[z-1][i-1] > 0)board[z][i] = -1;
					}
					else if(i == 7 && z < 7)
					{
						if(board[z+1][i] > 0)board[z][i] = -1;
						if(board[z-1][i] > 0)board[z][i] = -1;
						if(board[z][i-1] > 0)board[z][i] = -1;
						if(board[z-1][i-1] > 0)board[z][i] = -1;
						if(board[z+1][i-1] > 0)board[z][i] = -1;
					}
					else if(i < 7 && z < 7)
					{
						if(board[z+1][i] > 0)board[z][i] = -1;
						if(board[z-1][i] > 0)board[z][i] = -1;
						if(board[z][i+1] > 0)board[z][i] = -1;
						if(board[z][i-1] > 0)board[z][i] = -1;
						if(board[z+1][i+1] > 0)board[z][i] = -1;
						if(board[z-1][i-1] > 0)board[z][i] = -1;
						if(board[z+1][i-1] > 0)board[z][i] = -1;
						if(board[z-1][i+1] > 0)board[z][i] = -1;
					}
				}
			}
		}

		//Check if a disk is already there
        if(board[column][row] != -1) validMove = false;
        
		return validMove;
	}
	
	public void debugDisplayBoard()
	{
		for(int i = 0; i < tiles; i++)
		{
			for(int z = 0; z < tiles; z++)
			{
				System.out.printf("%3d", board[z][i]);
			}
			System.out.println();
		}
	}
	
	public int getRectangleSize() {
		return rectangleSize;
	}
}
