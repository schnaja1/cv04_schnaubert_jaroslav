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
	private Mat4 transformMatrix;
	private BufferedImage img;
	private Graphics g;

	public WireFrameRenderer(BufferedImage img){
		setImage(img);
	}
	
	@Override
	public void draw(List<Solid> list) {
		 g = img.getGraphics();
		for(Solid solid : list)
			draw(solid);
	}
	
	@Override
	public void draw(Solid solid) {
		List<Point3D> points = solid.getVertexBuffer();
		List<Integer> indexes = solid.getIndexBuffer();
		
		if(g==null)
			g = img.getGraphics();
		g.setColor(solid.getColor());
		
		transformMatrix = model.mul(view.mul(proj));

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
			
			render(pointA, pointB);
			
		}		
	}

	@Override
	public void render(Point3D pointA, Point3D pointB) {

		
		pointA = pointA.mul(transformMatrix);
		pointB = pointB.mul(transformMatrix);
		
		if(pointA.getW()<0||pointB.getW()<0)
			return;
		
		Optional<Vec3D> optVecA = pointA.dehomog();
		Optional<Vec3D> optVecB = pointB.dehomog();
		
		if(!optVecA.isPresent()||!optVecB.isPresent())
			return;
		
		Vec3D vecA = optVecA.get(),
			  vecB = optVecB.get();
		
		if(areVecProper(vecA, vecB)){
		
		vecA = vecA.mul(new Vec3D(1,1,1)).add(new Vec3D(1,1,0)).mul(new Vec3D((0.5 * (img.getWidth() - 1)), (0.5 * (img.getHeight() - 1)), 1));
		vecB = vecB.mul(new Vec3D(1,1,1)).add(new Vec3D(1,1,0)).mul(new Vec3D((0.5 * (img.getWidth() - 1)), (0.5 * (img.getHeight() - 1)), 1));

		if((vecA.getX()<1000&&vecB.getX()<1000)||(vecA.getY()<1000&&vecB.getY()<1000)||(vecA.getX()>0&&vecB.getX()>0)||(vecA.getY()>0&&vecB.getY()>0))
		g.drawLine((int) vecA.getX(), (int) vecA.getY(), (int) vecB.getX(),(int)  vecB.getY());
		}
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
	public boolean areVecProper(Vec3D a, Vec3D b) {
		return !(Math.min(a.getX(), b.getX()) > 1.0f)||
				(Math.max(a.getX(),b.getX()) < -1.0f);
	}


}
