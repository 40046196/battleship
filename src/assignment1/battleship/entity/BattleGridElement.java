/**
 * Leo Sudarma  -  40046196 
 * COMP249
 * Assignment 1 (include the assignment number)
 * Due Date  11:59 PM – February 1, 2017
 * 
 * This is the first assignment of COMP249 - Object Oriented Programming II
 * Concordia  University, Computer Science Class.
 *
 *  *   Object Oriented Design
 *	The purpose of the assignment is to make you practice proper object-oriented design. Classes must de defined where appropriate, methods should be decomposed appropriately, information should be hidden (private, public, …) and enumerated types should be used where appropriate. In particular, to represent the grid, you are expected to use a 2 dimensional array of objects, where each object represents a position in the grid and should be contain at least:
 * 		1.	the type of element at that position (a ship, a grenade or nothing)
 * 		2.	the owner of the element (the user or the computer)
 * 		3.	whether the position has already been called or not
  **/
package assignment1.battleship.entity;

/**
 * BattleGridElement has the following attributes :
 *  	-  the owner of the grid element, 
 *  	-  the element name (could be S[ship], s[ship], G[grenade], g[renade], or nothing
 * 		-  the status of the grid  (revealed or not revealed).
 *
 */
public class BattleGridElement extends BattleGrid {
	
	/**
	 * @param player
	 * @param elementName
	 * @param owner
	 * @param status
	 */
	public BattleGridElement(Player player, String elementName, Player owner, int status) {
		super();
		this.player = player;
		this.elementName = elementName;
		this.owner = owner;
		this.status = status;
	}	
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * @param elementName the elementName to set
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}



	private Player player; 
	private String elementName;  // could be S, s , G, or g
	private Player owner;
	private int status;   //  currently revealed or not revealed, but in the future possible to add more status

}
