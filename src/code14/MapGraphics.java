package code14;

import java.awt.*;
import java.awt.image.ImageObserver;

public class MapGraphics
{
  public Graphics g;
  public int changeX=0, changeY=0;

  public void setG(Graphics g)
  {
    this.g = g;
  }

  public void drawImage(Image img, int x, int y, ImageObserver imgO)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.drawImage(img, x - changeX, y - changeY, imgO);
  }
}
