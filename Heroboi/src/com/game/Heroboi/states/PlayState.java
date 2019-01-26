package com.game.Heroboi.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.Heroboi.utility.KeyHandler;
import com.game.Heroboi.utility.MouseHandler;
import com.game.graphics.Font;

public class PlayState extends GameState
{
	private Font font; 
	
	public PlayState(GameStateManager gsm)
	{
		super(gsm); 
		font = new Font("/src/ZeldaFont.png",16,16); 
	}
	
	public void update()
	{
		
	}
	
	public void input(MouseHandler mouse, KeyHandler key)
	{
		//Check if key UP is pressed down
		//if(key.up.down)
		//{
		//	System.out.println("URAGAY, W is being assaulted by me");
		//}
		//if(key.down.down)
		//{
		//	System.out.println("URAGAY, S is being assaulted by me");
		//}
	}
	
	public void render(Graphics2D g)
	{
		//g.setColor(Color.RED);
		//g.fillRect(100, 100, 200, 200);
	}

}
