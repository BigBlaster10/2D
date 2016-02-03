package org.trompgames.twod;

import java.io.File;

import org.trompgames.utils.Location;

public class TwoDMain {

	public static void main(String[] args) {		
		long startTime = System.currentTimeMillis();
		System.out.println("Initializing 2D game...");
		
		MainGame game = new MainGame();
		game.startGame();

		((new Thread(game))).start();
		
		
		System.out.println("Initialized 2D Game in " + (System.currentTimeMillis() - startTime) + "ms");
	}
	
}
