/*
 * 
 * Leo Sudarma  -  40046196 
 * COMP249
 * Assignment 1 (include the assignment number)
 * Due Date  11:59 PM – February 1, 2017
 * 
 * This is the first assignment of COMP249 - Object Oriented Programming II
 * Concordia  University, Computer Science Class.
 * 
 * Tools :  Eclipse Java EE IDE for Web Developers.
 *          Version: Neon Milestone 4 (4.6.0M4)
 * 
 * Java library :  None
 * 
 */

package assignment1.battleship;

import java.util.Random;
import java.util.Scanner;

/**
 * This program is the first assignment of COMP249 class
 * 
 * Purpose:The purpose of this assignment is to help you 
 *    1.  practice/review arrays of objects and 
 *    2.  objet oriented design.
 *    
 *  Battleship
 *     In this assignment, you will write a program to play a game of Battleship against the computer. Our simplified version of the game will 
 *     	be played on a single 8 by 8 grid. Before the actual game, each player secretly places 6 ships and 4 grenades on the grid.
 *     	Ships and grenades are a single position on the grid. The position of these ships and grenades are of course hidden from the opponent. 
 *      Once both players have placed their ships and grenades, the actual game starts. Each player, in turn, “shoots a rocket” on the grid (i.e. calls a position).
 *     - If the rocket (the position called) falls on a position where there is nothing, then nothing happens, and the other player can shoot his/her rocket.
 *     - If the rocket falls on a coordinate where the opponent (or the player) has a grenade, then the player loses a turn, and next time, the opponent 
 *       will play twice in a row. You will need to keep track of the total number of missed turns due to that and display that number once the game has ended.
 *     - If the rocket falls on a coordinate where the opponent (or the player...) has a ship, then that ship sinks.
 *     - If the rocket falls on a coordinate that has been called before, regardless of what was there before, nothing happens. (So for example, a grenade can 
 *       only explode once).
 *   The goal of the game is to sink all of your opponent’s ships before your opponent sinks yours.
 *   
 *   
 *   Object Oriented Design
 *	The purpose of the assignment is to make you practice proper object-oriented design. Classes must de defined where appropriate, methods should be decomposed appropriately, information should be hidden (private, public, …) and enumerated types should be used where appropriate. In particular, to represent the grid, you are expected to use a 2 dimensional array of objects, where each object represents a position in the grid and should be contain at least:
 * 		1.	the type of element at that position (a ship, a grenade or nothing)
 * 		2.	the owner of the element (the user or the computer)
 * 		3.	whether the position has already been called or not
 * 
 * @author   Leo Sudarma - 40046196
 * @see BattleShip
 * @see 
 * @since 1.0
 *
 */
public class BattleShip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BattleShip playBattleShip = new BattleShip();
		playBattleShip.startGame();
	}
	
	/**
	 * startGame   -  This is where the algorithm for the game will be developed
	 *  Initial phase of the Game:
	 *    1.  System will prepare the battle ground, in this case 8x8 battle ground grid
	 *    2.  The game start by asking user (Human) to input the placement of Ship Position coordinates
	 *    3.  The computer will input his position.   There will be no overlapping position between Human and Computer input. 
	 *  If H(uman) and C(omputer) already placed the ship and grenade position, then game started, Human run first. 
	 *    4.  Alternately Human and Computer will launchRocket to the target grid (8x8 grid). 
	 *        a.  If it hit empty grid, nothing happens,  and the grid become  open.   (x appears on the grid).
	 *        b.  If it hit Ship for 1st time,  message Ship exploded, and x appears on the grid.
	 *        c.  If it hit the same coordinate on case 3b, second time, nothing happens.    (the x still there).
	 *        d.  If it hit the grenade,  the player will lose a turn,  and x appears on the grid. 
	 *    5.  Every turn,  program will displayGrid latest status, but hiding where the Ship and Grenade position for both player, except if it  already hit by Rocket. 
	 *    6.  The winner is the one who destroy all enemy player ship first. 
	 *             
	 *    
	 * @param aa
	 * @return
	 */
	public  void startGame() {
		prepareBattleGroundGrid();
		System.out.println("Hi, let’s play Battleship! ");
		assignShipPosition(HUMAN);   // player placed his ship first, with this param, it would be easy if you want to make 2nd human player in the future
		assignShipPosition(COMPUTER);  // computer will place his ship 
		startBattle(); // start the battle till one player won
		displayBattleGroundGrid(this.STATUS_HIDDEN_MAP); //  (this.STATUS_OPEN_MAP);
		
	}
	
	/**
	 * prepareBattleGroundGrid  - Basically it will create the battle ground grid as the programmer defined
	 * 		Which is for this assignment is a grid of 8x8
	 *      Every grid position will be filled with ShipPosition object with initial status as STATUS_GRID_NOTHING (no ship or grenade). 
	 */
	public void prepareBattleGroundGrid() {
		System.out.println("The battleship ground is a grid of " + GRID_X + " x " + GRID_Y );
		System.out.println("Player must input using 2 digit of this pattern [ABCDEFGH][12345678]");
		System.out.println("Example:   A8,    B4,  C4,  etc.  Thank you for your attention !");
		System.out.println();
		System.out.println("Preparing the battle ground grid");
		
		for (int i=0; i < GRID_X; i++) {
			for (int j=0; j < GRID_Y; j++) {
				ship[i][j] = new ShipPosition(i, j);
			}
		}

		System.out.println("Battle ground grid ready!!");
		System.out.println();
	}
	
	/**
	 * User will input the coordinat of the object (Ship, Grenade, or anything). 
	 * Human will be asked to input the coordinates
	 * Meanwhile COMPUTER will  use random coordinates x,y position
	 * 
	 * How this module works:
	 *    1.  Set status to STATUS_HIDDEN_MAP. 
	 *    2.  Player will placed his ship first. 
	 *        Validation rule :  There will be no overlapping position for each object in the grid
	 *    3.  Player will placed his grenade
	 *        Validation rule :  There will be no overlapping position for each object in the grid
	 * @param owner
	 */
	public void assignShipPosition(String owner) {
		if (owner.compareTo(HUMAN)==0) {
			assignShipByPlayer();
			assignGrenadeByPlayer();
		}  else {
			assignShipByComputer();
			assignGrenadeByComputer();
		}
		
		this.displayBattleGroundGrid(this.STATUS_OPEN_MAP);

	}
	
	/**
	 * assignShipByPlayer  -  When a player assign his ship coordinate, the following rule will apply:
	 * 
	 *	Validate that input must be 2 character only
	 * 	Validate the first acceptable character is one of this character "ABCDEFGH"
	 * 	Validate the second character must be ["12345678"]
	 * 	Validate that position is not occupied by other objects (ship or grenade).
	 */
	private void assignShipByPlayer( ) {
		System.out.println();
		for (int i=0; i < MAX_PLAYER_SHIP; i++) {
			boolean isPlacementValid = false;
			String humanInput =  "";
			while (!isPlacementValid) {
				System.out.print("Enter the coordinates of your ship #" + (i+1) + ": ");
				humanInput = scanner.nextLine().toUpperCase().trim();
				String splitInput[] = humanInput.split("");  // get each character
				if (splitInput.length != 2) {       // validate if user input is 2 digit character
					System.out.println("sorry, Input must be 2 character using this pattern [ABCDEFGH][12345678]. Example A3, B5, etc ");
				} else {
					int xCoordinate = splitInput[0].charAt(0) - 'A';
					int yCoordinate = splitInput[1].charAt(0) - '1';  
					// validate coordinate is corret or not
					if (xCoordinate < 0  || yCoordinate < 0  ||  xCoordinate >= GRID_X  ||  yCoordinate >= GRID_Y) {
						System.out.println("sorry, coordinates outside the grid. try again.");
					}  else {
						// validate grid is already occupied or not
						if (!isShipCoordinatEmpty(yCoordinate, xCoordinate)) {
							System.out.println("sorry, coordinates already used. try again.");
						} else {
							ship[yCoordinate][xCoordinate].setObjectname(SHIP.toLowerCase().trim());
							ship[yCoordinate][xCoordinate].setOwner(HUMAN);
							ship[yCoordinate][xCoordinate].setStatus(STATUS_GRID_SHIP);
							isPlacementValid = true;
						}
					}
				}
			}
			
		}
	}
	
	
	/**
	 * assignGrenadeByPlayer  -  When a player assign his grenade coordinate, the following rule will apply:
	 * 
	 *	Validate that input must be 2 character only
	 * 	Validate the first acceptable character is one of this character "ABCDEFGH"
	 * 	Validate the second character must be ["12345678"]
	 * 	Validate that position is not occupied by other objects (ship or grenade).
	 */
	private void assignGrenadeByPlayer( ) {

		System.out.println();
		for (int i=0; i < MAX_PLAYER_GRENADE; i++) {
			boolean isPlacementValid = false;
			String humanInput =  "";
			while (!isPlacementValid) {
				System.out.print("Enter the coordinates of your grenade #" + (i+1) + ": ");
				humanInput = scanner.nextLine().toUpperCase().trim();
				String splitInput[] = humanInput.split("");  // get each character
				if (splitInput.length != 2) {       // validate if user input is 2 digit character
					System.out.println("sorry, Input must be 2 character using this pattern [ABCDEFGH][12345678]. Example A3, B5, etc ");
				} else {
					int xCoordinate = splitInput[0].charAt(0) - 'A';
					int yCoordinate = splitInput[1].charAt(0) - '1';  
					// validate coordinate is corret or not
					if (xCoordinate < 0  || yCoordinate < 0  ||  xCoordinate >= GRID_X  ||  yCoordinate >= GRID_Y) {
						System.out.println("sorry, coordinates outside the grid. try again.");
					}  else {
						// validate grid is already occupied or not
						if (!isShipCoordinatEmpty( yCoordinate, xCoordinate)) {
							System.out.println("sorry, coordinates already used. try again.");
						} else {
							ship[yCoordinate][xCoordinate].setObjectname(GRENADE.toLowerCase().trim());
							ship[yCoordinate][xCoordinate].setOwner(HUMAN);
							ship[yCoordinate][xCoordinate].setStatus(STATUS_GRID_GRENADE);
							isPlacementValid = true;
						}
					}
				}
			}
			
		}
	}	
	
	/**
	 * assignShipByComputer  -  Computer generated random number
	 *    Computer will generate random number to fill the XY grid position..
	 */
	private void assignShipByComputer() {
		Random random = new Random();
		System.out.println("\nComputer now placing his ship!");
		for (int i=0; i < MAX_PLAYER_SHIP; i++) {
			boolean isPlacementValid = false;
			while (!isPlacementValid) {
				int xCoordinate = random.nextInt(this.GRID_X);
				int yCoordinate = random.nextInt(this.GRID_Y);
				if (isShipCoordinatEmpty(yCoordinate, xCoordinate)) {   // only if grid empty, place computer ship
					ship[yCoordinate][xCoordinate].setObjectname(SHIP.toUpperCase().trim());
					ship[yCoordinate][xCoordinate].setOwner(COMPUTER);
					ship[yCoordinate][xCoordinate].setStatus(STATUS_GRID_SHIP);
					isPlacementValid = true;
				}

			}
		}
	}
	
	/**
	 * assignGrenadeByComputer  -  Computer generated random number
	 *    Computer will generate random number to fill the XY grid position..
	 */
	private void assignGrenadeByComputer() {
		Random random = new Random();
		System.out.println("Computer now placing his grenade!\n");
		for (int i=0; i < MAX_PLAYER_GRENADE; i++) {
			boolean isPlacementValid = false;
			while (!isPlacementValid) {
				int xCoordinate = random.nextInt(this.GRID_X);
				int yCoordinate = random.nextInt(this.GRID_Y);
				if (isShipCoordinatEmpty(yCoordinate, xCoordinate)) {   // only if grid empty, place computer ship
					ship[yCoordinate][xCoordinate].setObjectname(GRENADE.toUpperCase().trim());
					ship[yCoordinate][xCoordinate].setOwner(COMPUTER);
					ship[yCoordinate][xCoordinate].setStatus(STATUS_GRID_GRENADE);
					isPlacementValid = true;
				}
			}
		}
	}
	
	/**
	 * startBattle - the algorithm where human will start first,
	 * After human, computer will play
	 * 
	 */
	public void startBattle() {
		boolean isWinning = false;
		
		System.out.println("OK, the computer placed its ships and grenades at random. Let’s play.");
		this.displayBattleGroundGrid(STATUS_HIDDEN_MAP);
		
		while (!isWinning) {   // play until one player winning
			if (playerTurn >= this.PLAYER_TURN) {
				goPlayer();
				isWinning = isPlayerWinning(HUMAN);
				if (isWinning) {
					System.out.println(" C O N G R A T U L A T I O N... You Win!");
					isWinning = true;
				}
			} else {
				goComputer();
				isWinning = isPlayerWinning(COMPUTER);
				if (isWinning) {
					System.out.println(" C O N G R A T U L A T I O N...  Computer Win!");
					isWinning = true;
				}
			}
			this.displayBattleGroundGrid(STATUS_HIDDEN_MAP);
		}
		
		System.out.println(" C O N G R A T U L A T I O N... ");
		this.displayBattleGroundGrid(this.STATUS_OPEN_MAP);
	}
	
	/**
	 * goPlayer - Player will attack the battle field using rocket
	 *     This module will validate user input, and give message if rocket hit a ship
	 *     1. set the status of ship exploded, or reveal a map that already fired by rocket
	 *     2. Counting score if human hit enemy ship
	 */
	private void  goPlayer() {
		boolean isPlacementValid = false;
		String humanInput =  "";
		boolean isWinning = false;
		while (!isPlacementValid) {
			System.out.print("Human Player.  position of your rocket:");
			humanInput = scanner.nextLine().toUpperCase().trim();
			String splitInput[] = humanInput.split("");  // get each character
			if (splitInput.length != 2) {       // validate if user input is 2 digit character
				System.out.println("sorry, Input must be 2 character using this pattern [ABCDEFGH][12345678]. Example A3, B5, etc ");
			} else {
				int xCoordinate = splitInput[0].charAt(0) - 'A';
				int yCoordinate = splitInput[1].charAt(0) - '1';  
				// validate coordinate is corret or not
				if (xCoordinate < 0  || yCoordinate < 0  ||  xCoordinate >= GRID_X  ||  yCoordinate >= GRID_Y) {
					System.out.println("sorry, coordinates outside the grid. try again.");
				}  else {  // validate if rocket hit a ship, grenade or nothing
					// validate grid is already occupied or not
						int status = ship[yCoordinate][xCoordinate].getStatus();
						String owner = ship[yCoordinate][xCoordinate].getOwner();
						String objectName = ship[yCoordinate][xCoordinate].getObjectname();

						if (status == this.STATUS_EXPLODED || status == STATUS_OPEN_MAP) {
							System.out.println("position already called.");  // nothing happened
						} else  if (status == this.STATUS_GRID_SHIP) {
							if(owner.compareTo(this.HUMAN) == 0) {
								System.out.println("You just hit your own ship!");
								computerScore++;
							} else {
								System.out.println("ship hit.");
								humanScore++;
							}
							ship[yCoordinate][xCoordinate].setStatus(STATUS_EXPLODED);
							
						} else 	if (status == this.STATUS_GRID_GRENADE) {
							System.out.println("boom! grenade.");
							ship[yCoordinate][xCoordinate].setStatus(STATUS_EXPLODED);
							playerTurn--; //penaly losing 1 turn
						} else {
							ship[yCoordinate][xCoordinate].setStatus(STATUS_OPEN_MAP);
						}
						
						isPlacementValid = true;
				}
			}
		}
		if (humanScore == this.MAX_PLAYER_SHIP) {
			isWinning = true;
		}
		playerTurn--;  // the other player turn now
	}
	
	/**
	 * isPlayerWinning (owner)  will check if a player already win 
	 * @param owner
	 * @return
	 */
	private boolean isPlayerWinning(String owner) {
		if (humanScore == this.MAX_PLAYER_SHIP) {
			return true;
		} 
		if (computerScore==this.MAX_PLAYER_SHIP) {
			return true;
		}
		return false;
	}
	
	/**
	 * goComputer - Computer will use random number to launch rocket attack
	 * Update the status of the battle field, and counting computer score 
	 * 
	 * In this system, no Artificial Intelligence is applied.  Only pure random number.
	 * In the future, maybe the developer can add more intelligence system to launch rocket only to non-open-map only 
	 * and not to his own ship or own grenade. :D
	 */
	private void  goComputer() {
		Random random = new Random();
		boolean isWinning = false;
		boolean isPlacementValid = false;
		while (!isPlacementValid) {
			int xCoordinate = random.nextInt(this.GRID_X);
			int yCoordinate = random.nextInt(this.GRID_Y);
			
			String strX = String.valueOf((char) (xCoordinate + 'A'));
			String strY = String.valueOf((char) (yCoordinate + '1'));
			String computerInput = strX + strY;
			
			System.out.println("AI COmputer.  position of my rocket:" +  computerInput);
			
			// validate grid is already occupied or not
			int status = ship[yCoordinate][xCoordinate].getStatus();
			String owner = ship[yCoordinate][xCoordinate].getOwner();
			String objectName = ship[yCoordinate][xCoordinate].getObjectname();

			if (status == this.STATUS_EXPLODED || status == STATUS_OPEN_MAP) {
				System.out.println("position already called.");  // nothing happened
			} else 	if (status == this.STATUS_GRID_SHIP) {
				if(owner.compareTo(this.COMPUTER) == 0) {
					System.out.println("You just hit your own ship!");
					humanScore++;  // opponent get point
				} else {
					System.out.println("ship hit.");
					computerScore++;  // you get point
				}
				ship[yCoordinate][xCoordinate].setStatus(STATUS_EXPLODED);
				
			}  else if (status == this.STATUS_GRID_GRENADE) {
				System.out.println("boom! grenade.");
				ship[yCoordinate][xCoordinate].setStatus(STATUS_EXPLODED);
				playerTurn++; //penaly losing 1 turn
			} else {
				ship[yCoordinate][xCoordinate].setStatus(STATUS_OPEN_MAP);
			}
			isPlacementValid = true;
		}
		if (computerScore == this.MAX_PLAYER_SHIP) {
			isWinning = true;
		}
		playerTurn++;  // the other player turn now
	}	
	
	/**
	 * displayBattleGroundGrid(hiddenOrOpenMap)   - 
	 * 		if the parameter is HIDDEN_MAP then it will hidden the values, an open map status will be shown as *
	 *      if STATUS_OPEN_MAP,  the module will display all battleground grid as is. 
	 * @param hiddenOrOpenMap
	 */
	public void displayBattleGroundGrid(int hiddenOrOpenMap) {
		for (int i=0; i < GRID_X; i++) {
			for (int j=0; j < GRID_Y; j++) {
				if (hiddenOrOpenMap == this.STATUS_OPEN_MAP) {  // open the map grid
					System.out.print(ship[i][j].getObjectname()); //  +ship[i][j].getOwner() + " ");
				} else {  // on play,  hiding the attributes, except map already revealed. 
					int statusGrid = ship[i][j].getStatus();
					String object = ship[i][j].getObjectname();

					if (statusGrid == this.STATUS_OPEN_MAP) {   //  open map is signed by '*'
						System.out.print("* ");
					} else if (statusGrid == this.STATUS_EXPLODED) {  // a ship or grenade is now revealed
						System.out.print(object + " ");
					} else {
						System.out.print("_ ");  // other non-open map will be signed as '_'
					}
				}
			} 
			System.out.println();
		}
	}

	/**
	 * isShipCoordinatEmpty  -  Check if the battleground coordinat is occupied by object or not
	 * Return true if grid empty
	 * Return false if  an object ( ship or a grenade ) occupied that grid coordinat
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isShipCoordinatEmpty(int x, int y) {
		boolean isEmpty = false;
		String objectInCoordinat = ship[x][y].getObjectname();
		int coordinatStatus = ship[x][y].getStatus();
		
		if (coordinatStatus == STATUS_GRID_NOTHING)  {
			isEmpty = true;
		}
		return isEmpty;
	}
	

	// the static variables for Element Name is fixed. 
	private static final String SHIP = "S";
	private static final String GRENADE = "G";
	private static final String  HUMAN = "H";
	private static final String  COMPUTER = "C";
	
	// the static variables for STATUS_GRID is defined as follow
	private static final int STATUS_GRID_NOTHING = 0;
	private static final int STATUS_HIDDEN_MAP = 0;
	private static final int STATUS_GRID_SHIP = 1;
	private static final int STATUS_GRID_GRENADE = 2;
	private static final int STATUS_EXPLODED = 3;
	private static final int STATUS_OPEN_MAP = 9;
	
	
	private static final int PLAYER_TURN = 0;
	private static final int COMPUTER_TURN = 1;
	
	// the maximum element for each player is determined already, including a grid of 8 x 8
	private static final int MAX_PLAYER_SHIP = 6;   // change this variable to upgrade the system one day
	private static final int MAX_PLAYER_GRENADE = 4;
	private static final int GRID_X = 8;  // current requirement a grid of dimension  8x8 
	private static final int GRID_Y = 8; // current requirement a grid of dimension  8x8
	
	private ShipPosition[][]  ship = new ShipPosition[GRID_X][GRID_Y];
	
	private Scanner scanner = new Scanner(System.in);
	private int playerTurn = 0;  // the turn for whoever player play at this time
	private int humanScore =0;
	private int computerScore=0;

}
