package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import config.DBConfig;
import entities.Employee;
import entities.Theatre;
import services.TheatreService;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TheatreForm {

	public JFrame frameTheatreForm;
	private JTextField txtTheatreName;
	private JTable tblTheatre;
	private TheatreService theatreService;
	private final DBConfig dbConfig = new DBConfig();
	private JTextField txtSeat;
	private DefaultTableModel dtm = new DefaultTableModel();
	private Theatre theatre;
	private List<Theatre> theatreList = new ArrayList<>();
	private List<Theatre> filterTheatreList = new ArrayList();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TheatreForm window = new TheatreForm();
					window.frameTheatreForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public TheatreForm() {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllTheatre(Optional.empty());
	}

	private void initializeDependency() {
		this.theatreService = new TheatreService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Theatre Name");
		dtm.addColumn("Total Seat");
		this.tblTheatre.setModel(dtm);
	}

	private void loadAllTheatre(Optional<List<Theatre>> optionalTheatre) {

		this.dtm = (DefaultTableModel) this.tblTheatre.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.theatreList = this.theatreService.findAllTheatre();

		this.filterTheatreList = optionalTheatre.orElseGet(() -> this.theatreList).stream()
				.collect(Collectors.toList());

		filterTheatreList.forEach(e -> {
			Object[] row = new Object[3];
			row[0] = e.getTheatre_id();
			row[1] = e.getTheatre_name();
			row[2] = e.getTotal_seat();
			dtm.addRow(row);
		});

		this.tblTheatre.setModel(dtm);
	}

	private void resetFormData() {
		txtTheatreName.setText("");
		txtSeat.setText("");

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTheatreForm = new JFrame();
		frameTheatreForm.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frameTheatreForm.setTitle("Theatre Form");
		frameTheatreForm.setBounds(100, 100, 432, 377);
		frameTheatreForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTheatreForm.getContentPane().setLayout(null);

		JLabel lblTheatre = new JLabel("Theatre Name");
		lblTheatre.setHorizontalAlignment(SwingConstants.LEFT);
		lblTheatre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTheatre.setBounds(26, 26, 121, 29);
		frameTheatreForm.getContentPane().add(lblTheatre);

		txtTheatreName = new JTextField();
		txtTheatreName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTheatreName.setColumns(10);
		txtTheatreName.setBounds(147, 26, 155, 29);
		frameTheatreForm.getContentPane().add(txtTheatreName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 143, 381, 153);
		frameTheatreForm.getContentPane().add(scrollPane);

		JLabel lblSeat = new JLabel("Total Seat");
		lblSeat.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeat.setBounds(26, 65, 121, 29);
		frameTheatreForm.getContentPane().add(lblSeat);

		txtSeat = new JTextField();
		txtSeat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSeat.setColumns(10);
		txtSeat.setBounds(147, 65, 97, 29);
		frameTheatreForm.getContentPane().add(txtSeat);

		tblTheatre = new JTable();
		tblTheatre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblTheatre);

		JButton btnSave = new JButton("Create");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != theatre && theatre.getTheatre_id() != 0) {
					theatre.setTheatre_name(txtTheatreName.getText());
					theatre.setTotal_seat(txtSeat.getText());

					if (!theatre.getTheatre_name().isEmpty() && theatre.getTotal_seat() != null) {

						theatreService.updateTheatre(String.valueOf(theatre.getTheatre_id()), theatre);
						JOptionPane.showMessageDialog(null, "Update Successfully!!!");
						resetFormData();
						loadAllTheatre(Optional.empty());
						theatre = null;
						btnSave.setText("Create");
					}
				} else {
					Theatre theatreModel = new Theatre();
					theatreModel.setTheatre_name(txtTheatreName.getText());
					theatreModel.setTotal_seat(txtSeat.getText());

					if (!theatreModel.getTheatre_name().isEmpty() && !theatreModel.getTotal_seat().isEmpty()) {

						theatreService.createTheatre(theatreModel);
						JOptionPane.showMessageDialog(null, "Save Successfully!!!");
						resetFormData();
						loadAllTheatre(Optional.empty());

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				}
			}
		});
		btnSave.setBounds(62, 102, 85, 29);
		frameTheatreForm.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(157, 102, 85, 29);
		frameTheatreForm.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
				theatre = null;
				btnSave.setText("Create");
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Create Theatre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 381, 129);
		frameTheatreForm.getContentPane().add(panel);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CinemaForm cinema;
				try {
					cinema = new CinemaForm();
					cinema.frameCinema.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameTheatreForm.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(307, 299, 85, 29);
		frameTheatreForm.getContentPane().add(btnBack);
		this.tblTheatre.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {

			if (!tblTheatre.getSelectionModel().isSelectionEmpty()) {
				String id = tblTheatre.getValueAt(tblTheatre.getSelectedRow(), 0).toString();

				theatre = theatreService.findTheatreById(id);
				// System.out.println(employee);

				txtTheatreName.setText(theatre.getTheatre_name());
				txtSeat.setText(theatre.getTotal_seat());
				btnSave.setText("Edit");

			}
		});
	}
}
