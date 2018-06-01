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
package assignment1.battleship.entity;

/**
 * Player to keep player information
 *    In this case player Id only limited to 2 :  H  for Human , and C for Computer
 * @author lion
 *
 */
public class Player {

	/**
	 * Player() - Default constructor 
	 */
	public Player() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Player(String playerId, String playerName)  - Parameterized Contructor
	 * @param playerId
	 * @param playerName
	 */
	public Player(String playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
	}

	public Player(String playerId, String playerName, int playerTurn,  int playerScore) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerTurn = playerTurn;
		this.playerScore = playerScore;
	}
	
	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	/**
	 * @return the playerTurn
	 */
	public int getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * @param playerTurn the playerTurn to set
	 */
	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	/**
	 * @return the playerScore
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * @param playerScore the playerScore to set
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	private String playerId;
	private String playerName;
	private int playerTurn;
	private int playerScore; 
}
