package ukol3;

import java.awt.image.BufferedImage;
import java.util.List;

import transforms.Mat4;

public interface Renderable {
	
	void draw(Solid solid);
	
	void draw(List<Solid> list);
	
	void setModel(Mat4 model);
	
	void setView(Mat4 view);
	
	void setProj(Mat4 proj);

	void setImage(BufferedImage img);
}
