package view;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import model.ImagePanel;

@SuppressWarnings("serial")
public class SplashScreen extends JFrame{
    JProgressBar progress;
    ImagePanel splashPanel;
    Thread load;
    BufferedImage back;
    int val = 0;

    public SplashScreen(){
        initFrame();
        initPanels();
        initComponents();
    }

    public void initFrame(){
        setSize(750,400);
        setLayout(null);
        setTitle("Initializing...");
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
        setVisible(true);
    }

    public void initPanels(){
        try{
        	back = javax.imageio.ImageIO.read(new java.io.File ("images/splash2.png"));
        }
        catch(Exception e){}

        splashPanel = new ImagePanel(back);
        splashPanel.setLayout(null);
        splashPanel.setBounds(0,0,750,400);
        splashPanel.setVisible(true);
        add(splashPanel);
    }


    private void initComponents() {
        progress = new JProgressBar(0,1000);
        progress.setValue(0);
        repaint();
    }

    public void startLoad(){
        while (val <= 1000) {
            progress.setValue(val);            
            try {
                Thread.sleep(300);
            } 
            catch (InterruptedException e) {}
            val += 100;
        }
        dispose();
    }
}
