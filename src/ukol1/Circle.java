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
	 * Následující funkci countDistance používám k vypoètení polomìru. 
	 * V pùvodní verzi mého projektu jsem tuto funkci používal také k urèení bodu na pøímce vùèi pozici myši,
	 * díky které jsem se snažil vykreslit výseè. Avšak marnì.
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
		
		int radius = countDistance(sX, sY, rX, rY); // vápoèet polomìru
		
		double xA,yA,xB,yB;
		
		//Vypoètení prvního bodu kružnice
		xB = (sX + radius * Math.sin(alpha));  
		yB = (sY + radius * Math.cos(alpha));
		
		alpha+=0.01;
		
		//V pøípadì, že je kreslena výseè, spojí se støed s krajním bodem kružnice.
		if(beta != 2*Math.PI)
			line.draw(sX,sY,(int) xB,(int) yB);
		
		/*
		 * Následující funkce ošetøuje pøípad, že je úhel alfa vìtší než úhel beta
		 * To však mùže nastat pouze pøi kreslení výseèe.
		 */
		
		if(alpha > beta){
			alpha -= 2* Math.PI;
		}
		
		while(alpha<=beta){
			
			//Výpoèet bodu na kružnici
			xA =  (sX + radius * Math.sin(alpha));
			yA =  (sY + radius * Math.cos(alpha));
			
			/*
			 * Spojení pøedposledního bodu s posledním vypoèteným bodem na kružnici
			 * Vykreslení je opodmínkované pro pøípad, že by se body kružnice nacházeli mimo canvas.
			 */
			if((xA >= 0) && (xB >= 0) && (yA >= 0) && (yB >= 0) && (xA < 1000) && (xB < 1000) && (yA < 750) && (yB < 750))
			{
				line.draw((int) xA, (int)yA, (int)xB,(int) yB);	
			}
			
			xB = xA;
			yB = yA;
			alpha+=0.01;
		}
		
		 // V pøípadì, že se kreslí výseè kružnice je spojen poslední bod na pøímce se støedem kružnice. 
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
