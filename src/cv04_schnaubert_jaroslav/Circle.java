package cv04_schnaubert_jaroslav;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Circle {
	private BufferedImage img;
	private int color; 
	private List<Integer> listX = new ArrayList<Integer>();
	private List<Integer> listY = new ArrayList<Integer>();
	private LineRenderer line;
	
	
	public Circle(BufferedImage img, int color){
		this.setImg(img);
		this.setColor(color);
	} 
	 
	public Circle(BufferedImage img){
		this(img,0xffffff);
	} 
	 
	public void draw(int sX, int sY, double r){
		listX.clear();
		listY.clear();
		
		listX.add(sX);
		listY.add((int) (sY-r));
		
		double xA =  sX, yA = sY -r;
		double xB, yB;
		
		while((xA != yA) && (xA < sX + r)){
			xB = xA;
			//yB = yA;
			//xB = (int) listX.get(listX.size()-1);
			xA =  xB + 1;
			if(xA>r)
			{
				yA=  Math.sqrt(xA*xA-r*r);
			}
			else
				yA =  Math.sqrt(r*r-xA*xA);	
			listX.add((int)xA);
			listY.add((int)yA);
			System.out.println(xA + " " + yA);
		}
		drawPoints();
		
		
	}
	

	public void drawPoints(){
		line = new LineRenderer(img);
		int sizeX = listX.size()-1, sizeY = listY.size()-1;
		if(sizeX==sizeY){
			if(sizeX>1){
				line.setColor(0xffff00);
				int xA=0,yA=0,xB=0,yB=0;
				for(int i = 0; i<sizeX-1;i++){
					xA = listX.get(i);
					yA = listY.get(i);
					xB = listX.get(i+1);
					yB = listY.get(i+1);
					line.draw(xA, yA, xB, yB);
				}
				xA = (int) listX.get(0);
				yA = (int) listY.get(0);
				xB = (int) listX.get(sizeX-1);
				yB = (int) listY.get(sizeY-1);
				line.draw(xA, yA, xB, yB);
			}
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


	
}
