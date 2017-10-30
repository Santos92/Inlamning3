package Main.GameBoard;
 
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

public class GameBoard {

	public static LinkedList<Cells> GameButtons = new LinkedList<Cells>();
	private int index = 0;
	private Random r = new Random();
	private LinkedList<String> temp = new LinkedList<String>();
	private Cells cell0;
	
	public GameBoard(int col, int row, JPanel p)
	{

			p.removeAll();
			GameButtons.clear();
			temp.clear();
			p.setLayout(new GridLayout(row,col));
			int size = col*row; 
			int fontSize = 110-(col*9);
			if(fontSize <20)
				fontSize = 13;
			for(int i = 0; i<size;i++)
			{
				temp.add(i+"");
			}
			for(int i = 0; i<row;i++)
			{
				for (int x = 0; x<col; x++)
				{
					int position = r.nextInt(temp.size());
					if(temp.get(position).equals(0+""))
					{
						GameButtons.add(new Cells("", x, i, fontSize)); 
						cell0 = GameButtons.getLast();
					}
					else
						GameButtons.add(new Cells(temp.get(position), x, i, fontSize));
					p.add(GameButtons.get(index).getMark());
					temp.remove(position);
					index++;
				}
			}
			if(!checkSolveAbility(size))
				new GameBoard(col,row,p);
				
		
	}
	private boolean checkSolveAbility(int width) {
		int inversions = 0;
		for(int i = 0; i<GameButtons.size();i++)
			for(int j = i; j<GameButtons.size();j++)
				if(!"".equals(GameButtons.get(i).toString()) && !"".equals(GameButtons.get(j).toString()))
					if( ( Integer.parseInt(GameButtons.get(i).toString()) ) > ( Integer.parseInt(GameButtons.get(j).toString()) ))
						inversions++;
		
		if(inversions %2 == 0 && width %2 != 0)
			return true;
		if(width %2 == 0 && cell0.getRow() %2 == 0 && inversions %2 != 0)
			return true;
		if(width %2 == 0 && cell0.getRow() %2 != 0 && inversions %2 == 0)
			return true;
		return false;
	}
	public void setColors()
	{
		for(Cells x : GameButtons)
			x.setColors();
	}
}
