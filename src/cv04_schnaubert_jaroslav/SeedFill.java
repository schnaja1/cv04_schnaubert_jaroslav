package cv04_schnaubert_jaroslav;

import java.awt.image.BufferedImage;

public class SeedFill extends Filler{
	
	public SeedFill(BufferedImage img, int color){
		this.img=img;
		this.color=color;
	}
	
	public SeedFill(BufferedImage img) {
		this(img,0xFFFFFF);
	}
	
	//DU zprovoznit
	public void fill(int x, int y, int backgroundColor){
		fill(x+1, y, color);
		fill(x-1, y, color);
		fill(x, y+1, color);
		fill(x, y-1, color);
		
		if(img.getRGB(x, y)==backgroundColor){
			img.setRGB(x, y, color);
		}
	}
}
