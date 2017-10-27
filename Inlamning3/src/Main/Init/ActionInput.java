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
					moveMarks(x);
					x.setMark("");
					break;
				}
			}
		}
	}
	
	public void moveMarks(Cells Clicked)
	{
		if(Clicked.getRow() > cell0.getRow())
		{
			moveUpDown(Clicked, false);
		}
		if(Clicked.getRow() < cell0.getRow())
		{
			moveUpDown(Clicked, true);
		}
		if(Clicked.getCol() < cell0.getCol())
		{
			moveLeftRight(Clicked, true);
		}
		if(Clicked.getCol() > cell0.getCol())
		{
			moveLeftRight(Clicked, false);
		}
	}
	public void moveLeftRight(Cells Clicked, boolean moveLeft)
	{
		int row = Clicked.getRow();
		int Lcol = Clicked.getCol();
		int col = cell0.getCol();
		int swapTimes = Clicked.getCol() - cell0.getCol();
		if(moveLeft)
			swapTimes = cell0.getCol() - Clicked.getCol();
		
		for(int a = swapTimes;swapTimes>0;swapTimes-- )
			for(Cells x : GameBoard.GameButtons)
				for(Cells i : GameBoard.GameButtons)
				{
					if(!moveLeft)
					{
						if(x.getCol() == col && x.getRow() == row && col<Lcol)
							if(i.getCol() == col+1 && i.getRow() == row)
							{
								x.setMark(i.toString());
								col++;
								break;
							}
					}
					else
					{
						if(x.getCol() == col && x.getRow() == row && col>Lcol)
							if(i.getCol() == col-1 && i.getRow() == row)
							{
								x.setMark(i.toString());
								col--;
								break;
							}
					}
				}
	}
	public void moveUpDown(Cells Clicked, boolean moveUp)
	{
		int col = Clicked.getCol();
		int Lrow = Clicked.getRow();
		int row = cell0.getRow();
		int swapTimes = cell0.getRow() - Clicked.getRow();
		if(!moveUp)
			swapTimes = Clicked.getRow() - cell0.getRow();
		for(int a = swapTimes;swapTimes>0;swapTimes-- )
			for(Cells x : GameBoard.GameButtons)
				for(Cells i : GameBoard.GameButtons)
				{
					if(moveUp)
					{
						if(x.getRow() == row && x.getCol() == col && row>Lrow)
							if(i.getRow() == row-1 && i.getCol() == col)
							{
								x.setMark(i.toString());
								row--;
								break;
							}
					}
					else
					{
						if(x.getRow() == row && x.getCol() == col && row<Lrow)
							if(i.getRow() == row+1 && i.getCol() == col)
							{
							x.setMark(i.toString());
							row++;
							break;
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
