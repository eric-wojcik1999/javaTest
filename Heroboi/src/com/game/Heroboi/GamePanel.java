package com.game.Heroboi;

//GAY

import javax.swing.JPanel;

import com.game.Heroboi.states.GameStateManager;
import com.game.Heroboi.utility.KeyHandler;
import com.game.Heroboi.utility.MouseHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage; 

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 3464585978425008703L;
	
	//Dimensions
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480; 
	
	//Game Thread
	private Thread thread;
	private boolean running;
	
	//Image
	private BufferedImage image; 
	private Graphics2D g1; 
	
	private MouseHandler mouse; 
	private KeyHandler key;
	
	private GameStateManager gsm; 
	
	public GamePanel()
	{
		super(); 
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify()
	{
		super.addNotify();
		if(thread==null)
		{
			thread = new Thread(this, "GameThread");
			addKeyListener(this);
			thread.start(); 
		}
	}

	
	//FIXED TIME GAME LOOP - find out how many updates have gone by. Basically get the now time 
	//and subtract it by previous time to get the time that has passed and then we check if 
	//thats greater than how many hertz we want. If that is true, then we update. 
	//A fixed game loop is used because typically not going to be running on
	//radically different environments so not of major concern. If wanted to make variable 
	//accounts for lag, would put some arguments in update or some such. Can look this up 
	//later probs. Hybrid useful to overcome issues with say updating during/between renders 
	//which could raise issues with bullet physics and bullet time, etc. 
	public void run()
	{
		init();
		
		//Below we are setting the number of times we want to update/refresh 
		final double GAME_HERTZ = 60.0; 
		final double TBU = 1000000000 / GAME_HERTZ;   //Time before update, divided by a 
		//billion because working with nano-seconds   
		final int MUBR = 5; //Must update before render 
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime; 
		final double TARGET_FPS = 60.0;
		final double TTBR = 1000000000 / TARGET_FPS; //Total Time Before Render  
		
		int frameCounter = 0; //To count how many frames being rendered 
		int lastSecondTime = (int) (lastUpdateTime / 1000000000); 
		int oldFrameCounter = 0; 
		
		//This will be the main game loop
		while(running)
		{
			double currentTime = System.nanoTime(); 
			int updateCount = 0; 
			//Get current time and subtract by the last time updated and if thats greater
			//than the hertz we want AND the update count is less than the must updaate before
			//then we can perform an update - -  if already updated 5 times then can just go 
			//ahead and render, for instance. In particular (CURRENT TIME - LASTUPDATE) > TBU 
			//is checking if the gigahertz is on time 
			while(((currentTime - lastUpdateTime) > TBU) && (updateCount < MUBR)) 
			{
				update();
				input(mouse, key); 
				lastUpdateTime += TBU; //So can actually exit loop 
				updateCount++; 
			}
			
			//Ensure everything is within bounds 
			if((currentTime - lastUpdateTime) > TBU)
			{
				lastUpdateTime = currentTime - TBU; 
			}
			
			input(mouse, key); //we want input to be repeatedly checked 
			draw();
			render();
			
			lastRenderTime = currentTime; 
			frameCounter++; 
			
			//Only outputting the CHANGE in frame rate 
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime)
			{
				if(frameCounter != oldFrameCounter)
				{
					System.out.println("NEW SECOND" + thisSecond + " " + frameCounter); 
					oldFrameCounter = frameCounter; //to make if statement true
				}
				frameCounter = 0;
				lastSecondTime = thisSecond; 
			}
			
			
			//Allow CPU some time to rest
			while(currentTime - lastRenderTime < TTBR && currentTime - lastUpdateTime < TBU)
			{
				Thread.yield(); //Gives hint to thread scheduler that is is ready to pause 
				//execution 
				try
				{
					Thread.sleep(1);
				}
				catch(Exception e)
				{
					System.out.println("ERROR: thread is yielding"); 
				}
				currentTime = System.nanoTime(); 
			}
		}
		
	}
	
	private void init()
	{
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);  
		g1 = (Graphics2D) image.getGraphics(); //This graphic is being drawn onto the buffered image
		mouse = new MouseHandler(this); 
		key = new KeyHandler(this); 
		running = true;
		gsm = new GameStateManager();
	}
	
	
	public void update() 
	{
		gsm.update(); 
	} 
	
	
	public void input(MouseHandler mouse, KeyHandler key)
	{
		gsm.input(mouse,  key);
	}
	
	public void draw() 
	{
		Graphics g2 = (Graphics) this.getGraphics(); //Calling method in JPanel component 
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);  //'Image' is will be the buffered image that will be drawn on the screen
		g2.dispose(); //Optimizes and improves performance 
	} 
	
	
	public void render() 
	{
		if (g1 != null)
		{
			g1.setColor(new Color(66,134,244));
			g1.fillRect(0, 0, WIDTH, HEIGHT); //Creates rectangle with following params 
			gsm.render(g1);
		}
	} 
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {}
	public void keyReleased(KeyEvent key) {} 
	

}
