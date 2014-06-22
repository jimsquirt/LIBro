package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

	private BufferedImage image;

	public ImagePanel(BufferedImage image ){
		this.image = image;
	}

        public void setImage(BufferedImage image){
            this.image = image;
        }

    @Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, this );
	}

}
