package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import config.DBConfig;
import entities.Customer;
import entities.Seat;
import entities.Theatre;
import services.CustomerService;
import services.SeatService;

public class SeatForm {

	public JFrame frameSeatForm;
	private JTextField txtType;
	private JTextField txtPrice;
	private JTextField txtName;
	private JTable tblSeat;
	private Seat seat;
	private SeatService seatService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private final DBConfig dbConfig = new DBConfig();
	private List<Seat> seatList = new ArrayList();
	private List<Seat> filterSeatList = new ArrayList();
	private JTextField txtSearch;
	private JButton btnCreate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeatForm window = new SeatForm();
					window.frameSeatForm.setVisible(true);
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
	 * @wbp.parser.entryPoint
	 */
	public SeatForm() throws SQLException {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllSeat(Optional.empty());
	}

	private void initializeDependency() {
		this.seatService = new SeatService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Seat Type");
		dtm.addColumn("Seat Name");
		dtm.addColumn("Price");
		this.tblSeat.setModel(dtm);
	}

	private void loadAllSeat(Optional<List<Seat>> optionalSeat) {
		this.dtm = (DefaultTableModel) this.tblSeat.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.seatList = this.seatService.findAllSeat();

		this.filterSeatList = optionalSeat.orElseGet(() -> this.seatList).stream().collect(Collectors.toList());

		filterSeatList.forEach(e -> {
			Object[] row = new Object[4];
			row[0] = e.getSeat_id();
			row[1] = e.getSeat_type();
			row[2] = e.getSeatName();
			row[3] = e.getPrice();
			dtm.addRow(row);
		});

		this.tblSeat.setModel(dtm);
	}

	private void resetFormData() {
		txtType.setText("");
		txtPrice.setText("");
		txtName.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameSeatForm = new JFrame();
		frameSeatForm.setResizable(false);
		frameSeatForm.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frameSeatForm.setTitle("Seat Form");
		frameSeatForm.setBounds(100, 100, 451, 515);
		frameSeatForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSeatForm.getContentPane().setLayout(null);

		JLabel lblType = new JLabel("Seat Type");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblType.setBounds(36, 32, 74, 29);
		frameSeatForm.getContentPane().add(lblType);

		txtType = new JTextField();
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtType.setColumns(10);
		txtType.setBounds(114, 32, 118, 29);
		frameSeatForm.getContentPane().add(txtType);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(36, 71, 74, 29);
		frameSeatForm.getContentPane().add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBounds(114, 71, 118, 29);
		frameSeatForm.getContentPane().add(txtPrice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 168, 391, 203);
		frameSeatForm.getContentPane().add(scrollPane);

		JLabel lblName = new JLabel("Seat Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(36, 110, 74, 29);
		frameSeatForm.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(114, 110, 118, 29);
		frameSeatForm.getContentPane().add(txtName);

		tblSeat = new JTable();
		tblSeat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblSeat);
		this.tblSeat.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblSeat.getSelectionModel().isSelectionEmpty()) {
				String id = tblSeat.getValueAt(tblSeat.getSelectedRow(), 0).toString();

				seat = seatService.findSeatById(id);
				// System.out.println(employee);

				btnCreate.setText("Edit");
				txtType.setText(seat.getSeat_type());
				txtName.setText(seat.getSeatName());
				txtPrice.setText(String.valueOf(seat.getPrice()));

			}
		});

		txtSearch = new JTextField();
		txtSearch.setBounds(25, 424, 96, 31);
		frameSeatForm.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllSeat(Optional.empty());
			}
		});
		btnShowAll.setBounds(244, 400, 87, 26);
		frameSeatForm.getContentPane().add(btnShowAll);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 407, 220, 59);
		frameSeatForm.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(118, 23, 91, 26);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText();

				loadAllSeat(Optional.of(
						seatList.stream().filter(s -> (s.getSeat_type().toLowerCase().startsWith(search.toLowerCase()))
								|| (s.getSeatName().equals(search))).collect(Collectors.toList())));
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameSeatForm.setVisible(false);
				CinemaForm cinema;
				try {
					cinema = new CinemaForm();
					cinema.frameCinema.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(244, 440, 87, 26);
		frameSeatForm.getContentPane().add(btnBack);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Creating Seat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 11, 391, 147);
		frameSeatForm.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seat = null;
				resetFormData();
				btnCreate.setText("Create");
			}
		});
		btnCancel.setBounds(258, 82, 91, 26);
		panel_1.add(btnCancel);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != seat && seat.getSeat_id() != 0) {

					seat.setSeat_type(txtType.getText());
					seat.setSeatName(txtName.getText());
					seat.setPrice(Double.parseDouble(txtPrice.getText()));

					if (!seat.getSeat_type().isEmpty() && !seat.getSeatName().isEmpty() && seat.getPrice() != 0) {

						seatService.updateSeat(String.valueOf(seat.getSeat_id()), seat);
						resetFormData();
						seat = null;
						loadAllSeat(Optional.empty());
						// btnCreate.setText("Create");
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					Seat seatModel = new Seat();
					seatModel.setSeat_type(txtType.getText());
					seatModel.setSeatName(txtName.getText());
					seatModel.setPrice(Double.parseDouble(txtPrice.getText()));

					if (!seatModel.getSeat_type().isEmpty() && !seatModel.getSeatName().isEmpty()
							&& seatModel.getPrice() != 0) {

						seatService.createSeat(seatModel);
						JOptionPane.showMessageDialog(null, "Save Successfully!!!");
						resetFormData();
						loadAllSeat(Optional.empty());

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				}
			}
		});
		btnCreate.setBounds(258, 46, 91, 26);
		panel_1.add(btnCreate);

	}
}
