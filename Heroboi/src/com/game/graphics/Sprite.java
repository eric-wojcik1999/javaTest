package com.game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.game.Heroboi.utility.Vector2f;

public class Sprite {
	
	private BufferedImage SPRITESHEET = null; 
	private BufferedImage[][] spriteArray;
	private final int TITLE_SIZE = 32; 
	public int w;
	public int h; 
	private int wSprite; 
	private int hSprite; 
	
	public Sprite(String file)
	{
		w = TITLE_SIZE;
		h = TITLE_SIZE;
		
		System.out.println("Loading " + file + "...");
		SPRITESHEET = loadSprite(file);
		
		wSprite = SPRITESHEET.getWidth() / w;
		hSprite = SPRITESHEET.getHeight() / h;
		loadSpriteArray(); 
	}
	
	//Importing sprites and specifying W/H
	public Sprite(String file, int w, int h)
	{
		this.w = w;
		this.h = h; 
		
		System.out.println("Loading" + file + "...");
		SPRITESHEET = loadSprite(file);
		
		wSprite = SPRITESHEET.getWidth() / w;
		hSprite = SPRITESHEET.getHeight() / h; 
		loadSpriteArray(); 
	} 
	
	public void setSize(int width, int height)
	{
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int i)
	{
		w = i;
		wSprite = SPRITESHEET.getWidth() / w;
	}
	
	public void setHeight(int i)
	{
		h = i; 
		hSprite = SPRITESHEET.getHeight() / h; 
	}
	
	public int getWidth()
	{
		return w; 
	}
	
	public int getHeight() 
	{
		return h; 
	}
	
	private BufferedImage loadSprite(String file)
	{
		BufferedImage sprite = null;
		try 
		{
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		}
		catch(Exception e)
		{
			System.out.println("ERROR: could not read the file and meme" + file);
		}
		return sprite; 
	}
	
	//Iterates through total number of columns and rows in the spread sheet 
	private void loadSpriteArray()
	{
		spriteArray = new BufferedImage[wSprite][hSprite];
		
		for(int x = 0; x < wSprite; x++)
		{
			for(int y = 0; y < hSprite; y++)
			{
				spriteArray[x][y] = getSprite(x,y); 
			}
		}
	}
	
	
	public BufferedImage getSpriteSheet() 
	{
		return SPRITESHEET; 
	}
	
	public BufferedImage getSprite(int x, int y)
	{
		return SPRITESHEET.getSubimage(x*w,  y*h, w, h);
	}
	
	//This will be used for animation class
	public BufferedImage[] getSpriteArray(int i)
	{
		return spriteArray[i];
	}
	
	
	//Also used for animation class 
	public BufferedImage[][] getSpriteArrayFull(int i)
	{
		return spriteArray;
	}
	
	//This method will be used for something like LIVES on the HUD 
	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset)
	{
		float x = pos.x;
		float y = pos.y; 
		
		for(int i = 0; i < img.size(); i++)
		{
			if(img.get(i) != null)
			{
				g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
			}
			
			x += xOffset; 
			y += yOffset; 
		}	
	}

	//This method will be used for the FONT class
	public static void drawArray(Graphics2D g, Null f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset)
	{
		float x = pos.x;
		float y = pos.y; 
		
		for(int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) != 32)
			{
				g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null); 
			}
			x += xOffset;
			y += yOffset; 
		}
	}
}
