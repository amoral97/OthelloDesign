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
		board[3][3] = 1;
		board[4][4] = 1;
		board[3][4] = 2;
		board[4][3] = 2;
		
		//Initial valid moves check
		nextToDiskSpots();	
		lineOfSightCheck(turn);
		
		populateBoard();
		
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
		
		//Board Array Key: 0 = blank, 1 = white, 2 = black, -1 = valid move
		if (color == Color.WHITE) board[column][row] = 1;
		if (color == Color.BLACK) board[column][row] = 2;
		
		pane.getChildren().add(c);
		d.draw();
	}
	
	private void mouseClickChecker()
	{
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		    	int x = (int)event.getSceneX()/rectangleSize;
    			int y = (int)event.getSceneY()/rectangleSize;
		    	if(validMoveCheck(x, y, turn))
		    		{
		    			if(turn == Color.WHITE)
		    			{
		    				board[x][y]=1;
		    				diskFlipper(Color.WHITE, x, y);
		    			}
		    			if(turn == Color.BLACK)
		    			{
		    				board[x][y]=2;
		    				diskFlipper(Color.BLACK, x, y);
		    			}
		    			//Turn Swap
		    			if(turn == Color.BLACK) turn = Color.WHITE;
		    			else turn = Color.BLACK;
		    		}
		        else {System.out.println("Invalid Move"); debugDisplayBoard();}
		    	
		    	//Redraw Board and show valid move for next player
    			nextToDiskSpots();
    			lineOfSightCheck(turn);
    			populateBoard();
		    }
		});
	}
	
	private boolean validMoveCheck(int column, int row, Color color)
	{
		boolean validMove = true;

		if(board[column][row] != -1) validMove = false;
        
		return validMove;
	}

	private void nextToDiskSpots()
	{
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
		debugDisplayBoard();
	}
	
	private void lineOfSightCheck(Color color) {
		for(int i = 0; i < tiles; i++)
		{
			for(int z = 0; z < tiles; z++)
			{
				if(board[z][i] == -1)
				{
					board[z][i] = 0;
					//Check Right
					for(int x = z+1; x < tiles; x++)
					{
						if(board[x][i] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[x][i] == 2)
							{
								if(Math.abs(z - x) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
						if(color == Color.WHITE)
						{
							if(board[x][i] == 1)
							{
								if(Math.abs(z - x) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
					}
					
					//Check Left
					for(int x = z-1; x > 0; x--)
					{
						if(board[x][i] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[x][i] == 2)
							{
								if(Math.abs(z - x) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
						if(color == Color.WHITE)
						{
							if(board[x][i] == 1)
							{
								if(Math.abs(z - x) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
					}
					
					//Check Down
					for(int y = i+1; y < tiles; y++)
					{
						if(board[z][y] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[z][y] == 2)
							{
								if(Math.abs(i - y) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
						if(color == Color.WHITE)
						{
							if(board[z][y] == 1)
							{
								if(Math.abs(i - y) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
					}
					
					//Check Up
					for(int y = i-1; y > 0; y--)
					{
						if(board[z][y] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[z][y] == 2)
							{
								if(Math.abs(i - y) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
						if(color == Color.WHITE)
						{
							if(board[z][y] == 1)
							{
								if(Math.abs(i - y) <= 1) break;
								else
								{
									board[z][i] = -1;
									break;
								}
							}
						}
					}
					
					//Diagonal Checks
					//Right Down
					int y = i;
					if(i+1 < tiles)
					{
						y = i+1;
						for(int x = z+1; x < tiles; x++)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							
							if(y < tiles-1) y++;
							else break;
						}
					}
					
					//Left Down
					if(i+1 < tiles)
					{
						y = i+1;
						for(int x = z-1; x > 0; x--)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;	
										break;
									}
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							
							if(y < tiles-1) y++;
							else break;
						}
					}
					
					//Left Up
					if(i-1 > 1)
					{
						y = i-1;
						for(int x = z-1; x > 0; x--)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							
							if(y > 0) y--;
							else break;
						}
					}
					
					//Right Up
					if(i-1 > 1)
					{
						y = i-1;
						for(int x = z+1; x < tiles; x++)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									if(Math.abs(z - x) <= 1 || Math.abs(i - y) <= 1) break;
									else
									{
										board[z][i] = -1;
										break;
									}
								}
							}
							
							if(y > 0) y--;
							else break;
						}
					}
				}
			}
		}
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
		System.out.println("------------------------");
	}
	
	private void populateBoard()
	{
		for(int z = 0; z < diskArray.size(); z++)
		{
			pane.getChildren().clear();
			
			for (int i = 0; i < size; i += rectangleSize) {
				for (int j = 0; j < size; j += rectangleSize) {
					Rectangle r = new Rectangle(i, j, rectangleSize, rectangleSize);
					
					r.setFill(Color.LIMEGREEN);
					r.setStroke(Color.BLACK);
					pane.getChildren().add(r);
				}
			}
			
			diskArray.remove(z);
		}
		
		for(int i = 0; i < tiles; i++)
		{
			for(int z = 0; z < tiles; z++)
			{
				if(board[z][i] == 0)
				{
					//Don't add a disk
				}
				if(board[z][i] == 1)
				{
					createDisk(Color.WHITE, z, i);
				}
				if(board[z][i] == 2)
				{
					createDisk(Color.BLACK, z, i);
				}
				if(board[z][i] == -1)
				{
					createDisk(Color.YELLOW, z, i);
				}
			}
		}
	}
	
	private void diskFlipper(Color color, int z, int i) {
		
					//Check Right line of sight
					boolean lineOfSight = false;
					for(int x = z+1; x < tiles; x++)
					{
						if(board[x][i] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[x][i] == 2)
							{
								lineOfSight = true;
								break;
							}
						}
						if(color == Color.WHITE)
						{
							if(board[x][i] == 1)
							{
								lineOfSight = true;
								break;
							}
						}
					}
					//If line of sight is found, flip disks
					if(lineOfSight)
					{
						for(int x = z+1; x < tiles; x++)
						{
							if(board[x][i] <= 0) break;
							
							if(color == Color.BLACK)
							{
								
								if(board[x][i] == 2)
								{
									break;
								}
								board[x][i] = 2;
							}
							if(color == Color.WHITE)
							{
								
								if(board[x][i] == 1)
								{
									break;
								}
								board[x][i] = 1;
							}
						}
					}
					
					//Check Left
					lineOfSight = false;
					for(int x = z-1; x > 0; x--)
					{
						if(board[x][i] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[x][i] == 2)
							{
								lineOfSight = true;
								break;
							}
						}
						if(color == Color.WHITE)
						{
							if(board[x][i] == 1)
							{
								lineOfSight = true;
								break;
							}
						}
					}
					if(lineOfSight)
					{
						for(int x = z-1; x > 0; x--)
						{
							if(board[x][i] <= 0) break;
							
							if(color == Color.BLACK)
							{
								
								if(board[x][i] == 2)
								{
									break;
								}
								board[x][i] = 2;
							}
							if(color == Color.WHITE)
							{
								
								if(board[x][i] == 1)
								{
									break;
								}
								board[x][i] = 1;
							}
						}
					}
					
					//Check Down
					lineOfSight = false;
					for(int y = i+1; y < tiles; y++)
					{
						if(board[z][y] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[z][y] == 2)
							{
								lineOfSight = true;
								break;
							}
						}
						if(color == Color.WHITE)
						{
							if(board[z][y] == 1)
							{
								lineOfSight = true;
								break;
							}
						}
					}
					
					if(lineOfSight)
					{
						for(int y = i+1; y < tiles; y++)
						{
							if(board[z][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								
								if(board[z][y] == 2)
								{
									break;
								}
								board[z][y] = 2;
							}
							if(color == Color.WHITE)
							{
								if(board[z][y] == 1)
								{
									break;
								}
								board[z][y] = 1;
							}
						}
					}
					
					//Check Up
					lineOfSight = false;
					for(int y = i-1; y > 0; y--)
					{
						if(board[z][y] <= 0) break;
						
						if(color == Color.BLACK)
						{
							if(board[z][y] == 2)
							{
								lineOfSight = true;
								break;
							}
						}
						if(color == Color.WHITE)
						{
							if(board[z][y] == 1)
							{
								lineOfSight = true;
								break;
							}
						}
					}
					
					if(lineOfSight)
					{
						for(int y = i-1; y > 0; y--)
						{
							if(board[z][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[z][y] == 2)
								{
									break;
								}
								board[z][y] = 2;
							}
							if(color == Color.WHITE)
							{
								if(board[z][y] == 1)
								{
									break;
								}
								board[z][y] = 1;
							}
						}
					}
					
					//Diagonal Checks
					//Right Down
					lineOfSight = false;
					int y = i;
					if(i+1 < tiles)
					{
						y = i+1;
						for(int x = z+1; x < tiles; x++)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									lineOfSight = true;
									break;
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									lineOfSight = true;
									break;
								}
							}
							
							if(y < tiles-1) y++;
							else break;
						}
					}
					
					if(lineOfSight)
					{
						y = i;
						if(i+1 < tiles)
						{
							y = i+1;
							for(int x = z+1; x < tiles; x++)
							{
								if(board[x][y] <= 0) break;
								
								if(color == Color.BLACK)
								{
									if(board[x][y] == 2)
									{
										break;
									}
									board[x][y] = 2;
								}
								if(color == Color.WHITE)
								{
									if(board[x][y] == 1)
									{
										break;
									}
									board[x][y] = 1;
								}
								
								if(y < tiles-1) y++;
								else break;
							}
						}
					}
					
					//Left Down
					lineOfSight = false;
					if(i+1 < tiles)
					{
						y = i+1;
						for(int x = z-1; x > 0; x--)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									lineOfSight = true;
									break;
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									lineOfSight = true;
									break;
								}
							}
							
							if(y < tiles-1) y++;
							else break;
						}
					}
					
					if(lineOfSight)
					{
						if(i+1 < tiles)
						{
							y = i+1;
							for(int x = z-1; x > 0; x--)
							{
								if(board[x][y] <= 0) break;
								
								if(color == Color.BLACK)
								{
									if(board[x][y] == 2)
									{
										break;
									}
									board[x][y] = 2;
								}
								if(color == Color.WHITE)
								{
									if(board[x][y] == 1)
									{
										break;
									}
									board[x][y] = 1;
								}
								
								if(y < tiles-1) y++;
								else break;
							}
						}
					}
					
					//Left Up
					lineOfSight = false;
					if(i-1 > 1)
					{
						y = i-1;
						for(int x = z-1; x > 0; x--)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									lineOfSight = true;
									break;
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									lineOfSight = true;
									break;
								}
							}
							
							if(y > 0) y--;
							else break;
						}
					}
					
					if(lineOfSight)
					{
						if(i-1 > 1)
						{
							y = i-1;
							for(int x = z-1; x > 0; x--)
							{
								if(board[x][y] <= 0) break;
								
								if(color == Color.BLACK)
								{
									if(board[x][y] == 2)
									{
										break;
									}
									board[x][y] = 2;
								}
								if(color == Color.WHITE)
								{
									if(board[x][y] == 1)
									{
										break;
									}
									board[x][y] = 1;
								}
								
								if(y > 0) y--;
								else break;
							}
						}
					}
					
					//Right Up
					lineOfSight = false;
					if(i-1 > 1)
					{
						y = i-1;
						for(int x = z+1; x < tiles; x++)
						{
							if(board[x][y] <= 0) break;
							
							if(color == Color.BLACK)
							{
								if(board[x][y] == 2)
								{
									lineOfSight = true;
									break;
								}
							}
							if(color == Color.WHITE)
							{
								if(board[x][y] == 1)
								{
									lineOfSight = true;
									break;
								}
							}
							
							if(y > 0) y--;
							else break;
						}
					}
					
					if(lineOfSight)
					{
						if(i-1 > 1)
						{
							y = i-1;
							for(int x = z+1; x < tiles; x++)
							{
								if(board[x][y] <= 0) break;
								
								if(color == Color.BLACK)
								{
									if(board[x][y] == 2)
									{
										break;
									}
									board[x][y] = 2;
								}
								if(color == Color.WHITE)
								{
									if(board[x][y] == 1)
									{
										break;
									}
									board[x][y] = 1;
								}
								
								if(y > 0) y--;
								else break;
							}
						}
					}
				}
	
	public int getRectangleSize() {
		return rectangleSize;
	}


}
