package ukol3;

import java.util.ArrayList;
import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

public class Curve extends Solid{
	
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
			points[1]=new Point3D(-1,-1, 0);
			points[2]=new Point3D(1,-2,-1);
			points[3]=new Point3D(-2,-1,-3);
			points[4]=new Point3D(2,-1,-1);
			points[5]=new Point3D(5,-2,-4);
		break;
		case 1:
			matrix=Cubic.COONS;
			points[0]=new Point3D(0,1,1);
			points[1]=new Point3D(6,4, 2);
			points[2]=new Point3D(4,3,3);
			points[3]=new Point3D(2,4,4);
			points[4]=new Point3D(1,3,5);
			points[5]=new Point3D(3,3,3);
			break;
		default:
			matrix=Cubic.FERGUSON;
			points[0]=new Point3D(0.5,1,0.5);
			points[1]=new Point3D(2,3, 1);
			points[2]=new Point3D(3,2,3);
			points[3]=new Point3D(4,1,3);
			points[4]=new Point3D(5,4,5);
			points[5]=new Point3D(6,8,4);
			break;
		}
		
		cubic = new Cubic(matrix, points);
		
		vertexBuffer.add(points[0]);
		
		for(int i=2; i<=100; i++){
			Point3D p1 = cubic.compute(i/10.0);
			vertexBuffer.add(p1);
		}
		for(int i=0; i<vertexBuffer.size()-1; i++){
			indexBuffer.add(i);
			indexBuffer.add(i+1);
		}
	}
}
