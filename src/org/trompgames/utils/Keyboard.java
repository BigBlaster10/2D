package org.trompgames.utils;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.trompgames.twod.Panel2D;

public class Keyboard{

	private Panel2D panel;
	
	public Keyboard(Panel2D panel){
		this.panel = panel;		
		
      
		new Key("W", this).setKey();
		new Key("A", this).setKey();
		new Key("S", this).setKey();
		new Key("D", this).setKey();
		new Key("SPACE", this).setKey();
		new Key("SHIFT", this).setKey();

	}
	
	public boolean isPressed(String keyString){
		for(Key key : Key.keys){
			if(key.getKey().equals(keyString)) return key.isPressed();
		}
		System.out.println("Error: Key '" + keyString + "' was not found");
		return false;
	}

	
	public static class Key{		
		
		private boolean pressed = false;
		private String key;
		private Keyboard keyboard;
		
		public static ArrayList<Key> keys = new ArrayList<>();

		
		public Key(String key, Keyboard keyboard){
			this.key = key;
			this.keyboard = keyboard;
			keys.add(this);
		}
		
		public void setPressed(boolean pressed){
			this.pressed = pressed;
			//System.out.println(key + ": " + pressed);
		}
		
		public boolean isPressed(){
			return pressed;
		}
		
		public String getKey(){
			return key;
		}
		
		public void setKey(){
			//Normal settup
			keyboard.panel.getInputMap().put(KeyStroke.getKeyStroke("pressed " + key), key + "pressed");
			keyboard.panel.getActionMap().put(key + "pressed", new PressKey(this));	
			
			keyboard.panel.getInputMap().put(KeyStroke.getKeyStroke("released " + key), key + "released");
			keyboard.panel.getActionMap().put(key + "released", new ReleasedKey(this));	
			
			
			//With shift
			keyboard.panel.getInputMap().put(KeyStroke.getKeyStroke("shift pressed " + key), key + "pressed");
			keyboard.panel.getActionMap().put(key + "pressed", new PressKey(this));	
			
			keyboard.panel.getInputMap().put(KeyStroke.getKeyStroke("shift released " + key), key + "released");
			keyboard.panel.getActionMap().put(key + "released", new ReleasedKey(this));	

			
		}

		public static class PressKey extends AbstractAction{
			private static final long serialVersionUID = -4102984966348755397L;
			
			private Key key;
			public PressKey(Key key){
				this.key = key;
			}			
			@Override
			public void actionPerformed(ActionEvent event) {
				key.setPressed(true);
			}			
		}
		
		public static class ReleasedKey extends AbstractAction{			
			private static final long serialVersionUID = 7137592361015934417L;
			private Key key;
			public ReleasedKey(Key key){
				this.key = key;
			}			
			@Override
			public void actionPerformed(ActionEvent event) {
				key.setPressed(false);
			}			
		}
		
		
	}
	
}
