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
 * BattleGrid entity has the basic value a battle grid 
 * 
 */

package assignment1.battleship.entity;

/**
 * Battle Grid will has the coordinat of the grid.
 * @author lion
 *
 */
public class BattleGrid {
	
	/**
	 * default contructor 
	 */
	public BattleGrid() {
		
	}
	
	/**
	 * Paremeterized contructor
	 * @param x
	 * @param y
	 */
	public BattleGrid(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * copy contructor
	 * @param battleGrid
	 */
	public BattleGrid (BattleGrid battleGrid) {
		this (battleGrid.getX(), battleGrid.getY());
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


	private int x;
	private int y;

}
