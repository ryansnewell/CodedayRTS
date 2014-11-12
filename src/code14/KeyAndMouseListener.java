package code14;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class KeyAndMouseListener implements KeyListener, MouseListener{
    /** Handle the key typed event from the text field. */
	private TowerDefense td;
	public KeyAndMouseListener(TowerDefense td)
	{
		this.td = td;
	}

  public void keyTyped(KeyEvent e) {

  }

    /** Handle the key-pressed event from the text field. */
  public void keyPressed(KeyEvent e) {
  	if(e.getKeyCode() == KeyEvent.VK_LEFT)
    {
    	td.left = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
    	td.right = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_UP)
    {
    	td.up = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN)
    {
    	td.down = true;
    }
  }

    /** Handle the key-released event from the text field. */
  public void keyReleased(KeyEvent e) {
  	if(e.getKeyCode() == KeyEvent.VK_LEFT)
   	{
    	td.left = false;
    }
    if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
    	td.right = false;
    }
    if(e.getKeyCode() == KeyEvent.VK_UP)
  	{
   		td.up = false;
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN)
    {
    	td.down = false;
    }
  }

		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			if(SwingUtilities.isLeftMouseButton(e))
			{
				if(td.redTower==true)
				{
				System.out.println("CLICKED BITCH LEFT");
				td.addTower1R(e.getX(), e.getY());
				System.out.println(td.opp_towers.size());
				td.redTower= false;
				}
				if(td.redEnemy==true)
				{
					td.addEnemy1R(e.getX(), e.getY());
					td.redEnemy=false;
				}
				if(td.blueTower==true)
				{
					td.addTower1B(e.getX(),e.getY());
					td.blueTower=false;
				}
				if(td.blueEnemy==true)
				{
					td.addEnemy1B(e.getX(),e.getY());
					td.blueEnemy=false;
				}
			}
			if(SwingUtilities.isRightMouseButton(e))
			{
				if(td.redTower==true)
				{
					td.redTower= false;

				}
					else
				{
					System.out.println("CLICKED BITCH Right");
				}
			}
			td.checkarea(e.getX(), e.getY());
		}
		public void mouseReleased(MouseEvent e) {
			
	}

}
