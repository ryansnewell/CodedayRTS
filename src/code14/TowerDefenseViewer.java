package code14;

import java.io.IOException;

import javax.swing.JFrame;

public class TowerDefenseViewer {

	public static void main(String arg[]) throws IOException, ClassNotFoundException
	{
		JFrame frame = new JFrame();
		TowerDefense game = new TowerDefense(frame);	
			
		frame.add(game);
		//Instantiate new frame
		frame.setSize(1000, 600);
	
		frame.setTitle("Alien Face");		//Set frame title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		//Set frame visible
		


		while(true)
		{

			try {
				
				game.update();
				game.repaint();
			
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}
}
