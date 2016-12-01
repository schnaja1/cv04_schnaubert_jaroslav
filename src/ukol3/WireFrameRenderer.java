package ukol3;

import java.awt.image.BufferedImage;
import java.util.List;

import transforms.Mat4;

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
