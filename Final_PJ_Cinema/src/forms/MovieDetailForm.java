package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import entities.*;
import services.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieDetailForm {

	JFrame frameMovieDetail;
	private JTable tblMovie;
	private ActorService actorService = new ActorService();
	private MovieService movieService;
	private ActressService actressService = new ActressService();
	private MovieTypeService movieTypeService = new MovieTypeService();
	private DirectorService directorService = new DirectorService();
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Movie> movieList = new ArrayList<>();
	private List<Director> directorList = new ArrayList<>();
	private List<Actor> actorList = new ArrayList<>();
	private List<Actress> actressList = new ArrayList<>();
	private List<MovieType> movieTypeList = new ArrayList<>();
	private List<Movie> filteredMovieList = new ArrayList<>();
	Movie movie;
	JComboBox<String> cboActor, cboActress, cboDirector, cboMovieType;
	JRadioButton rdbtnActor, rdbtnMovieType, rdbtnDirector, rdbtnActress;
	private final ButtonGroup buttonGp = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieDetailForm window = new MovieDetailForm();
					window.frameMovieDetail.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MovieDetailForm() {
		initialize();
		this.movieService = new MovieService();
		setTableDesign();
		this.loadActorForComboBox();
		this.loadActressForComboBox();
		this.loadDirectorForComboBox();
		this.loadMovieTypeForComboBox();
		uploadData(Optional.empty());
	}

	public void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Movie Name");
		dtm.addColumn("Director");
		dtm.addColumn("Actor");
		dtm.addColumn("Actress");
		dtm.addColumn("Movie Type");
		dtm.addColumn("Duration");

		this.tblMovie.setModel(dtm);
	}

	private void uploadData(Optional<List<Movie>> optionalMovie) {

		this.dtm = (DefaultTableModel) this.tblMovie.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.movieList = this.movieService.findAllMovie();
		this.filteredMovieList = optionalMovie.orElseGet(() -> this.movieList).stream().collect(Collectors.toList());

		filteredMovieList.forEach(e -> {
			Object[] row = new Object[7];
			row[0] = e.getMovie_id();
			row[1] = e.getMovie_name();
			row[2] = e.getDirector().getDirector_name();
			row[3] = e.getActor().getActor_name();
			row[4] = e.getActress().getActress_name();
			row[5] = e.getMovieType().getMovieType_name();
			row[6] = e.getDuration();

			dtm.addRow(row);
		});

		this.tblMovie.setModel(dtm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMovieDetail = new JFrame();
		frameMovieDetail.setBounds(100, 100, 600, 450);
		frameMovieDetail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMovieDetail.getContentPane().setLayout(null);

		rdbtnDirector = new JRadioButton("Director");
		rdbtnDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// loadDirectorForComboBox();
				cboDirector.setVisible(true);
				cboActress.setVisible(false);
				cboActor.setVisible(false);
				cboMovieType.setVisible(false);
			}
		});
		rdbtnDirector.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnDirector.setBounds(26, 35, 91, 21);
		frameMovieDetail.getContentPane().add(rdbtnDirector);

		rdbtnActress = new JRadioButton("Actress");
		rdbtnActress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// loadActressForComboBox();
				cboActress.setVisible(true);
				cboActor.setVisible(false);
				cboMovieType.setVisible(false);
				cboDirector.setVisible(false);
			}
		});
		rdbtnActress.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnActress.setBounds(197, 35, 77, 21);
		frameMovieDetail.getContentPane().add(rdbtnActress);

		rdbtnActor = new JRadioButton("Actor");
		rdbtnActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// loadActorForComboBox();
				cboActor.setVisible(true);
				cboMovieType.setVisible(false);
				cboDirector.setVisible(false);
				cboActress.setVisible(false);
			}
		});
		rdbtnActor.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnActor.setBounds(116, 35, 77, 21);
		frameMovieDetail.getContentPane().add(rdbtnActor);

		rdbtnMovieType = new JRadioButton("Movie Type");
		rdbtnMovieType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// loadMovieTypeForComboBox();
				cboMovieType.setVisible(true);
				cboDirector.setVisible(false);
				cboActor.setVisible(false);
				cboActress.setVisible(false);
			}
		});
		rdbtnMovieType.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnMovieType.setBounds(26, 72, 119, 21);
		frameMovieDetail.getContentPane().add(rdbtnMovieType);

		buttonGp.add(rdbtnDirector);
		buttonGp.add(rdbtnActor);
		buttonGp.add(rdbtnActress);
		buttonGp.add(rdbtnMovieType);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 114, 546, 258);
		frameMovieDetail.getContentPane().add(scrollPane);

		tblMovie = new JTable();
		scrollPane.setViewportView(tblMovie);
		this.tblMovie.setFocusable(false);
		this.tblMovie.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					String id = tblMovie.getValueAt(tblMovie.getSelectedRow(), 0).toString();

					movie = movieService.findMovieById(id);
					MovieCreateForm movieCreate = new MovieCreateForm(movie);
					movieCreate.frameMovieCreate.setVisible(true);
					movieCreate.btnCreate.setText("Edit");
					movieCreate.frameMovieCreate.setTitle("Movie Edit Form");
					movieCreate.panel.setBorder(new TitledBorder(null, "Editing Movie", TitledBorder.LEADING,
							TitledBorder.TOP, null, null));
					frameMovieDetail.setVisible(false);
				}
			}
		});

		cboDirector = new JComboBox<>();
		cboDirector.setBounds(165, 72, 119, 21);
		frameMovieDetail.getContentPane().add(cboDirector);
		cboDirector.setVisible(false);

		cboActor = new JComboBox<>();
		cboActor.setBounds(165, 72, 119, 21);
		frameMovieDetail.getContentPane().add(cboActor);
		cboActor.setVisible(false);

		cboActress = new JComboBox<>();
		cboActress.setBounds(165, 72, 119, 21);
		frameMovieDetail.getContentPane().add(cboActress);
		cboActress.setVisible(false);

		cboMovieType = new JComboBox<>();
		cboMovieType.setBounds(165, 72, 119, 21);
		frameMovieDetail.getContentPane().add(cboMovieType);
		cboMovieType.setVisible(false);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword;
				if (rdbtnDirector.isSelected()) {
					if (cboDirector.getSelectedIndex() != 0) {
						keyword = cboDirector.getSelectedItem().toString();
						uploadData(Optional.of(movieList.stream()
								.filter(m -> m.getDirector().getDirector_name().equalsIgnoreCase(keyword))
								.collect(Collectors.toList())));
					} else {
						JOptionPane.showMessageDialog(null, "Please select director");
					}
				} else if (rdbtnActor.isSelected()) {
					if (cboActor.getSelectedIndex() != 0) {
						keyword = cboActor.getSelectedItem().toString();
						uploadData(Optional.of(
								movieList.stream().filter(m -> m.getActor().getActor_name().equalsIgnoreCase(keyword))
										.collect(Collectors.toList())));
					} else {
						JOptionPane.showMessageDialog(null, "Please select Actor");
					}
				} else if (rdbtnActress.isSelected()) {
					if (cboActress.getSelectedIndex() != 0) {
						keyword = cboActress.getSelectedItem().toString();
						uploadData(Optional.of(movieList.stream()
								.filter(m -> m.getActress().getActress_name().equalsIgnoreCase(keyword))
								.collect(Collectors.toList())));
					} else {
						JOptionPane.showMessageDialog(null, "Please select actress");
					}
				} else if (rdbtnMovieType.isSelected()) {
					if (cboMovieType.getSelectedIndex() != 0) {
						keyword = cboMovieType.getSelectedItem().toString();
						uploadData(Optional.of(movieList.stream()
								.filter(m -> m.getMovieType().getMovieType_name().equalsIgnoreCase(keyword))
								.collect(Collectors.toList())));
					} else {
						JOptionPane.showMessageDialog(null, "Please select movie type");
					}
				}
			}
		});
		btnSearch.setBounds(296, 72, 91, 21);
		frameMovieDetail.getContentPane().add(btnSearch);

		JButton btnShow = new JButton("Show All");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShow.setBounds(481, 83, 91, 21);
		frameMovieDetail.getContentPane().add(btnShow);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadData(Optional.empty());
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MovieCreateForm movieCreateForm;
				try {
					movieCreateForm = new MovieCreateForm();
					movieCreateForm.frameMovieCreate.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameMovieDetail.setVisible(false);
			}
		});
		btnBack.setBounds(481, 382, 91, 21);
		frameMovieDetail.getContentPane().add(btnBack);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 10, 398, 94);
		frameMovieDetail.getContentPane().add(panel);
	}

	private void loadActorForComboBox() {
		cboActor.addItem("- Select -");
		this.actorList = this.actorService.findAllActor();
		this.actorList.forEach(c -> cboActor.addItem(c.getActor_name()));
	}

	private void loadActressForComboBox() {
		cboActress.addItem("- Select -");
		this.actressList = this.actressService.findAllActress();
		this.actressList.forEach(c -> cboActress.addItem(c.getActress_name()));
	}

	private void loadMovieTypeForComboBox() {
		cboMovieType.addItem("- Select -");
		this.movieTypeList = this.movieTypeService.findAllMovieType();
		this.movieTypeList.forEach(c -> cboMovieType.addItem(c.getMovieType_name()));
	}

	private void loadDirectorForComboBox() {
		cboDirector.addItem("- Select -");
		this.directorList = this.directorService.findAllDirector();
		this.directorList.forEach(c -> cboDirector.addItem(c.getDirector_name()));
	}
}
