package objekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Ring extends Objekt{

    GamePanel gp;
    double theta;
    

    public Ring (GamePanel gp){
        this.gp = gp;
        ausgangsWerteSetzen();
        holBild();
        kollisionsBereich = new Rectangle();
        
    }

    public void ausgangsWerteSetzen(){
        x = 0;
        y = -400;
        hoehe = 40;
        breite = 90;
        bewegung = 6;
        bewegung2 = 4;
    }

    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/objekte/ring.png"));
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public boolean raenderErreicht() {
    	if (x + bewegung2 < 0 || x + bewegung2 > gp.bildschirmBreite - breite ) {
    		return true;
    	}
    	return false;
    	
    }
    
    public void update(){
    	
    	x += bewegung2;
        if (y + bewegung < gp.bildschirmHoehe +10){
            y += bewegung;
        } 
        if (raenderErreicht() == true) {
        	bewegung2 = bewegung2 * (-1);
        }
        kollisionsBereich.setBounds(x+10, y-10, 80, 30);
        	
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
