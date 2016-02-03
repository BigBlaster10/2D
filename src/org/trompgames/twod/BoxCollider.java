package org.trompgames.twod;

import org.trompgames.utils.Location;

public class BoxCollider implements Collider{

	private Location maxPoint;
	private Location minPoint;
	
	public BoxCollider(Location minPoint, Location maxPoint){
		this.maxPoint = maxPoint;
		this.minPoint = minPoint;
	}
	
	public Location getMaxPoint(){
		return maxPoint;
	}
	
	public Location getMinPoint(){
		return minPoint;
	}
	
}
