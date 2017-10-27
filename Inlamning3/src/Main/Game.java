package Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.GameBoard.GameBoard;
import Main.Init.ActionInput;
import Main.Init.ColorThemes;
import Main.Init.Window;

public class Game extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = -1789573121997106957L;
	public static final int WIDTH = 900, HEIGHT = 700;
	public static final String TITLE = "15 Spelet";

	private LinkedList<ColorThemes> ColorThemes;
	private Thread t;
	private boolean running = false;
	private double tid = 0;	
	private int size = 4;
	
	private JButton newGame = new JButton("Nytt spel");
	private JLabel timer = new JLabel("time: 15.5");
	
	private JLabel Size = new JLabel("Storlek: ");
	private JTextField newSize = new JTextField(""+size,2);
	
	private JLabel ColorThemeL = new JLabel("Färgtema");
	private JComboBox<String> themeChanger = new JComboBox<>();
	
	private JPanel Meny = new JPanel();
	private JPanel GameContent = new JPanel();
	
	public static Game game = new Game();
	private GameBoard Board;
	
	private Scanner scan;
	public static ColorThemes colorTheme; 
	
	public Game()
	{
		ColorThemes = initColorThemes();	
		
		for(ColorThemes x : ColorThemes)
			themeChanger.addItem(x.toString());
		
		Meny.setLayout(new FlowLayout());
		Meny.setPreferredSize(new Dimension(110,0));
		
		Meny.add(newGame);
		Meny.add(Size);
		Meny.add(newSize);
		Meny.add(timer);
		Meny.add(ColorThemeL);
		Meny.add(themeChanger);
		
		newGame.addActionListener(this);	
		newSize.addActionListener(this);
		themeChanger.addActionListener(this);

		Board = new GameBoard(size,size, GameContent);
		setColors();
		Start();
		
		setLayout(new BorderLayout());
		add(Meny, BorderLayout.WEST);
		add(GameContent, BorderLayout.CENTER);
	}
	public static void main(String[] args)
	{
		new Window(WIDTH, HEIGHT, TITLE, game);
	}
	
	public void actionPerformed(ActionEvent e) {
		Random r = new Random();
		if(e.getSource() == newGame)
		{
			scan = new Scanner(newSize.getText());
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
			ActionInput.won = false;
			Start();
			Board = new GameBoard(size,size, GameContent);
			tid=0;
			newSize.setText(size+"");
			GameContent.repaint();
			GameContent.revalidate();
		}
		else if (e.getSource() == themeChanger)
		{
			for(ColorThemes x : ColorThemes)
			{
				if(x.toString().equals(themeChanger.getSelectedItem().toString()))
				{
					colorTheme = x;
					setColors();
					themeChanger.setFocusable(false);
				}
			}
			ColorThemes.removeLast();
			ColorThemes.add(new ColorThemes("Random",new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));;
		}
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
				int Minuter = (int)tid/60;
				double sekunder = tid%60;
				timer.setText(String.format("Tid: %02d : %.2f", Minuter,sekunder));
			} catch (InterruptedException e) {
				// Ska Interuptas
			}
		}
	}
	
	public static Game getGameInstance()
	{
		return game;
	}
	public String getTid()
	{
		int Minuter = (int)tid/60;
		double sekunder = tid%60;
		if(tid > 60)
			return String.format("Tid: %02d Minuter %.2f sekunder", Minuter,sekunder);
		else
			return String.format("Tid: %.2f sekunder", sekunder);
	}

	public LinkedList<ColorThemes> initColorThemes()
	{
		Random r = new Random();
		LinkedList<ColorThemes> l = new LinkedList<>();
		l.add(new ColorThemes("Standard",new Color(0,76,153),new Color(150,210,240)));
		l.add(new ColorThemes("Black&White",new Color(0,0,0),new Color(255,255,255)));
		l.add(new ColorThemes("Shock pink",new Color(255,90,153),new Color(255,200,220)));
		l.add(new ColorThemes("Smooth",new Color(0,60,70),new Color(150,95,0)));
		l.add(new ColorThemes("Random",new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
		colorTheme = l.get(0);
		return l;
	}
	public void setColors()
	{
		Meny.setBackground(colorTheme.getBackGroundColor());
		
		newGame.setBackground(colorTheme.getForeGroundColor());
		newGame.setForeground(colorTheme.getBackGroundColor());
		
		newSize.setBackground(colorTheme.getForeGroundColor());
		newSize.setForeground(colorTheme.getBackGroundColor());
		
		themeChanger.setForeground(colorTheme.getBackGroundColor());
		themeChanger.setBackground(colorTheme.getForeGroundColor());
		
		ColorThemeL.setForeground(colorTheme.getForeGroundColor());
		ColorThemeL.setBackground(colorTheme.getBackGroundColor());
		
		Size.setForeground(colorTheme.getForeGroundColor());
		timer.setForeground(colorTheme.getForeGroundColor());
		
		GameContent.setBackground(colorTheme.getBackGroundColor());
		Board.setColors();
	}
}
