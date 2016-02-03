package org.trompgames.twod;

import java.io.File;

import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Location;
import org.trompgames.utils.Vector;

public class Player extends Character{

	public double walkSpeed = 300;
	public double runSpeed = 450;
	public double jumpSpeed = 1000;
	
	public Player(File imageFile, Location loc, MainGame game){
		super(imageFile, loc, game);
		this.setCollider(new BoxCollider(new Location(0,0), new Location(image.getWidth(), image.getHeight())));
		this.setGravity(true);
	}

	@Override
	void update(double deltaTime) {
		
		double x = 0;
		double y = 0;
		
		Keyboard board = game.getKeyboard();
		
		if(board.isPressed("SHIFT")){
			if(board.isPressed("W")){
				//y += -runSpeed;
			}if(board.isPressed("A")){
				x += -runSpeed;
			}if(board.isPressed("S")){
				//y += runSpeed;
			}if(board.isPressed("D")){
				x += runSpeed;
			}
		}else{
			if(board.isPressed("W")){
				//y += -walkSpeed;
			}if(board.isPressed("A")){
				x += -walkSpeed;
			}if(board.isPressed("S")){
				//y += walkSpeed;
			}if(board.isPressed("D")){
				x += walkSpeed;
			}
		}
		if(board.isPressed("SPACE")){
			//y += -jumpSpeed;
			if(checkCollision(this, new Location(0,1), false, deltaTime)){
				this.velocity.setY(-650);
			}
		}
		
		if(x == 0 && y == 0) return;
		
		BoxCollider col = (BoxCollider) collider;
		
		
		
		Location xLoc = this.loc.add(deltaTime * x, 0);
		
		boolean hit = checkCollision(xLoc, xLoc.add(col.getMaxPoint()), deltaTime);
		if(hit){
			
		}else{
			this.loc = xLoc;
		}
		
		
		Location yLoc = this.loc.add(0, deltaTime * y);
		
		hit = checkCollision(yLoc, yLoc.add(col.getMaxPoint()), deltaTime);
		if(hit){
			
		}else{
			this.loc = yLoc;
		}
		
	}

	@Override
	void fixedUpdate(double deltaTime) {		
		BoxCollider boxCollider = (BoxCollider) this.collider;

		boolean hit = checkCollision(this, deltaTime);
		
		
		if(hit){			
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
	
	public boolean checkCollision(Object2D mainObj, Location addLoc, boolean addVelocity, double deltaTime){
		BoxCollider col = (BoxCollider) mainObj.collider;	
		
		Location loc =null;
			if(addVelocity){
				loc = addLoc.add(mainObj.loc.add(velocity.getX() * deltaTime, velocity.getY() * deltaTime));

			}else{
				loc = addLoc.add(mainObj.loc);

			}

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
		if((loc1Max.getX() < loc2Min.getX())) return false;
		if((loc1Min.getX() > loc2Max.getX())) return false;
		if((loc1Max.getY() < loc2Min.getY())) return false;
		if((loc1Min.getY() > loc2Max.getY())) return false;
		return true;
	}
	
	
	
	
	
}
