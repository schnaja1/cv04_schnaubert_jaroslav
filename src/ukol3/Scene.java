package ukol3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import transforms.Camera;
import transforms.Mat4OrthoRH;
import transforms.Mat4PerspRH;
import transforms.Mat4RotXYZ;
import transforms.Mat4Scale;
import transforms.Mat4Transl;
import transforms.Vec3D;
/*
 * Panel, ve kterém jsou kresleny solidy
 */
public class Scene extends DrawPanel{
	/**
	 * Promìnná pøidaná kvùli vyskakujícímu warningu
	 */
	private static final long serialVersionUID = 1L;
	
	private Camera camera;
	private List<Solid> solids;
	private WireFrameRenderer renderer;	
	private double sizeX, sizeY, sizeZ, rotationX, rotationY, rotationZ, moveX, moveY, moveZ;
	private int beginX, beginY;
	

	public Scene(int width, int height){
		super(width, height);
		solids = new ArrayList<>();
		this.setPreferredSize(new Dimension(width, height));
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		camera = new Camera();

		solids.add(new Curve(0,Color.CYAN));
		solids.add(new Curve(1,Color.PINK));
		solids.add(new Curve(2,Color.MAGENTA));	
		solids.add(new XYZ());
		solids.add(new Cube(Color.WHITE));
		solids.add(new Pyramid(Color.YELLOW));
		
		renderer = new WireFrameRenderer(img);
		
		MouseAdapter ma = new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				beginX = e.getX();
				beginY = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e){
			}
		};
		
		MouseMotionListener mb = new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				camera=camera.addAzimuth((beginX - e.getX())*(Math.PI / 1000));
				camera=camera.addZenith((beginY - e.getY())*(Math.PI / 1000));
				
				beginX = e.getX();
				beginY = e.getY();
				
				renderScene(solids);
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		};

		KeyAdapter keyAdapter = new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){

					case KeyEvent.VK_UP: 
							camera=camera.forward(0.1); 
							break;
					case KeyEvent.VK_DOWN: 
							camera=camera.backward(0.1); 
							break;
					case KeyEvent.VK_LEFT: 
							camera=camera.left(0.1); 
							break;
					case KeyEvent.VK_RIGHT: 
							camera=camera.right(0.1); 
							break;
					case KeyEvent.VK_SPACE : 
							camera=camera.withFirstPerson(!camera.getFirstPerson()); 
							break;
					case KeyEvent.VK_A: 
							moveX += 0.1; 
							break;
					case KeyEvent.VK_D: 
							moveX -= 0.1; 
							break;
					case KeyEvent.VK_W: 
							moveY += 0.1; 
							break;
					case KeyEvent.VK_S: 
							moveY -=0.1; 
							break;
					case KeyEvent.VK_Q: 
							moveZ -=0.1; 
							break;
					case KeyEvent.VK_E:
							moveZ += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD1: 
							rotationX += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD3: 
							rotationX -= 0.1; 
							break;
					case KeyEvent.VK_NUMPAD4: 
							rotationY += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD6: 
							rotationY -= 0.1; 
							break;
					case KeyEvent.VK_NUMPAD7:
							rotationZ += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD9: 
							rotationZ -= 0.1; 
							break;
					case KeyEvent.VK_P: 
							renderer.setProj(new Mat4PerspRH(1, 1, 1, 100)); 
							break;
					case KeyEvent.VK_O: 
							renderer.setProj(new Mat4OrthoRH(8, 10.0 * getHeight() / getWidth(), 1, 40)); 
							break;
					case KeyEvent.VK_R: 
							resetView(); 
							break;
					case KeyEvent.VK_ADD:
							sizeX += 0.1; 
							sizeY += 0.1; 
							sizeZ += 0.1; 
							break;
					case KeyEvent.VK_SUBTRACT:
							sizeX -= 0.1;
							sizeY -= 0.1; 
							sizeZ-=0.1; 
							break;
				}
				renderScene(solids);
			}
		};

		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(keyAdapter);
		addMouseListener(ma);
		addMouseMotionListener(mb);
		
	}
	
	public void renderScene(List<Solid> solid){
		if(solid.isEmpty()) 
			return;
		clear(this,0x2f2f2f);
		
		renderer.setModel(new Mat4Scale(sizeX, sizeY, sizeZ).mul(
				new Mat4RotXYZ(rotationX, rotationY, rotationZ)).mul(
						new Mat4Transl(moveX, moveY, moveZ)));
		renderer.setView(camera.getViewMatrix()); 
		renderer.draw(solid);	
		DrawPanel.present(this);
		System.out.println(camera.getPosition());
		System.out.println(camera.getAzimuth() + " " + camera.getZenith());
		System.out.println(rotationX  + " " + rotationY  + " " + rotationZ);
		
	}
	
	public void resetView(){
		//rotationX = Math.PI /2; rotationY = 0; rotationZ = Math.PI;
		rotationX = -5; rotationY = -3.3; rotationZ = 2;
		sizeX = 3; sizeY = 3; sizeZ = 3;
		moveX = 0; moveY = 0; moveZ = 0;
		renderer.setProj(new Mat4PerspRH(1, 1, 1, 100));
		resetCamera();
		renderScene(solids);
	}
	
	public void resetCamera(){
		camera = camera.withPosition(new Vec3D(10.2,-2.6,-9.3));
		camera = camera.withAzimuth(-3.3);
		camera = camera.withZenith(0.67);
	}
}
