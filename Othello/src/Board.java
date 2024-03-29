import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Handles all tasks relating to keeping track of the board state and
 * displaying the board to the players.
 * 
 * @author Adan Morales & Harrison Gardner
 *
 */
public class Board {
	private Pane pane;
	private Pane bottomPane;
	private Label whiteDiskCount;
	private Label blackDiskCount;
	
	private Label finishedLabel;
	
	private int[][] board;
	
	private int size;
	private int tiles;
	public int rectangleSize;
	
	private ArrayList<Disk> diskArray;
	
	public int guessCounter = 0;
	public int whiteAmount = 0;
	public int blackAmount = 0;
	
	/**
	 * Sets up the board object variables.
	 * 
	 * @param size
	 * @param tiles
	 * @param pane
	 * @param bottomPane
	 * @param whiteDiskCount
	 * @param blackDiskCount
	 * @param finishedLabel
	 */
	public Board(int size, int tiles, Pane pane, Pane bottomPane, Label whiteDiskCount, Label blackDiskCount, Label finishedLabel)
	{
		//Pane pass
		this.pane = pane;
		this.bottomPane = bottomPane;
		
		//Disk labels pass
		this.blackDiskCount = blackDiskCount;
		this.whiteDiskCount = whiteDiskCount;
		this.finishedLabel = finishedLabel;
			
		//Dimensions setup
		this.tiles = tiles;
		this.size = size;
		this.rectangleSize = size / tiles;
		
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
	}
	
	/**
	 * Finds potential valid moves by
	 * outlining all spots that are empty
	 * and next to a disk.
	 */
	public void nextToDiskSpots()
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
	}
	
	/**
	 * Checks that the potential valid move spot
	 * has a disk in it's line of sight that
	 * matches the current players color.
	 * 
	 * @param color
	 */
	public void lineOfSightCheck(Color color) {
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
	
	/**
	 * After a disk is placed, finds all opposite color
	 * disks that need to be flipped.
	 * 
	 * @param color
	 * @param z
	 * @param i
	 */
	public void diskFlipper(Color color, int z, int i) {
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
	
	/**
	 * Creates the disk objects based on the 2d board
	 * array and finds the amount of each disk color.
	 */
	public void populateBoard()
	{
		
		whiteAmount = 0;
		blackAmount = 0;
		guessCounter = 0;
		
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
				//Board Array Key: 0 = blank, 1 = white, 2 = black, -1 = valid move
				if(board[z][i] == 0)
				{
					
				}
				if(board[z][i] == 1)
				{
					createDisk(1, z, i);
					whiteAmount++;
					
				}
				if(board[z][i] == 2)
				{
					createDisk(2, z, i);
					blackAmount++;
				}
				if(board[z][i] == -1)
				{
					createDisk(3, z, i);
					guessCounter++;
				}
			}
		}
		
		if(guessCounter == 0)
		{
			if(whiteAmount > blackAmount) finishedLabel.setText("Finished! White is the winner!");
			else finishedLabel.setText("Finished! Black is the winner!");
		}
		
		whiteDiskCount.setText("" + whiteAmount);
		blackDiskCount.setText("" + blackAmount);
	}
	
	/**
	 * Small method to quickly tell if a move is
	 * in a valid spot.
	 * 
	 * @param column
	 * @param row
	 * @param color
	 * @return
	 */
	public boolean validMoveCheck(int column, int row, Color color)
	{
		boolean validMove = true;

		if(board[column][row] != -1) validMove = false;
        
		return validMove;
	}
	
	/**
	 * Create a disk based on color and board coordinates.
	 * 
	 * @param type
	 * @param column
	 * @param row
	 */
	private void createDisk(int type, int column, int row)
	{
		Circle c = new Circle ();
		c.setStroke(Color.BLACK);
		switch(type) {
			case 1:
				c.setFill(Color.WHITE);
				break;
			case 2:
				c.setFill(Color.BLACK);
				break;
			case 3: 
				c.setFill(Color.TRANSPARENT);
				c.setStroke(Color.YELLOW);
				c.setStrokeWidth(10);
		}
	
		double radius = rectangleSize / 3.0;
		
		Disk d = new Disk(column, row, radius, c);
		
		diskArray.add(d);
		
		pane.getChildren().add(c);
		d.draw();
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

	public void setBoardSpot(int x, int y, int type) {
		board[x][y] = type;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}
}
