package ukol3;

import java.util.ArrayList;
import transforms.Point3D;

public class XYZ extends Solid{
	
	public XYZ(){
	vertexBuffer = new ArrayList<>();
	indexBuffer = new ArrayList<>();
	
	vertexBuffer.add(new Point3D(0, 0, 0));
	vertexBuffer.add(new Point3D(5, 0, 0));
	vertexBuffer.add(new Point3D(0, 5, 0));
	vertexBuffer.add(new Point3D(0, 0, 5));
	

	//Spojen� krajn�ch bod� jednotliv�ch �ar se st�edem se st�edem
	for(int i = 1; i < 4; i++){
		indexBuffer.add(0); 
		indexBuffer.add(i); 
	}
}
}
