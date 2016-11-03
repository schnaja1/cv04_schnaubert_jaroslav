package cv04_schnaubert_jaroslav;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ScanLineFiller extends Filler{
	private List<SLine> sLines;
	
	public ScanLineFiller(BufferedImage img, int color){
	this.img=img;
	this.color=color;
	}

	public ScanLineFiller(BufferedImage img) {
		this(img,0xFFFFFF);
	}	
	
	public void fill (List<Point> points){
		sLines = new ArrayList<>();
		int min = (int)points.get(0).getY();
		int max = min;
		for(int i = 0; i<points.size();i++){
			SLine sLine = new SLine(points.get(i),points.get((i+1)%points.size()));
			min = Math.min(min,(int) points.get(i).getY());
			max = Math.max(max,(int) points.get(i).getY());
			if(!sLine.isHorizontal()){
				sLines.add(sLine);
			}
		}
		List<Integer> intersections = new ArrayList<>();
		for(int y = min; y <= max; y++){
			for(SLine sLine : sLines){
				if(sLine.isIntersection(y)){
					intersections.add(sLine.getIntersection(y));
					
					//setridit
					//propojit
				}
			}
		}
	//	sLines.add(new SLine(points.get(points.size()-1), points.get(0)));
	}
	
/*	public int getMinY(){
		int minY = (int) sLines.get(0).getPoint1().getY();
		int y1,y2;
		for(SLine sLine : sLines){
			y1=(int) sLine.getPoint1().getY();
			y2=(int) sLine.getPoint2().getY();
			int y = Math.min(y1, y2);
			minY = Math.min(minY, y);
		}
		return minY;
	}
	
	public int getMaxY(){
		int maxY = (int) sLines.get(0).getPoint1().getY();
		int y1,y2;
		for(SLine sLine : sLines){
			y1=(int) sLine.getPoint1().getY();
			y2=(int) sLine.getPoint2().getY();
			int y = Math.max(y1, y2);
			maxY = Math.max(maxY, y);
		}
		return maxY;
	}*/
}
