package ukol2;



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
		
		
		//kontrola, zda souradnice jsou v kanvasu
		if(xA<1)
			xA=1;
		if(xB<1)
			xB=1;
		if(yA<1)
			yA=1;
		if(yB<1)
			yB=1;
		if(xA>999)
			xA=999;
		if(xB>999)
			xB=999;
		if(yA>749)
			yA=749;
		if(yB>749)
			yB=749;
		
		
		//následující dvì podmínky slouží pro kreslení 1. a 3. kvadrantu. První if otáèí hodnoty, aby se vždy kreslilo zleva doprava
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
		
		//následující podmínka kreslí èáru po ose y, když se x nemìní
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
		
		//následující podmínka kreslí èáru po ose x, pokud se y nemìní
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
			
			//zbytek kódu øeší kreslení v pøípadì, že se x zvìtšuje a zároveò y zmenšuje a naopak
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
