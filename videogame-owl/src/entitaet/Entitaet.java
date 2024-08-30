package entitaet;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entitaet {
    public int x, y;
    public int bewegung;
    public BufferedImage bild1, bild2;
    public Rectangle kollisionsBereich;
    
    public abstract void holBild();
    public abstract void ausgangsWerteSetzen();
    public abstract void update();
    public abstract void zeichne(Graphics2D g, int i);
	public abstract void stop();
    
    
}
