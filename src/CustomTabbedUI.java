package totoye;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class CustomTabbedUI extends BasicTabbedPaneUI 
{
	private Color backgroundColor;
	
	public CustomTabbedUI(Color backgroundColor)
	{
		super();
		this.backgroundColor = backgroundColor;
	}
	
	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	
	@Override
	public void paint(Graphics g, JComponent c)
	{
		g.setColor(this.backgroundColor);
		g.fillRect(0,0,tabPane.getBounds().width,tabPane.getBounds().height);
		
		super.paint(g, c);
	}
}
