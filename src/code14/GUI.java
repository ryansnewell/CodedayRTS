package code14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GUI extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2686126430963713110L;
	private JFrame frame;
	
	public GUI(JFrame frame)
	{
		this.frame = frame;
	}

	public void paint(Graphics g)
	{

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.gray);
		Rectangle2D bottomArea = new Rectangle2D.Double(0, frame.getHeight()-50, frame.getWidth(), 50);
		g2.fill(bottomArea);
		
		//Spawn rectangle
		g2.setColor(Color.BLACK);
		Rectangle2D spawnTower = new Rectangle2D.Double(975, 575, 25, 25);
		g2.fill(spawnTower);

	}
}
