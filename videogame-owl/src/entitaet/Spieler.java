package entitaet;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.TastenVerarbeiter;


public class Spieler extends Entitaet {
    GamePanel gp;
    TastenVerarbeiter tasteV;
    public Rectangle kollisionsBereich;

    public Spieler (GamePanel gp, TastenVerarbeiter tasteV){
        this.gp = gp;
        this.tasteV = tasteV;
        ausgangsWerteSetzen();
        holBild();
        kollisionsBereich = new Rectangle();
       
        
    }
    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/player/eule_1.png"));
    		bild2 = ImageIO.read(getClass().getResourceAsStream("/player/eule_2.png"));
    		
    	}catch(IOException e){
    		e.printStackTrace();	
    	}
    }

    public void ausgangsWerteSetzen(){
        x = 250;
        y = 390;
        bewegung = 4;
    }

    public void update(){
    	 kollisionsBereich.setBounds(x+10, y-10,100, 100 );
        if (tasteV.links == true){
            if (x - bewegung >= 0){
                x -= bewegung;
            }
            else {
                x -= x;
            }
        }
            
        else if (tasteV.rechts == true){
            if (x +bewegung <= (gp.bildschirmBreite-130)){
                x += bewegung;
            }  
            else{
                x += ((gp.bildschirmBreite-130)-x);
            }
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
    
}}