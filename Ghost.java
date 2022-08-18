import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * Represents a Ghost character in the Pacman game Ghosts move around and turn
 * randomly
 *
 * @author Cheryl Lin
 * @version May 5, 2020
 * @author Period: 2
 * @author Assignment: Final Project
 *
 * @author Sources: none
 */

public class Ghost extends Bug implements Edible {

	private Pacman p = null;
	private Color color = null;
	ActorWorld w = null;
	Location l = null;

	private boolean isEaten = false;
	private int time = 0;

	/**
	 * constructor to create a Ghost.
	 * 
	 * @param p     Pacman character in the board, needed because ghost interacts
	 *              with Pacman based on certain traits
	 * @param color color of the Ghost
	 * @param w     ActorWorld the Ghost is in
	 * @param l     location the Ghost starts in
	 */
	public Ghost(Pacman p, Color color, ActorWorld w, Location l) {
		this.p = p;
		this.setColor(color);
		this.color = color;
		this.w = w;
		this.l = l;
	}

	/**
	 * controls the movement of the Ghost. the time dependent traits, like
	 * respawning and moving after it has been eaten and changing colors in Pacman's
	 * PowerUp mode are controlled here. if a Ghost encounters a Pellet, it moves
	 * over them (not eating them)
	 */
	public void act() {

		if (isEaten == true) {
			time++;
			if (time > 10) {
				this.setColor(color);
				time = 0;
				isEaten = false;
			} else {
				return;
			}
		}

		if (p.getIsPowerUp() == true) {
			this.setColor(Color.blue);
		} else {
			this.setColor(color);
		}

		if (canMove()) {
			Grid<Actor> gr = getGrid();
			if (getLocation() == null) {
				return;
			}
			Location adj = getLocation().getAdjacentLocation(getDirection());
			Actor atJump = gr.get(adj);

			if (atJump instanceof BigPellet) {
				moveOverBigPellet();
			}

			else if (atJump instanceof SmallPellet) {
				moveOverSmallPellet();
			}

			else if (atJump instanceof Fruit) {
				jump();
			}

			else {
				jump();
			}
		} else {

			turn();
		}
	}

	/**
	 * if Ghost moves out of bounds or Pacman in PowerUp mode eats the Ghost, Ghosts
	 * can not move if there is a Pellet or Fruit, or Ghost eats Pacman, Ghost can
	 * move
	 * 
	 * @return if the Ghost can move or not
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

		if (atJump == null || atJump instanceof BigPellet || atJump instanceof SmallPellet || atJump instanceof Fruit) {
			return true;
		}

		else if (atJump == p) {
			if (p.getIsPowerUp() == true) {
				beEaten();
				p.setPointTotal(200);
				return false;

			} else {
				eatPacman();
				return true;
			}
		}

		return false;
	}

	/**
	 * moves itself one spot forward in its direction
	 */
	public void jump() {
		moveTo(getLocation().getAdjacentLocation(getDirection()));
	}

	/**
	 * moves over the big pellet (does not eat it)
	 */
	public void moveOverBigPellet() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(next))
			moveTo(next);
		else
			removeSelfFromGrid();
		BigPellet b = new BigPellet();
		b.putSelfInGrid(gr, loc);

	}

	/**
	 * moves over the small pellet (does not eat it)
	 */
	public void moveOverSmallPellet() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(next))
			moveTo(next);
		else
			removeSelfFromGrid();
		SmallPellet s = new SmallPellet();
		s.putSelfInGrid(gr, loc);

	}

	/**
	 * turns the Ghost right or left randomly
	 */
	public void turn() {
		double rand = Math.random();
		if (rand < .5) {
			setDirection(getDirection() + 90);
		} else {
			setDirection(getDirection() - 90);
		}
	}

	/**
	 * removes Pacman from board. when Pacman is eaten, the game ends and user loses
	 */
	public void eatPacman() {
		p.removeSelfFromGrid();
		p.lose();

	}

	/**
	 * removes itself from board. respawns immediately in starting position, but
	 * waits before it starts moving again
	 */
	public void beEaten() {
		removeSelfFromGrid();
		w.add(l, this);
		setColor(Color.white);
		isEaten = true;
	}

}