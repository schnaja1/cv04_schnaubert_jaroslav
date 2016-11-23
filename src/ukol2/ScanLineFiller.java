package ukol2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import transforms.Mat3Rot2D;
import transforms.Point2D;

public class ScanLineFiller extends Filler{
	private List<SLine> sLines;
	private LineRenderer line;
	
	public ScanLineFiller(BufferedImage img) {
		super(img);

	}
	public ScanLineFiller(BufferedImage img, int color){
		super(img,color);
	}
	
	public List<Double> sortListOfDoubles(List<Double> list){
		for(int i = 0; i < list.size(); i++){
			int min = i;
			for(int j = i+1; j < list.size();j++){
				if(list.get(j) < list.get(min))
					min = j;
			}
			double a = list.get(i);
			list.set(i, list.get(min));
			list.set(min,a);
		}
		return list;
	}
	
	public Point2D rotate(Point2D point, double alpha){
		final Mat3Rot2D mat3Rot2D = new Mat3Rot2D(alpha);
		return point.mul(mat3Rot2D);
	}

	public void fill (PolyLine points, int angle, int space){
		
		if(points.size()==0)
			return;
		line = new LineRenderer(img, this.color);
		sLines = new ArrayList<>();
		
		List<Point2D> rotatedPoints = new ArrayList<>(); 
		
		rotatedPoints= points.rotate(Math.toRadians(angle));
	
		double min = rotatedPoints.get(0).getY();
		double max = min;
		
		for(int i = 0; i<rotatedPoints.size();i++){
			SLine sLine = new SLine(rotatedPoints.get(i),rotatedPoints.get((i+1)%rotatedPoints.size()));
			min = Math.min(min, rotatedPoints.get(i).getY());
			max = Math.max(max, rotatedPoints.get(i).getY());
			if(!sLine.isHorizontal()){
				sLine.changeDirection();
				sLines.add(sLine);
			}
		}
		List<Double> intersectionsX = new ArrayList<>();
		for(double y = min; y < max; y+=space){
			for(SLine sLine : sLines)
				if(sLine.isIntersection(y))
					intersectionsX.add(sLine.getIntersection(y));
			
			intersectionsX = sortListOfDoubles(intersectionsX);
			for(int i = 0; i < intersectionsX.size(); i+=2)
			{
				Point2D point1 = new Point2D(intersectionsX.get(i),y);
				Point2D point2 = new Point2D(intersectionsX.get((i + 1)),y);

				point1 = rotate(point1,(Math.toRadians(360-angle)));
				point2 = rotate(point2,(Math.toRadians(360-angle)));
				
				line.draw(point1, point2);
			
			}
			intersectionsX.clear();
		}			
	}
}