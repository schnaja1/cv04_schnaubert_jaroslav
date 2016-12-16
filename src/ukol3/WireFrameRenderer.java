package ukol3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	private Graphics g;

	public WireFrameRenderer(BufferedImage img){
		setImage(img);
	}
	
	@Override
	public void draw(Solid solid) {
		List<Point3D> points = solid.getVertexBuffer();
		List<Integer> indexes = solid.getIndexBuffer();
		
		if(g==null)
			g = img.getGraphics();
		g.setColor(solid.getColor());
		Mat4 matrix = model.mul(view.mul(proj));

		for(int i = 0; i < indexes.size(); i+=2){
			if(solid.getClass()==XYZ.class){
				List<Color> colors = new ArrayList<>();
				colors.add(Color.RED);
				colors.add(Color.BLUE);
				colors.add(Color.GREEN);
				g.setColor(colors.get(i%3));
			}
			Point3D pointA = points.get(indexes.get(i));
			Point3D pointB = points.get(indexes.get(i+1));
			
			pointA = pointA.mul(matrix);
			pointB = pointB.mul(matrix);
			
			

			Optional<Vec3D> optVecA = pointA.dehomog();
			Optional<Vec3D> optVecB = pointB.dehomog();
			
			if(!optVecA.isPresent()||!optVecB.isPresent())
				continue;
			
			Vec3D vecA = optVecA.get(),
				  vecB = optVecB.get();
			if(isVecProper(vecA)||isVecProper(vecB)){
			
			vecA = vecA.mul(new Vec3D(1,1,1)).add(new Vec3D(1,1,0)).mul(new Vec3D((0.5 * (img.getWidth() - 1)), (0.5 * (img.getHeight() - 1)), 1));
			vecB = vecB.mul(new Vec3D(1,1,1)).add(new Vec3D(1,1,0)).mul(new Vec3D((0.5 * (img.getWidth() - 1)), (0.5 * (img.getHeight() - 1)), 1));
			
			/*if (
					vecA.getX() < -1 || vecA.getX() > 1 ||
					vecA.getY() < -1 || vecA.getY() > 1 ||
					vecB.getX() < -1 || vecB.getX() > 1 ||
					vecB.getY() < -1 || vecB.getY() > 1 ||
					vecA.getZ() < 0 || vecA.getZ() > 1 ||
					vecB.getZ() < 0 || vecB.getZ() > 1
					) continue;
	        
			
		/*	int xA =(int) (vecA.getX()+1)*(img.getWidth()-1)/2;
			int yA =(int) (vecA.getY()+1)*(img.getHeight()-1)/2;
			
			int xB =(int) (vecB.getX()+1)*(img.getWidth()-1)/2;
			int yB =(int) (vecB.getY()+1)*(img.getHeight()-1)/2;

			g.drawLine(xA, yA, xB, yB);
		*/
			
		//	if((vecA.getX()<1000&&vecB.getX()<1000)||(vecA.getY()<1000&&vecB.getY()<1000)||(vecA.getX()>0&&vecB.getX()>0)||(vecA.getY()>0&&vecB.getY()>0))
			g.drawLine((int) vecA.getX(), (int) vecA.getY(), (int) vecB.getX(),(int)  vecB.getY());
			}
			//System.out.println(xA + " " + yA + " " + xB + " " + yB);
			
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
	}

	

	@Override
	public void draw(List<Solid> list) {
		 g = img.getGraphics();
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


	@Override
	public boolean isVecProper(Vec3D vec) {
		return 	(vec.getX() < -1 || vec.getX() > 1 ||
				vec.getY() < -1 || vec.getY() > 1 ||
				vec.getZ() < 0 || vec.getZ() > 1 );
	}
}
