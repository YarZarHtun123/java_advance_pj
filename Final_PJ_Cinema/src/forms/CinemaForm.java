package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Schedule;
import entities.ScheduleDetail;
import services.ScheduleDetailService;

public class CinemaForm {

	JFrame frameCinema;
	TheatreI theatre1;
	TheatreII theatre2;
	TheatreIII theatre3;
	JMenu mnEntry;
	Date startDate, endDate;

	private JTable tblCinema;
	ScheduleDetail scheduleDetail = new ScheduleDetail();
	// Schedule schedule = new Schedule();
	private ScheduleDetailService scheduleDetailService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<ScheduleDetail> scheduleDetailList = new ArrayList<>();
	private List<ScheduleDetail> showScheduleList = new ArrayList<>();
	private List<ScheduleDetail> filteredScheduleDetailList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CinemaForm window = new CinemaForm();
					window.frameCinema.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public CinemaForm() throws ParseException {
		initialize();
		initializeDependency();
		this.setTableDesign();
		// uploadData(Optional.empty());

		dateRange();

		/*
		 * if (CurrentUserHolder.getCurrentUser() == null) { System.out.println();
		 * mnEntry.setEnabled(false); }
		 */
	}

	public void initializeDependency() {

		// this.scheduleDetail = new ScheduleDetail();
		this.scheduleDetailService = new ScheduleDetailService();
	}

	private void setTableDesign() {
		dtm.addColumn("Schedule ID");
		dtm.addColumn("Section Time");
		dtm.addColumn("Movie");
		dtm.addColumn("Theatre");
		dtm.addColumn("Duration");
		this.tblCinema.setModel(dtm);
	}

	private void uploadData(List<ScheduleDetail> scheduleDetailLists) {

		this.dtm = (DefaultTableModel) this.tblCinema.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		// this.scheduleDetailList = this.scheduleDetailService.findAllScheduleDetail();
		// this.filteredScheduleDetailList = scheduleDetailLists.orElseGet(() ->
		// this.scheduleDetail).stream()
		// .collect(Collectors.toList());

		scheduleDetailLists.forEach(e -> {
			Object[] row = new Object[5];

			row[0] = e.getSchedule_detail_id();
			row[1] = e.getSection().getStart_time() + "--" + e.getSection().getEnd_time();
			row[2] = e.getSchedule().getMovie().getMovie_name();
			row[3] = e.getSchedule().getTheatre().getTheatre_name();
			row[4] = e.getSchedule().getStart_date() + "--" + e.getSchedule().getEnd_date();

			dtm.addRow(row);
		});

		this.tblCinema.setModel(dtm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCinema = new JFrame();
		frameCinema.setTitle("Cinema");
		frameCinema.setBounds(100, 100, 906, 435);
		frameCinema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frmFile.setUndecorated(true);

		JMenuBar menuBar = new JMenuBar();
		frameCinema.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CredentialInfoForm credentialInfo;
				try {
					credentialInfo = new CredentialInfoForm();
					credentialInfo.frmLoginForm.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnNewMenu.add(mntmLogin);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.s
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);

		mnEntry = new JMenu("Entry");
		menuBar.add(mnEntry);

		JMenuItem mntmEmployee = new JMenuItem("Employee");
		mntmEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeForm emp;
				try {
					emp = new EmployeeForm();
					emp.frmEmployeeForm.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnEntry.add(mntmEmployee);

		JMenuItem mntmTheatre = new JMenuItem("Theatre");
		mntmTheatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TheatreForm theatreform;
				try {
					theatreform = new TheatreForm();
					theatreform.frameTheatreForm.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnEntry.add(mntmTheatre);

		JMenuItem mntmSeat = new JMenuItem("Seat");
		mntmSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeatForm seatForm;
				try {
					seatForm = new SeatForm();
					seatForm.frameSeatForm.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnEntry.add(mntmSeat);

		JMenuItem mntmSection = new JMenuItem("Section");
		mntmSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SectionForm sectionForm;
				try {
					sectionForm = new SectionForm();
					sectionForm.frameSectionForm.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnEntry.add(mntmSection);

		JMenuItem mntmMovie = new JMenuItem("Movie");
		mntmMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovieCreateForm movieCreateForm;
				try {
					movieCreateForm = new MovieCreateForm();
					movieCreateForm.frameMovieCreate.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnEntry.add(mntmMovie);

		JMenu mnNewMenu_2 = new JMenu("Process");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmSchedule = new JMenuItem("Schedule");
		mntmSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleForm scheduleForm;
				try {
					scheduleForm = new ScheduleForm();
					scheduleForm.frameSchedule.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnNewMenu_2.add(mntmSchedule);

		JMenu mnNewMenu_1 = new JMenu("Detail");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmTicket = new JMenuItem("Ticket");
		mntmTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketForm ticketForm;
				try {
					ticketForm = new TicketForm();
					ticketForm.frameTicketList.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnNewMenu_1.add(mntmTicket);

		JMenuItem mntmSale = new JMenuItem("Sale");
		mntmSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleVoucherRecordForm saleVoucherRecordForm;
				try {
					saleVoucherRecordForm = new SaleVoucherRecordForm();
					saleVoucherRecordForm.saleRecordframe.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameCinema.setVisible(false);
			}
		});
		mnNewMenu_1.add(mntmSale);
		frameCinema.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 76, 848, 239);
		frameCinema.getContentPane().add(scrollPane);

		tblCinema = new JTable();
		tblCinema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblCinema);
		this.tblCinema.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					String theatre_name = tblCinema.getValueAt(tblCinema.getSelectedRow(), 3).toString();
					String schedule_detail_id = tblCinema.getValueAt(tblCinema.getSelectedRow(), 0).toString();
					scheduleDetail = scheduleDetailService.findById(schedule_detail_id);
					// theatre = theatreService.findTheatreIDByName(theatre_name);
					// scheduleDetailList =
					// scheduleDetailService.findCinemaByTheatreName(theatre_name);
					System.out.print(scheduleDetail.getSchedule().getEnd_date());

					if (theatre_name.equals("TheatreOne")) {

						theatre1 = new TheatreI(scheduleDetail);
						theatre1.frameTheatreI.setVisible(true);
					} else if (theatre_name.equals("TheatreTwo")) {
						theatre2 = new TheatreII(scheduleDetail);
						theatre2.frameTheatreII.setVisible(true);
					} 
					/*
					 * movie = movieService.findMovieById(id); MovieCreateForm movieCreate=new
					 * MovieCreateForm(movie); movieCreate.frameMovieCreate.setVisible(true);
					 * movieCreate.btnCreate.setText("Edit");
					 * movieCreate.frameMovieCreate.setTitle("Movie Edit Form");
					 * movieCreate.panel.setBorder(new TitledBorder(null, "Editing Movie",
					 * TitledBorder.LEADING, TitledBorder.TOP, null, null));
					 */
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(12, 33, 848, 39);
		panel.setBackground(Color.BLACK);
		frameCinema.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cinema Ticket System for Jake");
		lblNewLabel.setBounds(297, 10, 269, 19);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblDate = new JLabel("Date ::");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(706, 10, 53, 13);
		frameCinema.getContentPane().add(lblDate);

		JLabel lblCurDate = new JLabel("");
		lblCurDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurDate.setBounds(764, 10, 96, 13);
		frameCinema.getContentPane().add(lblCurDate);
		lblCurDate.setText(setCurrentDate());

	}

	public String setCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return formatter.format(date);
	}

	public void dateRange() throws ParseException {
		this.scheduleDetailList = this.scheduleDetailService.findAllScheduleDetail();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(scheduleDetailList.size());
		for (int i = 0; i < scheduleDetailList.size(); i++) {
			// System.out.println(scheduleDetailList.get(i).getSchedule().getStart_date());
			startDate = sdf.parse(scheduleDetailList.get(i).getSchedule().getStart_date());
			endDate = sdf.parse(scheduleDetailList.get(i).getSchedule().getEnd_date());
			DateRangeValidator checker = new DateRangeValidator(startDate, endDate);
			Date testDate = sdf.parse(setCurrentDate());
			if (checker.isWithinRange(testDate)
					&& scheduleDetailList.get(i).getschedule_date().equalsIgnoreCase(setCurrentDate())) {
				showScheduleList.add(scheduleDetailList.get(i));
			}
		}
		uploadData(showScheduleList);
	}
}
