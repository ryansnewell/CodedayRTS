package code14;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2246808801242800005L;
	int hp;
	double x;
	double y;
	double speed;
	double damage;
	double range;
	double shotSpeed;
	int shotReload;
	boolean selected;
	boolean destroyed;
	int width;
	ImageIcon face;
	//RED
	ImageIcon leftR = new ImageIcon("images/walkLeftRedS.gif");
	ImageIcon downRightR = new ImageIcon("images/walkDownRightRed.gif");
	ImageIcon downLeftR = new ImageIcon("images/walkDownLeftRed.gif");
	ImageIcon upRightR = new ImageIcon("images/walkRightUPRed.gif");
	ImageIcon upLeftR = new ImageIcon("images/walkLeftUpRed.gif");
	ImageIcon downR = new ImageIcon("images/walkDownRed.gif");
	ImageIcon upR = new ImageIcon("images/walkUPRed.gif");
	ImageIcon rightR = new ImageIcon("images/walkRightRedS.gif");
	//Blue
	ImageIcon leftB = new ImageIcon("images/walkLeftBlueS.gif");
	ImageIcon downRightB = new ImageIcon("images/walkDownRightBlue.gif");
	ImageIcon downLeftB = new ImageIcon("images/walkDownLeftBlue.gif");
	ImageIcon upRightB = new ImageIcon("images/walkRightUPBlue.gif");
	ImageIcon upLeftB = new ImageIcon("images/walkLeftUpBlue.gif");
	ImageIcon downB = new ImageIcon("images/walkDownBlue.gif");
	ImageIcon upB = new ImageIcon("images/walkUPBlue.gif");
	ImageIcon rightB = new ImageIcon("images/walkRightBlueS.gif");
	
	boolean red = false;
	boolean up1 = false;
	boolean down1 = false;
	boolean left1 = false;
	boolean right1 = false;
	double angle =0;
	//moving coordinates
		double selectedx;
		double selectedy;

		boolean moving =false;
		public ArrayList<Bullets> missiles = new ArrayList<Bullets>();

	public Enemies( double x, double y, int hp, double speed, double range, double shotSpeed,
			int shotReload, double damage, boolean destroyed, int width, boolean red)
	{
		this.x =x;
		this.y = y;
		this.hp= hp;
		this.speed = speed;
		this.range = range;
		this.shotSpeed = shotSpeed;
		this.shotReload = shotReload;
		this.damage = damage;
		this.destroyed = destroyed;
		this.width = width;
		face = new ImageIcon("images/walkDownRed.gif");
		this.red = red;
		
	}
	public void damageDone( double damage)
	{
		hp-= damage;
		if(hp<=0)
			destroyed= true;
	}
	public void moveShots(ArrayList<Enemies> enemies , ArrayList<Tower> towers) {
		for(Bullets e : missiles)
		{
			e.move(enemies, towers);
		}
}

	public void giveCoordinates(double selectedX, double selectedY)
	{
		this.selectedx = selectedX;
		this.selectedy = selectedY;
		moving = true;
	}
	public void move() {
	
		if(moving==true)
		{
			
		 angle = 0;
		// Quad1
		if (selectedx > x && selectedy < y)
			angle = Math.toDegrees(Math
					.atan(((selectedy - y) / ((selectedx - x)))));
		// Quad 2
		if (selectedx < x && selectedy < y)
			angle = Math.toDegrees(Math.atan(((selectedx) / ((selectedy))))) + 180;
		// Quad3
		if (selectedx < x && selectedy > y) {
			angle = Math.toDegrees(Math
					.atan(((selectedy - y) / ((selectedx - x))))) + 180;
			// System.out.println("quad 3");
		}
		// Quad4
		if (selectedx > x && selectedy > y)
			angle = Math.toDegrees(Math
					.atan(((selectedx - x) / ((selectedy - y)))));
		x += speed * Math.cos(Math.toRadians(angle));
		y += speed * Math.sin(Math.toRadians(angle));

		if (Math.sqrt((x - selectedx)
				* (x - selectedx)
				+ (y - selectedy)
				* (y - selectedy)) < 5){
				moving = false;
			}
		}
		
		
	}
	//public void attack()
	public Image directionFacing()
	{
		if(red==true)
		{
		System.out.println(angle);
		if (selectedx > x+3 && selectedy < y-3)
			face= upRightR;
		if (selectedx < x-3 && selectedy < y+3)
			face= upLeftR;
		if (selectedx < x-3 && selectedy > y+3)
			face= downLeftR;
		if (selectedx > x+3 && selectedy > y-3)
			face= downRightR;
		if (selectedx < x+3 && selectedx>x-3 && selectedy > y+3)
			face= downR;
		if (selectedx < x+3 && selectedx>x-3 && selectedy < y-3)
			face= upR;
		if (selectedy < y+3 && selectedy>y-3 && selectedx > x+3)
			face= rightR;
		if (selectedy < y+3 && selectedy>y-3 && selectedx < x-3)
			face= leftR;
		
		return face.getImage();
		}
		else
		{
			System.out.println(angle);
			if (selectedx > x+3 && selectedy < y-3)
				face= upRightB;
			if (selectedx < x-3 && selectedy < y+3)
				face= upLeftB;
			if (selectedx < x-3 && selectedy > y+3)
				face= downLeftB;
			if (selectedx > x+3 && selectedy > y-3)
				face= downRightB;
			if (selectedx < x+3 && selectedx>x-3 && selectedy > y+3)
				face= downB;
			if (selectedx < x+3 && selectedx>x-3 && selectedy < y-3)
				face= upB;
			if (selectedy < y+3 && selectedy>y-3 && selectedx > x+3)
				face= rightB;
			if (selectedy < y+3 && selectedy>y-3 && selectedx < x-3)
				face= leftB;
			
			return face.getImage();
			}	

	}
	int reloadTime =0;
	public void attack(ArrayList<Enemies> enemies, ArrayList<Tower> towers)
	{
		if(enemies.size()>0 || towers.size()>0)
		{
			double leastDistance =0;
			if(enemies.size()>0)
			leastDistance = Math.sqrt((enemies.get(0).returnX() - x)	* (enemies.get(0).returnX() - x	+ (enemies.get(0).returnY() - y)	* (enemies.get(0).returnY() - y)));
			else
			leastDistance = Math.sqrt((towers.get(0).returnX() - x)
						* (towers.get(0).returnX() - x)
						+ (towers.get(0).returnY() - y)
						* (towers.get(0).returnY() - y));
		int countE = 0;
		int countT = 0;
		int marker = 0;
		boolean tower = true;
		
		for (Enemies e : enemies) {
			double distance = Math.sqrt((e.returnX() - x) * (e.returnX() - x)
					+ (e.returnY() - y) * (e.returnY()) - y);
			if (distance < leastDistance) {
				leastDistance = distance;
				marker = countE;
				tower = false;
			}
			countE++;
		}
		for (Tower e : towers) {
			double distance = Math.sqrt((e.returnX() - x) * (e.returnX() - x)
					+ (e.returnY() - y) * ((e.returnY()) - y));
			if (distance < leastDistance) {
				leastDistance = distance;
				marker = countT;
				tower = true;
				System.out.println("Tower detected");
			}
			countT++;
		}
			if (leastDistance<range && reloadTime<=0 && tower == false) {
			
				missiles.add(new Bullets(x, y, enemies.get(marker).returnX(),
						enemies.get(marker).returnY(), shotSpeed,
						damage, false,marker,false));
					//System.out.println("shoot");
					//System.out.println("Shot reload " +shotReload);
					reloadTime = shotReload;
					System.out.println("Soilder Shot");
			}
			if (leastDistance<range && reloadTime<=0 && tower == true) {
				
				missiles.add(new Bullets(x, y, towers.get(marker).returnX(),
						towers.get(marker).returnY(), shotSpeed,
						damage, false,marker,true));
					//System.out.println("shoot");
					//System.out.println("Shot reload " +shotReload);
				System.out.println("Soilder Shot tower");
					reloadTime = shotReload;
					
					
			}
			
			if(reloadTime>0)
			{
				reloadTime--;
				System.out.println(reloadTime);
			}
		}
	}
	
	

	public boolean returnDestroyed()
	{
		return destroyed;
	}
	public double returnX()
	{
		return x;
	}
	public double returnY()
	{
		return y;
	}
	public int returnWidth()
	{
		return width;
	}
	public ArrayList<Bullets> returnArray() {
		// TODO Auto-generated method stub
		return missiles;
	}
	
	public StrippedEnemies toStripped()
	{
		return new StrippedEnemies(x,y,hp,red);
	}
}
