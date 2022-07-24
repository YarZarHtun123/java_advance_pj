package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Movie;
import entities.Schedule;
import entities.Theatre;
import services.MovieService;
import services.ScheduleService;
import services.TheatreService;

public class ScheduleDetailForm {

	JFrame frameScheduleDetail;
	private JTable tblSchedule;
	private TheatreService theatreService = new TheatreService();
	private ScheduleService scheduleService = new ScheduleService();
	private MovieService movieService = new MovieService();
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Theatre> theatreList = new ArrayList<>();
	private List<Movie> movieList = new ArrayList<>();
	private List<Schedule> scheduleList = new ArrayList<>();
	private List<Schedule> filteredScheduleList = new ArrayList<>();
	Schedule schedule;
	JComboBox<String> cboTheatre, cboMovie;
	JRadioButton rdbtnTheatre, rdbtnMovie;
	private final ButtonGroup buttonGp = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleDetailForm window = new ScheduleDetailForm();
					window.frameScheduleDetail.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScheduleDetailForm() {
		initialize();
		this.scheduleService = new ScheduleService();
		setTableDesign();
		this.loadTheatreForComboBox();
		this.loadMovieForComboBox();
		uploadData(Optional.empty());
	}

	public void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Theatre Name");
		dtm.addColumn("Start Date");
		dtm.addColumn("End Date");
		dtm.addColumn("Movie Name");
		dtm.addColumn("Total Tickets");

		this.tblSchedule.setModel(dtm);
	}

	private void uploadData(Optional<List<Schedule>> optionalSchedule) {

		this.dtm = (DefaultTableModel) this.tblSchedule.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.scheduleList = this.scheduleService.findAllSchedule();
		this.filteredScheduleList = optionalSchedule.orElseGet(() -> this.scheduleList).stream()
				.collect(Collectors.toList());

		filteredScheduleList.forEach(e -> {
			Object[] row = new Object[6];
			row[0] = e.getSchedule_id();
			row[1] = e.getTheatre().getTheatre_name();
			row[2] = e.getStart_date();
			row[3] = e.getEnd_date();
			row[4] = e.getMovie().getMovie_name();
			row[5] = e.getNum_of_tickets();

			dtm.addRow(row);
		});

		this.tblSchedule.setModel(dtm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameScheduleDetail = new JFrame();
		frameScheduleDetail.setTitle("Schedule Details Form");
		frameScheduleDetail.setBounds(100, 100, 667, 463);
		frameScheduleDetail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameScheduleDetail.getContentPane().setLayout(null);

		rdbtnTheatre = new JRadioButton("Theatre");
		rdbtnTheatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// loadDirectorForComboBox();
				cboTheatre.setVisible(true);
				cboMovie.setVisible(false);

			}
		});
		rdbtnTheatre.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTheatre.setBounds(26, 35, 91, 21);
		frameScheduleDetail.getContentPane().add(rdbtnTheatre);

		buttonGp.add(rdbtnTheatre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 114, 599, 258);
		frameScheduleDetail.getContentPane().add(scrollPane);

		tblSchedule = new JTable();
		scrollPane.setViewportView(tblSchedule);
		this.tblSchedule.setFocusable(false);
		this.tblSchedule.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					String id = tblSchedule.getValueAt(tblSchedule.getSelectedRow(), 0).toString();

					schedule = scheduleService.findById(id);
					ScheduleForm scheduleCreate = new ScheduleForm(schedule);
					scheduleCreate.frameSchedule.setVisible(true);
					scheduleCreate.btnCreate.setText("Edit");
					scheduleCreate.frameSchedule.setTitle("Schedule Edit Form");
					scheduleCreate.panel.setBorder(new TitledBorder(null, "Editing Schedule", TitledBorder.LEADING,
							TitledBorder.TOP, null, null));
					frameScheduleDetail.setVisible(false);
				}
			}
		});

		JButton btnShow = new JButton("Show All");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShow.setBounds(534, 71, 91, 32);
		frameScheduleDetail.getContentPane().add(btnShow);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadData(Optional.empty());
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleForm scheduleForm;
				try {
					scheduleForm = new ScheduleForm();
					scheduleForm.frameSchedule.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameScheduleDetail.setVisible(false);
			}
		});
		btnBack.setBounds(534, 382, 91, 32);
		frameScheduleDetail.getContentPane().add(btnBack);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 10, 268, 94);
		frameScheduleDetail.getContentPane().add(panel);
		panel.setLayout(null);

		cboTheatre = new JComboBox<>();
		cboTheatre.setBounds(12, 63, 119, 21);
		panel.add(cboTheatre);

		cboMovie = new JComboBox<>();
		cboMovie.setBounds(12, 63, 119, 21);
		panel.add(cboMovie);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(163, 54, 91, 30);
		panel.add(btnSearch);

		rdbtnMovie = new JRadioButton("Movie");
		rdbtnMovie.setBounds(95, 24, 77, 21);
		panel.add(rdbtnMovie);
		rdbtnMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// loadActressForComboBox();
				cboTheatre.setVisible(false);
				cboMovie.setVisible(true);

			}
		});
		rdbtnMovie.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGp.add(rdbtnMovie);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword;
				if (rdbtnTheatre.isSelected()) {
					if (cboTheatre.getSelectedIndex() != 0) {
						keyword = cboTheatre.getSelectedItem().toString();
						uploadData(Optional.of(scheduleList.stream()
								.filter(m -> m.getTheatre().getTheatre_name().equalsIgnoreCase(keyword))
								.collect(Collectors.toList())));
					} else {
						JOptionPane.showMessageDialog(null, "Please select director");
					}
				} else if (rdbtnMovie.isSelected()) {
					if (cboMovie.getSelectedIndex() != 0) {
						keyword = cboMovie.getSelectedItem().toString();
						uploadData(Optional.of(scheduleList.stream()
								.filter(m -> m.getMovie().getMovie_name().equalsIgnoreCase(keyword))
								.collect(Collectors.toList())));
					} else {
						JOptionPane.showMessageDialog(null, "Please select Actor");
					}
				}
			}
		});
		cboMovie.setVisible(false);
		cboTheatre.setVisible(false);
	}

	private void loadTheatreForComboBox() {
		cboTheatre.addItem("- Select -");
		this.theatreList = this.theatreService.findAllTheatre();
		this.theatreList.forEach(c -> cboTheatre.addItem(c.getTheatre_name()));
	}

	private void loadMovieForComboBox() {
		cboMovie.addItem("- Select -");
		this.movieList = this.movieService.findAllMovie();
		this.movieList.forEach(c -> cboMovie.addItem(c.getMovie_name()));
	}

}
