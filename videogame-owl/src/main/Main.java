package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main {
    
    JFrame fenster1;
    
    public static void main(String[] args) throws Exception {
        new Main();
    }
    
    public Main(){
        fenster1 = new JFrame();
        fenster1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster1.setResizable(false);
        fenster1.setTitle("Owl");
        TitelBildschirm titelBildschirm = new TitelBildschirm();
        fenster1.add(titelBildschirm);
        titelBildschirm.requestFocus();
        fenster1.pack();
        fenster1.setLocationRelativeTo(null);
        fenster1.setVisible(true);
        fenster1.addKeyListener(new TitelBildschirmHandler());
    }
    
    public void starteSpiel() {
        fenster1.setVisible(false);
        JFrame fenster2 = new JFrame();
        fenster2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster2.setResizable(false);
        fenster2.setTitle("Owl");
        GamePanel gamePanel = new GamePanel();
        fenster2.add(gamePanel);
        gamePanel.requestFocus();
        fenster2.pack();
        fenster2.setLocationRelativeTo(null);
        fenster2.setVisible(true);
        gamePanel.startGameThread();
    }
    
    public class TitelBildschirmHandler implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            
            if (code == KeyEvent.VK_X) {
                starteSpiel();
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }
        
    }



}

    
