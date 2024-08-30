package objekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;


	public class MeteoritZwei extends Objekt {
	    GamePanel gp;

	    public MeteoritZwei (GamePanel gp){
	        this.gp = gp;
	        ausgangsWerteSetzen();
	        holBild();
	        kollisionsBereich = new Rectangle();
	        
	    }

	    public void ausgangsWerteSetzen(){
	        x = 0;
	        y = -400;
	        hoehe = 60;
	        breite = 90;
	        bewegung = 8;
	    }

	    public void holBild() {
	    	try {
	    		bild1 = ImageIO.read(getClass().getResourceAsStream("/objekte/meteorit_2.png"));
	    	}
	    	catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    public void update(){
	    	
	        if (y + bewegung <= gp.bildschirmHoehe +10){
	            y += bewegung;
	            
	        } 
	        kollisionsBereich.setBounds(x+10, y-10, 80, 50);
	    }

	public void zeichne(Graphics2D g2){
	    	BufferedImage bild = bild1;
	    	g2.drawImage(bild, x, y, breite, hoehe, null);
	    
	}

	@Override
	public void stop() {
		bewegung = 0;
		
	}
}
