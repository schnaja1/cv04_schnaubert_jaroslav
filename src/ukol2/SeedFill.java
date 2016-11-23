package ukol2;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class SeedFill extends Filler{
	List<Point> points;
	private int design [][];

	public SeedFill(BufferedImage img) {
		this(img, 0xffffff, 0xff0000);
	
	}
	
	public SeedFill(BufferedImage img, int color1, int color2){
		super(img);
		points = new ArrayList<>();
	/*	design = new int[][]{
			{color1,color1,color1,color1,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color2,color1,color1,color1,color1},
			{color1,color1,color1,color2,color2,color2,color1,color1,color1},
			{color1,color1,color2,color2,color2,color2,color2,color1,color1},
			{color1,color2,color2,color2,color2,color2,color2,color2,color1},
			{color1,color1,color2,color2,color2,color2,color2,color1,color1},
			{color1,color1,color1,color2,color2,color2,color1,color1,color1},
			{color1,color1,color1,color1,color2,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color1,color1,color1,color1}
			};*/
		design = new int[][]{
			{color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color2,color2,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color2,color2,color2,color2,color1,color1,color1,color1},
			{color1,color1,color1,color2,color2,color2,color2,color2,color2,color1,color1,color1},
			{color1,color1,color2,color2,color2,color2,color2,color2,color2,color2,color1,color1},
			{color1,color1,color2,color2,color2,color2,color2,color2,color2,color2,color1,color1},
			{color1,color1,color1,color2,color2,color2,color2,color2,color2,color1,color1,color1},
			{color1,color1,color1,color1,color2,color2,color2,color2,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color2,color2,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1},
			{color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1,color1}
			};
	}
	
	public boolean checkPointPosition(Point point){
		return ((point.getX()<0)||(point.getY()<0)||(point.getX()>=Canvas.WIDTH)||(point.getY()>=Canvas.HEIGHT));
	}
	
	public int getColor(Point point, boolean fillWithPattern){
		if(fillWithPattern)
			return design[(int)point.getY() % design.length][(int)point.getX() % design[0].length];
		else
			return design[0][0];
		
	}
	
	public void spreadSeed(Point point, int previousColor){
			points.add(new Point((int) point.getX()+1, (int) point.getY()));
			points.add(new Point((int) point.getX(), (int) point.getY()+1));
			points.add(new Point((int) point.getX()-1, (int) point.getY()));
			points.add(new Point((int) point.getX(), (int) point.getY()-1));
	}
	
	public void fill(int x, int y, boolean fillWithPattern){
		points.add(new Point(x,y));	
		
		int previousColor = img.getRGB(x, y);
		
		while(points.size()>0){
			Point point = points.get(0);				
			if(!checkPointPosition(point))
				if(img.getRGB((int)point.getX(),(int) point.getY() ) == previousColor){
					img.setRGB((int)point.getX(),(int) point.getY(), getColor(point,fillWithPattern));
					spreadSeed(point, previousColor);
					if(point.getX() % 20 == 0)
						Canvas.present();
					}
			points.remove(point);
		}
	}
}
