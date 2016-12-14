package ukol3;

import java.util.ArrayList;

import transforms.Point3D;

public class Cube extends Solid {
	
	public Cube(){
		vertexBuffer = new ArrayList<>();
		indexBuffer = new ArrayList<>();
		vertexBuffer.add(new Point3D(0,0,0)); //Bod 1
		vertexBuffer.add(new Point3D(0,1,0)); //Bod 2
		vertexBuffer.add(new Point3D(1,0,0)); //Bod 3
		vertexBuffer.add(new Point3D(1,1,0)); //Bod 4
		vertexBuffer.add(new Point3D(0,0,1)); //Bod 5
		vertexBuffer.add(new Point3D(0,1,1)); //Bod 6
		vertexBuffer.add(new Point3D(1,0,1)); //Bod 7
		vertexBuffer.add(new Point3D(1,1,1)); //Bod 8
			
		/* pøední ètverec
		 * 
		 *  2 - 3
		 *  |   |
		 *  0 - 1
		 */  
		
		indexBuffer.add(0); indexBuffer.add(1); 
		indexBuffer.add(1); indexBuffer.add(3); 
		indexBuffer.add(2); indexBuffer.add(3); 
		indexBuffer.add(2); indexBuffer.add(0); 
				
		/* zadní ètverec
		 *
		 *  6 - 7
		 *  |   |
		 *  4 - 5
		 */
		
		indexBuffer.add(4); indexBuffer.add(5); 
		indexBuffer.add(5); indexBuffer.add(7); 
		indexBuffer.add(6); indexBuffer.add(7); 
		indexBuffer.add(6); indexBuffer.add(4); 
		
		/* spojení ètvercù z pravé strany
		 *
		 *  3 - 7
		 * 
		 *  1 - 5
		 */
		
		indexBuffer.add(1); indexBuffer.add(5); 
		indexBuffer.add(3); indexBuffer.add(7); 

		
		/* spojení ètvercù z levé strany
		 * 
		 *  0 - 4
		 * 
		 *  2 - 6
		 */
		
		indexBuffer.add(0); indexBuffer.add(4); 
		indexBuffer.add(2); indexBuffer.add(6); 
	}

}
