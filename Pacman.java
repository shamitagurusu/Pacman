import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.KeyStroke;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.gui.GUIController;

/**
 * Represents a Pacman character in the Pacman game Pacman moves according to
 * user input
 *
 * @author Shamita Gurusu and Cheryl Lin
 * @version May 28, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 *
 * @author Sources: none
 */
public class Pacman extends Bug implements Edible {

	private double pointTotal = 0;
	ActorWorld w = null;
	int numBigPellets = 0;
	int numSmallPellets = 0;
	private int gameLevel;
	Board gameBoard = null;

	int time = 0;
	int fruitTime = 0;
	private boolean isPowerUp = false;
	private boolean hasFruitAlreadyAppeared = false;
	private boolean hasDisappeared = false;
	HashMap<String, Fruit> type = null;

	/**
	 * 
	 * @param color           color of the pacman
	 * @param pointTotal      initial pointotal which should be 0
	 * @param w               the Actor world that the pacman is in
	 * @param numBigPellets   the total numberofBigpellets on the gameboard
	 *                        initially
	 * @param numSmallPellets the total numberofSmallPellets on the gameboard
	 *                        initially
	 * @param gameLevel       the level of the game
	 * @param gameBoard       the gameBoard
	 * 
	 */
	public Pacman(Color color, double pointTotal, ActorWorld w, int numBigPellets, int numSmallPellets, int gameLevel,
			Board gameBoard) {
		this.setColor(color);
		this.pointTotal = pointTotal;
		this.w = w;
		this.numBigPellets = numBigPellets;
		this.numSmallPellets = numSmallPellets;
		this.gameLevel = gameLevel;
		this.gameBoard = gameBoard;
		type = new HashMap<String, Fruit>();
		type.put("cherry", new Cherry(gameLevel));
		type.put("orange", new Orange(gameLevel));
		type.put("strawberry", new Strawberry(gameLevel));
		type.put("watermelon", new Watermelon(gameLevel));

	}

	/**
	 * controls the movement of the Pacman - if user specifies movement, the Pacman
	 * will turn, otherwise it will move forward if it can. the time dependent
	 * traits, like being in PowerUp mode and the appearance of the Fruit are
	 * controlled and turned on and off. user controls are also monitored in this
	 * method
	 */
	public void act() {

		if (isPowerUp == true) {
			time++;
			if (time > 25) {
				time = 0;
				isPowerUp = false;
			}
		}

		if (hasFruitAlreadyAppeared == true && hasDisappeared == false) {
			fruitTime++;
			if (fruitTime > 20) {
				if (getFruit().getLocation() != null) {
					w.remove(getFruit().getLocation());
				}
				hasDisappeared = true;
			}
		}

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent event) {
				String key = KeyStroke.getKeyStrokeForEvent(event).toString();

				if (key.equals("pressed RIGHT")) {
					setDirection(90);
				} 
				else if (key.equals("pressed LEFT")) {
					setDirection(270);
				} 
				else if (key.equals("pressed DOWN")) {
					setDirection(180);
				}
				else if (key.equals("pressed UP")) {
					setDirection(0);
				}
				return true;

			}
		});

		if (canMove()) {
			jump();
		}
	}

	/**
	 * The pacman can move only when the adjacent square is empty or if the adjacent
	 * square contains a bigPellet, smallPellet or fruit or ghost(only when the
	 * ghosts are blue, which is after the pacman eats a bigpellet)
	 * 
	 * @return boolean value of whether the pacman can move or not
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		Location adj = getLocation().getAdjacentLocation(getDirection());
		int cols = gr.getNumCols();
		int rows = gr.getNumRows();
		if (adj.getCol() > cols - 1 || adj.getRow() > rows - 1 || adj.getCol() < 0 || adj.getRow() < 0) {
			return false;
		}

		Actor atJump = gr.get(adj);

		if (atJump == null) {
			return true;
		}

		else if (atJump instanceof SmallPellet) {
			eatSmallPellet((SmallPellet) atJump);
			return true;

		}

		else if (atJump instanceof BigPellet) {
			eatBigPellet((BigPellet) atJump);
			return true;
		}

		else if (atJump instanceof Fruit) {
			eatFruit((Fruit) atJump);
			return true;
		}

		return false;
	}

	/**
	 * the pacman moves to the adjacent location, jump = move
	 */
	public void jump() {
		if (getLocation() == null) {
			return;
		}
		moveTo(getLocation().getAdjacentLocation(getDirection()));
	}

	/**
	 * eats a bigpellet at a given location
	 * 
	 * @param b the bigpellet at a given location on the gameboard
	 */
	public void eatBigPellet(BigPellet b) {
		setPointTotal(b.getPointValue());
		b.beEaten();
		isPowerUp = true;
		numBigPellets--;

		if (numBigPellets == 0 && numSmallPellets == 0) {
			win();
		}

	}

	/**
	 * eats a smallpellet at a given location
	 * 
	 * @param s the smallpelet at a given location on the gameboard
	 */
	public void eatSmallPellet(SmallPellet s) {
		setPointTotal(s.getPointValue());
		s.beEaten();
		numSmallPellets--;

		if (numBigPellets == 0 && numSmallPellets == 0) {
			win();
		}
	}

	/**
	 * eats fruit at a given location
	 * 
	 * @param f the fruit at a given location on the gameboard
	 */
	public void eatFruit(Fruit f) {
		setPointTotal(f.getPointValue());
		hasDisappeared = true;
		f.beEaten();
	}

	/**
	 * returns the point total
	 * 
	 * @return the point total
	 */
	public double getPointTotal() {
		return pointTotal;
	}

	/**
	 * sets the point total
	 * 
	 * @param points the number of points being added to point total
	 */
	public void setPointTotal(double points) {
		pointTotal += points;
		w.setMessage(getPointTotal() + "");
		if (pointTotal > 600 && hasFruitAlreadyAppeared == false) {
			w.add(getFruit());
			hasFruitAlreadyAppeared = true;
		}
	}

	/**
	 * returns boolean true if Pacman ate bigpellet and false otherwise, used in
	 * ghostclass to make ghosts edible
	 * 
	 * @return isPowerUp boolean
	 */
	public boolean getIsPowerUp() {
		return isPowerUp;
	}

	/**
	 * if the user wins the level, display the message "YOU WIN!!! RESTART TO PLAY
	 * AGAIN" and resets the gameboard level
	 */
	public void win() {
		w.setMessage("Score: " + pointTotal);
		w.setMessage("YOU WIN!!! RESTART TO PLAY AGAIN");
		gameBoard.resetLevel();
		// gameLevel++;
		// b = new Board(gameLevel, new ActorWorld());
		// b.organize();

	}

	/**
	 * if the user loses the level, display the message "YOU LOSE!!! RESTART TO PLAY
	 * AGAIN" and resets the gameboard level
	 */
	public void lose() {
		w.setMessage("YOU LOSE!!! RESTART TO PLAY AGAIN");
		gameBoard.resetLevel();
		// b = new Board(gameLevel, new ActorWorld());
		// b.organize();
		// b.resetLevel();
		// b.organize();
	}

	/**
	 * gets the point value of all the fruits depending on the game level, used for
	 * getPointValue()
	 * 
	 * @return the fruit for the certain level
	 */
	public Fruit getFruit() {

		if (gameLevel == 1) {
			return type.get("cherry");
		} else if (gameLevel == 2) {
			return type.get("orange");
		} else if (gameLevel == 3) {
			return type.get("strawberry");
		} else if (gameLevel == 4) {
			return type.get("watermelon");
		}

		return null;
	}

}