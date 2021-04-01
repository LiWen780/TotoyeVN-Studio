package totoye;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class HTScrollBarUI extends BasicScrollBarUI{
	
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
	{
		
	}
	
	  @Override
	  protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);
	    Color color = null;
	    JScrollBar sb = (JScrollBar) c;
	    if (!sb.isEnabled() || r.width < r.height) {
	      return;
	    } else if (isDragging) {
	      color = Color.BLACK;
	    } else if (isThumbRollover()) {
	      color = Color.GRAY;
	    } else {
	      color = Color.DARK_GRAY;
	    }
	    g2.setPaint(color);
	    g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
	    g2.setPaint(Color.WHITE);
	    g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
	    g2.dispose();
	  }
}
