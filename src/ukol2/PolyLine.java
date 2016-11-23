package ukol2;

import java.util.ArrayList;
import java.util.List;

import transforms.Mat3Rot2D;
import transforms.Point2D;

public class PolyLine {
	private List<Point2D> points;
	private LineRenderer line;
	
	public PolyLine(LineRenderer line){
		this.line = line;
		points = new ArrayList<Point2D>();
	}

	public double checkX(double x){
		if(x<0)
			x=0;
		else if(x>Canvas.WIDTH - 1)
			x=Canvas.WIDTH - 1;
		return x;
	}
	
	public double checkY(double y){
		if(y<0)
			y=0;
		else if(y>Canvas.HEIGHT - 1)
			y=Canvas.HEIGHT - 1;
		return y;
	}
	
	public void add(double x, double y){
		x = checkX(x);
		y = checkY(y);
		points.add(new Point2D(x,y));
	}
	
	public List<Point2D> rotate(double angle){
		Mat3Rot2D mat = new Mat3Rot2D(angle);
		List<Point2D> transformedPoints = new ArrayList<Point2D>();		
		
		for(Point2D point : points)
			transformedPoints.add(point.mul(mat));
		
		return transformedPoints;
	}
	
	public int size(){
		return points.size();
	}
	
	public void clear(){
		points.clear();
	}

	public void draw(){
		if(points.size()>1){
			line.setColor(0xffff00);
			for(int i = 0; i<points.size()-1;i++)
				line.draw(points.get(i), points.get(i+1));
			line.draw(points.get(0), points.get(points.size()-1));
		}
	}
	public Point2D get(int index){
		return points.get(index);
	}
}
