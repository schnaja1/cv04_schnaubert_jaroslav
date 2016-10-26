package cv04_schnaubert_jaroslav;


import java.awt.Point;
import java.awt.image.BufferedImage;
public class Circle {
	private BufferedImage img;
	private int color; 
	private int color2;
	private LineRenderer line;
	
	
	public Circle(BufferedImage img, int color){
		this.setImg(img);
		this.setColor(color);
	} 
	 
	public Circle(BufferedImage img){
		this(img,0xffffff);
	} 
	 
	public int countDistance(int sX, int sY, int rX, int rY){
		int x,y;
		x = Math.abs(sX - rX);
		y = Math.abs(sY - rY);
		
		int distance = (int) Math.sqrt((x*x)+(y*y));
		
		return distance;
	}
	
	public Point findPointOnCircle(int sX, int sY, int radius, int x, int y){

		int distance = countDistance(sX, sY, x, y);
		int shift;
		shift = Math.abs(distance - radius);
		return new Point(sX + distance - shift, sY + distance - shift);
	}
	
			
	
	public void drawCircle(int sX, int sY, int rX, int rY, double alpha, double end){
		line = new LineRenderer(img);
		int radius = countDistance(sX, sY, rX, rY);
		double xA,yA;
		double xB = (sX + radius * Math.sin(alpha-0.01));
		double yB = (sY + radius * Math.cos(alpha-0.01));
		if(alpha > end){
			alpha -= 2* Math.PI;
		}
		
		while(alpha<=end){
			xA =  (sX + radius * Math.sin(alpha));
			yA =  (sY + radius * Math.cos(alpha));
			
			if((xA >= 0) && (xB >= 0) && (yA >= 0) && (yB >= 0) && (xA < 1000) && (xB < 1000) && (yA < 750) && (yB < 750))
			{
				line.draw((int) xA, (int)yA, (int)xB,(int) yB);	
			}
			xB = xA;
			yB = yA;
			alpha+=0.01;
		}		 
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

	public int getColor2() {
		return color2;
	}

	public void setColor2(int color2) {
		this.color2 = color2;
	}


	
}
