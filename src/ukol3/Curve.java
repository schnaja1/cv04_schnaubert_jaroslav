package ukol3;

import java.awt.Color;
import java.util.ArrayList;
import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

public class Curve extends Solid{
	
	public Curve(int type, Color color) {
		this(type);
		this.color=color;
	}
	
	public Curve(int type) {
		vertexBuffer = new ArrayList<>();
		indexBuffer = new ArrayList<>();
		Mat4 matrix;
		Cubic cubic;
		Point3D[] points = new Point3D[6];
		switch(type){
		case 0:
			matrix=Cubic.BEZIER;;
			points[0]=new Point3D(0,0,0);
			points[1]=new Point3D(0.25,1, 0.25);
			points[2]=new Point3D(0.5,0,0.5);
			points[3]=new Point3D(0.625,0.5,0.625);
			points[4]=new Point3D(0.75,0,0.75);
			points[5]=new Point3D(1,1,1);
		break; 
		case 1:
			matrix=Cubic.COONS;
			points[0]=new Point3D(1,0,0);
			points[1]=new Point3D(0,0.25, 0.25);
			points[2]=new Point3D(1,0.4,0.4);
			points[3]=new Point3D(0,0.6,0.6);
			points[4]=new Point3D(1,0.8,0.8);
			points[5]=new Point3D(0,1,1);
			break;
		default:
			matrix=Cubic.FERGUSON;
			points[0]=new Point3D(0,0,1);
			points[1]=new Point3D(0.2,0.8, 0);
			points[2]=new Point3D(0.4,0.6,1);
			points[3]=new Point3D(0.6,0.4,0);
			points[4]=new Point3D(0.8,0.2,1);
			points[5]=new Point3D(1,1,0);
			break;
		}
		
		cubic = new Cubic(matrix, points);
		
		vertexBuffer.add(points[0]);
		
		for(int i=2; i<=100; i++){
			Point3D p1 = cubic.compute(i/100.0);
			vertexBuffer.add(p1);
		}
		vertexBuffer.add(points[5]);
		for(int i=0; i<vertexBuffer.size()-1; i++){
			indexBuffer.add(i);
			indexBuffer.add(i+1);
		}
	}
}
