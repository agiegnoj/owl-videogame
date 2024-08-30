package entitaet;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import main.GamePanel;
import main.TastenVerarbeiter;

public class MonsterEins extends Entitaet{
	GamePanel gp;
    TastenVerarbeiter tasteV;
    Random r2;

    public MonsterEins (GamePanel gp, TastenVerarbeiter tasteV){
        this.gp = gp;
        this.tasteV = tasteV;
        r2 = new Random();
        ausgangsWerteSetzen();
        holBild();
        kollisionsBereich = new Rectangle();
    }
    
    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/player/monster_1_1.png"));
    		bild2 = ImageIO.read(getClass().getResourceAsStream("/player/monster_1_2.png"));
    		
    	}catch(IOException e){
    		e.printStackTrace();	
    	}
    }

    public void ausgangsWerteSetzen(){
        x = r2.nextInt(gp.bildschirmBreite-130);
        y = 0;
        bewegung = 6;
    }

    public void update(){
    	
    	kollisionsBereich.setBounds(x+10, y-10,90, 90 );
        if (y +bewegung < gp.bildschirmHoehe +50) {
        	y += bewegung;
        }
      
    }
    
    public void stop() {
    	bewegung = 0;
    	
    }
    

    public void zeichne(Graphics2D g2, int i){
    	
    	BufferedImage bild = null;
    	
    	switch(i) {
    	
    	case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9:
    		
    	bild = bild1;
    	break;
    	
    	case 10, 11, 12, 13, 14, 15, 16, 17, 18:
    		bild = bild2;
    	break;
    	

    }
    	g2.drawImage(bild, x, y, 130, 130, null);
    
}

}
