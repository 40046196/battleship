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
 * Created on  January 12, 2017. 
 * 
 * The ShipPosition is an entity class for the ShipPosition status information
 * Entity class normally only consist of constructor and Setter / Getter only
 * 
 */

package assignment1.battleship;

/**
 * Purpose:The purpose of this assignment is to help you 
 *    1.  practice/review arrays of objects and 
 *    2.  objet oriented design.
 *    
 * This is the entity class for Object ShipPosition
 *   ShipPosition will keep the type of object (either Nothing,  Ship,  ship, Grenade, or grenade)
 *   It also keep the coordinat (x, y) of the  object 
 *   And the status (Open Map,  Ship Hit, Grenade Hit, etc as the programmer define).
 *   
 * This object will be used in the arrays of objects later in the assignments. 
 * ShipPosition will keep the object, position, and status
 * 
 *  *   Object Oriented Design
 *	The purpose of the assignment is to make you practice proper object-oriented design. Classes must de defined where appropriate, methods should be decomposed appropriately, information should be hidden (private, public, …) and enumerated types should be used where appropriate. In particular, to represent the grid, you are expected to use a 2 dimensional array of objects, where each object represents a position in the grid and should be contain at least:
 * 		1.	the type of element at that position (a ship, a grenade or nothing)
 * 		2.	the owner of the element (the user or the computer)
 * 		3.	whether the position has already been called or not
 * 
 * @author   Leo Sudarma - 40046196
 * @see ShipPosition
 * @since 1.0

 *
 */
public class ShipPosition {
	
	/**
	 * a blanc constructor will be used for initiation
	 */
	public ShipPosition() {
		
	}
	
	/**
	 * Constructor with initial value of the object information
	 * The status by default will be Neutral (0). 
	 * @param objectname
	 * @param x
	 * @param y
	 */
	public ShipPosition(String objectname, int x, int y) {
		this.objectname = objectname;
		this.x = x;
		this.y = y;
		this.status = 0;    
	}	
	
	/**
	 * Constructor with initial value of the object information
	 * The status by default will be Neutral (0). 
	 * @param objectname
	 * @param x
	 * @param y
	 */
	public ShipPosition(int x, int y) {
		this.objectname = "_"; // empty object
		this.x = x;
		this.y = y;
		this.status = 0;
		this.owner = "";
	}	
	
	

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * @return the objectname
	 */
	public String getObjectname() {
		return objectname;
	}

	/**
	 * @param objectname the objectname to set
	 */
	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	private String objectname;    //  user defined such  as S (ship),  or  G (grenade)
	private int x;     // the x position
	private int y;      // the y position	
	private String owner;
	private int status;    // either ship already sunk(exploded), or not
	
}
