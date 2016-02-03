package org.trompgames.twod;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.trompgames.utils.Location;

public abstract class Object2D {

	public File imageFile;
	public BufferedImage image;
	public Location loc;
	public boolean visible = true;
	public MainGame game;
	
	
	
	//Physics
	public Collider collider;	
	public boolean gravity = false;
	public double mass = 0;
	
	
	public Object2D(File imageFile, Location loc, MainGame game){
		this.imageFile = imageFile;
		this.loc = loc;
		this.game = game;
		loadImage();
	}
	
	abstract void update(double deltaTime);
	
	abstract void fixedUpdate(double deltaTime);
	
	public void setCollider(Collider collider){
		this.collider = collider;
	}
	
	public boolean hasGravity(){
		return gravity;
	}
	
	public void setGravity(boolean gravity){
		this.gravity = gravity;
	}
	
	public boolean hasCollider(){
		return collider != null;
	}
	
	private void loadImage(){
		try{
			image = ImageIO.read(imageFile);
		}catch(IOException e){
			System.out.println("Error: The file '" + imageFile.toPath() + "' was not found");
		}
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
	}
	
	public MainGame getMainGame(){
		return game;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean isVisible){
		this.visible = isVisible;
	}
	
}
