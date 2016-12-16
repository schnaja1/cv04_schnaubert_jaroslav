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
	private static JPanel panel1;
	private static LabelPanel panel2;
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
		
	/*	panel2 = new JPanel();*/
		panel2 = new LabelPanel();

		panel2.setPreferredSize(new Dimension(200,HEIGHT));
		panel2.setLayout(null);
	
		frame.add(panel1,BorderLayout.CENTER);
		frame.add(panel2,BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		
		img = new BufferedImage(panel1.getWidth(),panel1.getHeight(), BufferedImage.TYPE_INT_RGB);
		camera = new Camera();
		
		solid.add(new Curve(0,Color.CYAN));
		solid.add(new Curve(1,Color.PINK));
		solid.add(new Curve(2,Color.MAGENTA));
		
		solid.add(new XYZ());
		solid.add(new Cube(Color.WHITE));
		solid.add(new Pyramid(Color.YELLOW));
		
		renderer = new WireFrameRenderer(img);
		resetView();
		
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
				
				renderScene(solid);
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
							rotX += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD3: 
							rotX -= 0.1; 
							break;
					case KeyEvent.VK_NUMPAD4: 
							rotY += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD6: 
							rotY -= 0.1; 
							break;
					case KeyEvent.VK_NUMPAD7:
							rotZ += 0.1; 
							break;
					case KeyEvent.VK_NUMPAD9: 
							rotZ -= 0.1; 
							break;
					case KeyEvent.VK_P: 
							renderer.setProj(new Mat4PerspRH(1, 1, 1, 100)); 
							break;
					case KeyEvent.VK_O: 
							renderer.setProj(new Mat4OrthoRH(8, 10.0 * HEIGHT / WIDTH, 1, 40)); 
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
				renderScene(solid);
			}
		};

		
		frame.addKeyListener(keyAdapter);
		panel1.addMouseListener(ma);
		panel1.addMouseMotionListener(mb);
	}
	private void renderScene(List<Solid> solid){
		if(solid == null) return;
		clear(0x2f2f2f);
		
		
		renderer.setModel(new Mat4Scale(sizeX, sizeY, sizeZ).mul(
				new Mat4RotXYZ(rotX, rotY, rotZ)).mul(
						new Mat4Transl(moveX, moveY, moveZ)));
		renderer.setView(camera.getViewMatrix()); 
		renderer.draw(solid);	

		present();
	}
	private void resetView(){
		rotX = Math.PI /2; rotY = 0; rotZ = Math.PI;
		sizeX = 3; sizeY = 3; sizeZ = 3;
		moveX = -5; moveY = -1.5; moveZ = -1.5;
		renderer.setProj(new Mat4PerspRH(1, 1, 1, 100));
		resetCamera();
		renderScene(solid);
	}
	private void resetCamera(){
		camera = new Camera(new Vec3D(0,1,2), Math.PI/32,Math.PI/32,-Math.PI/2, true);
		camera=camera.backward(15);		
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