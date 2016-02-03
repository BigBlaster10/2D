package org.trompgames.twod;

import java.io.File;

import org.trompgames.utils.Location;
import org.trompgames.utils.Vector;

public class Crate extends Character{

	public Crate(File imageFile, Location loc, MainGame game) {
		super(imageFile, loc, game);
		this.setCollider(new BoxCollider(new Location(0,0), new Location(image.getWidth(), image.getHeight())));
		this.setGravity(true);
	}

	@Override
	void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void fixedUpdate(double deltaTime) {		
		BoxCollider boxCollider = (BoxCollider) this.collider;

		boolean hit = checkCollision(this, deltaTime);
		
		
		if(hit){
			double y = this.velocity.getY();
			
			for(int i = 0; i <= this.velocity.getY(); i++){
				if(checkCollision(this.loc.add(0,i), boxCollider.getMaxPoint().add(this.loc).add(0,i), deltaTime)){
					this.loc = this.loc.add(0,i-1);
					//System.out.println(":DDDD");
					break;
				}
				
			}
			this.velocity = new Vector(0, 0);

		}else{
			this.loc = loc.add(velocity.getX() * deltaTime, velocity.getY() * deltaTime);
		}
		this.velocity = velocity.add(0, 1150 * deltaTime);

	}
	
	
	public boolean checkCollision(Object2D mainObj, double deltaTime){
		BoxCollider col = (BoxCollider) mainObj.collider;	
		
		Location loc = mainObj.loc.add(velocity.getX() * deltaTime, velocity.getY() * deltaTime);

		Location maxLoc = loc.add(col.getMaxPoint());
		Location minLoc = loc;
		for(Object2D obj : game.getObjects()){
			if(obj.equals(this)) continue;
			if(!obj.hasCollider()) continue;
			BoxCollider collider = (BoxCollider) obj.collider;
			
			Location objMax = collider.getMaxPoint().add(obj.getLocation());
			Location objMin = obj.getLocation();		
			
			
			if(checkCollision(minLoc, maxLoc, objMin, objMax))			
				return true;			
		}
		return false;
	}
	
	public boolean checkCollision(Location minLoc, Location maxLoc, double deltaTime){
		
		for(Object2D obj : game.getObjects()){
			if(obj.equals(this)) continue;
			if(!obj.hasCollider()) continue;
			BoxCollider collider = (BoxCollider) obj.collider;
			
			Location objMax = collider.getMaxPoint().add(obj.getLocation());
			Location objMin = obj.getLocation();		
			
			
			if(checkCollision(minLoc, maxLoc, objMin, objMax))			
				return true;			
		}
		return false;
	}
	
	public boolean checkCollision(Location loc1Min, Location loc1Max, Location loc2Min, Location loc2Max){
		if((loc1Max.getX() <= loc2Min.getX())) return false;
		if((loc1Min.getX() >= loc2Max.getX())) return false;
		if((loc1Max.getY() <= loc2Min.getY())) return false;
		if((loc1Min.getY() >= loc2Max.getY())) return false;
		return true;
	}

}
