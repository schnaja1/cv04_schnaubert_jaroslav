package ukol3;

import java.util.ArrayList;

import transforms.Point3D;

public class Cube extends Solid {
	
	public Cube(){
		vertexBuffer = new ArrayList<>();
		indexBuffer = new ArrayList<>();
		this.vertexBuffer.add(new Point3D(1,1,1));
	}

}
