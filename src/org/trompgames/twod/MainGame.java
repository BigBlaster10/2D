package org.trompgames.twod;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import org.trompgames.utils.Keyboard;
import org.trompgames.utils.Location;

public class MainGame implements Runnable{

	private ArrayList<Object2D> objects = new ArrayList<>();
	private boolean started = false;
	private Frame2D frame;
	private Keyboard keyboard;
	
	private long lastTimeMills = System.currentTimeMillis();	
	private long maxFpsTimeMills = System.currentTimeMillis();	

	
	public MainGame(){		
	}
	
	
	public static final int MAXFPS = 200;  //16.6
	public static final int MAXMSBETWEEN = 1000/MAXFPS;
	
	public static final int FIXEDFPS = 50; //20 ms
	public static final int FIXEDMSBETWEEN = 1000/FIXEDFPS;
	
	@Override
	public void run() {
		while(true){
			if(!started) continue;	
			
			if(System.currentTimeMillis() - maxFpsTimeMills >= MAXMSBETWEEN){			
				for(Object2D obj : objects){
					obj.update(deltaTime());
				}
				maxFpsTimeMills = System.currentTimeMillis();
			}
			
			if(System.currentTimeMillis() - lastTimeMills >= FIXEDMSBETWEEN){			
				for(Object2D obj : objects){
					obj.fixedUpdate(fixedDeltaTime());
				}
				frame.getPanel().repaint();
				lastTimeMills = System.currentTimeMillis();
			}
			
		}	
	}
	
	
	public double deltaTime(){
		return (1.0 * System.currentTimeMillis() - maxFpsTimeMills)/1000;
	}
	
	public double fixedDeltaTime(){
		return (1.0 * System.currentTimeMillis() - lastTimeMills)/1000;
	}
	
	public void startGame(){
	
		
		frame = new Frame2D(this);
		this.keyboard = new Keyboard(frame.getPanel());
		
		
		File playerImage = new File("C:\\Users\\Thomas\\Desktop\\New Game\\First Character (Trimmed).png");
		Player player = new Player(playerImage, new Location(250,150), this);		
		
		File groundFile = new File("C:\\Users\\Thomas\\Desktop\\New Game\\Ground Trimmed.png");
		Ground ground = new Ground(groundFile, new Location(10,450), this);
		ground.setCollider(new BoxCollider(ground.getLocation(), new Location(ground.getImage().getWidth(), ground.getImage().getHeight())));
		
		Ground ground2 = new Ground(groundFile, new Location(10 + ground.getImage().getWidth(),450), this);
		ground2.setCollider(new BoxCollider(ground.getLocation(), new Location(ground.getImage().getWidth(), ground.getImage().getHeight())));

		
		File crateFile = new File("C:\\Users\\Thomas\\Desktop\\New Game\\Crate.png");
		Crate crate = new Crate(crateFile, new Location(250, -100), this);
		
		objects.add(player);
		objects.add(ground);
		objects.add(ground2);

		objects.add(crate);
		started = true;
	}

	
	public ArrayList<Object2D> getObjects(){
		return objects;
	}
	
	public Keyboard getKeyboard(){
		return keyboard;
	}
	
}
