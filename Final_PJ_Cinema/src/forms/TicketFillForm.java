package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class TicketFillForm {

	private JFrame frame;
	private JTextField txtTicketNo;
	private JTextField txtTheatre;
	private JTextField txtSDate;
	private JTextField txtEndDate;
	private JTextField textField_1;
	private JTextField txtEndTime;
	private JTextField txtSeat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketFillForm window = new TicketFillForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicketFillForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ticket No.");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel.setBounds(52, 61, 71, 20);
		frame.getContentPane().add(lblNewLabel);
		
		txtTicketNo = new JTextField();
		txtTicketNo.setBounds(168, 62, 105, 19);
		frame.getContentPane().add(txtTicketNo);
		txtTicketNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date :");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel_1.setBounds(341, 10, 50, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lbldate = new JLabel("");
		lbldate.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lbldate.setBounds(389, 10, 107, 13);
		frame.getContentPane().add(lbldate);
		
		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblMovie.setBounds(52, 91, 71, 20);
		frame.getContentPane().add(lblMovie);
		
		JComboBox cboMovie = new JComboBox();
		cboMovie.setBounds(168, 91, 105, 21);
		frame.getContentPane().add(cboMovie);
		
		txtTheatre = new JTextField();
		txtTheatre.setEnabled(false);
		txtTheatre.setColumns(10);
		txtTheatre.setBounds(168, 227, 105, 19);
		frame.getContentPane().add(txtTheatre);
		
		JLabel lblTheatre = new JLabel("Theatre");
		lblTheatre.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblTheatre.setBounds(52, 226, 71, 20);
		frame.getContentPane().add(lblTheatre);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblStartDate.setBounds(51, 273, 71, 20);
		frame.getContentPane().add(lblStartDate);
		
		txtSDate = new JTextField();
		txtSDate.setEnabled(false);
		txtSDate.setColumns(10);
		txtSDate.setBounds(134, 274, 105, 19);
		frame.getContentPane().add(txtSDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblEndDate.setBounds(258, 273, 71, 20);
		frame.getContentPane().add(lblEndDate);
		
		txtEndDate = new JTextField();
		txtEndDate.setEnabled(false);
		txtEndDate.setColumns(10);
		txtEndDate.setBounds(341, 274, 105, 19);
		frame.getContentPane().add(txtEndDate);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblStartTime.setBounds(51, 303, 71, 20);
		frame.getContentPane().add(lblStartTime);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(134, 304, 105, 19);
		frame.getContentPane().add(textField_1);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblEndTime.setBounds(258, 303, 71, 20);
		frame.getContentPane().add(lblEndTime);
		
		txtEndTime = new JTextField();
		txtEndTime.setEnabled(false);
		txtEndTime.setColumns(10);
		txtEndTime.setBounds(341, 304, 105, 19);
		frame.getContentPane().add(txtEndTime);
		
		JLabel lblSeatNo = new JLabel("Seat No.");
		lblSeatNo.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblSeatNo.setBounds(299, 226, 71, 20);
		frame.getContentPane().add(lblSeatNo);
		
		txtSeat = new JTextField();
		txtSeat.setEnabled(false);
		txtSeat.setColumns(10);
		txtSeat.setBounds(371, 227, 105, 19);
		frame.getContentPane().add(txtSeat);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(39, 257, 437, 91);
		frame.getContentPane().add(panel);
		
		JLabel lblMovieDescription = new JLabel("Movie Description");
		lblMovieDescription.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblMovieDescription.setBounds(52, 121, 105, 20);
		frame.getContentPane().add(lblMovieDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setBounds(168, 122, 308, 95);
		frame.getContentPane().add(textArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(327, 368, 64, 21);
		frame.getContentPane().add(btnExit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(406, 368, 64, 21);
		frame.getContentPane().add(btnSave);
	}
}
