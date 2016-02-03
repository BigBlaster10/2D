package org.trompgames.twod;

import java.io.File;

import org.trompgames.utils.Location;
import org.trompgames.utils.Vector;

public abstract class Character extends Object2D{

	public Vector velocity = new Vector(0,0);
	
	public Character(File imageFile, Location loc, MainGame game) {
		super(imageFile, loc, game);
		// TODO Auto-generated constructor stub
	}

	public Vector getVelocity(){
		return velocity;
	}

	
	
	
}
