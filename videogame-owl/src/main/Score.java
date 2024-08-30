package main;

import java.awt.Font;
import java.awt.Graphics2D;

public class Score {
	String anzeigeString;
	int x,y;
	public Score() {
		anzeigeString = "Score: 0";
		x = 10;
		y = 30;
		
	}
	public void zeichne(Graphics2D g2, int i) {
		
		String anzeigeString = "Score: " + String.valueOf(i);
		g2.setFont(new Font("Calibri", Font.PLAIN, 30));
		g2.drawString(anzeigeString, x, y );
		
	}

}
