package org.trompgames.twod;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.trompgames.utils.Location;

public class Panel2D extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7623090509042582531L;

	private Frame2D frame;
	
	public Panel2D(Frame2D frame){
		this.frame = frame;
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		for(Object2D obj : frame.getGame().getObjects()){
			if(obj.isVisible()){
				g2d.drawImage(obj.getImage(), null, (int) obj.getLocation().getX(), (int) obj.getLocation().getY());
				if(obj.hasCollider()){
					BoxCollider collider = (BoxCollider) obj.collider;
					Location loc = obj.getLocation();
					Location maxLoc = collider.getMaxPoint().add(loc);
					
					g2d.drawLine((int) loc.getX(), (int) loc.getY(), (int) maxLoc.getX(), (int) loc.getY());				
					g2d.drawLine((int) loc.getX(), (int) loc.getY(), (int) loc.getX(), (int) maxLoc.getY());
					g2d.drawLine((int) maxLoc.getX(), (int) loc.getY(), (int) maxLoc.getX(), (int) maxLoc.getY());
					g2d.drawLine((int) loc.getX(), (int) maxLoc.getY(), (int) maxLoc.getX(), (int) maxLoc.getY());

					//g2d.drawRect(loc.getX(), loc.getY(), collider.getMaxPoint().getX(), collider.getMaxPoint().getY());
				}
			}
		}
		g2d.dispose();
		g.dispose();
	}
	
}
