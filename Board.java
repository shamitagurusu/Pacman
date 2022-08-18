import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

/**
 * Creates a game board for the Pacman game. The board contains all the walls
 * and characters needed to play the came
 *
 * @author Sanjana Neeli, Shamita Gurusu, Cheryl Lin
 * @version May 28, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 *
 * @author Sources: none
 */
public class Board {

	private int level;
	private ActorWorld w;
	private BoundedGrid ref;

	int numBigPellets = 0;
	int numSmallPellets = 0;
	int numGhosts = 0;

	Pacman p;

	Ghost g1;
	Ghost g2;
	Ghost g3;
	Ghost g4;

	/**
	 * constructor for the Board.
	 * 
	 * @param gameLevel what level the user wants to play
	 * @param world     world the Board should be reated in
	 */
	public Board(int gameLevel, ActorWorld world) {
		level = gameLevel;
		w = world;
		ref = new BoundedGrid(30, 35);
		w.setGrid(ref);
		
		

	}

	/**
	 * creates the appropriate level of board indicated in the constructor
	 */
	public void organize() {
		if (level == 1) {
			createLevelOne();
		}
		if (level == 2) {
			createLevelTwo();
		}
		if (level == 3) {
			createLevelThree();
		}
		if (level == 4) {
			createLevelFour();
		}
	}

	/**
	 * resets the entire board, clearing all Actors from the board leaving a blank
	 * slate. all character's global variables are set to null
	 */
	public void reset() {
		for (int i = 0; i < ref.getNumRows(); i++) {
			for (int j = 0; j < ref.getNumCols(); j++) {
				Location loc = new Location(i, j);
				Object temp = ref.get(loc);
				if (temp != null) {
					ref.remove(loc);
				}
			}
		}
		numBigPellets = 0;
		numSmallPellets = 0;
		numGhosts = 0;
		p = null;
		g1 = null;
		g2 = null;
		g3 = null;
		g4 = null;

	}

	/**
	 * resets the entire board, clearing all Actors from the board leaving a blank
	 * slate.
	 */
	public void resetLevel() {
		ArrayList<Location> list = w.getGrid().getOccupiedLocations();
		for (Location l : list) {
			w.remove(l);
		}
		w.setGrid(ref);

	}

	/**
	 * adds all walls, ghosts, pacman, and pellets needed for level one and shows
	 * the board
	 */
	public void createLevelOne() {
		// STEP ZERO: RESET!!!!!!!!!!!!!!!
		reset();

		// STEP ONE: ADD WALLS WHERE DESIRED

		// left to down top
		createLongWall(new Location(1, 1), new Location(1, 8));
		createLongWall(new Location(1, 1), new Location(10, 1));
		createLongWall(new Location(11, 1), new Location(19, 1));
		createLongWall(new Location(20, 1), new Location(29, 1));

		// left to right top
		createLongWall(new Location(1, 9), new Location(1, 17));
		createLongWall(new Location(1, 18), new Location(1, 27));
		createLongWall(new Location(1, 28), new Location(1, 34));

		// left to down top
		createLongWall(new Location(1, 33), new Location(10, 33));
		createLongWall(new Location(11, 33), new Location(19, 33));
		createLongWall(new Location(20, 33), new Location(29, 33));

		// bottom to top left 2nd layer
		createLongWall(new Location(29, 3), new Location(20, 3));
		createLongWall(new Location(19, 3), new Location(7, 3));
		createLongWall(new Location(6, 3), new Location(3, 3));

		// bottom to top right 2nd layer
		createLongWall(new Location(29, 31), new Location(20, 31));
		createLongWall(new Location(19, 31), new Location(7, 31));
		createLongWall(new Location(6, 31), new Location(3, 31));

		// left to right bottom 2nd layer
		createLongWall(new Location(28, 3), new Location(28, 11));
		createLongWall(new Location(28, 12), new Location(28, 21));
		createLongWall(new Location(28, 22), new Location(28, 32));

		// 2 bottom segments
		createLongWall(new Location(27, 5), new Location(24, 5));
		createLongWall(new Location(23, 5), new Location(16, 5));
		createLongWall(new Location(15, 5), new Location(11, 5));
		createLongWall(new Location(10, 5), new Location(10, 17));
		createLongWall(new Location(27, 29), new Location(24, 29));
		createLongWall(new Location(23, 29), new Location(16, 29));
		createLongWall(new Location(15, 29), new Location(11, 29));
		createLongWall(new Location(10, 18), new Location(10, 30));

		// 1st layer inside bottom 2 segments
		createLongWall(new Location(27, 7), new Location(12, 7));
		createLongWall(new Location(12, 7), new Location(12, 10));
		createLongWall(new Location(12, 10), new Location(12, 7));
		createLongWall(new Location(25, 9), new Location(14, 9));
		createLongWall(new Location(26, 7), new Location(26, 28));
		createLongWall(new Location(27, 27), new Location(26, 28));
		createLongWall(new Location(26, 27), new Location(17, 27));
		createLongWall(new Location(15, 27), new Location(12, 27));

		// bottom layer 3rd layer
		createLongWall(new Location(18, 11), new Location(12, 11));
		createLongWall(new Location(25, 11), new Location(19, 11));
		createLongWall(new Location(24, 11), new Location(24, 26));
		createLongWall(new Location(25, 25), new Location(19, 25));
		createLongWall(new Location(18, 25), new Location(12, 25));
		createLongWall(new Location(21, 11), new Location(21, 25));
		createLongWall(new Location(22, 11), new Location(22, 25));
		createLongWall(new Location(23, 11), new Location(23, 25));
		createLongWall(new Location(13, 23), new Location(13, 25));
		createLongWall(new Location(14, 23), new Location(14, 25));
		createLongWall(new Location(13, 11), new Location(13, 14));
		createLongWall(new Location(14, 11), new Location(14, 14));
		createLongWall(new Location(17, 13), new Location(14, 24));
		createLongWall(new Location(18, 13), new Location(18, 24));
		createLongWall(new Location(19, 12), new Location(19, 25));
		createLongWall(new Location(20, 12), new Location(20, 25));
		createLongWall(new Location(11, 13), new Location(11, 17));
		createLongWall(new Location(11, 18), new Location(11, 24));
		createLongWall(new Location(13, 15), new Location(13, 21));
		createLongWall(new Location(17, 18), new Location(13, 18));
		createLongWall(new Location(17, 19), new Location(13, 19));
		createLongWall(new Location(17, 20), new Location(13, 20));
		createLongWall(new Location(16, 22), new Location(13, 24));

		// top segments
		createLongWall(new Location(9, 5), new Location(3, 5));
		createLongWall(new Location(9, 29), new Location(3, 29));
		createLongWall(new Location(3, 7), new Location(3, 17));
		createLongWall(new Location(4, 7), new Location(4, 17));
		createLongWall(new Location(3, 18), new Location(3, 28));
		createLongWall(new Location(4, 18), new Location(4, 28));
		createLongWall(new Location(6, 8), new Location(6, 27));
		createLongWall(new Location(7, 8), new Location(7, 27));
		createLongWall(new Location(8, 8), new Location(8, 27));

		// createLongWall(new Location(24, 25), new Location(19, 25));
		// STEP TWO: ADD BIG PELLETS (rn its doing it randomly)

		numBigPellets = 5;
		for (int i = 0; i < numBigPellets; i++) {
			w.add(new BigPellet());
		}

		// STEP THREE: FILL EVERYWHERE ELSE WITH SMALL PELLETS
		fillWithSmallPellet();

		// STEP FOUR: ADD PACMAN
		numSmallPellets = numSmallPellets - 5; // one for the pacman itself!
		p = new Pacman(Color.yellow, 0, w, numBigPellets, numSmallPellets, level, this);
		Location pacmanLoc = new Location(16, 27);
		w.remove(pacmanLoc);
		w.add(pacmanLoc, p);
		
		

		// STEP SIX: ADD GHOSTS

		Location l1 = new Location(15, 16);
		Location l2 = new Location(16, 15);
		Location l3 = new Location(15, 14);
		Location l4 = new Location(14, 15);

		g1 = new Ghost(p, Color.pink, w, l1);
		g1.setDirection(90);
		g2 = new Ghost(p, Color.red, w, l2);
		g2.setDirection(180);
		g3 = new Ghost(p, Color.cyan, w, l3);
		g3.setDirection(270);
		g4 = new Ghost(p, Color.orange, w, l4);

		w.add(l1, g1);
		w.add(l2, g2);
		w.add(l3, g3);
		w.add(l4, g4);

		w.show();

	}

	/**
	 * adds all walls, ghosts, pacman, and pellets needed for level two and shows
	 * the board
	 */
	public void createLevelTwo() {

		// STEP ZERO: RESET!!!!!!!!!!!!!!!
		reset();

		// STEP ONE: ADD WALLS WHERE DESIRED

		// row 1
		createLongWall(new Location(1, 1), new Location(1, 6));
		createLongWall(new Location(1, 7), new Location(1, 12));
		createLongWall(new Location(1, 13), new Location(1, 18));
		createLongWall(new Location(1, 19), new Location(1, 24));
		createLongWall(new Location(1, 25), new Location(1, 30));
		createLongWall(new Location(1, 31), new Location(1, 34));

		// row 2
		createLongWall(new Location(3, 1), new Location(3, 4));
		createLongWall(new Location(3, 5), new Location(3, 10));
		createLongWall(new Location(3, 11), new Location(3, 16));
		createLongWall(new Location(3, 17), new Location(3, 22));
		createLongWall(new Location(3, 23), new Location(3, 28));
		createLongWall(new Location(3, 29), new Location(3, 34));

		// row 3
		createLongWall(new Location(5, 1), new Location(5, 5));
		createLongWall(new Location(5, 6), new Location(5, 13));
		createLongWall(new Location(5, 14), new Location(5, 24));
		createLongWall(new Location(5, 25), new Location(5, 34));

		// row 4
		createLongWall(new Location(6, 1), new Location(8, 1));

		// row 5
		createLongWall(new Location(7, 3), new Location(7, 10));
		createLongWall(new Location(7, 11), new Location(7, 20));
		createLongWall(new Location(7, 21), new Location(7, 28));
		createLongWall(new Location(7, 29), new Location(7, 33));
		createLongWall(new Location(7, 33), new Location(9, 33));

		// row 6
		createLongWall(new Location(9, 1), new Location(9, 11));
		createLongWall(new Location(9, 12), new Location(9, 16));
		createLongWall(new Location(9, 17), new Location(9, 19));
		createLongWall(new Location(9, 20), new Location(9, 23));
		createLongWall(new Location(9, 24), new Location(9, 34));

		// middle columns
		createLongWall(new Location(11, 2), new Location(15, 2));
		createLongWall(new Location(11, 5), new Location(16, 5));
		createLongWall(new Location(10, 8), new Location(15, 8));
		createLongWall(new Location(10, 18), new Location(13, 18));
		createLongWall(new Location(10, 14), new Location(16, 18));
		createLongWall(new Location(11, 20), new Location(11, 25));
		createLongWall(new Location(10, 26), new Location(15, 26));
		createLongWall(new Location(11, 29), new Location(16, 29));
		createLongWall(new Location(11, 32), new Location(15, 32));

		// row 7
		createLongWall(new Location(16, 1), new Location(16, 4));
		createLongWall(new Location(16, 5), new Location(16, 11));
		createLongWall(new Location(16, 12), new Location(16, 15));
		createLongWall(new Location(16, 16), new Location(16, 20));
		createLongWall(new Location(16, 21), new Location(16, 24));
		createLongWall(new Location(16, 25), new Location(16, 28));
		createLongWall(new Location(16, 29), new Location(16, 34));

		// row 8
		createLongWall(new Location(18, 1), new Location(18, 5));
		createLongWall(new Location(18, 6), new Location(18, 10));
		createLongWall(new Location(18, 11), new Location(18, 16));
		createLongWall(new Location(18, 17), new Location(18, 24));
		createLongWall(new Location(18, 25), new Location(18, 29));
		createLongWall(new Location(18, 30), new Location(18, 34));
		createLongWall(new Location(18, 1), new Location(20, 1));

		// row 9
		createLongWall(new Location(20, 1), new Location(20, 6));
		createLongWall(new Location(20, 7), new Location(20, 13));
		createLongWall(new Location(20, 14), new Location(20, 20));
		createLongWall(new Location(20, 14), new Location(20, 20));
		createLongWall(new Location(20, 21), new Location(20, 27));
		createLongWall(new Location(20, 28), new Location(20, 31));
		createLongWall(new Location(20, 32), new Location(20, 34));
		createLongWall(new Location(21, 33), new Location(22, 33));

		// row 10
		createLongWall(new Location(22, 1), new Location(22, 9));
		createLongWall(new Location(22, 10), new Location(22, 18));
		createLongWall(new Location(22, 19), new Location(22, 24));
		createLongWall(new Location(22, 25), new Location(22, 29));
		createLongWall(new Location(22, 30), new Location(22, 34));

		// row 11
		createLongWall(new Location(23, 1), new Location(24, 1));
		createLongWall(new Location(24, 1), new Location(24, 4));
		createLongWall(new Location(24, 5), new Location(24, 13));
		createLongWall(new Location(24, 14), new Location(24, 21));
		createLongWall(new Location(24, 22), new Location(24, 27));
		createLongWall(new Location(24, 28), new Location(24, 34));

		// row 12
		createLongWall(new Location(26, 1), new Location(26, 4));
		createLongWall(new Location(26, 5), new Location(26, 10));
		createLongWall(new Location(26, 11), new Location(26, 16));
		createLongWall(new Location(26, 17), new Location(26, 22));
		createLongWall(new Location(26, 23), new Location(26, 28));
		createLongWall(new Location(26, 29), new Location(26, 34));

		// row 13
		createLongWall(new Location(28, 1), new Location(28, 6));
		createLongWall(new Location(28, 7), new Location(28, 12));
		createLongWall(new Location(28, 13), new Location(28, 18));
		createLongWall(new Location(28, 19), new Location(28, 24));
		createLongWall(new Location(28, 25), new Location(28, 30));
		createLongWall(new Location(28, 31), new Location(28, 34));

		// STEP TWO: ADD BIG PELLETS (rn its doing it randomly)

		numBigPellets = 5;
		for (int i = 0; i < numBigPellets; i++) {
			w.add(new BigPellet());
		}

		// STEP THREE: FILL EVERYWHERE ELSE WITH SMALL PELLETS
		fillWithSmallPellet();

		// STEP FOUR: ADD PACMAN
		numSmallPellets = numSmallPellets - 5; // one for the pacman itself!
		p = new Pacman(Color.yellow, 0, w, numBigPellets, numSmallPellets, level, this);
		Location pacmanLoc = new Location(13, 22);
		w.remove(pacmanLoc);
		w.add(pacmanLoc, p);

		// STEP SIX: ADD GHOSTS

		Location l1 = new Location(13, 12);
		Location l2 = new Location(13, 13);
		Location l3 = new Location(14, 12);
		Location l4 = new Location(14, 13);

		g1 = new Ghost(p, Color.pink, w, l1);
		g1.setDirection(0);
		g2 = new Ghost(p, Color.red, w, l2);
		g2.setDirection(90);
		g3 = new Ghost(p, Color.cyan, w, l3);
		g3.setDirection(180);
		g4 = new Ghost(p, Color.orange, w, l4);
		g4.setDirection(270);

		w.add(l1, g1);
		w.add(l2, g2);
		w.add(l3, g3);
		w.add(l4, g4);

		w.show();
	}

	/**
	 * adds all walls, ghosts, pacman, and pellets needed for level three and shows
	 * the board
	 */
	public void createLevelThree() {
		// STEP ZERO: RESET!!!!!!!!!!!!!!!
		reset();

		// STEP ONE: ADD WALLS WHERE DESIRED

		// row 1
		createLongWall(new Location(1, 1), new Location(1, 8));
		createLongWall(new Location(1, 9), new Location(1, 13));
		createLongWall(new Location(1, 14), new Location(1, 20));
		createLongWall(new Location(1, 21), new Location(1, 27));
		createLongWall(new Location(1, 28), new Location(1, 34));

		// row 2
		createLongWall(new Location(2, 1), new Location(2, 2));

		// row 3
		createLongWall(new Location(3, 1), new Location(3, 2));
		createLongWall(new Location(3, 3), new Location(3, 15));
		createLongWall(new Location(3, 16), new Location(3, 17));
		createLongWall(new Location(3, 18), new Location(3, 30));
		createLongWall(new Location(3, 31), new Location(3, 34));

		// row 4
		createLongWall(new Location(4, 1), new Location(4, 2));
		createLongWall(new Location(4, 3), new Location(4, 4));
		createLongWall(new Location(4, 14), new Location(4, 15));
		createLongWall(new Location(4, 18), new Location(4, 19));
		createLongWall(new Location(4, 31), new Location(4, 34));

		// row 5
		createLongWall(new Location(5, 1), new Location(5, 2));
		createLongWall(new Location(5, 5), new Location(5, 8));
		createLongWall(new Location(5, 9), new Location(5, 13));
		createLongWall(new Location(5, 14), new Location(5, 15));
		createLongWall(new Location(5, 16), new Location(5, 17));
		createLongWall(new Location(5, 18), new Location(5, 19));
		createLongWall(new Location(5, 20), new Location(5, 24));
		createLongWall(new Location(5, 25), new Location(5, 30));
		createLongWall(new Location(5, 31), new Location(5, 34));

		// row 6
		createLongWall(new Location(6, 1), new Location(6, 2));
		createLongWall(new Location(6, 3), new Location(6, 4));
		createLongWall(new Location(6, 5), new Location(6, 6));
		createLongWall(new Location(6, 9), new Location(6, 10));
		createLongWall(new Location(6, 14), new Location(6, 15));
		createLongWall(new Location(6, 16), new Location(6, 17));
		createLongWall(new Location(6, 18), new Location(6, 19));
		createLongWall(new Location(6, 23), new Location(6, 24));
		createLongWall(new Location(6, 29), new Location(6, 30));
		createLongWall(new Location(6, 31), new Location(6, 34));

		// row 7
		createLongWall(new Location(7, 1), new Location(7, 2));
		createLongWall(new Location(7, 3), new Location(7, 4));
		createLongWall(new Location(7, 5), new Location(7, 6));
		createLongWall(new Location(7, 5), new Location(7, 6));
		createLongWall(new Location(7, 7), new Location(7, 8));
		createLongWall(new Location(7, 9), new Location(7, 10));
		createLongWall(new Location(7, 11), new Location(7, 13));
		createLongWall(new Location(7, 14), new Location(7, 15));
		createLongWall(new Location(7, 16), new Location(7, 17));
		createLongWall(new Location(7, 18), new Location(7, 19));
		createLongWall(new Location(7, 20), new Location(7, 22));
		createLongWall(new Location(7, 23), new Location(7, 24));
		createLongWall(new Location(7, 25), new Location(7, 28));
		createLongWall(new Location(7, 29), new Location(7, 30));
		createLongWall(new Location(7, 31), new Location(7, 34));

		// row 8
		createLongWall(new Location(8, 1), new Location(8, 2));
		createLongWall(new Location(8, 3), new Location(8, 4));
		createLongWall(new Location(8, 5), new Location(8, 6));
		createLongWall(new Location(8, 7), new Location(8, 8));
		createLongWall(new Location(8, 9), new Location(8, 10));
		createLongWall(new Location(8, 11), new Location(8, 13));
		createLongWall(new Location(8, 14), new Location(8, 15));
		createLongWall(new Location(8, 16), new Location(8, 17));
		createLongWall(new Location(8, 18), new Location(8, 19));
		createLongWall(new Location(8, 20), new Location(8, 22));
		createLongWall(new Location(8, 23), new Location(8, 24));
		createLongWall(new Location(8, 29), new Location(8, 30));

		// row 9
		createLongWall(new Location(9, 1), new Location(9, 2));
		createLongWall(new Location(9, 3), new Location(9, 4));
		createLongWall(new Location(9, 5), new Location(9, 6));
		createLongWall(new Location(9, 7), new Location(9, 8));
		createLongWall(new Location(9, 9), new Location(9, 10));
		createLongWall(new Location(9, 11), new Location(9, 13));
		createLongWall(new Location(9, 14), new Location(9, 15));
		createLongWall(new Location(9, 18), new Location(9, 19));
		createLongWall(new Location(9, 20), new Location(9, 22));
		createLongWall(new Location(9, 23), new Location(9, 24));
		createLongWall(new Location(9, 25), new Location(9, 28));
		createLongWall(new Location(9, 29), new Location(9, 30));
		createLongWall(new Location(9, 31), new Location(9, 34));

		// row 10
		createLongWall(new Location(9, 3), new Location(9, 4));
		createLongWall(new Location(9, 5), new Location(9, 6));
		createLongWall(new Location(9, 7), new Location(9, 8));
		createLongWall(new Location(9, 9), new Location(9, 10));
		createLongWall(new Location(9, 11), new Location(9, 13));
		createLongWall(new Location(9, 14), new Location(9, 15));
		createLongWall(new Location(9, 16), new Location(9, 17));
		createLongWall(new Location(9, 18), new Location(9, 19));
		createLongWall(new Location(9, 20), new Location(9, 22));
		createLongWall(new Location(9, 23), new Location(9, 24));
		createLongWall(new Location(9, 25), new Location(9, 28));
		createLongWall(new Location(9, 29), new Location(9, 30));
		createLongWall(new Location(9, 31), new Location(9, 34));

		// row 10 and below
		createLongWall(new Location(10, 3), new Location(10, 4));
		createLongWall(new Location(11, 1), new Location(11, 4));
		createLongWall(new Location(10, 5), new Location(16, 5));
		createLongWall(new Location(10, 7), new Location(22, 7));
		createLongWall(new Location(10, 9), new Location(12, 9));
		createLongWall(new Location(10, 11), new Location(19, 11));
		createLongWall(new Location(10, 12), new Location(19, 12));
		createLongWall(new Location(10, 14), new Location(18, 14));
		createLongWall(new Location(10, 16), new Location(14, 16));
		createLongWall(new Location(10, 18), new Location(18, 18));
		createLongWall(new Location(10, 20), new Location(19, 20));
		createLongWall(new Location(10, 21), new Location(19, 21));
		createLongWall(new Location(10, 23), new Location(10, 24));
		createLongWall(new Location(10, 25), new Location(10, 28));
		createLongWall(new Location(10, 29), new Location(13, 29));
		createLongWall(new Location(10, 31), new Location(15, 31));
		createLongWall(new Location(10, 32), new Location(15, 32));
		createLongWall(new Location(10, 33), new Location(15, 33));
		createLongWall(new Location(12, 23), new Location(12, 29));
		createLongWall(new Location(13, 1), new Location(13, 4));
		createLongWall(new Location(14, 1), new Location(20, 1));
		createLongWall(new Location(15, 3), new Location(20, 3));
		createLongWall(new Location(17, 4), new Location(17, 6));
		createLongWall(new Location(18, 5), new Location(29, 5));
		createLongWall(new Location(28, 1), new Location(28, 5));
		createLongWall(new Location(23, 1), new Location(28, 1));
		createLongWall(new Location(21, 1), new Location(21, 4));
		createLongWall(new Location(22, 3), new Location(27, 3));
		createLongWall(new Location(23, 7), new Location(29, 7));
		createLongWall(new Location(28, 8), new Location(28, 13));
		createLongWall(new Location(27, 9), new Location(28, 9));
		createLongWall(new Location(13, 9), new Location(26, 9));
		createLongWall(new Location(22, 11), new Location(27, 11));
		createLongWall(new Location(20, 11), new Location(20, 22));
		createLongWall(new Location(28, 21), new Location(28, 26));
		createLongWall(new Location(22, 22), new Location(27, 22));
		createLongWall(new Location(22, 24), new Location(27, 24));
		createLongWall(new Location(14, 23), new Location(21, 23));
		createLongWall(new Location(14, 24), new Location(14, 30));
		createLongWall(new Location(16, 25), new Location(19, 25));
		createLongWall(new Location(20, 25), new Location(20, 27));
		createLongWall(new Location(21, 26), new Location(27, 26));
		createLongWall(new Location(26, 27), new Location(29, 27));
		createLongWall(new Location(17, 16), new Location(19, 16));
		createLongWall(new Location(23, 14), new Location(28, 14));
		createLongWall(new Location(23, 19), new Location(28, 19));
		createLongWall(new Location(15, 29), new Location(19, 29));
		createLongWall(new Location(16, 27), new Location(16, 29));
		createLongWall(new Location(18, 27), new Location(18, 29));
		createLongWall(new Location(20, 28), new Location(25, 28));
		createLongWall(new Location(20, 29), new Location(29, 29));

		createLongWall(new Location(28, 30), new Location(28, 34));
		createLongWall(new Location(27, 31), new Location(27, 34));
		createLongWall(new Location(26, 31), new Location(26, 34));

		createLongWall(new Location(24, 31), new Location(24, 34));
		createLongWall(new Location(23, 31), new Location(23, 34));
		createLongWall(new Location(22, 31), new Location(22, 34));
		createLongWall(new Location(21, 31), new Location(21, 34));
		createLongWall(new Location(20, 31), new Location(20, 34));
		createLongWall(new Location(19, 31), new Location(19, 34));

		createLongWall(new Location(17, 31), new Location(17, 34));
		createLongWall(new Location(16, 31), new Location(16, 34));

		// STEP TWO: ADD BIG PELLETS (rn its doing it randomly)

		
		numBigPellets = 5;
		for (int i = 0; i < numBigPellets; i++) {
			w.add(new BigPellet());
		}

		// STEP THREE: FILL EVERYWHERE ELSE WITH SMALL PELLETS
		fillWithSmallPellet();

		// STEP FOUR: ADD PACMAN
		numSmallPellets = numSmallPellets - 6; // one for the pacman itself!
		p = new Pacman(Color.yellow, 0, w, numBigPellets, numSmallPellets, level, this);
		Location pacmanLoc = new Location(15, 16);
		w.remove(pacmanLoc);
		w.add(pacmanLoc, p);

		// STEP SIX: ADD GHOSTS

		Location l1 = new Location(24, 16);
		Location l2 = new Location(24, 17);
		Location l3 = new Location(25, 16);
		Location l4 = new Location(25, 17);

		g1 = new Ghost(p, Color.pink, w, l1);
		g1.setDirection(90);
		g2 = new Ghost(p, Color.red, w, l2);
		g2.setDirection(270);
		g3 = new Ghost(p, Color.cyan, w, l3);
		g3.setDirection(90);
		g4 = new Ghost(p, Color.orange, w, l4);
		g4.setDirection(270);

		w.add(l1, g1);
		w.add(l2, g2);
		w.add(l3, g3);
		w.add(l4, g4);

		
		
		w.show();
	}

	/**
	 * adds all walls, ghosts, pacman, and pellets needed for level four and shows
	 * the board
	 */
	public void createLevelFour() {
		// STEP ZERO: RESET!!!!!!!!!!!!!!!
		reset();

		// STEP ONE: ADD WALLS WHERE DESIRED

		// left top
		createLongWall(new Location(1, 1), new Location(1, 7));
		createLongWall(new Location(3, 1), new Location(3, 7));
		createLongWall(new Location(5, 1), new Location(5, 9));
		createLongWall(new Location(1, 8), new Location(6, 8));
		createLongWall(new Location(1, 10), new Location(4, 10));
		createLongWall(new Location(1, 12), new Location(4, 12));
		createLongWall(new Location(1, 14), new Location(4, 14));
		createLongWall(new Location(5, 10), new Location(5, 15));
		createLongWall(new Location(7, 1), new Location(7, 6));
		createLongWall(new Location(7, 6), new Location(10, 6));
		createLongWall(new Location(7, 6), new Location(10, 6));
		createLongWall(new Location(9, 1), new Location(9, 7));
		createLongWall(new Location(9, 1), new Location(9, 7));
		createLongWall(new Location(7, 8), new Location(7, 15));
		createLongWall(new Location(9, 8), new Location(9, 15));

		// middle top
		createLongWall(new Location(4, 16), new Location(10, 16));
		createLongWall(new Location(4, 16), new Location(4, 22));
		createLongWall(new Location(4, 22), new Location(10, 22));
		createLongWall(new Location(1, 16), new Location(1, 23));
		createLongWall(new Location(2, 16), new Location(2, 23));

		// right top
		createLongWall(new Location(1, 24), new Location(10, 24));
		createLongWall(new Location(1, 24), new Location(1, 26));
		createLongWall(new Location(1, 27), new Location(1, 31));
		createLongWall(new Location(1, 32), new Location(1, 34));
		createLongWall(new Location(3, 26), new Location(3, 32));
		createLongWall(new Location(1, 33), new Location(10, 33));
		createLongWall(new Location(4, 27), new Location(4, 31));
		createLongWall(new Location(5, 27), new Location(5, 31));
		createLongWall(new Location(6, 27), new Location(6, 31));
		createLongWall(new Location(8, 26), new Location(8, 32));
		createLongWall(new Location(9, 26), new Location(9, 32));

		// right middle
		createLongWall(new Location(11, 1), new Location(20, 1));
		createLongWall(new Location(11, 7), new Location(20, 7));
		createLongWall(new Location(11, 3), new Location(11, 6));
		createLongWall(new Location(12, 4), new Location(12, 5));
		createLongWall(new Location(14, 4), new Location(14, 5));
		createLongWall(new Location(15, 3), new Location(15, 6));
		createLongWall(new Location(16, 4), new Location(16, 5));
		createLongWall(new Location(19, 3), new Location(19, 6));
		createLongWall(new Location(18, 4), new Location(18, 5));

		// left middle
		createLongWall(new Location(11, 33), new Location(20, 33));
		createLongWall(new Location(11, 27), new Location(20, 27));
		createLongWall(new Location(11, 29), new Location(11, 32));
		createLongWall(new Location(12, 30), new Location(12, 31));
		createLongWall(new Location(14, 30), new Location(14, 31));
		createLongWall(new Location(15, 29), new Location(15, 32));
		createLongWall(new Location(16, 30), new Location(16, 31));
		createLongWall(new Location(18, 30), new Location(18, 31));
		createLongWall(new Location(19, 29), new Location(19, 32));

		// middle middle
		createLongWall(new Location(11, 9), new Location(11, 17)); // left
		createLongWall(new Location(11, 9), new Location(14, 9));
		createLongWall(new Location(13, 9), new Location(13, 17));
		createLongWall(new Location(11, 18), new Location(11, 26)); // right
		createLongWall(new Location(11, 25), new Location(14, 25));
		createLongWall(new Location(13, 18), new Location(13, 26));
		createLongWall(new Location(15, 9), new Location(20, 9)); // middle
		createLongWall(new Location(15, 11), new Location(20, 11));
		createLongWall(new Location(15, 13), new Location(20, 13));
		createLongWall(new Location(15, 15), new Location(20, 15));
		createLongWall(new Location(15, 17), new Location(20, 17));
		createLongWall(new Location(15, 19), new Location(20, 19));
		createLongWall(new Location(15, 21), new Location(20, 21));
		createLongWall(new Location(15, 23), new Location(20, 23));
		createLongWall(new Location(15, 25), new Location(20, 25));
		createLongWall(new Location(16, 10), new Location(19, 10));
		createLongWall(new Location(16, 12), new Location(19, 12));
		createLongWall(new Location(16, 14), new Location(19, 14));
		createLongWall(new Location(16, 16), new Location(19, 16));
		createLongWall(new Location(16, 18), new Location(19, 18));
		createLongWall(new Location(16, 20), new Location(19, 20));
		createLongWall(new Location(16, 22), new Location(19, 22));
		createLongWall(new Location(16, 24), new Location(19, 24));

		// left bottom
		createLongWall(new Location(21, 1), new Location(21, 4)); // left
		createLongWall(new Location(21, 1), new Location(29, 1));
		createLongWall(new Location(28, 1), new Location(28, 3));
		createLongWall(new Location(21, 5), new Location(21, 8));
		createLongWall(new Location(21, 9), new Location(21, 12)); // right
		createLongWall(new Location(21, 11), new Location(29, 11));
		createLongWall(new Location(28, 10), new Location(28, 12));
		createLongWall(new Location(28, 4), new Location(28, 9));
		createLongWall(new Location(23, 3), new Location(23, 10)); // middle
		createLongWall(new Location(24, 4), new Location(24, 9));
		createLongWall(new Location(25, 4), new Location(25, 9));
		createLongWall(new Location(26, 4), new Location(26, 9));

		// right bottom
		createLongWall(new Location(21, 33), new Location(24, 33));
		createLongWall(new Location(21, 31), new Location(24, 31));
		createLongWall(new Location(21, 29), new Location(24, 29));
		createLongWall(new Location(25, 29), new Location(25, 34));
		createLongWall(new Location(27, 29), new Location(27, 34));
		createLongWall(new Location(28, 29), new Location(28, 34));
		createLongWall(new Location(21, 27), new Location(27, 27));
		createLongWall(new Location(21, 21), new Location(21, 26));
		createLongWall(new Location(22, 21), new Location(22, 26));
		createLongWall(new Location(23, 21), new Location(23, 26));
		createLongWall(new Location(24, 21), new Location(24, 26));
		createLongWall(new Location(26, 20), new Location(26, 27));
		createLongWall(new Location(26, 20), new Location(29, 20));
		createLongWall(new Location(28, 20), new Location(28, 28));

		// middle bottom
		createLongWall(new Location(21, 13), new Location(21, 16));
		createLongWall(new Location(21, 17), new Location(21, 20));
		createLongWall(new Location(23, 13), new Location(23, 16));
		createLongWall(new Location(23, 17), new Location(23, 20));
		createLongWall(new Location(25, 13), new Location(25, 15));
		createLongWall(new Location(26, 13), new Location(26, 15));
		createLongWall(new Location(25, 16), new Location(25, 19));
		createLongWall(new Location(26, 16), new Location(26, 19));
		createLongWall(new Location(28, 13), new Location(28, 16));
		createLongWall(new Location(28, 17), new Location(28, 19));

		// createLongWall(new Location(24, 25), new Location(19, 25));
		// STEP TWO: ADD BIG PELLETS (rn its doing it randomly)

		numBigPellets = 5;
		for (int i = 0; i < numBigPellets; i++) {
			w.add(new BigPellet());
		}

		// STEP THREE: FILL EVERYWHERE ELSE WITH SMALL PELLETS
		fillWithSmallPellet();

		// STEP FOUR: ADD PACMAN
		numSmallPellets = numSmallPellets - 6; // one for the pacman itself!
		p = new Pacman(Color.yellow, 0, w, numBigPellets, numSmallPellets, level, this);
		Location pacmanLoc = new Location(12, 17);
		w.remove(pacmanLoc);
		w.add(pacmanLoc, p);

		// STEP SIX: ADD GHOSTS

		Location l1 = new Location(6, 19);
		Location l2 = new Location(7, 20);
		Location l3 = new Location(8, 19);
		Location l4 = new Location(7, 18);

		g1 = new Ghost(p, Color.pink, w, l1);
		g1.setDirection(90);
		g2 = new Ghost(p, Color.red, w, l2);
		g2.setDirection(180);
		g3 = new Ghost(p, Color.cyan, w, l3);
		g3.setDirection(270);
		g4 = new Ghost(p, Color.orange, w, l4);

		w.add(l1, g1);
		w.add(l2, g2);
		w.add(l3, g3);
		w.add(l4, g4);
		
		w.show();
	}

	/**
	 * creates a vertical or horizontal line of wall
	 * 
	 * @param l1 location the wall starts
	 * @param l2 location the wall ends
	 */
	public void createLongWall(Location l1, Location l2) {
		int r1 = l1.getRow();
		int c1 = l1.getCol();
		int r2 = l2.getRow();
		int c2 = l2.getCol();

		if (r1 == r2) {
			int start = Math.min(c1, c2);
			int end = Math.max(c1, c2);
			while (start < end) {
				w.add(new Location(r1, start), new Wall());
				start++;
			}
		}

		else if (c1 == c2) {
			int start = Math.min(r1, r2);
			int end = Math.max(r1, r2);
			while (start < end) {
				w.add(new Location(start, c1), new Wall());
				start++;
			}
		}
	}

	/**
	 * fills all remaining empty spaces in board with small pellets
	 */
	public void fillWithSmallPellet() {

		for (int i = 0; i < ref.getNumRows(); i++) {
			for (int j = 0; j < ref.getNumCols(); j++) {
				Location l = new Location(i, j);
				if (ref.get(l) == null) {
					w.add(l, new SmallPellet());
					numSmallPellets++;
				}
			}
		}
	}
}