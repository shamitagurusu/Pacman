import java.util.HashMap;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;

/**
 * Represents an edible Fruit in the game, the designated superclass for all
 * individual Fruit-type subclasses created within the Pacman class during the
 * game. Cherry, Orange, Strawberry, and Watermelon classes all share the same
 * methods, listed in this class.
 * 
 * @author Sanjana Neeli
 * @version May 28, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 * 
 * @author Sources: none
 */
public class Fruit extends Actor implements Edible {

	// stores the point values for the different fruits, based on level
	private HashMap<String, Integer> pointVal = null;
	// current game level
	private int gameLevel = 0;

	/**
	 * Constructor: initializes and creates the HashMap that stores point values for
	 * the different Fruits based on game level, stores the game level
	 * 
	 * @param level game level
	 */
	public Fruit(int level) {
		super();
		pointVal = new HashMap<String, Integer>();
		pointVal.put("1", 100);
		pointVal.put("2", 200);
		pointVal.put("3", 300);
		pointVal.put("4", 400);
		gameLevel = level;
	}

	/**
	 * returns the point values from the HashMap, converting the current level into
	 * a string and using it as a key
	 * 
	 * @return point value of the fruit
	 */
	public int getPointValue() {
		return pointVal.get("" + gameLevel);
	}

	/**
	 * removes the fruit from the grid when eaten by a Pacman
	 */
	public void beEaten() {
		removeSelfFromGrid();
	}

}
