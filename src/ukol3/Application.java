package ukol3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

 
public class Application{
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 500;

	private JFrame frame;
	private static Scene panel1;
	private LabelPanel panel2;


	public Application(){
		frame = new JFrame(); 
		frame.setTitle("Projekt 3");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		panel1 = new Scene(WIDTH-200, HEIGHT);
		panel2 = new LabelPanel();
		panel2.setPreferredSize(new Dimension(WIDTH-1000,HEIGHT));
		panel2.setLayout(null);

		frame.add(panel1,BorderLayout.CENTER);
		frame.add(panel2,BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);	
	}

	public static void main(String[] args) {
		new Application();
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					SwingUtilities.invokeLater(() -> {
						panel1.resetView();
					});
				});
			});
		});
	}
}