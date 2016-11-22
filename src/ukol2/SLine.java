package ukol2;

import transforms.Point2D;

public class SLine {
	private Point2D point1;
	private Point2D point2;
	
	public SLine(Point2D point1, Point2D point2) {
		this.point1 = point1;
		this.point2 = point2;
	}

	public Point2D getPoint1() {
		return point1;
	}

	public void setPoint1(Point2D point1) {
		this.point1 = point1;
	}

	public Point2D getPoint2() {
		return point2;
	}

	public void setPoint2(Point2D point2) {
		this.point2 = point2;
	}
	
	public boolean isHorizontal(){
		Double a = new Double(point1.getY());
		Double b = new Double(point2.getY());
		return a.equals(b);
	}
	
	public void changeDirection(){
		if(point1.getY() > point2.getY()){
			Point2D point3 = point1;
			point1=point2;
			point2=point3;	
		}
	}
	
	public boolean isIntersection(double y){
		return (y >= point1.getY() && y < point2.getY());
	}
	
	
	public double getIntersection(double y){
		double dx = point2.getX() - point1.getX();
		double dy = point2.getY() - point1.getY();

		return  point1.getX() + (dx / dy) * (y - point1.getY());
	}
}
