package code14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TowerDefense extends JComponent {

	private static final long serialVersionUID = 1L;
	// Changes in player view and key variables
	public int changeX = 0, changeY = 0;
	public boolean up, down, left, right;

	public GameClient gc;
	public boolean isServer = true;

	// Towers
	public ArrayList<Tower> opp_towers = new ArrayList<Tower>();
	public ArrayList<Tower> ally_towers = new ArrayList<Tower>();
	// Enemies
	public ArrayList<Enemies> opp_enemies = new ArrayList<Enemies>();
	public ArrayList<Enemies> ally_enemies = new ArrayList<Enemies>();

	// Boolean for radius selection
	boolean towerRRange = false;
	boolean towerBRange = false;

	// Boolean for movement
	boolean redSelected = false;
	boolean blueSelected = false;

	public MapGraphics mapG = new MapGraphics();

	// Boolean for towers
	boolean redTower = false;
	boolean redEnemy = false;
	// Boolean for Blue
	boolean blueTower = false;
	boolean blueEnemy = false;

	// expodeing gif
	Image explode = new ImageIcon("images/explosion.gif").getImage();

	// Boolean for radius selection
	boolean tower = false;
	int marker = 0;

	int yDirection;
	int xDirection;

	public void update() throws ClassNotFoundException, IOException {
		// Blue Towers getting red eneimies pos.
		for (Tower e : ally_towers) {
			e.updateStatsAndDetect(opp_enemies);
			e.moveShots(opp_enemies, opp_towers);
		}
		for (Enemies e : ally_enemies) {
			e.move();
			e.attack(opp_enemies, opp_towers);
			e.moveShots(opp_enemies, opp_towers);
		}

		ArrayList<StrippedEnemies> strip = new ArrayList<StrippedEnemies>();
		for (Enemies e : ally_enemies) {
			strip.add(e.toStripped());
		}
		gc.transfer(ally_towers, strip);
		strip = gc.getEnemies();
		ArrayList<Enemies> tempEn = new ArrayList<Enemies>();
		for (StrippedEnemies s : strip) {
			tempEn.add(s.toEnemies());
		}
		opp_enemies = tempEn;
		opp_towers = gc.getTowers();

		for (Enemies e : opp_enemies) {
			e.move();
			e.attack(ally_enemies, ally_towers);
			e.moveShots(ally_enemies, ally_towers);
		}
		// Red towers getting blue enemies pos.
		for (Tower e : opp_towers) {
			e.updateStatsAndDetect(ally_enemies);
			e.moveShots(ally_enemies, ally_towers);
		}
		if (left)
			changeX -= 40;
		if (right)
			changeX += 40;
		if (up)
			changeY -= 40;
		if (down)
			changeY += 40;
	}

	// Add Red Tower
	public void addTower1R(double mousex, double mousey) {

		double range = 1000;
		double shotSpeed = 5;
		int shotReload = 100;
		double damage = 1;
		double health = 50;
		boolean destroyed = false;
		int width = 25;
		boolean red = true;
		opp_towers.add(new Tower(mousex, mousey, range, shotSpeed, shotReload,
				damage, health, destroyed, width, red));
	}

	// Add Blue Tower
	public void addTower1B(double mousex, double mousey) {

		double range = 1000;
		double shotSpeed = 5;
		int shotReload = 100;
		double damage = 1;
		double health = 50;
		boolean destroyed = false;
		int width = 25;
		boolean red = false;
		ally_towers.add(new Tower(mousex, mousey, range, shotSpeed, shotReload,
				damage, health, destroyed, width, red));
	}

	// Add Red Enemy
	public void addEnemy1R(double mousex, double mousey) {
		int hp = 5;
		double speed = 1;
		double damage = 3;
		double range = 500;
		double shotSpeed = 5;
		int shotReload = 75;
		boolean destroyed = false;
		int width = 70;
		boolean red = true;
		opp_enemies.add(new Enemies(mousex, mousey, hp, speed, range,
				shotSpeed, shotReload, damage, destroyed, width, red));

	}

	// Add Blue Enemy
	public void addEnemy1B(double mousex, double mousey) {
		int hp = 5;
		double speed = 1;
		double damage = 3;
		double range = 500;
		double shotSpeed = 5;
		int shotReload = 75;
		boolean destroyed = false;
		int width = 70;
		boolean red = false;
		ally_enemies.add(new Enemies(mousex, mousey, hp, speed, range,
				shotSpeed, shotReload, damage, destroyed, width, red));

	}

	public void checkarea(double mousex, double mousey) {
		// The reason this is first is because the marker is still on the last
		// Person selected and can move them.
		if (redSelected == true) {
			opp_enemies.get(marker).giveCoordinates(mousex, mousey);
		}
		if (blueSelected == true) {
			ally_enemies.get(marker).giveCoordinates(mousex, mousey);
		}
		towerRRange = false;
		towerBRange = false;
		redSelected = false;
		marker = 0;
		int count = 0;

		if (mousex > frame.getWidth() - 75 && mousex < frame.getWidth() - 35
				&& mousey > frame.getHeight() - 70
				&& mousey < frame.getHeight() - 35)
			redTower = true;
		if (mousex > frame.getWidth() - 150 && mousex < frame.getWidth() - 115
				&& mousey > frame.getHeight() - 70
				&& mousey < frame.getHeight() - 35)
			redEnemy = true;
		if (mousex > frame.getWidth() - 225 && mousex < frame.getWidth() - 195
				&& mousey > frame.getHeight() - 70
				&& mousey < frame.getHeight() - 35)
			blueTower = true;
		if (mousex > frame.getWidth() - 300 && mousex < frame.getWidth() - 265
				&& mousey > frame.getHeight() - 70
				&& mousey < frame.getHeight() - 35)
			blueEnemy = true;

		for (Tower e : opp_towers) {
			if (mousex > e.returnX() && mousex < e.returnX() + e.returnWidth()
					&& mousey > e.returnY()
					&& mousey < e.returnY() + e.returnWidth()) {
				towerRRange = true;
				marker = count;
				System.out.println("tower clicked");
				break;
			}
			count++;

		}
		count = 0;
		for (Tower e : ally_towers) {
			if (mousex > e.returnX() && mousex < e.returnX() + e.returnWidth()
					&& mousey > e.returnY()
					&& mousey < e.returnY() + e.returnWidth()) {
				towerBRange = true;
				marker = count;
				break;
			}
			count++;
		}
		count = 0;
		for (Enemies e : opp_enemies) {
			if (mousex > e.returnX() && mousex < e.returnX() + e.returnWidth()
					&& mousey > e.returnY()
					&& mousey < e.returnY() + e.returnWidth()) {
				redSelected = true;
				marker = count;
				break;
			}
			count++;
		}

	}

	public synchronized void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		mapG.changeX = changeX;
		mapG.changeY = changeY;
		mapG.setG(g);

		// Draws background
		Image background = new ImageIcon("images/Map.png").getImage();
		mapG.drawImage(background, 0, 0, this);

		g2.setColor(Color.gray);

		Rectangle2D bottomArea = new Rectangle2D.Double(0,
				frame.getHeight() - 75, frame.getWidth(), 75);
		g2.fill(bottomArea);

		// Spawn rectangle
		g2.setColor(Color.RED);
		Rectangle2D spawnTowerR = new Rectangle2D.Double(frame.getWidth() - 75,
				frame.getHeight() - 70, 45, 35);
		g2.fill(spawnTowerR);
		// Spawn Enemy RED
		g2.setColor(Color.RED);
		Ellipse2D spawnEnemyR = new Ellipse2D.Double(frame.getWidth() - 150,
				frame.getHeight() - 70, 45, 35);
		g2.fill(spawnEnemyR);

		// Spawn Enemy Blue Tower
		g2.setColor(Color.BLUE);
		Rectangle2D spawnTowerB = new Rectangle2D.Double(
				frame.getWidth() - 225, frame.getHeight() - 70, 45, 35);
		g2.fill(spawnTowerB);

		// spawn enemy blue
		g2.setColor(Color.BLUE);
		Ellipse2D spawnEnemyB = new Ellipse2D.Double(frame.getWidth() - 300,
				frame.getHeight() - 70, 45, 35);
		g2.fill(spawnEnemyB);

		// Red Tower
		for (Tower e : opp_towers) {
			g2.setColor(Color.RED);
			if (e.destroyed == false) {
				Rectangle2D tower = new Rectangle2D.Double(e.returnX(),
						e.returnY(), 25, 25);

				g2.fill(tower);
				ArrayList<Bullets> bullets = e.returnArray();
				Iterator<Bullets> bullet_it = bullets.iterator();
				while (bullet_it.hasNext()) {
					Bullets b = bullet_it.next();
					if (b.returnDestroyed() == false) {
						g2.setColor(Color.YELLOW);
						Ellipse2D bullet = new Ellipse2D.Double(b.returnX(),
								b.returnY(), 8, 8);
						g2.fill(bullet);
					} else {
						bullet_it.remove();
						bullets.remove(b);

					}
				}
			} else {
				opp_towers.remove(e);
			}
		}
		// Blue Tower
		for (Tower e : ally_towers) {
			g2.setColor(Color.BLUE);
			if (e.destroyed == false) {
				Rectangle2D tower = new Rectangle2D.Double(e.returnX(),
						e.returnY(), 25, 25);

				g2.fill(tower);
				ArrayList<Bullets> bullets = e.returnArray();
				Iterator<Bullets> bullet_it = bullets.iterator();
				while (bullet_it.hasNext()) {
					Bullets b = bullet_it.next();
					if (b.returnDestroyed() == false) {
						g2.setColor(Color.YELLOW);
						Ellipse2D bullet = new Ellipse2D.Double(b.returnX(),
								b.returnY(), 8, 8);
						g2.fill(bullet);
					} else {
						bullet_it.remove();
						bullets.remove(b);

					}
				}
			} else {
				ally_towers.remove(e);
			}
		}
		// Red Enmine Stuff
		Iterator<Enemies> enemiesR_it = opp_enemies.iterator();
		while (enemiesR_it.hasNext()) {
			Enemies e = enemiesR_it.next();
			if (e.returnDestroyed() == false) {

				mapG.drawImage(e.directionFacing(), (int) e.x, (int) e.y, this);
				// g.drawImage(ball1, x1, y1, this);
				// g.fillRect(250, 250, rectWidth, rectHight);
				// repaint();
			} else {

				enemiesR_it.remove();
				opp_enemies.remove(e);
			}
		}
		// Ground Unit Red Bullets
		for (Enemies e : opp_enemies) {
			g2.setColor(Color.red);
			if (e.destroyed == false) {
				ArrayList<Bullets> missles = e.returnArray();
				Iterator<Bullets> bullet_it = missles.iterator();
				while (bullet_it.hasNext()) {
					Bullets b = bullet_it.next();
					if (b.returnDestroyed() == false) {
						g2.setColor(Color.YELLOW);
						Ellipse2D bullet = new Ellipse2D.Double(b.returnX(),
								b.returnY(), 8, 8);
						g2.fill(bullet);
					} else {
						bullet_it.remove();
						missles.remove(b);

					}
				}
			}
		}
		// Blue enemies stuff
		Iterator<Enemies> enemiesB_it = ally_enemies.iterator();
		while (enemiesB_it.hasNext()) {

			Enemies e = enemiesB_it.next();
			if (e.returnDestroyed() == false) {
				mapG.drawImage(e.directionFacing(), (int) e.x, (int) e.y, this);
				// g.drawImage(ball1, x1, y1, this);
				// g.fillRect(250, 250, rectWidth, rectHight);
				// repaint();

			} else {
				mapG.drawImage(explode, (int) e.x, (int) e.y, this);
				enemiesB_it.remove();
				ally_enemies.remove(e);
			}
		}
		// Ground Unit Blue Bullets
		for (Enemies e : ally_enemies) {
			g2.setColor(Color.blue);
			if (e.destroyed == false) {
				ArrayList<Bullets> missles = e.returnArray();
				Iterator<Bullets> bullet_it = missles.iterator();
				while (bullet_it.hasNext()) {
					Bullets b = bullet_it.next();
					if (b.returnDestroyed() == false) {
						g2.setColor(Color.YELLOW);
						Ellipse2D bullet = new Ellipse2D.Double(b.returnX(),
								b.returnY(), 8, 8);
						g2.fill(bullet);
					} else {
						bullet_it.remove();
						missles.remove(b);

					}
				}
			}
		}

		g2.setColor(Color.BLACK);

		if (towerRRange == true) {
			g2.setColor(Color.red);
			Ellipse2D range = new Ellipse2D.Double(opp_towers.get(marker)
					.returnX() - opp_towers.get(marker).returnRange() / 4,
					opp_towers.get(marker).returnY()
							- opp_towers.get(marker).returnRange() / 4,
					opp_towers.get(marker).returnRange() / 2, opp_towers.get(
							marker).returnRange() / 2);
			g2.draw(range);
		}
		if (towerBRange == true) {
			g2.setColor(Color.red);
			Ellipse2D range = new Ellipse2D.Double(ally_towers.get(marker)
					.returnX() + ally_towers.get(marker).returnRange() / 2,
					ally_towers.get(marker).returnY()
							+ ally_towers.get(marker).returnRange() / 2,
					ally_towers.get(marker).returnRange() / 2, ally_towers.get(
							marker).returnRange() / 2);
			g2.draw(range);
		}
		if (redSelected == true) {
			g2.setColor(Color.BLACK);
			Ellipse2D range = new Ellipse2D.Double(opp_enemies.get(marker)
					.returnX(), opp_enemies.get(marker).returnY(), opp_enemies
					.get(marker).returnWidth(), opp_enemies.get(marker)
					.returnWidth());
			g2.draw(range);
		}

		g2.setColor(Color.BLACK);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	JFrame frame;

	public TowerDefense(JFrame frame) throws IOException {
		this.frame = frame;
		KeyAndMouseListener kaml = new KeyAndMouseListener(this);
		addMouseListener(kaml);
		addKeyListener(kaml);
		gc = new GameClient("192.168.1.18", 5123);
	}
}
