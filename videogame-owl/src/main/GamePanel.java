package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import entitaet.Entitaet;
import entitaet.MonsterEins;
import entitaet.MonsterZwei;
import entitaet.Spieler;
import objekt.Meteorit;
import objekt.Objekt;
import objekt.Baum;
import objekt.Spirale;
import objekt.Wolke;
import weitereAnimationen.Schall;
import objekt.Plattform;
import objekt.Ring;
import objekt.MeteoritZwei;


public class GamePanel extends JPanel implements Runnable{
    
    public final int bildschirmBreite = 528;
    public final int bildschirmHoehe = 720;
    final int FPS = 60;
    
    TastenVerarbeiter tasteV;
    Random r;
    Spieler spieler;
    Meteorit meteorit;
    Baum baum;
    Spirale spirale;
    Wolke wolke;
    Plattform plattform;
    Ring ring;
    MeteoritZwei meteoritZwei;
    MonsterEins monsterEins;
    MonsterZwei monsterZwei;
    Entitaet aktuellesMonster;
    Objekt aktuellesObjekt;
    Objekt vorherigesObjekt;
    Score scoreAnzeige;
    Schall schall;
    ArrayList<Objekt> objekte;
    ArrayList<Entitaet> monster;
    

    private BufferedImage backgroundImage;
    public int i = 0;
    int explosionsCounter= 0;
    private int monsterCountup = 0;
    private int updateZaehler = 0;
    public int objektZaehler = 0;
    public int score = 0;
    private int hintergrundY = -360;
    

    Thread gameThread;
    
    //constructor
    public GamePanel ()
    {
        this.setPreferredSize (new Dimension (bildschirmBreite, bildschirmHoehe));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.tasteV = new TastenVerarbeiter();
        this.addKeyListener(tasteV);
        this.setFocusable(true);
        r = new Random();
        scoreAnzeige = new Score();
        objekte = new ArrayList<Objekt>();
        monster = new ArrayList<Entitaet>();
        monsterEins = new MonsterEins(this, tasteV);
        monster.add(monsterEins);
        monsterZwei = new MonsterZwei(this, tasteV);
        monster.add(monsterZwei);
        baum = new Baum(this);
        spieler = new Spieler(this, tasteV); 
        vorherigesObjekt = baum;
        neueObjekte();
        
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/weitere/hintergrund.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void neueObjekte() {
    	meteorit = new Meteorit(this);
        objekte.add(meteorit);
        spirale = new Spirale(this);
        objekte.add(spirale);
        wolke = new Wolke(this);
        objekte.add(wolke);
        plattform = new Plattform(this);
        objekte.add(plattform);
        ring = new Ring(this);
        objekte.add(ring);
        meteoritZwei = new MeteoritZwei(this);
        objekte.add(meteoritZwei);
        
        for (Objekt objekt : objekte) {
            objekt.x = r.nextInt(bildschirmBreite - objekt.breite); 
        }
    }

  
    
    public Objekt gibZufallsObjekt(){
    	int n = r.nextInt(objekte.size());
    	if (objekte.get(n) != vorherigesObjekt) {
    		return objekte.get(n);
    	}
    	return gibZufallsObjekt();
    	
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * game clock
     */
    
    @Override
    public void run() {
        double zeichenIntervall = 1000000000 / FPS;
        double delta = 0;
        double letzteZeit = System.nanoTime();
        long aktuelleZeit;

        while (gameThread != null){
        	
            aktuelleZeit = System.nanoTime();
            delta += (aktuelleZeit - letzteZeit) / zeichenIntervall;
            letzteZeit = aktuelleZeit;
            
            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
            }   
        }
    

    public void update(){
    	
    	if (updateZaehler <100) {
    		spieler.update();
            baum.update();
	        hintergrundUpdate();
	    	updateZaehler++;
	        monsterCountup++;
    	}
    	
    	
    	else  {

    		if(kollision()== true) {
    			spieler.stop();
    			aktuellesObjekt.stop();
    			if (aktuellesMonster != null) {
    				aktuellesMonster.stop();
    			}
    			
    		}
    			    		
    		else {
    			spieler.update();
    			explosionsUpdate();
    			schussUpdate();
            	baum.update();
                objektUpdate();
                monsterUpdate();
    	        hintergrundUpdate();
    	    	updateZaehler++;
    	        monsterCountup++;
    		}   
    		}	
    	}		
    		
        

    private void objektUpdate(){
        if (aktuellesObjekt == null || aktuellesObjekt.y > bildschirmHoehe) {
        	if (aktuellesObjekt != null ) {
            	score += 5;
            }
        	aktuellesObjekt = gibZufallsObjekt();
            aktuellesObjekt.y = 0;
            vorherigesObjekt = aktuellesObjekt;
            objektZaehler++;
            
        }
        if (objektZaehler == objekte.size()) {
        	neueObjekte();
        	objektZaehler = 0;
        }

        else if (aktuellesObjekt.y > bildschirmHoehe) {
        	aktuellesObjekt = gibZufallsObjekt();
        	score += 5;
        }
        aktuellesObjekt.update();

    }
    
    private void monsterUpdate(){
        if (monsterCountup == 600) {
        	aktuellesMonster = monster.get(r.nextInt(monster.size()));
        	monsterCountup = 0;
        	
        }
        if (aktuellesMonster != null) {
        	aktuellesMonster.update();
        	if (aktuellesMonster.y >= bildschirmHoehe +10) {
        		aktuellesMonster.y = 0;
        		aktuellesMonster = null;
        		score += 5;
        		
        	}
        }
    }
    
    private void hintergrundUpdate() {
    	if (updateZaehler % 5 == 0 || updateZaehler % 3 == 0 || updateZaehler % 7 == 0) {
    		hintergrundY += 2;
    	}
    	if (hintergrundY >= 80) {
    		hintergrundY = -330;
    	}	
    }
    
    private void schussUpdate() {
    	if (tasteV.schuss == true && schall == null) {
    		schall = new Schall(spieler.x, spieler.y - 130);
    	}
    	if (schall != null) {
    		schall.update();
    		if (schall.yKoord == 0) {
    			schall = null;
    		}
    		
    	}
    }
    
    private void explosionsUpdate() {
    	if (kollision2() == true) {
    		if (schall.kollisionsBereich.intersects(aktuellesObjekt.kollisionsBereich)){
    			schall = null;
    			aktuellesObjekt = null;
    			score += 5;
    		}
    		else if(aktuellesMonster != null && schall.kollisionsBereich.intersects(aktuellesMonster.kollisionsBereich)) {
    			schall = null;
    			aktuellesMonster = null;
    			score += 5;
    		}
    	}
    }
    
    private boolean kollision() {
    	
    	if (aktuellesObjekt != null && spieler.kollisionsBereich.intersects(aktuellesObjekt.kollisionsBereich)){
    		return true;
    	}
    	else if (aktuellesMonster!= null && spieler.kollisionsBereich.intersects(aktuellesMonster.kollisionsBereich)){
    		return true;
    	}
    	return false;
    }
    
private boolean kollision2() {
    	
    	if (aktuellesObjekt != null  && schall != null && schall.kollisionsBereich.intersects(aktuellesObjekt.kollisionsBereich)){
    		return true;
    	}
    	if (aktuellesMonster != null && schall != null && schall.kollisionsBereich.intersects(aktuellesMonster.kollisionsBereich)){
    		return true;
    	}
    	return false;
    }
    
    
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, hintergrundY, null);
        }
        
        if (i==18) {
        	i = 0;
        }
        baum.zeichne(g2);
        
        if (kollision() != true) {
        	spieler.zeichne(g2, i);
        }
        else {
        	spieler.zeichne(g2,  1);
        }
        
        
        if (updateZaehler >= 100) {
        	if(aktuellesObjekt != null) {
        		aktuellesObjekt.zeichne(g2);
        	}
        
            if (aktuellesMonster != null) {
            	aktuellesMonster.zeichne(g2, i);
            }
            if (schall != null) {
            	schall.zeichne(g2, i);
            }
        	scoreAnzeige.zeichne(g2, score);
        	
        i++;
        g2.dispose();
    } 
}}


