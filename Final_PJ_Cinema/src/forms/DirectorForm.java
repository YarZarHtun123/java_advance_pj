package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entities.Director;
import services.DirectorService;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class DirectorForm {

	private JFrame frameDirector;
	private JTextField txtDirectorName;
	private JTable tblDirector;
	private DirectorService directorService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Director> directorList = new ArrayList<>();
	private List<Director> filteredDirectorList = new ArrayList<>();
	private Director director;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DirectorForm window = new DirectorForm();
					window.frameDirector.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DirectorForm() {

		initialize();
		setTableDesign();
		this.directorService = new DirectorService();
		uploadData(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		this.tblDirector.setModel(dtm);
	}

	private void initialize() {
		frameDirector = new JFrame();
		frameDirector.setTitle("Director Form");
		frameDirector.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frameDirector.setBounds(100, 100, 398, 327);
		frameDirector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDirector.getContentPane().setLayout(null);

		JLabel lblDirectorName = new JLabel("Director Name");
		lblDirectorName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDirectorName.setBounds(20, 37, 107, 31);
		frameDirector.getContentPane().add(lblDirectorName);

		txtDirectorName = new JTextField();
		txtDirectorName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDirectorName.setBounds(137, 42, 107, 24);
		frameDirector.getContentPane().add(txtDirectorName);
		txtDirectorName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 358, 148);
		frameDirector.getContentPane().add(scrollPane);

		JButton btnSave = new JButton("Create");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(265, 43, 89, 23);
		frameDirector.getContentPane().add(btnSave);

		tblDirector = new JTable();
		scrollPane.setViewportView(tblDirector);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				director = null;
				txtDirectorName.setText("");
				btnSave.setText("Create");
			}
		});
		btnCancel.setBounds(265, 68, 89, 23);
		frameDirector.getContentPane().add(btnCancel);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Create New Director", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 358, 90);
		frameDirector.getContentPane().add(panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameDirector.setVisible(false);
			}
		});
		btnBack.setBounds(300, 257, 68, 23);
		frameDirector.getContentPane().add(btnBack);
		this.tblDirector.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblDirector.getSelectionModel().isSelectionEmpty()) {

				String id = tblDirector.getValueAt(tblDirector.getSelectedRow(), 0).toString();

				director = directorService.findDirectorById(id);

				txtDirectorName.setText(director.getDirector_name());
				btnSave.setText("Edit");
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != director && director.getDirector_id() != 0) {

					director.setDirector_name(txtDirectorName.getText());

					if (!director.getDirector_name().isEmpty()) {

						directorService.updateDirector(String.valueOf(director.getDirector_id()), director);
						txtDirectorName.setText("");
						director = null;
						uploadData(Optional.empty());
						btnSave.setText("Create");
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					if (txtDirectorName.getText().matches(".*[0-9].*")) {
						JOptionPane.showMessageDialog(null, "Name must be String");
					} else if (!txtDirectorName.getText().isEmpty()) {

						Director director = new Director();
						director.setDirector_name(txtDirectorName.getText());
						directorService.createDirector(director);
						uploadData(Optional.empty());
						txtDirectorName.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Enter RequiredField");
					}
				}
			}
		});
	}

	private void uploadData(Optional<List<Director>> optionaldirector) {

		this.dtm = (DefaultTableModel) this.tblDirector.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.directorList = this.directorService.findAllDirector();
		this.filteredDirectorList = optionaldirector.orElseGet(() -> this.directorList).stream()
				.collect(Collectors.toList());

		filteredDirectorList.forEach(e -> {
			Object[] row = new Object[2];
			row[0] = e.getDirector_id();
			row[1] = e.getDirector_name();
			dtm.addRow(row);
		});

		this.tblDirector.setModel(dtm);
	}
}
