package com.game.Heroboi.utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.game.Heroboi.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener 
{
	
	private static int xPos = -1; //Set to -1 if not being pressed
	private static int yPos = -1; 
	private static int button = -1; 
	
	public MouseHandler(GamePanel game)
	{
		game.addMouseListener(this);
	}
	
	public int getX() 
	{
		return xPos; 
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public int getButton()
	{
		return button; 
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		xPos = e.getX(); 
		yPos = e.getY(); 
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		xPos = e.getX(); 
		yPos = e.getY(); 
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		button = e.getButton(); 
		//If left button, result is 1; right button = 3, middle = 2 
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		button = -1;
		//Set back to negative when released as that is default 
		
	}

}
