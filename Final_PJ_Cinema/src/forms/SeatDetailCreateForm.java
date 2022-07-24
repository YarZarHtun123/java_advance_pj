package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entities.Movie;
import entities.Schedule;
import entities.ScheduleDetail;
import entities.Seat;
import entities.SeatDetail;
import entities.Section;
import entities.Theatre;
import services.MovieService;
import services.ScheduleDetailService;
import services.ScheduleService;
import services.SeatDetailService;
import services.SeatService;
import services.SectionService;
import services.TheatreService;

public class SeatDetailCreateForm {

	JFrame frameSeatDetailCreateForm;
	private TheatreService theatreService;
	private SeatService seatService;
	private SeatDetailService seatDetailService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Theatre> theatreList = new ArrayList<>();
	private List<Seat> seatList = new ArrayList<>();
	private List<SeatDetail> seatDetailList = new ArrayList<>();

	JComboBox<String> cboTheatre = new JComboBox<>();
	JComboBox<String> cboSeat = new JComboBox<>();
	Seat seat;
	Theatre theatre;
	JButton btnCreate;
	JPanel panel;

	SeatDetail seatDetail = new SeatDetail();
	private Optional<Theatre> selectedTheatre;
	private Optional<Seat> selectedSeat;
	private JTextField txtSeatType;
	private JTextField txtSeatPrice;
	private JLabel lblSeatName;
	private JLabel lblSeat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeatDetailCreateForm window = new SeatDetailCreateForm();
					window.frameSeatDetailCreateForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public SeatDetailCreateForm() {

		this.seatDetail = seatDetail;
		this.seatDetailList.add(seatDetail);

		initialize(); // dataSet(scheduleDetail);
		initializeDependency();
		loadCombo();

	}

	public void initializeDependency() {
		this.theatre = new Theatre();
		this.seat = new Seat();
		this.theatreService = new TheatreService();
		this.seatService = new SeatService();
		this.seatDetailService = new SeatDetailService();
	}

	public void loadCombo() {
		loadTheatreForComboBox();
		loadSeatForComboBox();
	}

	/*
	 * public ScheduleCreateDetailForm(Schedule schedule) { this.scheduleDetail =
	 * scheduleDetail; initialize(); // dataSet(scheduleDetail); ScheduleDetail
	 * scheduleDetail = new ScheduleDetail(); scheduleDetail.setSchedule(schedule);
	 * scheduleDetail.setSchedule(schedule.getStart_date());
	 * purchaseDetails.setPrice(product.getPrice());
	 * this.purchaseDetailsList.add(purchaseDetails);
	 * 
	 * }
	 */

	private void loadTheatreForComboBox() {
		cboTheatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboTheatre.getSelectedIndex() != 0 && cboTheatre.getSelectedIndex() != -1) {
					selectedTheatre = theatreList.stream()
							.filter(p -> p.getTheatre_name().equals(cboTheatre.getSelectedItem())).findFirst();
					int i = selectedTheatre.map(st -> st.getTheatre_id()).get();
					theatre = theatreService.findTheatreById(String.valueOf(i)); // System.out.println(selectedTheatre.get());
				}
			}
		});

		cboTheatre.addItem("- Select -");
		this.theatreList = this.theatreService.findAllTheatre();
		this.theatreList.forEach(c -> cboTheatre.addItem(c.getTheatre_name()));
	}

	private void loadSeatForComboBox() {
		cboSeat.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cboSeat.addItem("- Select -");
		this.seatList = this.seatService.findAllSeat();
		this.seatList.forEach(c -> cboSeat.addItem(c.getSeatName()));

	}

	private void initialize() {
		frameSeatDetailCreateForm = new JFrame();
		frameSeatDetailCreateForm.setTitle("Seat Create Form");
		frameSeatDetailCreateForm.setBounds(100, 100, 323, 324);
		frameSeatDetailCreateForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSeatDetailCreateForm.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Creat Seat Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 20, 259, 215);
		frameSeatDetailCreateForm.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSeatType = new JLabel("Type");
		lblSeatType.setBounds(25, 129, 36, 15);
		lblSeatType.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblSeatType);
		panel.add(cboTheatre);

		JLabel lblTheatre = new JLabel("Theatre");
		lblTheatre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTheatre.setBounds(25, 32, 89, 15);
		panel.add(lblTheatre);

		txtSeatType = new JTextField();
		txtSeatType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSeatType.setEditable(false);
		txtSeatType.setBounds(107, 122, 111, 29);
		panel.add(txtSeatType);
		txtSeatType.setColumns(10);

		txtSeatPrice = new JTextField();
		txtSeatPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSeatPrice.setEditable(false);
		txtSeatPrice.setBounds(106, 161, 111, 29);
		panel.add(txtSeatPrice);
		txtSeatPrice.setColumns(10);

		cboSeat.setBounds(106, 83, 112, 29);
		cboSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboSeat.getSelectedIndex() != 0 && cboSeat.getSelectedIndex() != -1) {
					selectedSeat = seatList.stream().filter(p -> p.getSeatName().equals(cboSeat.getSelectedItem()))
							.findFirst();
					seat = seatService.findSeatById(String.valueOf(selectedSeat.map(se -> se.getSeat_id()).get()));

					String seatType = selectedSeat.map(m -> m.getSeat_type()).orElse("");
					String seatPrice = selectedSeat.map(m -> String.valueOf(m.getPrice())).orElse("");

					txtSeatType.setText(seatType);
					txtSeatPrice.setText(String.valueOf(seatPrice));
				}
			}
		});
		panel.add(cboSeat);

		cboTheatre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboTheatre.setBounds(106, 25, 111, 29);

		JLabel lblSeatPrice = new JLabel("Price");
		lblSeatPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeatPrice.setBounds(25, 169, 56, 13);
		panel.add(lblSeatPrice);

		lblSeatName = new JLabel("Name");
		lblSeatName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeatName.setBounds(25, 89, 99, 13);
		panel.add(lblSeatName);

		lblSeat = new JLabel("Seat ::");
		lblSeat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeat.setBounds(25, 64, 68, 15);
		panel.add(lblSeat);

		btnCreate = new JButton("Create");

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				seatDetail.setSeat(seat);
				seatDetail.setTheatre(theatre);
				setSeatDetailDataFromForm(seatDetail);

				if (null != seatDetail.getSeat().getSeatName()/*
																 * && null != seat.getSeatName() && 0 != seat.getPrice()
																 * && null != seat.getSeat_type()
																 */) {

					seatDetailService.createSeatDetail(seatDetail);
					JOptionPane.showMessageDialog(null, "Create Successfully!!!");

				} else {
					JOptionPane.showMessageDialog(null, "Check Required Field");
				}

			}

		});

		btnCreate.setBounds(157, 237, 91, 29);
		frameSeatDetailCreateForm.getContentPane().add(btnCreate);
	}

	private void setSeatDetailDataFromForm(SeatDetail seatDetail) {

		Optional<Seat> selectedSeatForm = seatList.stream()
				.filter(c -> c.getSeatName().equals(cboSeat.getSelectedItem())).findFirst();
		seatDetail.setSeat(selectedSeatForm.orElse(null));

	}

}
