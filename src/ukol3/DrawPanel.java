package ukol3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/*
 * Jelikož pøedpokládám, že projekty pøedmìtu PGRF2 budou navazovat na tento projekt, pøidal jsem tuto tøídu.
 * Tato tøída je abstraktní tøída pro všechny panely, ve kterých budou kresleny objekty.
 */
public abstract class DrawPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	protected BufferedImage img;
	
	public DrawPanel(int width, int height){
		super();

	}
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}


	public static void present(DrawPanel panel){
		if(panel.getGraphics() != null)
			panel.getGraphics().drawImage(panel.getImg(), 0, 0, null);
	}
	
	public void clear(DrawPanel panel,int color) {
		Graphics gr = panel.getImg().getGraphics();
		gr.setColor(new Color(color));
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
}
