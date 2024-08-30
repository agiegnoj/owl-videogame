package objekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Plattform extends Objekt{
GamePanel gp;
    

    public Plattform (GamePanel gp){
        this.gp = gp;
        ausgangsWerteSetzen();
        holBild();
    }

    public void ausgangsWerteSetzen(){
        x = 200;
        y = -400;
        hoehe = 90;
        breite = 80;
        bewegung = 6;
        bewegung2 = 3;
        kollisionsBereich = new Rectangle();
        
        
        
    }

    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/objekte/plattform.png"));
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
        if (y + bewegung <= gp.bildschirmHoehe +10){
            y += bewegung;
            
        } 
        if (raenderErreicht() == true) {
        	bewegung2 = bewegung2 * (-1);
        }
        kollisionsBereich.setBounds(x+10, y-10, 90, 70);
    }

public void zeichne(Graphics2D g2){
    	BufferedImage bild = bild1;
    	g2.drawImage(bild, x, y, hoehe, breite, null);
    
}

@Override
public void stop() {
	bewegung = 0;
	bewegung2 = 0;
	
}
    
}


