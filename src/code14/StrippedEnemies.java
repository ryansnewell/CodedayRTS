package code14;

import java.io.Serializable;

public class StrippedEnemies implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7212755203514923471L;
	public int hp;
	public double x, y;
	public boolean red;

	public StrippedEnemies(double x, double y, int hp, boolean red) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.red = red;
	}

	public Enemies toEnemies() {
		return new Enemies(x, y, hp, 1, 500, 5, 75, 3, false, 70, red);
	}
}
