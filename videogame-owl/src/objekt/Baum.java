package objekt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;


public class Baum extends Objekt {
    GamePanel gp;
    
    public Baum (GamePanel gp){
        this.gp = gp;
        ausgangsWerteSetzen();
    }

    public void ausgangsWerteSetzen(){
        x = 220;
        y = 420;
        bewegung = 1;
        holBild();
    }

    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/objekte/baum.png"));
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public void update(){
        if (y + bewegung < gp.bildschirmHoehe ){
            y += bewegung;
            
        } 
    }

public void zeichne(Graphics2D g2){
    	
    	BufferedImage bild = bild1;
    	g2.drawImage(bild, x, y, 350, 350, null);
    
}


public void stop() {
	bewegung = 0;
	
	
}
    
}