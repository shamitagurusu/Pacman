import java.awt.Color;
import java.util.Scanner;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;

/**
 * The class that initiates the game, by creating a specific board as per user input.
 *
 * @author Cheryl Lin
 * @version 5/25/20
 * @author Period: 2
 * @author Assignment: Final Project
 *
 * @author Sources: none
 */
public class Game {
	
	// greenteapress.com/thinkapjava/javadoc/gridworld/

	/**
	 * asks for user input to select a level, and launches the board corresponding
	 * to that level
	 * 
	 * @param args string input paramater
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("CHOOSE A LEVEL:");
		System.out.println("1 2 3 4");
		int in = scanner.nextInt();
		
		if (in > 4 || in < 1)
		{
			System.out.print("Invalid, enter different level!");
		}

		Board b = new Board(in, new ActorWorld());
		b.organize();

	}
}