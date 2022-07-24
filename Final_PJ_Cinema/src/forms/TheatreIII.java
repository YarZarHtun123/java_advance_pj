package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TheatreIII {

	public JFrame frameTheatreIII;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TheatreIII window = new TheatreIII();
					window.frameTheatreIII.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TheatreIII() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTheatreIII = new JFrame();
		frameTheatreIII.getContentPane().setBackground(new Color(176, 196, 222));
		frameTheatreIII.setTitle("TheatreIII");
		frameTheatreIII.setBounds(100, 100, 707, 559);
		frameTheatreIII.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTheatreIII.getContentPane().setLayout(null);

		JButton btnA01 = new JButton("A01");
		btnA01.setBackground(Color.ORANGE);
		btnA01.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA01.setBounds(22, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA01);

		JButton btnA02 = new JButton("A02");
		btnA02.setBackground(Color.ORANGE);
		btnA02.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA02.setBounds(92, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA02);

		JButton btnA03 = new JButton("A03");
		btnA03.setBackground(Color.ORANGE);
		btnA03.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA03.setBounds(165, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA03);

		JButton btnA04 = new JButton("A04");
		btnA04.setBackground(Color.ORANGE);
		btnA04.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA04.setBounds(235, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA04);

		JButton btnA05 = new JButton("A05");
		btnA05.setBackground(Color.ORANGE);
		btnA05.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA05.setBounds(399, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA05);

		JButton btnA06 = new JButton("A06");
		btnA06.setBackground(Color.ORANGE);
		btnA06.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA06.setBounds(469, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA06);

		JButton btnA07 = new JButton("A07");
		btnA07.setBackground(Color.ORANGE);
		btnA07.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA07.setBounds(539, 272, 64, 21);
		frameTheatreIII.getContentPane().add(btnA07);

		JButton btnA08 = new JButton("A08");
		btnA08.setBackground(Color.ORANGE);
		btnA08.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnA08.setBounds(605, 272, 65, 21);
		frameTheatreIII.getContentPane().add(btnA08);

		JButton btnB01 = new JButton("B01");
		btnB01.setBackground(Color.GREEN);
		btnB01.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB01.setBounds(22, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB01);

		JButton btnB02 = new JButton("B02");
		btnB02.setBackground(Color.GREEN);
		btnB02.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB02.setBounds(92, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB02);

		JButton btnB03 = new JButton("B03");
		btnB03.setBackground(Color.GREEN);
		btnB03.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB03.setBounds(165, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB03);

		JButton btnB04 = new JButton("B04");
		btnB04.setBackground(Color.GREEN);
		btnB04.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB04.setBounds(235, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB04);

		JButton btnB05 = new JButton("B05");
		btnB05.setBackground(Color.GREEN);
		btnB05.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB05.setBounds(399, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB05);

		JButton btnB06 = new JButton("B06");
		btnB06.setBackground(Color.GREEN);
		btnB06.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB06.setBounds(469, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB06);

		JButton btnB07 = new JButton("B07");
		btnB07.setBackground(Color.GREEN);
		btnB07.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB07.setBounds(542, 315, 61, 21);
		frameTheatreIII.getContentPane().add(btnB07);

		JButton btnB08 = new JButton("B08");
		btnB08.setBackground(Color.GREEN);
		btnB08.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB08.setBounds(605, 315, 65, 21);
		frameTheatreIII.getContentPane().add(btnB08);

		JButton btnC01 = new JButton("C01");
		btnC01.setBackground(Color.CYAN);
		btnC01.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC01.setBounds(22, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC01);

		JButton btnC02 = new JButton("C02");
		btnC02.setBackground(Color.CYAN);
		btnC02.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC02.setBounds(92, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC02);

		JButton btnC03 = new JButton("C03");
		btnC03.setBackground(Color.CYAN);
		btnC03.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC03.setBounds(165, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC03);

		JButton btnC04 = new JButton("C04");
		btnC04.setBackground(Color.CYAN);
		btnC04.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC04.setBounds(235, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC04);

		JButton btnC05 = new JButton("C05");
		btnC05.setBackground(Color.CYAN);
		btnC05.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC05.setBounds(399, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC05);

		JButton btnC06 = new JButton("C06");
		btnC06.setBackground(Color.CYAN);
		btnC06.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC06.setBounds(469, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC06);

		JButton btnC07 = new JButton("C07");
		btnC07.setBackground(Color.CYAN);
		btnC07.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC07.setBounds(542, 362, 61, 21);
		frameTheatreIII.getContentPane().add(btnC07);

		JButton btnC08 = new JButton("C08");
		btnC08.setBackground(Color.CYAN);
		btnC08.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC08.setBounds(605, 362, 65, 21);
		frameTheatreIII.getContentPane().add(btnC08);

		JButton btnD01 = new JButton("D01");
		btnD01.setBackground(new Color(255, 0, 255));
		btnD01.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD01.setBounds(22, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD01);

		JButton btnD02 = new JButton("D02");
		btnD02.setBackground(new Color(255, 0, 255));
		btnD02.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD02.setBounds(92, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD02);

		JButton btnD03 = new JButton("D03");
		btnD03.setBackground(new Color(255, 0, 255));
		btnD03.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD03.setBounds(165, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD03);

		JButton btnD04 = new JButton("D04");
		btnD04.setBackground(new Color(255, 0, 255));
		btnD04.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD04.setBounds(235, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD04);

		JButton btnD05 = new JButton("D05");
		btnD05.setBackground(new Color(255, 0, 255));
		btnD05.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD05.setBounds(399, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD05);

		JButton btnD06 = new JButton("D06");
		btnD06.setBackground(new Color(255, 0, 255));
		btnD06.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD06.setBounds(469, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD06);

		JButton btnD07 = new JButton("D07");
		btnD07.setBackground(new Color(255, 0, 255));
		btnD07.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD07.setBounds(542, 402, 61, 21);
		frameTheatreIII.getContentPane().add(btnD07);

		JButton btnD08 = new JButton("D08");
		btnD08.setBackground(new Color(255, 0, 255));
		btnD08.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD08.setBounds(605, 402, 65, 21);
		frameTheatreIII.getContentPane().add(btnD08);

		JButton btnE01 = new JButton("E01");
		btnE01.setBackground(new Color(0, 153, 153));
		btnE01.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnE01.setBounds(42, 444, 95, 21);
		frameTheatreIII.getContentPane().add(btnE01);

		JButton btnE02 = new JButton("E02");
		btnE02.setBackground(new Color(0, 153, 153));
		btnE02.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnE02.setBounds(185, 444, 95, 21);
		frameTheatreIII.getContentPane().add(btnE02);

		JButton btnE03 = new JButton("E03");
		btnE03.setBackground(new Color(0, 153, 153));
		btnE03.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnE03.setBounds(422, 444, 95, 21);
		frameTheatreIII.getContentPane().add(btnE03);

		JButton btnE04 = new JButton("E04");
		btnE04.setBackground(new Color(0, 153, 153));
		btnE04.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnE04.setBounds(564, 444, 89, 21);
		frameTheatreIII.getContentPane().add(btnE04);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setForeground(new Color(240, 255, 255));
		panel.setBounds(22, 10, 648, 193);
		frameTheatreIII.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(25, 23, 595, 146);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome To Our Cinema");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(180, 58, 296, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblNewLabel);

		JLabel lblTheatreI3 = new JLabel("Theatre III");
		lblTheatreI3.setForeground(Color.RED);
		lblTheatreI3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTheatreI3.setBounds(233, 29, 145, 19);
		panel_1.add(lblTheatreI3);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(242, 489, 91, 21);
		frameTheatreIII.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(351, 489, 91, 21);
		frameTheatreIII.getContentPane().add(btnCancel);
	}
}
