package ukol1;



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
	 
	/*
	 * N�sleduj�c� funkci countDistance pou��v�m k vypo�ten� polom�ru. 
	 * V p�vodn� verzi m�ho projektu jsem tuto funkci pou��val tak� k ur�en� bodu na p��mce v��i pozici my�i,
	 * d�ky kter� jsem se sna�il vykreslit v�se�. Av�ak marn�.
	 */
	
	public int countDistance(int sX, int sY, int rX, int rY){
		int x,y;
		
		x = Math.abs(sX - rX);
		y = Math.abs(sY - rY);
		
		int distance = (int) Math.sqrt((x*x)+(y*y));
		
		return distance;
	}	
	
	public void drawCircle(int sX, int sY, int rX, int rY, double alpha, double beta){
		line = new LineRenderer(img);
		
		int radius = countDistance(sX, sY, rX, rY); // v�po�et polom�ru
		
		double xA,yA,xB,yB;
		
		//Vypo�ten� prvn�ho bodu kru�nice
		xB = (sX + radius * Math.sin(alpha));  
		yB = (sY + radius * Math.cos(alpha));
		
		alpha+=0.01;
		
		//V p��pad�, �e je kreslena v�se�, spoj� se st�ed s krajn�m bodem kru�nice.
		if(beta != 2*Math.PI)
			line.draw(sX,sY,(int) xB,(int) yB);
		
		/*
		 * N�sleduj�c� funkce o�et�uje p��pad, �e je �hel alfa v�t�� ne� �hel beta
		 * To v�ak m��e nastat pouze p�i kreslen� v�se�e.
		 */
		
		if(alpha > beta){
			alpha -= 2* Math.PI;
		}
		
		while(alpha<=beta){
			
			//V�po�et bodu na kru�nici
			xA =  (sX + radius * Math.sin(alpha));
			yA =  (sY + radius * Math.cos(alpha));
			
			/*
			 * Spojen� p�edposledn�ho bodu s posledn�m vypo�ten�m bodem na kru�nici
			 * Vykreslen� je opodm�nkovan� pro p��pad, �e by se body kru�nice nach�zeli mimo canvas.
			 */
			if((xA >= 0) && (xB >= 0) && (yA >= 0) && (yB >= 0) && (xA < 1000) && (xB < 1000) && (yA < 750) && (yB < 750))
			{
				line.draw((int) xA, (int)yA, (int)xB,(int) yB);	
			}
			
			xB = xA;
			yB = yA;
			alpha+=0.01;
		}
		
		 // V p��pad�, �e se kresl� v�se� kru�nice je spojen posledn� bod na p��mce se st�edem kru�nice. 
		if(beta != 2*Math.PI){
			xA =  (sX + radius * Math.sin(alpha-0.01));
			yA =  (sY + radius * Math.cos(alpha-0.01));
			line.draw(sX,sY,(int) xA,(int) yA);
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
