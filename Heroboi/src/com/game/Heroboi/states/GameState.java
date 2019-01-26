package com.game.Heroboi.states;

import java.awt.Graphics2D;

import com.game.Heroboi.utility.KeyHandler;
import com.game.Heroboi.utility.MouseHandler; 

public abstract class GameState 
{
	
	private GameStateManager gsm; 
	
	public GameState(GameStateManager inGsm)
	{
		inGsm = gsm; 
	}
	
	public abstract void update(); 
	public abstract void render(Graphics2D g); 
	public abstract void input(MouseHandler mouse, KeyHandler key); 
}
