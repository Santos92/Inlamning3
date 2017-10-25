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

public class Game extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = -1789573121997106957L;
	public static final int WIDTH = 900, HEIGHT = 700;
	public static final String TITLE = "15 Spelet";

	private Thread t;
	private boolean running = false;
	private double tid = 0;
	
	private int size = 4;
	
	private JButton newGame = new JButton("Nytt spel");
	private JLabel timer = new JLabel("time: 15.5");
	
	private JLabel Size = new JLabel("Storlek: ");
	private JTextField newSize = new JTextField(""+size,2);
	
	private JPanel Meny = new JPanel();
	private JPanel GameContent = new JPanel();
	private JPanel Score = new JPanel();
	
	private GameBoard Board;
	public static Game game = new Game();
	
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
		
		Start();
		Board = new GameBoard(size,size, GameContent);
		
		Score.add(timer);
		
		add(Score, BorderLayout.EAST);
		add(Meny, BorderLayout.WEST);
		add(GameContent, BorderLayout.CENTER);
		
	}
	public static void main(String[] args)
	{
		new Window(WIDTH, HEIGHT, TITLE, game);
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
		if(size < 2)
		{
			JOptionPane.showMessageDialog(null, "Kan ej gå under 4 rutor!");
			size = 2;
		}
		Start();
		Board = new GameBoard(size,size, GameContent);
		tid=0;
		newSize.setText(size+"");
		GameContent.repaint();
		GameContent.revalidate();
	}

	public synchronized void Start()
	{
		if(running)
			return;
		
		t = new Thread(this);
		running = true;
		t.start();
	}
	public synchronized void Stop()
	{
		if(!running)
			return;
		
		running = false;
		t.interrupt();
	}
	
	public void run() {
		while(running)
		{
			try {
				t.sleep(10);
				tid += 0.01;
				timer.setText(String.format("Tid: %.2f", tid));
			} catch (InterruptedException e) {
				// Ska Interuptas
			}
		}
	}
	
	public static Game getGameInstance()
	{
		return game;
	}
	
}
