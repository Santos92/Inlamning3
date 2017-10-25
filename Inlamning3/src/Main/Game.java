package Main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.GameBoard.GameBoard;
import Main.Init.Window;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = -1789573121997106957L;
	public static final int WIDTH = 900, HEIGHT = 700;
	public static final String TITLE = "15 Spelet";

	ArrayList<JButton> GameButtons = new ArrayList<>();
	int size = 4;
	
	JButton newGame = new JButton("Nytt spel");
	JLabel timer = new JLabel("time: 15.5");
	
	JLabel Size = new JLabel("Storlek: ");
	JTextField newSize = new JTextField(""+size,2);
	
	JPanel Meny = new JPanel();
	JPanel GameContent = new JPanel();
	JPanel Score = new JPanel();
	
	GameBoard Board;
	
	public Game()
	{
		setLayout(new BorderLayout());
		Meny.setLayout(new FlowLayout());
		Score.setLayout(new FlowLayout());
		
		Meny.setPreferredSize(new Dimension(110,0));
		Score.setPreferredSize(new Dimension(120,0));
		
		Meny.add(newGame);
		Meny.add(Size);
		Meny.add(newSize);
		newGame.addActionListener(this);	
		newSize.addActionListener(this);
		
		Board = new GameBoard(size,size, GameContent);
		
		Score.add(timer);
		
		add(Score, BorderLayout.EAST);
		add(Meny, BorderLayout.WEST);
		add(GameContent, BorderLayout.CENTER);
		
	}
	public static void main(String[] args)
	{
		new Window(WIDTH, HEIGHT, TITLE, new Game());
	}
	
	public void actionPerformed(ActionEvent e) {
		Scanner scan = new Scanner(newSize.getText());
		try{
			size = scan.nextInt();
		}
		catch(InputMismatchException ex)
		{
			JOptionPane.showMessageDialog(null, "Skriv ett nummer mellan 2-12 tack!");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Oväntat fel inträffade! Stänger av..");
			System.exit(0);
		}
		if(size > 12)
		{
			JOptionPane.showMessageDialog(null, "Kan ej gå över 144 rutor!");
			size = 12;
		}
		if(size <= 1)
		{
			JOptionPane.showMessageDialog(null, "Kan ej gå under 4 rutor!");
			size = 2;
		}
		Board = new GameBoard(size,size, GameContent);
		newSize.setText(size+"");
		GameContent.repaint();
		GameContent.revalidate();
	}
}
