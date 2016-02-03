package org.trompgames.utils;

public class Vector{

	private double x;
	private double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;		
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Vector add(Vector loc){
		return new Vector(loc.x  + x, loc.y + y);
	}
	
	public Vector minus(Vector loc){
		return new Vector(x - loc.x, y - loc.y);
	}
	
	public Vector add(double x, double y){
		return new Vector(this.x + x, this.y + y);
	}
	
	public Vector multiply(double i){
		return new Vector(x * i, y * i);
	}
	
	
	public static Vector lerp(Vector loc1, Vector loc2, double alpha){
		if(alpha < 0) alpha = 0;
		if(alpha > 1) alpha = 1;
		return loc1.multiply(1-alpha).add(loc2.multiply(alpha));
	}
	
	@Override
	public String toString(){
		return "X: " + x + " Y: " + y;
	}

}
