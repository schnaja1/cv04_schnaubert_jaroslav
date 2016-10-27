package cv04_schnaubert_jaroslav;

import java.awt.image.BufferedImage;

public class SeedFill extends Filler{
	private boolean first = true;
	private int firstY;
	
	
	public SeedFill(BufferedImage img, int color){
		this.img=img;
		this.color=color;
	}
	
	public SeedFill(BufferedImage img) {
		this(img,0xFFFFFF);
	}
	
	//DU zprovoznit
	public void fill(int x, int y, int backgroundColor, int borderColor){
		if(first == true){
			first=false;
			firstY=y;
		}
		int smallX = x;
		int bigX = x+1;
		boolean makeYLower = true;
		

		System.out.println(backgroundColor);
		System.out.println(img.getRGB(x, y));
		if(img.getRGB(x, y)==backgroundColor){
			System.out.println("projdu");
			while((smallX>0)&&(img.getRGB(smallX, y)!=borderColor)){
					img.setRGB(smallX, y, color);
					smallX--;
				}
			while((bigX<1000)&&(img.getRGB(bigX, y)!=borderColor)){
				
					img.setRGB(bigX, y, color);
					bigX++;
				}
			if(makeYLower = true)
				if(y-1<1)
					makeYLower=false;
				else{
					if(img.getRGB(x, y-1)==backgroundColor){
						fill(x, y-1, backgroundColor, borderColor);
					}
				}
			if(makeYLower = false)
				if(y+1>749)
					first=true;
				else
				{
					if(img.getRGB(x, firstY+1)==backgroundColor){
						firstY++;
						fill(x, firstY, backgroundColor, borderColor);
						
					}
				}
			
		}
		
		/*if(img.getRGB(x, y)==backgroundColor){
			img.setRGB(x, y, color);
		}*/
	}
}
