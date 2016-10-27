package cv04_schnaubert_jaroslav;

import java.awt.Point;

public class Line {
	Point a, b;
	float k, q;
	
	public Line(Point a, Point b){
		this.a = a;
		this.b = b;
	}
	
	public boolean isHorizontal(){
		return true;
	}
	
	public boolean isIntersecton(int y){
		 return true;
	}

}
