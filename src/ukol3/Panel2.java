package ukol3;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Tato t��da obsahuje JLabely, kter� popisuj� ovl�d�n� programu.
 * Je mo�n� je vid�t v prav� ��sti aplikace. n
 */

public class Panel2 extends JPanel{
	
	/**
	 * Prom�nn� p�idan� kv�li vyskakuj�c�mu warningu
	 */
	private static final long serialVersionUID = 1L;

	public Panel2(){
		super();

		Font font = new Font("Arial", Font.PLAIN, 12);
		JLabel controllsJLabel = new JLabel("Ovl�d�n�:");
		controllsJLabel.setBounds(20,20, 70, 14);
		controllsJLabel.setFont(new Font("Arial", Font.BOLD, 14));
		add(controllsJLabel);
		
		JLabel cameraControllJLabel = new JLabel ("Pohyb kamerou: �ipky");
		cameraControllJLabel.setBounds(20, 40, 150, 14);
		cameraControllJLabel.setFont(font);
		add(cameraControllJLabel);
		
		JLabel moveOnXJLabel = new JLabel("Pohyb po ose x : A a D");
		moveOnXJLabel.setBounds(20, 60, 150, 14);
		moveOnXJLabel.setFont(font);
		add(moveOnXJLabel);
		
		JLabel moveOnYJLabel = new JLabel("Pohyb po ose y : W a S");
		moveOnYJLabel.setBounds(20, 80, 150, 14);
		moveOnYJLabel.setFont(font);
		add(moveOnYJLabel);
		
		JLabel moveOnZJLabel = new JLabel("Pohyb po ose z : Q a E");
		moveOnZJLabel.setBounds(20, 100, 150, 14);
		moveOnZJLabel.setFont(font);
		add(moveOnZJLabel);
		
		JLabel rotationXJLabel = new JLabel("Rotace podle osy x: 1 a 3");
		rotationXJLabel.setBounds(20, 120, 150, 14);
		rotationXJLabel.setFont(font);
		add(rotationXJLabel);

		JLabel rotationYJLabel = new JLabel("Rotace podle osy y: 4 a 6");
		rotationYJLabel.setBounds(20, 140, 150, 14);
		rotationYJLabel.setFont(font);
		add(rotationYJLabel);
		
		JLabel rotationZJLabel = new JLabel("Rotace podle osy z: 7 a 9");
		rotationZJLabel.setBounds(20, 160, 150, 14);
		rotationZJLabel.setFont(font);
		add(rotationZJLabel);
		
		JLabel sizePlusJLabel = new JLabel("Zv�t�en� objektu:  +");
		sizePlusJLabel.setBounds(20, 180, 150, 14);
		sizePlusJLabel.setFont(font);
		add(sizePlusJLabel);
		
		JLabel sizeMinusJLabel = new JLabel("Zmen�en� objektu:  -");
		sizeMinusJLabel.setBounds(20, 200, 150, 14);
		sizeMinusJLabel.setFont(font);
		add(sizeMinusJLabel);
		
		JLabel firstPersonJLabel = new JLabel("Pohled prvn� osoby - mezern�k");
		firstPersonJLabel.setBounds(20, 220, 180, 14);
		firstPersonJLabel.setFont(font);
		add(firstPersonJLabel);
		
		JLabel projPJLabel = new JLabel("Perspektivn� projekce - P");
		projPJLabel.setBounds(20, 240, 180, 14);
		projPJLabel.setFont(font);
		add(projPJLabel);

		JLabel projOJLabel = new JLabel("Paraleln� projekce - O");
		projOJLabel.setBounds(20, 260, 180, 14);
		projOJLabel.setFont(font);
		add(projOJLabel);
	}
}
