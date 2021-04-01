package totoye;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class ReturnIcon {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int Swidth = (int) (screenSize.getWidth());
    
	public ReturnIcon()
	{
	
	}
	
	ImageIcon get(String url)
	{
		ImageIcon icon = null;
		
		icon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("nodeIcon//"+url)).getImage().getScaledInstance( Swidth/55, Swidth/55, java.awt.Image.SCALE_SMOOTH));
		
		return icon;
	}
	
	ImageIcon getIcon(Object value)
	{
		ImageIcon icon = null;
		
		if(value.toString().equals("Character"))
		{
			icon = get("characterIcon.png");
		}
		else if(value.toString().equals("button"))
		{
			icon = get("buttonIcon.png");
		}
		else if(value.toString().equals("image"))
		{
			icon = get("imageIcon.png");
		}
		else if(value.toString().equals("Reader"))
		{
			icon = get("readerIcon.png");
		}
		else if(value.toString().equals("Layer"))
		{
			icon = get("layerIcon.png");
		}
		else if(value.toString().equals("endChapter"))
		{
			icon = get("endIcon.png");
		}
		else if(value.toString().equals("startChapter"))
		{
			icon = get("startIcon.png");
		}
		else if(value.toString().equals("addDialog"))
		{
			icon = get("dialogIcon.png");
		}
		else if(value.toString().equals("sceneBackground"))
		{
			icon = get("backgroundIcon.png");
		}
		else if(value.toString().equals("show"))
		{
			icon = get("showIcon.png");
		}
		else if(value.toString().equals("play"))
		{
			icon = get("playIcon.png");
		}
		else if(value.toString().equals("volume"))
		{
			icon = get("volumeIcon.png");
		}
		else if(value.toString().equals("addChoice"))
		{
			icon = get("choiceIcon.png");
		}
		else if(value.toString().equals("eventListener"))
		{
			icon = get("eventIcon.png");
		}
		else if(value.toString().equals("Screen"))
		{
			icon = get("screenIcon.png");
		}
		else if(value.toString().equals("Book"))
		{
			icon = get("bookIcon.png");
		}
		else if(value.toString().equals("hide"))
		{
			icon = get("hideIcon.png");
		}
		else if(value.toString().equals("pause"))
		{
			icon = get("pauseIcon.png");
		}
		else if(value.toString().equals("stop"))
		{
			icon = get("stopIcon.png");
		}
		
		return icon;
	}
}
