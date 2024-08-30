package main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class TitelBildschirm extends JPanel {
	public final int bildschirmBreite = 528;
    public final int bildschirmHoehe = 720;
    BufferedImage bild1, bild2, bild3;
    
    public TitelBildschirm(){
    	holBild();
    }
    
    public void holBild() {
    	try {
    		bild1 = ImageIO.read(getClass().getResourceAsStream("/player/eule_1.png"));
    		bild2 = ImageIO.read(getClass().getResourceAsStream("/weitere/hintergrund.jpg"));
    		bild3 = ImageIO.read(getClass().getResourceAsStream("/objekte/baum.png"));
    		
    	}catch(IOException e){
    		e.printStackTrace();	
    	}
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bild2,0, 0, null);
        g2.drawImage(bild3, 220, 420, 350, 350, null);
        g2.drawImage(bild1, 270, 350, 130, 130, null);
        String anzeigeString = "Owl";
		g2.setFont(new Font("Calibri", Font.BOLD, 50));
		g2.drawString(anzeigeString, 210, 50 );
		String anzeigeString2 = "Press x to start";
		g2.setFont(new Font("Calibri", Font.PLAIN, 30));
		g2.drawString(anzeigeString2, 160, 160 );
		
       
}
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bildschirmBreite, bildschirmHoehe);
        }}
