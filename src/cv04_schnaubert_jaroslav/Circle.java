package cv04_schnaubert_jaroslav;

import java.awt.image.BufferedImage;

public class Circle {
	private BufferedImage img;
	private int color;
	 
	public Circle(BufferedImage img, int color){
		this.setImg(img);
		this.setColor(color);
	} 
	 
	public Circle(BufferedImage img){
		this(img,0xffffff);
	} 
	 
	public void draw(int sX, int sY, int r){
		
	}


	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
}
