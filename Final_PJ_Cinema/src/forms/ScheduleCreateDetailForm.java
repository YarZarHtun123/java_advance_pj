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
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entities.Actor;
import entities.Movie;
import entities.Schedule;
import entities.ScheduleDetail;
import entities.Section;
import services.MovieService;
import services.ScheduleDetailService;
import services.ScheduleService;
import services.SectionService;
import javax.swing.JScrollPane;

public class ScheduleCreateDetailForm {

	JFrame frameScheduleCreate;
	private ScheduleDetailService scheduleDetailService;
	private SectionService sectionService;
	private MovieService movieService;
	private ScheduleService scheduleService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Schedule> scheduleList = new ArrayList<>();
	private List<Section> sectionList = new ArrayList<>();
	private List<Schedule> filteredScheduleList = new ArrayList<>();
	JComboBox<String> cboSection = new JComboBox<>();
	private List<ScheduleDetail> scheduleDetailList = new ArrayList<>();
	private List<ScheduleDetail> filteredScheduleDetailList = new ArrayList<>();
	ScheduleDetail scheduleDetail = new ScheduleDetail();
	Section section;
	Schedule scheduleDate;
	private List<Movie> movieList = new ArrayList<>();
	JButton btnCreate;
	JPanel panel;
	private JDateChooser txtDateChooser;
	private JTable tblscheduleCreate;
	Schedule schedule = new Schedule();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private Optional<Schedule> selectedMovie;
	private Optional<Section> selectedSectionTime;
	private JTextField txtScheduleStart;
	private JLabel lblDate;
	private JTextField txtScheduleEnd;
	private JLabel lblSectionEnd;
	private JTextField txtScetionEnd;
	private JLabel lblStartDate;
	private JLabel lblSection;
	private JTextField txtMovie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleCreateDetailForm window = new ScheduleCreateDetailForm();
					window.frameScheduleCreate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param schedule
	 */
	public ScheduleCreateDetailForm() {
		initialize();
		initializeDependency();
		loadCombo();
		setTableDesign();
		uploadAllData(Optional.empty());
	}

	public ScheduleCreateDetailForm(Schedule schedule) {
		this.schedule = schedule;
		initialize();
		txtMovie.setText(schedule.getMovie().getMovie_name());
		txtScheduleStart.setText(schedule.getStart_date());
		txtScheduleEnd.setText(schedule.getEnd_date());
		initializeDependency();
		loadCombo();
		setTableDesign();
		searchByMovie();
		// uploadData(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Movie");
		dtm.addColumn("Section");
		dtm.addColumn("Schedule Date");

		this.tblscheduleCreate.setModel(dtm);
	}

	public void initializeDependency() {
		this.section = new Section();
		// this.scheduleDetail = new ScheduleDetail();
		this.movieService = new MovieService();
		this.scheduleDetailService = new ScheduleDetailService();
		this.scheduleService = new ScheduleService();
		this.sectionService = new SectionService();
	}

	public void loadCombo() {
		loadSectionForComboBox();
		loadMovieForComboBox();

	}

	private void clear() {
		txtDateChooser.setCalendar(null);
		txtScetionEnd.setText("");
		txtScheduleStart.setText("");
		txtScheduleEnd.setText("");
		cboSection.removeAllItems();
		loadCombo();
	}

	public ScheduleCreateDetailForm(ScheduleDetail scheduleDetail) {

		this.scheduleDetailList.add(scheduleDetail);
		this.scheduleDetail = scheduleDetail;
		initialize();
		// dataSet(scheduleDetail);
		initializeDependency();
		loadCombo();
		cboSection.setSelectedItem(
				scheduleDetail.getSection().getStart_time() /* + "--" + scheduleDetail.getSection().getEnd_time() */);

	}

	private void loadSectionForComboBox() {

		cboSection.addItem("- Select -");
		this.sectionList = this.sectionService.findAllSection();
		this.sectionList.forEach(c -> cboSection.addItem(c.getStart_time()));
	}

	private void loadMovieForComboBox() {

		// cboMovie.addItem();

	}

	private void initialize() {
		frameScheduleCreate = new JFrame();
		frameScheduleCreate.setTitle("Schedule Create Form");
		frameScheduleCreate.setBounds(100, 100, 558, 519);
		frameScheduleCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameScheduleCreate.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Creating Schedule Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 21, 496, 215);
		frameScheduleCreate.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSectionStart = new JLabel("Start Time");
		lblSectionStart.setBounds(280, 95, 76, 15);
		lblSectionStart.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblSectionStart);
		panel.add(cboSection);

		JLabel lblSchedule = new JLabel("Schedule::");
		lblSchedule.setBounds(48, 62, 89, 15);
		panel.add(lblSchedule);
		lblSchedule.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMovie.setBounds(48, 37, 89, 15);
		panel.add(lblMovie);

		txtScheduleStart = new JTextField();
		txtScheduleStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtScheduleStart.setEditable(false);
		txtScheduleStart.setBounds(129, 86, 111, 29);
		panel.add(txtScheduleStart);
		txtScheduleStart.setColumns(10);

		txtScheduleEnd = new JTextField();
		txtScheduleEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtScheduleEnd.setEditable(false);
		txtScheduleEnd.setBounds(128, 125, 112, 29);
		panel.add(txtScheduleEnd);
		txtScheduleEnd.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 286, 496, 148);
		frameScheduleCreate.getContentPane().add(scrollPane);

		tblscheduleCreate = new JTable();
		tblscheduleCreate.setBackground(UIManager.getColor("CheckBox.background"));
		scrollPane.setViewportView(tblscheduleCreate);
		this.tblscheduleCreate.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblscheduleCreate.getSelectionModel().isSelectionEmpty()) {

				String id = tblscheduleCreate.getValueAt(tblscheduleCreate.getSelectedRow(), 0).toString();

				scheduleDetail = scheduleDetailService.findById(id);

				txtMovie.setText(scheduleDetail.getSchedule().getMovie().getMovie_name());
				txtScheduleEnd.setText(scheduleDetail.getSchedule().getEnd_date());
				txtScheduleStart.setText(scheduleDetail.getSchedule().getStart_date());
				this.schedule = scheduleDetail.getSchedule();
				
			}
		});

		cboSection.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboSection.setBounds(368, 88, 111, 29);
		cboSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboSection.getSelectedIndex() != 0 && cboSection.getSelectedIndex() != -1) {
					selectedSectionTime = sectionList.stream()
							.filter(p -> p.getStart_time().equals(cboSection.getSelectedItem())).findFirst();
					int i = selectedSectionTime.map(st -> st.getSection_id()).get();
					section = sectionService.findSectionById(String.valueOf(i)); // System.out.println(selectedTheatre.get());

					String endTime = selectedSectionTime.map(m -> m.getEnd_time()).orElse("");
					txtScetionEnd.setText(endTime);
				}
			}

		});

		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(48, 172, 50, 13);
		panel.add(lblDate);

		JLabel lblScheduleEnd = new JLabel("End Date");
		lblScheduleEnd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblScheduleEnd.setBounds(48, 128, 89, 13);
		panel.add(lblScheduleEnd);

		lblSectionEnd = new JLabel("End Time");
		lblSectionEnd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSectionEnd.setBounds(280, 128, 76, 13);
		panel.add(lblSectionEnd);

		txtScetionEnd = new JTextField();
		txtScetionEnd.setEditable(false);
		txtScetionEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtScetionEnd.setBounds(368, 127, 111, 27);
		panel.add(txtScetionEnd);
		txtScetionEnd.setColumns(10);

		lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStartDate.setBounds(48, 94, 99, 13);
		panel.add(lblStartDate);

		lblSection = new JLabel("Section::");
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSection.setBounds(280, 63, 68, 15);
		panel.add(lblSection);

		txtDateChooser = new JDateChooser();
		txtDateChooser.setDateFormatString("dd/MM/yyyy");
		txtDateChooser.setBounds(129, 164, 111, 29);
		panel.add(txtDateChooser);

		txtMovie = new JTextField();
		txtMovie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMovie.setEditable(false);
		txtMovie.setColumns(10);
		txtMovie.setBounds(129, 35, 111, 29);

		panel.add(txtMovie);

		btnCreate = new JButton("Create");

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ScheduleDetail scheduleDetail = new ScheduleDetail();
				scheduleDetail.setSchedule(schedule);
				scheduleDetail.setSection(section);
				scheduleDetail.setschedule_date(dateFormat.format(txtDateChooser.getDate()));
				setScheduleDetailDataFromForm(scheduleDetail);

				// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				try {

					Date dt = dateFormat.parse(txtScheduleEnd.getText());
					Calendar c = Calendar.getInstance();
					c.setTime(dt);
					c.add(Calendar.DATE, 1);
					dt = c.getTime();

					if (txtDateChooser.getDate().before(dateFormat.parse(txtScheduleStart.getText()))
							|| txtDateChooser.getDate().after(dt)) {
						JOptionPane.showMessageDialog(null, "Schdule Date is not in Movie Duration");

					} else

					if (!scheduleDetail.getSection().getStart_time().isEmpty() && txtDateChooser.getDate() == null) {

						scheduleDetailService.createScheduleDetail(scheduleDetail);
						JOptionPane.showMessageDialog(null, "Create Successfully!!!");
						clear();
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Check Required Field");
				}

			}

		});

		btnCreate.setBounds(433, 246, 91, 29);
		frameScheduleCreate.getContentPane().add(btnCreate);

		JButton btnShow = new JButton("Show All");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadAllData(Optional.empty());
			}
		});
		btnShow.setBounds(433, 444, 91, 26);
		frameScheduleCreate.getContentPane().add(btnShow);
		
		JButton btnBack = new JButton("Back");
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
				frameScheduleCreate.setVisible(false);
			}
		});
		btnBack.setBounds(330, 444, 91, 26);
		frameScheduleCreate.getContentPane().add(btnBack);

	}

	private void uploadAllData(Optional<List<ScheduleDetail>> optionalScheduleDetail) {

		this.dtm = (DefaultTableModel) this.tblscheduleCreate.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.scheduleDetailList = this.scheduleDetailService.findAllScheduleDetail();
		this.filteredScheduleDetailList = optionalScheduleDetail.orElseGet(() -> this.scheduleDetailList).stream()
				.collect(Collectors.toList());

		filteredScheduleDetailList.forEach(e -> {
			Object[] row = new Object[4];
			row[0] = e.getSchedule_detail_id();
			row[1] = e.getSchedule().getMovie().getMovie_name();
			row[2] = e.getSection().getStart_time() + "--" + e.getSection().getEnd_time();
			row[3] = e.getschedule_date();
			dtm.addRow(row);
		});

		this.tblscheduleCreate.setModel(dtm);
	}

	private void setScheduleDetailDataFromForm(ScheduleDetail scheduleDetail) {

		Optional<Section> selectedSection = sectionList.stream()
				.filter(c -> c.getStart_time().equals(cboSection.getSelectedItem())).findFirst();
		scheduleDetail.setSection(selectedSection.orElse(null));

	}

	public void searchByMovie() {
		String key = txtMovie.getText();
		this.scheduleDetailList = scheduleDetailService.findAllScheduleDetail();
		System.out.println(this.scheduleDetailList.get(0).getSchedule().getMovie().getMovie_name());
		uploadData(scheduleDetailList.stream()
				.filter(m -> m.getSchedule().getMovie().getMovie_name().equalsIgnoreCase(key))
				.collect(Collectors.toList()));
	}

	private void uploadData(List<ScheduleDetail> scheduleDetailLists) {

		this.dtm = (DefaultTableModel) this.tblscheduleCreate.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		scheduleDetailLists.forEach(e -> {
			Object[] row = new Object[4];
			row[0] = e.getSchedule_detail_id();
			row[1] = e.getSchedule().getMovie().getMovie_name();
			row[2] = e.getSection().getStart_time() + "--" + e.getSection().getEnd_time();
			row[3] = e.getschedule_date();
			dtm.addRow(row);
		});

		this.tblscheduleCreate.setModel(dtm);
	}
}