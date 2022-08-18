import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;

/**
 * Represents an edible big pellet in the game, which fills 5 spaces on the
 * board, and is worth more than a small pellet but less than a fruit.
 * 
 * @author Sanjana Neeli
 * @version May 28, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 * 
 * @author Sources: none
 */
public class BigPellet extends Actor implements Edible {

	/**
	 * Constructor: uses Actor's constructor and sets color.
	 */
	public BigPellet() {
		super();
		this.setColor(Color.orange);
	}

	/**
	 * method to get point value, called in Pacman
	 * 
	 * @return point value of the object
	 */
	public int getPointValue() {
		return 50;
	}

	/**
	 * removes the pellet from the grid when eaten by a Pacman
	 */
	public void beEaten() {
		removeSelfFromGrid();
	}
}
