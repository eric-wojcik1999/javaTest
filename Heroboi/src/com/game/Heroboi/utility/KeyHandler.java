package com.game.Heroboi.utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.game.Heroboi.GamePanel;


//The methods keyPressed, released and typed are all inherited abstract methods that we must implement
public class KeyHandler implements KeyListener 
{

	public static List<Key> keys = new ArrayList<Key>(); 
	
	
	//Basically for every key, there is going to be a class for it (or all the keys you need) 
	//In that class you can see if a key is being toggled, pressed, how many times it has been pressed
	//and if its been pressed down or not.
	//And then we have ticks, which is basically if its been clicked or not. Different from press wherein a
	//click is more press and immediately release whereas you would hold the otherone for a period of time 
	public class Key
	{
		public int presses, absorbs;
		public boolean down, clicked; 
		
		public Key()
		{
			keys.add(this); 
		}
		
		public void toggle(boolean pressed)
		{
			if(pressed != down)
			{
				down = pressed; 
			}
			if(pressed)
			{
				presses++; 
			}
		}
		
		public void tick()
		{
			if(absorbs < presses)
			{
				absorbs++;
				clicked = true; 
			}
			else
			{
				clicked = false; 
			}
		}
	}
	
	//These will be associated with WASD and numbers 
	public Key up = new Key();
	public Key down = new Key(); 
	public Key left = new Key(); 
	public Key right = new Key(); 
	public Key menu = new Key();
	public Key enter = new Key(); 
	public Key escape = new Key(); 
	public Key attack = new Key(); 
	
	//Constructor for KeyHandler
	public KeyHandler(GamePanel game)
	{
		game.addKeyListener(this);
	}
	
	//So basically every key that has been pressed down now has no pressure applied 
	public void releaseAll()
	{
		for(int i = 0; i < keys.size(); i++)
		{
			keys.get(i).down = false; 
		}
	}
	
	public void tick()
	{
		for(int i = 0; i < keys.size(); i++)
		{
			keys.get(i).tick(); 
		}
	}
	
	//Associate these key classes to a key on the keyboard
	//This is basically a handler, and everytime something is being pressed, it has a code to it so we get the code 
	//And check if that code is equal to a key
	public void toggle(KeyEvent e, boolean pressed)
	{
		if(e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed); //Essentially, if the key W pressed, associated with up
		if(e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		if(e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if(e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if(e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
		if(e.getKeyCode() == KeyEvent.VK_TAB) menu.toggle(pressed);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
		if(e.getKeyCode() == KeyEvent.VK_E) enter.toggle(pressed);
		
	}
	
	//Call method toggle and pass in key event, and since it is being pressed we pass in true
	@Override
	public void keyPressed(KeyEvent e)
	{
		toggle(e,true);
	}

	//Call method toggle and pass in key event, and pass in false because it is not being pressed anymore 
	@Override
	public void keyReleased(KeyEvent e) 
	{
		toggle(e,false );	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nada 
		//Do not need this method because it is handled by the tick() methods (the one not within a key class, although a key
		//class has its own tick() method that is called by the external tick() method  
	}

}
