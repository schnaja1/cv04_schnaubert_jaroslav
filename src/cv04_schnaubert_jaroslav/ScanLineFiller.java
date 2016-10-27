package cv04_schnaubert_jaroslav;

import java.awt.image.BufferedImage;

public class ScanLineFiller extends Filler{
	/*
	 * Mat3, Point2D, Vec2D
	 */
	
	public ScanLineFiller(BufferedImage img, int color){
	this.img=img;
	this.color=color;
	}

	public ScanLineFiller(BufferedImage img) {
		this(img,0xFFFFFF);
	}	
}
