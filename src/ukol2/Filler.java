package ukol2;

import java.awt.image.BufferedImage;

public abstract class Filler {
	protected int color;
	protected BufferedImage img;
	
	public Filler(BufferedImage img, int color){
		this.img=img;
		this.color=color;
	}
	public Filler(BufferedImage img){
		this(img,0xffff00);
	}

}
