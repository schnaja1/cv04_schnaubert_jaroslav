package ukol3;

import java.util.ArrayList;

import transforms.Point3D;

public class Pyramid extends Solid {

	public Pyramid(){
		vertexBuffer = new ArrayList<>();
		indexBuffer = new ArrayList<>();
		vertexBuffer.add(new Point3D(0,0,0)); //Bod 1
		vertexBuffer.add(new Point3D(1,0,0)); //Bod 2
		vertexBuffer.add(new Point3D(0,0,1)); //Bod 3
		vertexBuffer.add(new Point3D(1,0,1)); //Bod 4
		vertexBuffer.add(new Point3D(0.5,1,0.5)); //Vrchol
	
			
		/* podstava
		 * 
		 *  2 - 3
		 *  |   |
		 *  0 - 1
		 */  
		
		indexBuffer.add(0); indexBuffer.add(1); 
		indexBuffer.add(1); indexBuffer.add(3); 
		indexBuffer.add(2); indexBuffer.add(3); 
		indexBuffer.add(2); indexBuffer.add(0); 
				
		//spojení všech bodù podstavy s vrcholem
		for(int i = 0; i < 4; i++){
			indexBuffer.add(i);
			indexBuffer.add(4);
		}

	}
}
