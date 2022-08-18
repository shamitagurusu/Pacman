import java.awt.Color;

/**
 * Represents an edible Cherry in the Pacman game, that appears for bonus points
 * for a limited time in level 1.
 * 
 * @author Sanjana Neeli
 * @version May 28, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 * 
 * @author Sources: none
 */
public class Cherry extends Fruit {

	/**
	 * Constructor: calls upon superclass Fruit's constructor, because it shares all
	 * the same functionalities.
	 * 
	 * @param level current game level (superclass constructor parameter)
	 */
	public Cherry(int level) {
		super(level);
		this.setColor(Color.red);
	}
}
