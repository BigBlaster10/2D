package org.trompgames.twod;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Frame2D extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4193822843911963966L;
	private Panel2D panel;
	private MainGame game;
	public Frame2D(MainGame game){
		this.game = game;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		panel = new Panel2D(this);
		this.add(panel);
		
		
		this.setSize(1000, 1000);
		//this.pack();
		
		this.setVisible(true);
	}
	
	public Panel2D getPanel(){
		return panel;
	}
	
	public MainGame getGame(){
		return game;
	}
	
}
