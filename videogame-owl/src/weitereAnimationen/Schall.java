package weitereAnimationen;

	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import javax.imageio.ImageIO;
	

	public class Schall  {
	    public Rectangle kollisionsBereich;
	    BufferedImage bild1;
	    BufferedImage bild2;
	    int xKoord;
	    public int yKoord;
	    public int hoehe;
	    int breite;
	    int bewegung;
	    

	    public Schall (int x, int y){
	        ausgangsWerteSetzen(x,y );
	        holBild();
	        kollisionsBereich = new Rectangle();
	    }
	    
	    public void holBild() {
	    	try {
	    		bild1 = ImageIO.read(getClass().getResourceAsStream("/weitere/schall_1.png"));
	    		bild2 = ImageIO.read(getClass().getResourceAsStream("/weitere/schall_2.png"));
	    		
	    	}catch(IOException e){
	    		e.printStackTrace();	
	    	}
	    }

	    public void ausgangsWerteSetzen(int x, int y){
	        xKoord = x;
	        yKoord = y;
	        bewegung = 4;
	    }

	    public void update(){
	    	 kollisionsBereich.setBounds(xKoord+10, yKoord-10,90, 90 );
	    	 yKoord -= bewegung;
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
	    	g2.drawImage(bild, xKoord, yKoord, 100, 100, null);
	    
	}}


