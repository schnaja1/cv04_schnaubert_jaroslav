package cv04_schnaubert_jaroslav;

import java.awt.image.BufferedImage;

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

	public void draw(int xA, int yA, int xB, int yB) {	
		if ((xA > xB) && (yA > yB))
		{                             
			xB += xA;
			xA = xB - xA;
			xB -= xA;

			yB += yA;
			yA = yB - yA;
			yB -= yA;
		}
		if ((xA<=xB) && (yA<=yB))
		{
			int err = 0, dx, dy;
			dx=xB-xA;
			dy=yB-yA;
			while ((xA<=xB) && (yA<=yB))
			{
				img.setRGB(xA, yA, color);
				if(err>0){
					xA+=1;
					err-=dy;
				}
				else{
					yA+=1;
					err+=dx;
				}
			}
		}
		else if (xA==xB)
		{
			if(yA > yB)
			{	
				yB += yA;
				yA = yB - yA;
				yB -= yA;				
			}
			while (yA<=yB)
			{
				img.setRGB(xA, yA, color);
				yA++;
			}	
		}
		else if (yA==yB)
			{
				if(xA > xB)
				{
					xB += xA;
					xA = xB - xA;
					xB -= xA;				
				}
				while (xA<=xB)
				{
					img.setRGB(xA, yA, color
							);
					xA++;
				}	
			}
		else {
			if ((xA > xB) && (yA < yB))
			{                             
				xB += xA;
				xA = xB - xA;
				xB -= xA;

				yB += yA;
				yA = yB - yA;
				yB -= yA;
			}
			int err = 0, dx, dy;
			dx=xB-xA;
			dy=yA-yB;
			while ((xA<=xB) && (yA>=yB))
			{
				img.setRGB(xA, yA, color);
				if(err>0){
					xA+=1;
					err-=dy;
				}
				else{
					yA-=1;
					err+=dx;
				}
			}	
		}
	}

}
