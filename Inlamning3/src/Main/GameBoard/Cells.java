package Main.GameBoard;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Main.Init.ActionInput;

public class Cells {

	private JButton Button;
	private String number;
	private int row, col;
	public Cells(String Number, int col, int row, int FontSize)
	{
		Button = new JButton(Number);
		Button.setFont(new Font("Arial", Font.BOLD, FontSize));
		Button.setBackground(new Color(150,210,240));
		Button.setForeground(new Color(0,76,153));
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
	}
	public String toString()
	{
		return number;
	}
}
