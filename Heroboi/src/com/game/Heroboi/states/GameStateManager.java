package com.game.Heroboi.states;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.game.Heroboi.GamePanel;
import com.game.Heroboi.utility.KeyHandler;
import com.game.Heroboi.utility.MouseHandler;
import com.game.Heroboi.utility.Vector2f;

//Includes menus state, game over state
//Might be five different states (could put in stack or array list - - might be stack
//as menu needs to be popped off before going into the actual game state
//But also might have array list because want game to be visible during pause state 
public class GameStateManager 
{
	
	private ArrayList<GameState> states; 
	public static Vector2f map; //Reason why this is static is because we are using this to manipulate the 
	//x and y positions of our scene, the player will stay in the centre of the screen. Unless you hit a boundary, 
	//then you move world instead of player. 
	public static final int PLAY = 0; //Figure out which state is which 
	public static final int MENU = 1;
	public static final int PAUSE = 2; 
	public static final int GAMEOVER = 3; 
	
	public GameStateManager()
	{
		map = new Vector2f(GamePanel.WIDTH, GamePanel.HEIGHT); 
		Vector2f.setWorldVar(map.x,  map.y);
		states = new ArrayList<GameState>(); 
		states.add(new PlayState(this));
	}
	
	public void pop(int state)
	{
		states.remove(state);
	}
	
	public void add(int state)
	{
		if(state == PLAY)
		{
			states.add(new PlayState(this)); 
		}
		if(state == MENU)
		{
			states.add(new MenuState(this));
		}
		if(state == PAUSE)
		{
			states.add(new PauseState(this));
		}
		if(state == GAMEOVER)
		{
			states.add(new GameOverState(this));
		}
	}
	
	
	public void addAndPop(int state)
	{
		states.remove(0);
		add(state);
		
	}
	
	//Don't quite get the point of the recursion here....unless the individual state's update function does something diff 
	//TBH thats probs what its doing
	public void update()
	{
		Vector2f.setWorldVar(map.x, map.y);
		for(int i = 0; i < states.size(); i++)
		{
			states.get(i).update(); 
		}	
	}
	
	public void render(Graphics2D g)
	{
		for(int i = 0; i < states.size(); i++)
		{
			states.get(i).render(g); 
		}
	}
	
	public void input(MouseHandler mouse, KeyHandler key)
	{
		for(int i = 0; i < states.size(); i++)
		{
			states.get(i).input(mouse, key); 
		}	
	}

}
