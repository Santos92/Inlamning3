package Main.Init;
 
import java.awt.Dimension;

import javax.swing.JFrame;

import Main.Game;

public class Window extends JFrame {

	private static final long serialVersionUID = -7717423611717851921L;

	public Window(int W, int H, String Title, Game game)
	{
		super(Title);
		game.setPreferredSize(new Dimension(W,H));
		game.setMinimumSize(new Dimension(W,H));
		game.setMaximumSize(new Dimension(W,H));
		add(game);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
