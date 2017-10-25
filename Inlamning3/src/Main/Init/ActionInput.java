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
					if(row == cell0.getRow() || col == cell0.getCol())
					{
						cell0.setMark(x.toString());
						x.setMark("");
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
