package objekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;


public class Spirale extends Objekt {
    GamePanel gp;
    boolean randErreicht;
   
    public Spirale (GamePanel gp){
        this.gp = gp;
        ausgangsWerteSetzen();
    }

    public void ausgangsWerteSetzen(){
        x = 0;
        y = -400;
        hoehe = 80;
        breite = 80;
        bewegung = 6;
        bewegung2 = 6;
        holBild();
        kollisionsBereich = new Rectangle();
    }

    public boolean raenderErreicht() {
    	if (x + bewegung2 < 0 || x + bewegung2 > gp.bildschirmBreite - breite ) {
    		return true;
    	}
    	return false;
    	
    }
    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/objekte/spirale.png"));
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public void update(){
    	x += bewegung2;
        if (y + bewegung < gp.bildschirmHoehe +10){
            y += bewegung;
        } 
        if (raenderErreicht() == true) {
        	bewegung2 = bewegung2 * (-1);
        }
        kollisionsBereich.setBounds(x+10, y-10, 70, 70);
        	
        }
        
   
   public void zeichne(Graphics2D g2){
    	
    	BufferedImage bild = bild1;
    	g2.drawImage(bild, x, y, breite, hoehe, null);
    
   }

@Override
public void stop() {
	bewegung = 0;
	bewegung2 = 0;
	
}
    
}