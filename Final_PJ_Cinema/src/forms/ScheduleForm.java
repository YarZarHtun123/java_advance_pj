package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import entities.Movie;
import entities.Schedule;
import entities.Theatre;
import services.MovieService;
import services.ScheduleService;
import services.TheatreService;
import com.toedter.calendar.JDateChooser;

public class ScheduleForm {

	JFrame frameSchedule;
	private JTextField txtNumOfTickets;
	private MovieService movieService;
	private TheatreService theatreService;
	private ScheduleService scheduleService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Schedule> scheduleList = new ArrayList<>();
	private List<Schedule> filteredScheduleList = new ArrayList<>();
	private Schedule schedule;
	JComboBox<String> cboTheatre = new JComboBox<>();
	JComboBox<String> cboMovie = new JComboBox<>();
	private List<Theatre> theatreList = new ArrayList<>();
	private List<Movie> movieList = new ArrayList<>();
	JButton btnCreate;
	JPanel panel;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField txtDuration;
	private Optional<Movie> selectedDuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleForm window = new ScheduleForm();
					window.frameSchedule.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScheduleForm() {
		initialize();
		initializeDependency();
		loadCombo();
	}

	public void initializeDependency() {
		this.scheduleService = new ScheduleService();
		this.movieService = new MovieService();
		this.theatreService = new TheatreService();
	}

	public void loadCombo() {
		loadTheatreForComboBox();
		loadMovieForComboBox();
	}

	public ScheduleForm(Schedule schedule) {
		this.schedule = schedule;
		initialize();
		dataSet(schedule);
		initializeDependency();
		loadCombo();
		cboTheatre.setSelectedItem(schedule.getTheatre().getTheatre_name());
		cboMovie.setSelectedItem(schedule.getMovie().getMovie_name());
		multiplys();
	}

	private void loadTheatreForComboBox() {
		cboTheatre.setBounds(186, 24, 181, 29);
		cboTheatre.addItem("- Select -");
		this.theatreList = this.theatreService.findAllTheatre();
		this.theatreList.forEach(c -> cboTheatre.addItem(c.getTheatre_name()));
	}

	private void loadMovieForComboBox() {

		cboMovie.addItem("- Select -");
		this.movieList = this.movieService.findAllMovie();
		this.movieList.forEach(c -> cboMovie.addItem(c.getMovie_name()));
	}

	private void initialize() {
		frameSchedule = new JFrame();
		frameSchedule.setTitle("Schedule Create Form");
		frameSchedule.setBounds(100, 100, 600, 480);
		frameSchedule.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSchedule.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Creating Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(49, 40, 450, 318);
		frameSchedule.getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(cboTheatre);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(47, 185, 89, 15);
		panel.add(lblStartDate);
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(47, 224, 89, 15);
		panel.add(lblEndDate);
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setBounds(47, 86, 89, 15);
		lblMovie.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblMovie);
		panel.add(cboMovie);

		JLabel lblNumOfTickets = new JLabel("Total Tickets");
		lblNumOfTickets.setBounds(47, 271, 89, 15);
		panel.add(lblNumOfTickets);
		lblNumOfTickets.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtNumOfTickets = new JTextField();
		txtNumOfTickets.setBounds(186, 267, 181, 29);
		panel.add(txtNumOfTickets);
		txtNumOfTickets.setColumns(10);

		JLabel lblTheatre = new JLabel("Theatre");
		lblTheatre.setBounds(47, 33, 89, 15);
		panel.add(lblTheatre);
		lblTheatre.setFont(new Font("Tahoma", Font.BOLD, 13));

		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(186, 178, 181, 29);
		startDateChooser.setDateFormatString("dd/MM/yyyy");
		panel.add(startDateChooser);

		JDateChooser EndDateChooser = new JDateChooser();
		EndDateChooser.setBounds(186, 217, 181, 29);
		EndDateChooser.setDateFormatString("dd/MM/yyyy");
		panel.add(EndDateChooser);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDuration.setBounds(47, 133, 89, 13);
		panel.add(lblDuration);

		txtDuration = new JTextField();
		txtDuration.setEditable(false);
		txtDuration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDuration.setBounds(186, 125, 181, 29);
		panel.add(txtDuration);
		txtDuration.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(398, 10, 58, 13);
		frameSchedule.getContentPane().add(lblNewLabel_1);

		JLabel lblDate = new JLabel("");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(449, 10, 100, 13);
		frameSchedule.getContentPane().add(lblDate);

		cboMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedDuration = movieList.stream().filter(p -> p.getMovie_name().equals(cboMovie.getSelectedItem()))
						.findFirst();

				String duration = selectedDuration.map(m -> m.getDuration()).orElse("");

				txtDuration.setText(duration);
				// multiplys();
			}
		});
		cboMovie.setBounds(186, 77, 181, 29);

		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != schedule && schedule.getSchedule_id() != 0) {
					setScheduleDataFromForm(schedule);

					if (!schedule.getStart_date().isEmpty() && !schedule.getEnd_date().isEmpty()
							&& !schedule.getNum_of_tickets().isEmpty() && null != schedule.getTheatre()
							&& null != schedule.getMovie()) {

						scheduleService.updateSchedule(String.valueOf(schedule.getSchedule_id()), schedule);
						frameSchedule.setVisible(false);
						ScheduleDetailForm scheduleForm = new ScheduleDetailForm();
						scheduleForm.frameScheduleDetail.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					Schedule schedule = new Schedule();
					setScheduleDataFromForm(schedule);
					if (!schedule.getNum_of_tickets().isEmpty() && null != schedule.getTheatre()
							&& null != schedule.getMovie()) {

						schedule.setStart_date(dateFormat.format(startDateChooser.getDate()));
						schedule.setEnd_date(dateFormat.format(EndDateChooser.getDate()));
						scheduleService.createSchedule(schedule);

						schedule = scheduleService.findById(scheduleService.getNewScheduleId() + "");
						ScheduleCreateDetailForm scheduleCreate = new ScheduleCreateDetailForm(schedule);
						scheduleCreate.frameScheduleCreate.setVisible(true);
						frameSchedule.setVisible(false);
						// System.out.println(schedule.getEnd_date());
						/*
						 * frameSchedule.setVisible(false); ScheduleDetailForm scheduleForm = new
						 * ScheduleDetailForm(); scheduleForm.frameScheduleDetail.setVisible(true);
						 */

					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}

				}
			}
		});
		btnCreate.setBounds(402, 368, 91, 29);
		frameSchedule.getContentPane().add(btnCreate);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CinemaForm cinemaForm;
				try {
					cinemaForm = new CinemaForm();
					cinemaForm.frameCinema.setVisible(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				frameSchedule.setVisible(false);
			}
		});
		btnBack.setBounds(299, 368, 91, 29);
		frameSchedule.getContentPane().add(btnBack);

		JButton btnViewMoviesRecord = new JButton("View Schedule Record");
		btnViewMoviesRecord.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewMoviesRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleDetailForm scheduleDetailForm = new ScheduleDetailForm();
				scheduleDetailForm.frameScheduleDetail.setVisible(true);
				frameSchedule.setVisible(false);
			}
		});
		btnViewMoviesRecord.setBounds(49, 368, 238, 29);
		frameSchedule.getContentPane().add(btnViewMoviesRecord);
	}

	private void setScheduleDataFromForm(Schedule schedule) {

		Optional<Theatre> selectedTheatre = theatreList.stream()
				.filter(c -> c.getTheatre_name().equals(cboTheatre.getSelectedItem())).findFirst();
		schedule.setTheatre(selectedTheatre.orElse(null));

		Optional<Movie> selectedMovie = movieList.stream()
				.filter(b -> b.getMovie_name().equals(cboMovie.getSelectedItem())).findFirst();
		schedule.setMovie(selectedMovie.orElse(null));

		schedule.setNum_of_tickets(txtNumOfTickets.getText());
	}

	public void dataSet(Schedule schedule) {
		txtNumOfTickets.setText(schedule.getNum_of_tickets());
	}

	private void multiplys() {
		String dur[] = txtDuration.getText().split(" ");

		int a = Integer.parseInt(dur[0]);
		System.out.println(a);
	}
}
