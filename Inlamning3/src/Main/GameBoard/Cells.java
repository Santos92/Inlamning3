package Main.GameBoard;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Main.Game;
import Main.Init.ActionInput;

public class Cells {

	private JButton Button;
	private String number;
	private int row, col;
	public Cells(String Number, int col, int row, int FontSize)
	{
		Button = new JButton(Number);
		Button.setFont(new Font("Arial", Font.BOLD, FontSize));
		if(Number.equals(""))
			Button.setBackground(Color.white);
		else
			Button.setBackground(Game.colorTheme.getBackGroundColor());
		Button.setForeground(Game.colorTheme.getForeGroundColor());
		Button.addActionListener(new ActionInput());
		number = Number;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public JButton getMark()
	{
		return Button;
	}
	public void setMark(String s)
	{
		Button.setText(s);
		number=s;
		if(s.equals(""))
			Button.setBackground(Color.white);
		else
			Button.setBackground(Game.colorTheme.getBackGroundColor());
	}
	public void setColors()
	{
		if(number.equals(""))
		{
			Button.setBackground(Color.white);
			Button.setForeground(Game.colorTheme.getForeGroundColor());
		}
		else
		{
			Button.setBackground(Game.colorTheme.getBackGroundColor());
			Button.setForeground(Game.colorTheme.getForeGroundColor());
		}
	}
	public String toString()
	{
		return number;
	}
}
