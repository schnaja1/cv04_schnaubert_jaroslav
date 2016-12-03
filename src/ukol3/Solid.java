package ukol3;

import java.util.List;

import transforms.Point3D;

public abstract class Solid {

	protected List<Point3D> vertexBuffer;
	protected List<Integer> indexBuffer;
	
/*	protected void draw(){
		
	}*/	
	public void addEdge(int index1, int index2){
		indexBuffer.add(index1);
		indexBuffer.add(index2);
	}
	
	public void addPoint(Point3D point){
		vertexBuffer.add(point);
	}
	
	public List<Point3D> getVertexBuffer() {
		return vertexBuffer;
	}
	public List<Integer> getIndexBuffer() {
		return indexBuffer;
	}

}
