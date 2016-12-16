package ukol3;

import java.awt.Color;
import java.util.List;

import transforms.Point3D;

public abstract class Solid {

	protected List<Point3D> vertexBuffer;
	protected List<Integer> indexBuffer;
	protected Color color;
	
	
	public List<Point3D> getVertexBuffer() {
		return vertexBuffer;
	}
	public List<Integer> getIndexBuffer() {
		return indexBuffer;
	}
	
	public Color getColor() {
		if(color == null)
			setColor(Color.WHITE);
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

}
