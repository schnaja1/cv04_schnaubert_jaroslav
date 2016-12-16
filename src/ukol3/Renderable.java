package ukol3;

import java.awt.image.BufferedImage;
import java.util.List;

import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

public interface Renderable {
	
	void draw(Solid solid);
	
	void draw(List<Solid> list);
	
	void render(Point3D pointA, Point3D pointB);
	
	void setModel(Mat4 model);
	
	void setView(Mat4 view);
	
	void setProj(Mat4 proj);

	void setImage(BufferedImage img);
	
	boolean areVecProper(Vec3D a, Vec3D b);
}
