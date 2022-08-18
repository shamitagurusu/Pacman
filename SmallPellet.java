import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;

/**
 * Represents an edible small pellet in the game, which fills all empty spaces
 * of the board, and must all be eaten in order to win the game.
 * 
 * @author Sanjana Neeli
 * @version May 28, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 * 
 * @author Sources: none
 */
public class SmallPellet extends Actor implements Edible {

	/**
	 * Constructor: uses Actor's constructor and sets color.
	 */
	public SmallPellet() {
		super();
		this.setColor(Color.orange);
	}

	/**
	 * method to get point value, called in Pacman
	 * 
	 * @return point value of the object
	 */
	public int getPointValue() {
		return 10;
	}

	/**
	 * removes the pellet from the grid when eaten by a Pacman
	 */
	public void beEaten() {
		removeSelfFromGrid();
	}

}
