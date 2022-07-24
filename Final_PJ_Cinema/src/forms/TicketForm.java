package forms;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Actress;
import entities.Employee;
import entities.Ticket;
import services.TicketService;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TicketForm {

	public JFrame frameTicketList;
	private JTable tblTicket;
	private JTextField txtSearch;
	private TicketService ticketService;
	private List<Ticket> ticketList = new ArrayList<>();
	private List<Ticket> filteredTicketList = new ArrayList<>();
	DefaultTableModel dtm = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketForm window = new TicketForm();
					window.frameTicketList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicketForm() {
		initialize();
		setTableDesign();
		this.ticketService = new TicketService();
		uploadData(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("Ticket No.");
		dtm.addColumn("Movie Name");
		dtm.addColumn("Theatre Name");
		dtm.addColumn("Seat No.");
		dtm.addColumn("Start Date");
		dtm.addColumn("End Date");
		dtm.addColumn("Start Time");
		dtm.addColumn("End Time");
		dtm.addColumn("Date");
		dtm.addColumn("Price");
		dtm.addColumn("Status");
		this.tblTicket.setModel(dtm);
	}

	private void uploadData(Optional<List<Ticket>> optionalTicket) {

		this.dtm = (DefaultTableModel) this.tblTicket.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.ticketList = this.ticketService.findAllTicket();
		this.filteredTicketList = optionalTicket.orElseGet(() -> this.ticketList).stream().collect(Collectors.toList());

		filteredTicketList.forEach(e -> {
			Object[] row = new Object[11];
			row[0] = e.getTicket_id();
			row[1] = e.getScheduleDetail().getSchedule().getMovie().getMovie_name();
			row[2] = e.getScheduleDetail().getSchedule().getTheatre().getTheatre_name();
			row[3] = e.getSeatDetail().getSeat().getSeatName();
			row[4] = e.getScheduleDetail().getSchedule().getStart_date();
			row[5] = e.getScheduleDetail().getSchedule().getEnd_date();
			row[6] = e.getScheduleDetail().getSection().getStart_time();
			row[7] = e.getScheduleDetail().getSection().getEnd_time();
			row[8] = e.getDate();
			row[9] = e.getSeatDetail().getSeat().getPrice();
			row[10] = e.getStatus();
			dtm.addRow(row);
		});

		this.tblTicket.setModel(dtm);
	}

	private void initialize() {
		frameTicketList = new JFrame();
		frameTicketList.setTitle("Ticket Record Form");
		frameTicketList.setBounds(100, 100, 909, 475);
		frameTicketList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTicketList.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 70, 866, 306);
		frameTicketList.getContentPane().add(scrollPane);

		tblTicket = new JTable();
		scrollPane.setViewportView(tblTicket);

		txtSearch = new JTextField();
		txtSearch.setBounds(52, 24, 118, 32);
		frameTicketList.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtSearch.getText();
				uploadData(
						Optional.of(
								ticketList
										.stream().filter(t -> t.getScheduleDetail().getSchedule().getMovie()
												.getMovie_name().toLowerCase().startsWith(key))
										.collect(Collectors.toList())));
				txtSearch.setText("");

			}
		});
		btnSearch.setBounds(194, 29, 91, 21);
		frameTicketList.getContentPane().add(btnSearch);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 0, 299, 68);
		frameTicketList.getContentPane().add(panel);

		JButton btnExit = new JButton("Back");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTicketList.setVisible(false);
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
		btnExit.setBounds(803, 393, 78, 33);
		frameTicketList.getContentPane().add(btnExit);

		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadData(Optional.empty());
			}
		});
		btnShowAll.setBounds(790, 29, 91, 31);
		frameTicketList.getContentPane().add(btnShowAll);
	}
}
