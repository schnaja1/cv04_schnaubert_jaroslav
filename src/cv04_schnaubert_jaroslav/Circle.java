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
		return new Point(sX + shift, sY + shift);
	}
	
	/*public void drawCircleSector(int sX, int sY, int rX, int rY, int x, int y){
		int radius = countDistance(sX, sY, rX, rY);
		Point point = new Point(rX, rY);
		Point point2 = findPointOnCircle(sX, sY, radius, x, y);
		double alpha = 0;
		int xA,yA;
		while(alpha<360){
			xA = (int) (sX + radius * Math.sin(alpha));
			yA = (int) (sY + radius * Math.cos(alpha));
			
			if(((xA == point.getX() && (yA == point.getY())) || ((xA == point2.getX()) && yA == point2.getY()))){
				if(color == 0xffff00){
					color = color2;
					color2 = 0xffff00;
				}
				else{
					int color3 = color;
					this.setColor(color2);
					this.setColor2(color3);
				}
			}
			
			if((xA >= 0) && (yA >= 0) && (xA < 1000) && (yA < 750))
			{
				img.setRGB(xA, yA, color);
			}
			alpha+=0.01;
		}
	}*/
			
	
	public void drawCircle(int sX, int sY, int rX, int rY){
	
		
		line = new LineRenderer(img);
		int radius = countDistance(sX, sY, rX, rY);
		//int distance = (int) (2*(Math.sin(1)*radius));
		
		int xA,yA;
		
		int xB = (int) (sX + radius * Math.sin(0));
		int yB = (int) (sY + radius * Math.cos(0));
		
		double alpha = 0;
		
		while(alpha<360){
			xA = (int) (sX + radius * Math.sin(alpha));
			yA = (int) (sY + radius * Math.cos(alpha));
			
	/*		if((xA >= 0) && (yA >= 0) && (xA < 1000) && (yA < 750))
			{
				//line.draw(xA, yA, xB, yB);
				img.setRGB(xA, yA, color);
			}*/
			
			
			if((xA >= 0) && (xB >= 0) && (yA >= 0) && (yB >= 0) && (xA < 1000) && (xB < 1000) && (yA < 750) && (yB < 750))
			{
				line.draw(xA, yA, xB, yB);
				
			}
			xB = xA;
			yB = yA;
			alpha+=0.01;
		}
		
		xA = (int) (sX + radius * Math.sin(359));
		yA = (int) (sY + radius * Math.cos(359));
		
		if((xA >= 0) && (xB >= 0) && (yA >= 0) && (yB >= 0) && (xA < 1000) && (xB < 1000) && (yA < 750) && (yB < 750))
		line.draw(xA, yA, xB, yB);
		 
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
