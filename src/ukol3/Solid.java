package ukol3;

import java.util.List;

import transforms.Point3D;

public abstract class Solid {

	protected List<Point3D> vertexBuffer;
	protected List<Integer> indexBuffer;
	
/*	protected void draw(){
		
	}*/	
	
	public List<Point3D> getVertexBuffer() {
		return vertexBuffer;
	}
	public List<Integer> getIndexBuffer() {
		return indexBuffer;
	}

}
