package com.game.Heroboi;

import javax.swing.JFrame; 

public class GameWindow extends JFrame
{
	private static final long serialVersionUID = -5701811177551821338L;

	public GameWindow()
	{
		JFrame frame = new JFrame("Heroboi");
		frame.setTitle("NoodleMan3000");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
