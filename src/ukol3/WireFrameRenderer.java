package ukol3;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

public class WireFrameRenderer implements Renderable {
	private Mat4 view;
	private Mat4 model;
	private Mat4 proj;
	private BufferedImage img;
	
	public WireFrameRenderer(BufferedImage img){
		setImage(img);
	}
	
	@Override
	public void draw(Solid solid) {
		
		List<Point3D> points = solid.getVertexBuffer();
		List<Integer> indexes = solid.getIndexBuffer();
		
		Mat4 matrix = model.mul(view.mul(proj));
		
		for(int i = 0; i < indexes.size(); i+=2){
			Point3D pointA = points.get(indexes.get(i));
			Point3D pointB = points.get(indexes.get(i+1));
		
			pointA = pointA.mul(matrix);
			pointB = pointB.mul(matrix);
			
			Optional<Vec3D> optVecA = pointA.dehomog();
			Optional<Vec3D> optVecB = pointB.dehomog();
			
			Vec3D vecA,vecB;
			
			//TODO clip z
			
			if(optVecA.isPresent()&&optVecB.isPresent()){
				vecA = optVecA.get();
				int xA =(int) (pointA.getX()+1)*(img.getWidth()-1)/2;
				int yA =(int) (pointA.getY()+1)*(img.getHeight()-1)/2;
				
				vecB=optVecB.get();
				
				int xB =(int) (pointB.getX()+1)*(img.getWidth()-1)/2;
				int yB =(int) (pointB.getY()+1)*(img.getHeight()-1)/2;
				
			}
			
			
		}
		
		
		
		//model mat4translate a je mozne *mat4scale
		//proj persp - proj zorny uhel pi/4,1,0.01,100.. ort - jen 0.01,100
		//view  new matview vec3d 5 -4 3.., -5 4 -3, new vec3d 0 1 0,
		//transformace
		//4D -> 4D MVP
		//clip w pro w > 0
		//4D -> 3D dehomogenizace
		//clip ZO
		//3D -> 2D odstranime z
		//viewPort transformace
		//vykresleni drawLine - rasterizace
		
		/*for(int i=0;i<indexBuffer.size();i+=2){
			
		}*/
	}

	@Override
	public void draw(List<Solid> list) {
		for(Solid solid : list)
			draw(solid);
	}

	@Override
	public void setModel(Mat4 model) {
		this.model=model;
	}

	@Override
	public void setView(Mat4 view) {
		this.view=view;
	}

	@Override
	public void setProj(Mat4 proj) {
		this.proj=proj;
	}

	@Override
	public void setImage(BufferedImage img) {
		this.img = img;
	}

}
