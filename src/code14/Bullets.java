package code14;

import java.io.Serializable;
import java.util.ArrayList;

public class Bullets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4225680035544974196L;
	double x;
	double y;
	double enemyy;
	double enemyx;
	double angle;
	double speed;
	double damage;
	boolean destroyed;
	int marker;
	boolean tower;

	public Bullets(double x, double y, double enemyx, double enemyy,
		 double speed, double damage, boolean destroyed,
			int marker,boolean tower) {
		this.x = x;
		this.y = y;
		this.enemyx = enemyx;
		this.enemyy = enemyy;
		this.speed = speed;
		this.damage = damage;
		this.destroyed = destroyed;
		this.marker = marker;
		this.tower = tower;
	}

	public void move(ArrayList<Enemies> enemies ,ArrayList<Tower> towers) {
		
		if(tower==false)
		{
		if(enemies.size()>0)
		{
			if (destroyed == false) {
				// Quad1
				if (enemies.get(marker).returnX() > x
						&& enemies.get(marker).returnY() < y)
					angle = Math.toDegrees(Math.atan(((enemies.get(marker)
							.returnY() - y) / ((enemies.get(marker)
									.returnX() - x)))));
				//Quad 2
				if (enemies.get(marker).returnX() < x
						&& enemies.get(marker).returnY() < y)
					angle = Math.toDegrees(Math.atan(((x-enemies.get(marker)
							.returnX() ) / ((y-enemies.get(marker)
									.returnY()  ))))) + 180;
				//Quad3
				if (enemies.get(marker).returnX() < x
						&& enemies.get(marker).returnY() > y)
				{
					angle = Math.toDegrees(Math.atan(((enemies.get(marker)
							.returnY() - y) / ((enemies.get(marker)
									.returnX() - x))))) + 180;
				//System.out.println("quad 3");
				}
				//Quad4
				if (enemies.get(marker).returnX() > x
					&& enemies.get(marker).returnY() > y)
				angle = Math.toDegrees(Math
						.atan(((enemies.get(marker).returnX() - x) / ((enemies
								.get(marker).returnY() - y)))));
			x +=  speed * Math.cos(Math.toRadians(angle));
			y +=  speed * Math.sin(Math.toRadians(angle));

			if (Math.sqrt((x - enemies.get(marker).returnX())
					* (x - enemies.get(marker).returnX())
					+ (y - enemies.get(marker).returnY())
					* (y - enemies.get(marker).returnY())) < 15){
				destroyed = true;
				enemies.get(marker).damageDone(damage);
				}
				
			}
			}
		}
		else
			if(towers.size()>0)
			{
				if (destroyed == false) {
					// Quad1
					if (towers.get(marker).returnX() > x
							&& towers.get(marker).returnY() < y)
						angle = Math.toDegrees(Math.atan(((towers.get(marker)
								.returnY() - y) / ((towers.get(marker)
										.returnX() - x)))));
					//Quad 2
					if (towers.get(marker).returnX() < x
							&& towers.get(marker).returnY() < y)
						angle = Math.toDegrees(Math.atan(((x-towers.get(marker)
								.returnX() ) / ((y-towers.get(marker)
										.returnY()  ))))) + 180;
					//Quad3
					if (towers.get(marker).returnX() < x
							&& towers.get(marker).returnY() > y)
					{
						angle = Math.toDegrees(Math.atan(((towers.get(marker)
								.returnY() - y) / ((towers.get(marker)
										.returnX() - x))))) + 180;
					//System.out.println("quad 3");
					}
					//Quad4
					if (towers.get(marker).returnX() > x
						&& towers.get(marker).returnY() > y)
					angle = Math.toDegrees(Math
							.atan(((towers.get(marker).returnX() - x) / ((towers
									.get(marker).returnY() - y)))));
				x +=  speed * Math.cos(Math.toRadians(angle));
				y +=  speed * Math.sin(Math.toRadians(angle));

				if (Math.sqrt((x - towers.get(marker).returnX())
						* (x - towers.get(marker).returnX())
						+ (y - towers.get(marker).returnY())
						* (y - towers.get(marker).returnY())) < 15){
					destroyed = true;
					towers.get(marker).damageDone(damage);
					}
					
				}
				
		}
	}

	public double returnX() {
		return x;
	}

	public double returnY() {
		return y;
	}

	public boolean returnDestroyed() {
		return destroyed;
	}

}
