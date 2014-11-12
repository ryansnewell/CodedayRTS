package code14;

import java.io.Serializable;
import java.util.ArrayList;

public class Tower implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4304714339658338621L;
	double x;
	double y;
	double range;
	double shotSpeed;
	int shotReload;
	double damage;
	double hp;
	int width;
	boolean red;
	boolean destroyed;
	boolean selected;
	
	public ArrayList<Bullets> bullets = new ArrayList<Bullets>();

	public Tower(double x, double y, double range, double shotSpeed,
			int shotReload, double damage, double hp, boolean destroyed, int width, boolean red) {
		this.x = x;
		this.y = y;
		this.range = range;
		this.shotSpeed = shotSpeed;
		this.shotReload = shotReload;
		this.damage = damage;
		this.hp = hp;
		this.destroyed = destroyed;
		this.red = red;
	}
	int reloadTime =0;
	public void updateStatsAndDetect(ArrayList<Enemies> enemies) {
		// TODO Auto-generated method stub
		
		if(enemies.size()>0)
		{
		double leastDistance = Math.sqrt((enemies.get(0).returnX() - x)
				* (enemies.get(0).returnX() - x)
				+ (enemies.get(0).returnY() - y)
				* (enemies.get(0).returnY() - y));
		int count = 0;
		int marker = 0;
		
		for (Enemies e : enemies) {
			double distance = Math.sqrt((e.returnX() - x) * (e.returnX() - x)
					+ (e.returnY() - y) * (e.returnY()) - y);
			if (distance < leastDistance) {
				leastDistance = distance;
				marker = count;
			}
			count++;
		}
			if (leastDistance<range && reloadTime<=0) {
			
				bullets.add(new Bullets(x, y, enemies.get(marker).returnX(),
						enemies.get(marker).returnY(), shotSpeed,
						damage, false,marker,false));
					System.out.println("shoot");
					System.out.println("Shot reload " +shotReload);
					reloadTime = shotReload;
					
			}
			
			if(reloadTime>0)
			{
				reloadTime--;
				//System.out.println(reloadTime);
			}
		}
	}



	public void moveShots(ArrayList<Enemies> enemies , ArrayList<Tower> towers) {
			for(Bullets e : bullets)
			{
				e.move(enemies, towers);
			}
	}
	public ArrayList<Bullets> returnArray()
	{
		return bullets;
		
	}
	public void selected() {
		selected = true;
	}

	public double returnX() {
		return x;
	}

	public double returnY() {
		return y;
	}
	public double returnRange()
	{
		return range;
	}
	public double returnWidth()
	{
		return width;
	}
	public void damageDone( double damage)
	{
		hp-= damage;
		if(hp<=0)
			destroyed= true;
	}
}
