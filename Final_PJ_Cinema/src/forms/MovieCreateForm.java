package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.*;
import services.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;

public class MovieCreateForm {

	JFrame frameMovieCreate;
	private JTextField txtMovieName;
	private JTextField txtDuration;
	private MovieService movieService;
	private DirectorService directorService;
	private ActorService actorService;
	private ActressService actressService;
	private MovieTypeService movieTypeService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Movie> movieList = new ArrayList<>();
	private List<Movie> filteredMOvieList = new ArrayList<>();
	private Movie movie;
	JComboBox<String> cboDirector = new JComboBox<>();
	JComboBox<String> cboMovieType = new JComboBox<>();
	JComboBox<String> cboActor = new JComboBox<>();
	JComboBox<String> cboActress = new JComboBox<>();
	private List<Director> directorList = new ArrayList<>();
	private List<Actor> actorList = new ArrayList<>();
	private List<Actress> actressList = new ArrayList<>();
	private List<MovieType> movieTypeList = new ArrayList<>();
	JButton btnCreate;
	JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieCreateForm window = new MovieCreateForm();
					window.frameMovieCreate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MovieCreateForm() {
		initialize();
		initializeDependency();
		loadCombo();
	}

	public void initializeDependency() {
		this.movieService = new MovieService();
		this.directorService = new DirectorService();
		this.actorService = new ActorService();
		this.actressService = new ActressService();
		this.movieTypeService = new MovieTypeService();
	}

	public void loadCombo() {
		loadDirectorForComboBox();
		loadActorForComboBox();
		loadActressForComboBox();
		loadMovieTypeForComboBox();
	}

	public MovieCreateForm(Movie movie) {
		this.movie = movie;
		initialize();
		dataSet(movie);
		initializeDependency();
		loadCombo();
		cboDirector.setSelectedItem(movie.getDirector().getDirector_name());
		cboActor.setSelectedItem(movie.getActor().getActor_name());
		cboActress.setSelectedItem(movie.getActress().getActress_name());
		cboMovieType.setSelectedItem(movie.getMovieType().getMovieType_name());
	}

	private void loadDirectorForComboBox() {
		cboDirector.addItem("- Select -");
		this.directorList = this.directorService.findAllDirector();
		this.directorList.forEach(c -> cboDirector.addItem(c.getDirector_name()));
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

	private void initialize() {
		frameMovieCreate = new JFrame();
		frameMovieCreate.setTitle("Movie Create Form");
		frameMovieCreate.setBounds(100, 100, 600, 450);
		frameMovieCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMovieCreate.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Movie Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(98, 79, 89, 15);
		frameMovieCreate.getContentPane().add(lblNewLabel);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDirector.setBounds(98, 117, 89, 15);
		frameMovieCreate.getContentPane().add(lblDirector);

		JLabel lblActor = new JLabel("Actor");
		lblActor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActor.setBounds(98, 154, 89, 15);
		frameMovieCreate.getContentPane().add(lblActor);

		JLabel lblActress = new JLabel("Actress");
		lblActress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActress.setBounds(98, 190, 89, 15);
		frameMovieCreate.getContentPane().add(lblActress);

		JLabel lblMovieType = new JLabel("Movie Type");
		lblMovieType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMovieType.setBounds(98, 231, 89, 15);
		frameMovieCreate.getContentPane().add(lblMovieType);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDuration.setBounds(98, 271, 89, 15);
		frameMovieCreate.getContentPane().add(lblDuration);

		txtMovieName = new JTextField();
		txtMovieName.setBounds(237, 70, 181, 29);
		frameMovieCreate.getContentPane().add(txtMovieName);
		txtMovieName.setColumns(10);

		cboDirector.setBounds(237, 108, 181, 29);
		frameMovieCreate.getContentPane().add(cboDirector);

		cboActor.setBounds(237, 145, 181, 29);
		frameMovieCreate.getContentPane().add(cboActor);

		cboActress.setBounds(237, 185, 181, 29);
		frameMovieCreate.getContentPane().add(cboActress);

		cboMovieType.setBounds(237, 226, 181, 29);
		frameMovieCreate.getContentPane().add(cboMovieType);

		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		txtDuration.setBounds(237, 267, 181, 29);
		frameMovieCreate.getContentPane().add(txtDuration);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Creating Movie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(49, 40, 450, 301);
		frameMovieCreate.getContentPane().add(panel);

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(398, 10, 58, 13);
		frameMovieCreate.getContentPane().add(lblNewLabel_1);

		JLabel lblDate = new JLabel("");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(449, 10, 100, 13);
		frameMovieCreate.getContentPane().add(lblDate);
		lblDate.setText(setSaleDate());

		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != movie && movie.getMovie_id() != 0) {
					setMovieDataFromForm(movie);

					if (!movie.getMovie_name().isEmpty() && !movie.getDuration().isEmpty()
							&& null != movie.getDirector() && null != movie.getActor() && null != movie.getActress()
							&& null != movie.getMovieType()) {

						movieService.updateMovie(String.valueOf(movie.getMovie_id()), movie);
						frameMovieCreate.setVisible(false);
						MovieDetailForm detailForm = new MovieDetailForm();
						detailForm.frameMovieDetail.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					Movie movie = new Movie();
					setMovieDataFromForm(movie);
					if (!movie.getMovie_name().isEmpty() && !movie.getDuration().isEmpty()
							&& null != movie.getDirector() && null != movie.getActor() && null != movie.getActress()
							&& null != movie.getMovieType()) {

						movieService.createMovie(movie);
						frameMovieCreate.setVisible(false);
						MovieDetailForm detailForm = new MovieDetailForm();
						detailForm.frameMovieDetail.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}

				}
			}
		});
		btnCreate.setBounds(408, 361, 91, 29);
		frameMovieCreate.getContentPane().add(btnCreate);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CinemaForm cinema;
				try {
					cinema = new CinemaForm();
					cinema.frameCinema.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameMovieCreate.setVisible(false);
			}
		});
		btnBack.setBounds(305, 361, 91, 29);
		frameMovieCreate.getContentPane().add(btnBack);

		JButton btnViewMoviesRecord = new JButton("View Movies Record");
		btnViewMoviesRecord.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewMoviesRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovieDetailForm detailForm = new MovieDetailForm();
				detailForm.frameMovieDetail.setVisible(true);
				frameMovieCreate.setVisible(false);
			}
		});
		btnViewMoviesRecord.setBounds(55, 361, 238, 29);
		frameMovieCreate.getContentPane().add(btnViewMoviesRecord);
	}

	private void setMovieDataFromForm(Movie movie) {
		movie.setMovie_name(txtMovieName.getText());

		Optional<Director> selectedDirector = directorList.stream()
				.filter(c -> c.getDirector_name().equals(cboDirector.getSelectedItem())).findFirst();
		movie.setDirector(selectedDirector.orElse(null));

		Optional<Actor> selectedActor = actorList.stream()
				.filter(b -> b.getActor_name().equals(cboActor.getSelectedItem())).findFirst();
		movie.setActor(selectedActor.orElse(null));

		Optional<Actress> selectedActress = actressList.stream()
				.filter(c -> c.getActress_name().equals(cboActress.getSelectedItem())).findFirst();
		movie.setActress(selectedActress.orElse(null));

		Optional<MovieType> selectedMovieType = movieTypeList.stream()
				.filter(b -> b.getMovieType_name().equals(cboMovieType.getSelectedItem())).findFirst();
		movie.setMovieType(selectedMovieType.orElse(null));

		movie.setDuration(txtDuration.getText());
	}

	public void dataSet(Movie movie) {
		txtMovieName.setText(movie.getMovie_name());
		txtDuration.setText(movie.getDuration());
	}

	public String setSaleDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
}
