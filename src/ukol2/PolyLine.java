package ukol2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import transforms.Mat3Rot2D;
import transforms.Point2D;

public class PolyLine {
	private List<Point2D> points;
	
	public PolyLine(){
		points = new ArrayList<Point2D>();
	}
	
	public void add(double x, double y){
		points.add(new Point2D(x,y));
	}
	
	public List<Point2D> rotate(double angle){
		Mat3Rot2D mat = new Mat3Rot2D(angle);
		List<Point2D> transformedPoints = new ArrayList<Point2D>();
		
		for(Point2D point : points){

			transformedPoints.add(point.mul(mat));
		}
		return transformedPoints;
	}
	
	public Point2D get(int index){
		return points.get(index);
	}
	
	public int size(){
		return points.size();
	}

}
