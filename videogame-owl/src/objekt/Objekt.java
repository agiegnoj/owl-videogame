package objekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Objekt {
	public int x, y, hoehe, breite;
    public int bewegung, bewegung2;
    public BufferedImage bild1;
    public Rectangle kollisionsBereich; 
    
    public abstract void ausgangsWerteSetzen();
    public abstract void holBild();
    public abstract void update();
    public abstract void zeichne(Graphics2D g);
	public abstract void stop();
    
}
