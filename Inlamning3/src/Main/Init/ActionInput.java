package Main.Init;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Main.Game;
import Main.GameBoard.Cells;
import Main.GameBoard.GameBoard;

public class ActionInput implements ActionListener {

	Cells cell0;
	private Game game = Game.getGameInstance();
	public static boolean won = false;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!won)
		{
			getEmptyCellPosition();
			moveMark(e);
			checkWin(e);
		}
	}
	public void moveMark(ActionEvent e)
	{
		for(Cells x : GameBoard.GameButtons)
		{
			if(e.getSource().equals(x.getMark()))
			{
				int col = x.getCol();
				int row = x.getRow();
				if(x.equals(cell0))
					JOptionPane.showMessageDialog(null, "Kan inte flytta en tom ruta");
				if(col-1 == cell0.getCol() || col+1 == cell0.getCol() || row-1 == cell0.getRow() || row+1 == cell0.getRow())
				{
					if(row == cell0.getRow() || col == cell0.getCol())
					{
						cell0.setMark(x.toString());
						x.setMark("");
					}
				}
				else if(row == cell0.getRow() || col == cell0.getCol())
				{
					//moveSeveralMarks(x);
					break;
				}
			}
		}
	}
	
	public void moveSeveralMarks(Cells Clicked)
	{
		if(Clicked.getRow() > cell0.getRow())
		{
			int swapTimes = (Clicked.getRow() - cell0.getRow());
			int startRow = cell0.getRow();
			swapMarksDown(swapTimes, startRow);
			Clicked.setMark("");
		}
		if(Clicked.getRow() < cell0.getRow())
		{
			int swapTimes = (cell0.getRow()- Clicked.getRow());
			int startRow = cell0.getRow()-1;


			//swapMarksUp(swapTimes, startRow, Clicked);
			//Clicked.setMark("");
		}
	}
	
	public void swapMarksDown(int swapTimes, int startRow)
	{
		int i = startRow;
		while(i<swapTimes)
		{
			for(int x = 0;x<GameBoard.GameButtons.size();x++)
			{
				Cells temp = GameBoard.GameButtons.get(x);
				if(temp.getRow() == i && temp.getCol() == cell0.getCol())
				{
					if(((i+1)*4)+cell0.getCol()< GameBoard.GameButtons.size())
						temp.setMark(GameBoard.GameButtons.get(((i+1)*4)+cell0.getCol()).getMark().getText());
					i++;
				}
			}
		}
	}
	
	public void swapMarksUp(int swapTimes, int startRow, Cells Clicked)
	{
		int i = swapTimes;
		System.out.println(swapTimes);
		System.out.println(startRow);
		while (i>swapTimes)
		{
			for(int x = GameBoard.GameButtons.size()-1;x>0;x--)
			{
				Cells temp = GameBoard.GameButtons.get(x);
				if(temp.getCol() == cell0.getCol() && temp.getRow() == i)
				{
					if(((i+1)*4)-4< GameBoard.GameButtons.size() && i != Clicked.getRow())
					{
						System.out.println(i);
						if(i-1 >0)
						temp.setMark(GameBoard.GameButtons.get(((i)*4)+cell0.getCol()).getMark().getText());
					}
					i--;
				}
			}
		}
	}
	
	public void checkWin(ActionEvent e)
	{
		int win = 0;
		for(int i = 0; i<GameBoard.GameButtons.size(); i++)
		{
			if(GameBoard.GameButtons.get(i).toString().equals((i+1)+""))
				win++;
			if(win == GameBoard.GameButtons.size()-1 && GameBoard.GameButtons.getLast().toString().equals(""))
			{
				game.Stop();
				won = true;
				JOptionPane.showMessageDialog(null, "Grattis du klarade spelet!\n" + game.getTid());
				break;
			}
		}
	}
	public void getEmptyCellPosition(){
	for(Cells x : GameBoard.GameButtons)
		if(x.getMark().getText().equals(""))
			cell0 = x;
	}
}
