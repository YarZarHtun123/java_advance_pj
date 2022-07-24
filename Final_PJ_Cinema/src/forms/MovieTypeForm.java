package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import entities.MovieType;
import services.MovieTypeService;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class MovieTypeForm {

	JFrame frameMovieType;
	private JTextField txtMovieType;
	private JTable tblMtype;
	private MovieTypeService movieTypeService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<MovieType> movieTypeList = new ArrayList<>();
	private List<MovieType> filteredMovieTypeList = new ArrayList<>();
	private MovieType movieType;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieTypeForm window = new MovieTypeForm();
					window.frameMovieType.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MovieTypeForm() {
		initialize();
		this.movieTypeService = new MovieTypeService();
		setTableDesign();
		uploadData(Optional.empty());

	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		this.tblMtype.setModel(dtm);
	}

	private void initialize() {
		frameMovieType = new JFrame();
		frameMovieType.setTitle("Movie Type Form");
		frameMovieType.setBounds(100, 100, 379, 326);
		frameMovieType.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMovieType.getContentPane().setLayout(null);

		JLabel lblMovieType = new JLabel("Movie Type Name");
		lblMovieType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMovieType.setBounds(23, 34, 107, 31);
		frameMovieType.getContentPane().add(lblMovieType);

		txtMovieType = new JTextField();
		txtMovieType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMovieType.setBounds(142, 39, 107, 24);
		frameMovieType.getContentPane().add(txtMovieType);
		txtMovieType.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 340, 148);
		frameMovieType.getContentPane().add(scrollPane);

		tblMtype = new JTable();
		tblMtype.setBackground(UIManager.getColor("CheckBox.background"));
		scrollPane.setViewportView(tblMtype);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Create New Movie Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 340, 90);
		frameMovieType.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnSave = new JButton("Create");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(252, 28, 74, 23);
		panel.add(btnSave);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(252, 61, 74, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMovieType.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(278, 253, 72, 23);
		frameMovieType.getContentPane().add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movieType = null;
				txtMovieType.setText("");
				btnSave.setText("Create");
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != movieType && movieType.getMovieType_id() != 0) {

					movieType.setMovieType_name(txtMovieType.getText());

					if (!movieType.getMovieType_name().isEmpty()) {

						movieTypeService.updateMovieType(String.valueOf(movieType.getMovieType_id()), movieType);
						txtMovieType.setText("");
						movieType = null;
						uploadData(Optional.empty());
						btnSave.setText("Create");
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					if (txtMovieType.getText().matches(".*[0-9].*")) {
						JOptionPane.showMessageDialog(null, "Name must be String");
					} else if (!txtMovieType.getText().isEmpty()) {

						MovieType movieType = new MovieType();
						movieType.setMovieType_name(txtMovieType.getText());
						movieTypeService.createMovieType(movieType);
						uploadData(Optional.empty());
						txtMovieType.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Enter RequiredField");
					}
				}

			}
		});
		this.tblMtype.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblMtype.getSelectionModel().isSelectionEmpty()) {

				String id = tblMtype.getValueAt(tblMtype.getSelectedRow(), 0).toString();

				movieType = movieTypeService.findMovieTypeById(id);

				txtMovieType.setText(movieType.getMovieType_name());
				btnSave.setText("Edit");
			}
		});
	}

	private void uploadData(Optional<List<MovieType>> optionalMovieType) {

		this.dtm = (DefaultTableModel) this.tblMtype.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.movieTypeList = this.movieTypeService.findAllMovieType();
		this.filteredMovieTypeList = optionalMovieType.orElseGet(() -> this.movieTypeList).stream()
				.collect(Collectors.toList());

		filteredMovieTypeList.forEach(e -> {
			Object[] row = new Object[2];
			row[0] = e.getMovieType_id();
			row[1] = e.getMovieType_name();
			dtm.addRow(row);
		});

		this.tblMtype.setModel(dtm);
	}
}
