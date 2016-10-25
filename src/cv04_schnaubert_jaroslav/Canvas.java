package cv04_schnaubert_jaroslav;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class Canvas {
	private int x,y, rX, rY;
	private JFrame frame;
	private JPanel panel;
	private BufferedImage img; 
	private LineRenderer line;
	private Circle circle;  
	private List<Point> points = new ArrayList<Point>();
//	private boolean firstClick = true;
	private boolean circleMode = false;
	 
	public Canvas(int width, int height){
		frame = new JFrame(); 
		frame.setTitle("Projekt 1");
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		line = new LineRenderer(img);
	//	SeedFill seedfill = new SeedFill(img);
		circle = new Circle(img);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		MouseAdapter ma = new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				super.mousePressed(e);
				x=e.getX();
				y=e.getY();
				if(e.getButton()==MouseEvent.BUTTON1)
					if(circleMode==true)
						circleMode=false;
					if(points.size()==0)
						points.add(new Point(x,y));
					if(points.size()==0)
							points.add(new Point(x,y));
				if(e.getButton()==MouseEvent.BUTTON3)
					if(circleMode==false)
						circleMode=true;
		}
			@Override
			public void mouseReleased(MouseEvent e){
				super.mouseReleased(e);
				clear(0x2f2f2f);
				if(e.getButton()==MouseEvent.BUTTON1){
					points.add(new Point(e.getX(),e.getY()));
					drawPolygon();
					if(circleMode==true){
						circleMode=false;
					}
				}
				
				if(e.getButton()==MouseEvent.BUTTON3){
					points.clear();
					rX=e.getX();
					rY=e.getY();
					circle.drawCircle(x, y, rX, rY);
				//	circle.drawCircleSector(x, y, rX, rY, e.getX(), e.getY());
					if(circleMode==false){
						circleMode=true;
					}
				}

			/*	if(e.getButton()==MouseEvent.BUTTON3){
					seedfill.fill(e.getX(), e.getY(),0xff0000);
				}*/
				present();
				
			}
		};
		
		MouseMotionListener mb = new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				clear(0x2f2f2f);
				
				if(circleMode==false){
					drawPolygon();
					line.setColor(0xffffff);
					line.draw((int)(points.get(points.size()-1).getX()), (int)(points.get(points.size()-1).getY()), e.getX(),e.getY());
					line.draw((int)points.get(0).getX(), (int)points.get(0).getY(), e.getX(),e.getY());
				}
				
				if(circleMode==true){
					circle.drawCircle(x, y, e.getX(), e.getY());
				}
				present();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				//circle.drawCircleSector(x, y, rX, rY, e.getX(), e.getY());
				present();
			}

		};
		panel.addMouseListener(ma);
		panel.addMouseMotionListener(mb);
	}
	
	public void drawPolygon(){
		if(points.size()>1){
				line.setColor(0xffff00);
				int xA=0,yA=0,xB=0,yB=0;
				for(int i = 0; i<points.size()-1;i++){
					xA = (int) points.get(i).getX();
					yA = (int) points.get(i).getY();
					xB = (int) points.get(i+1).getX();
					yB = (int) points.get(i+1).getY();
					line.draw(xA, yA, xB, yB);
				}
				xA = (int) points.get(0).getX();
				yA = (int) points.get(0).getY();
				xB = (int) points.get(points.size()-1).getX();
				yB = (int) points.get(points.size()-1).getY();
				line.draw(xA, yA, xB, yB);
		}
	}
	
	public void present(){
		if(panel.getGraphics() != null)
			panel.getGraphics().drawImage(img, 0, 0, null);
	}
	public void start(){
		present();
	}

	public void clear(int color) {
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(color));
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
	
	public static void main(String[] args) {
		Canvas canvas = new Canvas(1000,750);
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					SwingUtilities.invokeLater(() -> {
						canvas.start();
					});
				});
			});
		});
	}
	


}