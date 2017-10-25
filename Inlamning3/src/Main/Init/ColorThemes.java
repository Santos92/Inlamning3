package Main.Init;

import java.awt.Color;

public class ColorThemes {

	Color foreGroundColor;
	Color backGroundColor;
	String name;
	
	public ColorThemes(String name, Color backGround, Color foreGround)
	{
		foreGroundColor = foreGround;
		backGroundColor = backGround;
		this.name = name;
	}

	public Color getForeGroundColor() {
		return foreGroundColor;
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}
	public String toString()
	{
		return name;
	}
}
