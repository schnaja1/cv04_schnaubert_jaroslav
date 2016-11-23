package ukol2;



import java.awt.image.BufferedImage;

import transforms.Point2D;

public class LineRenderer {
	private BufferedImage img;
	private int color; 
	   
	public LineRenderer(BufferedImage img, int color){
		this.setImg(img);
		this.setColor(color);
	}
	  
	public LineRenderer(BufferedImage img){
		this(img,0xffffff);
	}
	
	public int getColor() {
		return color;
	} 

	public void setColor(int color) {
		this.color = color;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int checkX(int x){
		if(x<0)
			x=0;
		else if(x>Canvas.WIDTH - 1)
			x=Canvas.WIDTH - 1;
		return x;
	}
	
	public int checkY(int y){
		if(y<0)
			y=0;
		else if(y>Canvas.HEIGHT - 1)
			y=Canvas.HEIGHT - 1;
		return y;
	}
	
	public void draw(Point2D point1, Point2D point2){
		int xA = (int) (point1.getX()),
			xB = (int) (point2.getX()),
			yA = (int) (point1.getY()),
			yB = (int) (point2.getY());
		xA = checkX(xA);
		xB = checkX(xB);
		yA = checkY(yA);
		yB = checkY(yB);

		if ((xA == xB) && (yA == yB)) {           
			img.setRGB(xA, yA, color);
		} else {              
			int dx = Math.abs(xB - xA);
			int dy = Math.abs(yB - yA);
			int err = dx - dy;			
			int shiftX, shiftY;
			if (xA < xB) 
				shiftX = 1; 
			else 
				shiftX = -1;
			if (yA < yB)
				shiftY = 1; 
			else
				shiftY = -1;
			while ((xA != xB) || (yA != yB)) {  
				img.setRGB(xA, yA, color);
				int p = 2 * err;	
				if (p > -dy) {
					err -= dy;
					xA += shiftX;
				}
				if (p < dx) {
	     		  err += dx;
	     		  yA += shiftY;
		      }
	    	}
		}
	}
}

