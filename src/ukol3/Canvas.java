package ukol3;

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

import ukol2.LineRenderer;
import ukol2.PolyLine;
 
public class Canvas {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;

	private JFrame frame;
	private static JPanel panel1;
	private static BufferedImage img; 
	
	private LineRenderer line;

	public Canvas(){
		frame = new JFrame(); 
		frame.setTitle("Projekt 1");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		line = new LineRenderer(img);
		frame.add(panel1);
		frame.pack();
		frame.setVisible(true);
		MouseAdapter ma = new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				super.mousePressed(e);
			

		}
			@Override
			public void mouseReleased(MouseEvent e){
				super.mouseReleased(e);
			}

		};
		
		MouseMotionListener mb = new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			
			}

		};
		
		panel1.addMouseListener(ma);
		panel1.addMouseMotionListener(mb);
	}
	

	
	public static void present(){
		if(panel1.getGraphics() != null)
			panel1.getGraphics().drawImage(img, 0, 0, null);
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