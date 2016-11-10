package ukol2;

import java.awt.Point;

public class SLine {
	private Point point1;
	private Point point2;
	
	public SLine(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}
	
	public boolean isHorizontal(){
		return (int)(point1.getY()) == (int)(point2.getY());
	}
	
	public void changeDirection(){
		if(point1.getY() > point2.getY()){
			Point point3 = point1;
			point1=point2;
			point2=point3;	
		}
	}
	
	public boolean isIntersection(int y){
		return(point1.getY()<=y && point2.getY() > y);	
	}
	
	public int getIntersection(int y){
		float dx = point2.x - point1.x;
		float dy = point2.y - point1.y;

		return Math.round(point1.x + (dx / dy) * (y - point1.y));
	}
}
