package ukol3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import transforms.Camera;
import transforms.Mat4OrthoRH;
import transforms.Mat4PerspRH;
import transforms.Mat4RotXYZ;
import transforms.Mat4Scale;
import transforms.Mat4Transl;
import transforms.Vec3D;

 
public class Canvas {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;

	private JFrame frame;
	private static JPanel panel1,panel2;
	
	private Camera camera;
	private static BufferedImage img; 
	private List<Solid> solid = new ArrayList<>();
	private WireFrameRenderer renderer;	
	private double sizeX = 1, sizeY = 1, sizeZ = 1;
	private double rotX, rotY, rotZ, moveX, moveY, moveZ;
	private int beginX, beginY;
	
	public Canvas(){
		frame = new JFrame(); 
		frame.setTitle("Projekt 3");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(225,HEIGHT));
		panel2.setLayout(null);
	
		frame.add(panel1,BorderLayout.CENTER);
		frame.add(panel2,BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		
		img = new BufferedImage(panel1.getWidth(),panel1.getHeight(), BufferedImage.TYPE_INT_RGB);
		camera = new Camera();
		
		solid.add(new Curve(0));
		solid.add(new Curve(1));
		solid.add(new Curve(2));
		
		solid.add(new XYZ());
		solid.add(new Cube());
		solid.add(new Pyramid());
	//	solid = cube;	
		renderer = new WireFrameRenderer(img);
		
		MouseAdapter ma = new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				super.mousePressed(e);
				beginX = e.getX();
				beginY = e.getY();

			}
			@Override
			public void mouseReleased(MouseEvent e){
				super.mouseReleased(e);
			
			}

		};
		
		MouseMotionListener mb = new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {

			//	int finalX = e.getX() - beginX;
			//	int finalY = e.getY() - beginY;
		/*		if(finalX < -5)
					camera=camera.addAzimuth(-0.001);
				if(finalX > 5)
					camera=camera.addAzimuth(+0.001);
				if(finalY < -5)
					camera=camera.addZenith(-0.001);
				if(finalY > 5)
					camera=camera.addZenith(+0.001);
			*/
				camera=camera.addAzimuth((Math.PI / 1000) * (beginX - e.getX()));
				camera=camera.addZenith((Math.PI / 1000) * (beginY - e.getY()));
				beginX = e.getX();
				beginY = e.getY();
			/*double azimuth = camera.getAzimuth() + (e.getX() - beginX)/100;
				double zenith = camera.getZenith() + (e.getY() - beginY)/100;
				
				camera=camera.withAzimuth(azimuth);
				camera=camera.withZenith(zenith);
				*/
				
				
				
				renderFrame(solid);
			}
			


			@Override
			public void mouseMoved(MouseEvent e) {

			}

		};

		KeyAdapter keyAdapter = new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){

					case KeyEvent.VK_UP: camera=camera.forward(0.1); break;
					case KeyEvent.VK_DOWN: camera=camera.backward(0.1); break;
					case KeyEvent.VK_LEFT: camera=camera.left(0.1); break;
					case KeyEvent.VK_RIGHT: camera=camera.right(0.1); break;
					case KeyEvent.VK_SPACE : camera=camera.withFirstPerson(!camera.getFirstPerson()); break;
				
					case KeyEvent.VK_A: moveX = moveX-0.1; break;
					case KeyEvent.VK_D: moveX = moveX+0.1; break;
					case KeyEvent.VK_W: moveY = moveY-0.1; break;
					case KeyEvent.VK_S: moveY = moveY+0.1; break;
					case KeyEvent.VK_Q: moveZ = moveZ-0.1; break;
					case KeyEvent.VK_E: moveZ = moveZ+0.1; break;
				
					case KeyEvent.VK_NUMPAD4: rotX = rotX+0.1; break;
					case KeyEvent.VK_NUMPAD6: rotX = rotX-0.1; break;
					case KeyEvent.VK_NUMPAD8: rotY = rotY+0.1; break;
					case KeyEvent.VK_NUMPAD2: rotY = rotY-0.1; break;
					case KeyEvent.VK_NUMPAD7: rotZ = rotZ+0.1; break;
					case KeyEvent.VK_NUMPAD9: rotZ = rotZ-0.1; break;
					
			
					
					
					//	case KeyEvent.VK_sADD: sizeX = sizeX+0.1; sizeY = sizeY+0.1; sizeZ = sizeZ+0.1; break;
				//	case 109: sizeX = sizeX-0.1; sizeY = sizeY-0.1; sizeZ = sizeZ-0.1; break;


					//perspektivni
					case KeyEvent.VK_Y: renderer.setProj(new Mat4PerspRH(1, 1, 1, 100)); break;
					//paralelni
					case KeyEvent.VK_X: renderer.setProj(new Mat4OrthoRH(8, 10.0 * HEIGHT / WIDTH, 1, 40)); break;
					
			//		case KeyEvent.VK_R: reset(); break;
				}
				renderFrame(solid);
			}
		};

		reset();
		frame.addKeyListener(keyAdapter);
		panel1.addMouseListener(ma);
		panel1.addMouseMotionListener(mb);
	}
	private void renderFrame(List<Solid> solid){
		if(solid == null) return;
		clear(0x2f2f2f);
		
		
		renderer.setModel(new Mat4Scale(sizeX, sizeY, sizeZ).mul(
				new Mat4RotXYZ(rotX, rotY, rotZ)).mul(
						new Mat4Transl(moveX, moveY, moveZ)));
		renderer.setView(camera.getViewMatrix());
		renderer.draw(solid);	

		present();
	}
	private void reset(){
		rotX = Math.PI / 2; rotY = 0; rotZ = Math.PI;
		sizeX = 1; sizeY = 1; sizeZ = 1;
		moveX = -5; moveY = -1.5; moveZ = -1.5;
		renderer.setProj(new Mat4PerspRH(1, 1, 1, 100));
		setCameraOptions();
		renderFrame(solid);
	}
	private void setCameraOptions(){
		//camera=camera.withPosition(new Vec3D(20, 20, 15));
	//	camera=camera.withZenith(-Math.atan(15.0 / (20.0 * Math.sqrt(2.0))));
	//	camera=camera.withAzimuth(5 * Math.PI / 4);
		camera = new Camera(new Vec3D(0,0,0), 0,0, Math.PI/2, true);
		
		camera=camera.withPosition(new Vec3D(5,0,0));
//	camera=camera.addAzimuth(2.80);
		//(new Vec3D(5, 3.5, 1.5));
		camera=camera.withZenith(Math.PI);
	//	camera=camera.withPosition(new Vec3D(8, 3.5, 1.5));
		//camera=camera.backward(5);
	}
	
	
	public static void present(){
		if(panel1.getGraphics() != null)
			panel1.getGraphics().drawImage(img, 0, 0, null);
	}

	public void clear(int color) {
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(color));
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
	
	public static void main(String[] args) {
		new Canvas();
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					SwingUtilities.invokeLater(() -> {
						Canvas.present();
					});
				});
			});
		});
	}
}