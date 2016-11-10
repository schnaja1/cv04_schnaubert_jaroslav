package ukol2;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLineFiller extends Filler{
	private List<SLine> sLines;
	private LineRenderer line;
	
	public ScanLineFiller(BufferedImage img, int color){
	this.img=img;
	this.color=color;
	}

	public ScanLineFiller(BufferedImage img) {
		this(img,0xFFFFFF);
	}	
	
	
/*	public void scanlineFill(List<Point> points){
				for(int i = 0; i < points.size(); i++){
			Point a = points.get(i);
			Point b = points.get((i + 1) % points.size());
			SLine sLine = new SLine(a, b);
			
			if(!sLine.isHorizontal()){
				sLine.changeDirection();
				sLines.add(sLine);
			}

			if(maxY < a.getY())
				maxY = a.y;
			
			if(minY > a.getY())
				minY = a.y;
		}

		List<Integer> intersectionsX = new ArrayList<>();
		for(int y = minY; y < maxY; y++){
			
			for(SLine sLine : sLines)
				if(sLine.isIntersection(y))
					intersectionsX.add(sLine.getIntersection(y));
				
			//sort podle x intersekce
			Collections.sort(intersectionsX);
			//pospojovat liche se sudym prusecikem
			for(int i = 0; i < intersectionsX.size(); i+=2){
				Point a = new Point(intersectionsX.get(i),y);
				Point b = new Point(intersectionsX.get((i + 1)%intersectionsX.size()),y);
				
			}
			
			intersectionsX.clear();
		}
	}*/
	public void fill (List<Point> points){
		line = new LineRenderer(img, 0x0000ff);
		sLines = new ArrayList<>();
		int min = (int)points.get(0).getY();
		int max = min;
		for(int i = 0; i<points.size();i++){
			SLine sLine = new SLine(points.get(i),points.get((i+1)%points.size()));
			min = Math.min(min,(int) points.get(i).getY());
			max = Math.max(max,(int) points.get(i).getY());
			if(!sLine.isHorizontal()){
				sLine.changeDirection();
				sLines.add(sLine);
			}
		}

		List<Integer> intersections = new ArrayList<>();
		for(int y = min; y <= max; y++){
			for(SLine sLine : sLines)
				if(sLine.isIntersection(y))
					intersections.add(sLine.getIntersection(y));
			Collections.sort(intersections);
				for(int i = 0; i < intersections.size();i+=2){
					int xA = intersections.get(i);
					int xB = intersections.get((i+1)%intersections.size());
					System.out.println(xA + " " + xB);
					line.draw(xA,y,xB,y);	
				}
		//	intersections.clear();
			}
		}	
}
