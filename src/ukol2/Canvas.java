package ukol2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import transforms.Point2D;

public class Canvas {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;

	private JFrame frame;
	private static JPanel panel1,panel2;
	private static BufferedImage img; 
	
	private LineRenderer line;
	private PolyLine polyLine;
	
	private int angle, space;

	public Canvas(){
		frame = new JFrame(); 
		frame.setTitle("Projekt 2");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		line = new LineRenderer(img);
		polyLine = new PolyLine(line);
		SeedFill seedFill = new SeedFill(img);
		ScanLineFiller scanLine =  new ScanLineFiller(img, 0x00ffff);

		JRadioButton seedJButton = new JRadioButton();
		seedJButton.setSelected(true);
		seedJButton.setBounds(20,20, 20, 20);
		
		JLabel seedJLabel = new JLabel();
		seedJLabel.setBounds(50,20, 150, 20);
		seedJLabel.setText("Vyplnit pomocí SeedFill");
		
		JCheckBox usePattern = new JCheckBox();
		usePattern.setBounds(30,40,20,20);
		usePattern.setSelected(false);
		
		JLabel usePatternJLabel = new JLabel();
		usePatternJLabel.setBounds(55 ,40, 150, 20);
		usePatternJLabel.setText("Pouít vzor pro vyplnìní");
		
		JRadioButton scanJButton = new JRadioButton();
		scanJButton.setSelected(false);
		scanJButton.setBounds(20, 70, 25, 25);
		
		JLabel scanJLabel = new JLabel();
		scanJLabel.setBounds(50,70, 200, 25);
		scanJLabel.setText("Vyplnit pomocí ScanLine");
		
		ButtonGroup group = new ButtonGroup();
		group.add(seedJButton);
		group.add(scanJButton);
		
		JCheckBox linesInPolygon = new JCheckBox();
		linesInPolygon.setEnabled(false);
		linesInPolygon.setBounds(30,90,25,25);
		linesInPolygon.setSelected(false);
		
		JLabel linesInPolygonJLabel = new JLabel();
		linesInPolygonJLabel.setText("Vyšrafovat");
		linesInPolygonJLabel.setBounds(60,90,100,25);
		
		SpinnerModel spaceSpinnerModel = new SpinnerNumberModel(5,5,20,1);
		JSpinner spaceJSpinner = new JSpinner(spaceSpinnerModel);
		spaceJSpinner.setBounds(30, 120, 50, 25);
		spaceJSpinner.setEnabled(false);
		
		JLabel spaceBetweenLinesJLabel = new JLabel();
		spaceBetweenLinesJLabel.setBounds(90,120, 200, 25);
		spaceBetweenLinesJLabel.setText("Rozdíl mezi šrafami");
		
		SpinnerModel angleSpinnerModel = new SpinnerNumberModel(5,0,360,1);
		JSpinner angleJSpinner = new JSpinner(angleSpinnerModel);
		angleJSpinner.setBounds(30, 150, 50, 25);
		angleJSpinner.setEnabled(false);

		JLabel angleOfLinesJLabel = new JLabel();
		angleOfLinesJLabel.setBounds(90,150, 200, 25);
		angleOfLinesJLabel.setText("Úhel šraf");

		JButton clearCanvas = new JButton();
		clearCanvas.setText("Clear Canvas");
		clearCanvas.setBounds(25, 400, 175, 50);
		

		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(225,HEIGHT));
		panel2.setLayout(null);
		panel2.add(seedJButton);
		panel2.add(seedJLabel);
		panel2.add(usePattern);
		panel2.add(usePatternJLabel);
		panel2.add(scanJButton);
		panel2.add(scanJLabel);
		panel2.add(linesInPolygon);
		panel2.add(linesInPolygonJLabel);
		panel2.add(spaceJSpinner);
		panel2.add(spaceBetweenLinesJLabel);
		panel2.add(angleJSpinner);
		panel2.add(angleOfLinesJLabel);
		panel2.add(clearCanvas);

		frame.add(panel1,BorderLayout.CENTER);
		frame.add(panel2,BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		
		angle = (int) angleJSpinner.getValue();
		space = (int) spaceJSpinner.getValue();
		

	    ActionListener listenerOfRadioButtons = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
				if(seedJButton.isSelected()){
					linesInPolygon.setEnabled(false);
					linesInPolygon.setSelected(false);
					usePattern.setEnabled(true);
				}
				else if(scanJButton.isSelected()){
						linesInPolygon.setEnabled(true);
						usePattern.setEnabled(false);
						usePattern.setSelected(false);
				}
	        }
	      };
		

		seedJButton.addActionListener(listenerOfRadioButtons);
		scanJButton.addActionListener(listenerOfRadioButtons);
	      
		clearCanvas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				polyLine.clear();
				present();
			}
		});
		
		linesInPolygon.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(linesInPolygon.isSelected()){
					spaceJSpinner.setEnabled(true);
					angleJSpinner.setEnabled(true);
				}
				else{
					spaceJSpinner.setEnabled(false);
					angleJSpinner.setEnabled(false);
				}
			}
		});
		
		MouseAdapter ma = new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				super.mousePressed(e);
				if(e.getButton()==MouseEvent.BUTTON1)
					if(polyLine.size()==0)
						polyLine.add(e.getX(),e.getY());
				if(e.getButton()==MouseEvent.BUTTON3){
					clear(0x2f2f2f);
					if(seedJButton.isSelected()){
						polyLine.draw();
						seedFill.fill(e.getX(), e.getY(), usePattern.isSelected());
					}
					else{
						if(linesInPolygon.isSelected()){
							angle = (int) angleJSpinner.getValue();
							space = (int) spaceJSpinner.getValue();
						}
						else {
							angle = 0;
							space = 1;
						}
						scanLine.fill(polyLine,angle,space);
					}
					polyLine.draw();
					present();
				}

		}
			@Override
			public void mouseReleased(MouseEvent e){
				super.mouseReleased(e);
				clear(0x2f2f2f);
				if(e.getButton()==MouseEvent.BUTTON1){
					polyLine.add(e.getX(),e.getY());
					polyLine.draw();
					present();
				}
			}
		};
		
		MouseMotionListener mb = new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {	
				if(SwingUtilities.isLeftMouseButton(e)){
					clear(0x2f2f2f);
					polyLine.draw();
					line.setColor(0xffffff);
					line.draw(polyLine.get(polyLine.size()-1),new Point2D(e.getX(),e.getY()));
					line.draw(polyLine.get(0),new Point2D(e.getX(),e.getY()));
					present();
				}
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