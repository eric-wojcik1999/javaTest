package com.game.Heroboi.utility;

public class Vector2f 
{

	public float x;
	public float y;
	
	public static float worldX; 
	public static float worldY; 
	
	//Default Constructor
	public Vector2f()
	{
		x = 0;
		y = 0; 
	}
	
	public Vector2f(Vector2f pos)
	{
		new Vector2f(pos.x, pos.y);
	}
	
	//Constructor
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y; 
	}
	
	//Just used so don't have to manually and continously add x/y to a certain amount
	public void addX(float f)
	{
		x += f; 
	}
	
	public void addY(float f)
	{
		y += f; 
	}
	
	public void setX(float f)
	{
		x = f; 
	}
	
	public void setY(float f)
	{
		y = f; 
	}
	
	public void setVector(Vector2f vec)
	{
		this.x = vec.x; 
		this.y = vec.y; 
	}
	
	public void setVector(float x, float y)
	{
		this.x = x;
		this.y = y; 
	}
	
	public static void setWorldVar(float x, float y)
	{
		worldX = x;
		worldY = y;
	}
	
	public Vector2f getWorldVar()
	{
		return new Vector2f(x - worldX, y - worldY);
	}
	
	@Override
	public String toString()
	{
		return x + ", " + y;
	}
	
	
	
}
