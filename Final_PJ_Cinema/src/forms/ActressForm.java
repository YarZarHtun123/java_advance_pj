package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entities.Actress;
import services.ActressService;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ActressForm {

	private JFrame frameActress;
	private JTextField txtActressName;
	private JTable tblActress;
	private ActressService actressService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Actress> actressList = new ArrayList<>();
	private List<Actress> filteredActressList = new ArrayList<>();
	private Actress actress;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActressForm window = new ActressForm();
					window.frameActress.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ActressForm() {

		initialize();
		setTableDesign();
		this.actressService = new ActressService();
		uploadData(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		this.tblActress.setModel(dtm);
	}

	private void initialize() {
		frameActress = new JFrame();
		frameActress.setTitle("Actress Form");
		frameActress.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frameActress.setBounds(100, 100, 392, 330);
		frameActress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameActress.getContentPane().setLayout(null);

		JLabel lblActressName = new JLabel("Actress Name");
		lblActressName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActressName.setBounds(23, 34, 107, 31);
		frameActress.getContentPane().add(lblActressName);

		txtActressName = new JTextField();
		txtActressName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtActressName.setBounds(122, 39, 116, 24);
		frameActress.getContentPane().add(txtActressName);
		txtActressName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 358, 148);
		frameActress.getContentPane().add(scrollPane);

		JButton btnSave = new JButton("Create");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(265, 43, 89, 23);
		frameActress.getContentPane().add(btnSave);

		tblActress = new JTable();
		scrollPane.setViewportView(tblActress);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actress = null;
				txtActressName.setText("");
				btnSave.setText("Create");
			}
		});
		btnCancel.setBounds(265, 68, 89, 23);
		frameActress.getContentPane().add(btnCancel);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Create New Actress", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 358, 90);
		frameActress.getContentPane().add(panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameActress.setVisible(false);
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
		btnBack.setBounds(277, 261, 89, 23);
		frameActress.getContentPane().add(btnBack);
		this.tblActress.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblActress.getSelectionModel().isSelectionEmpty()) {

				String id = tblActress.getValueAt(tblActress.getSelectedRow(), 0).toString();

				actress = actressService.findActressById(id);

				txtActressName.setText(actress.getActress_name());
				btnSave.setText("Edit");
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != actress && actress.getActress_id() != 0) {

					actress.setActress_name(txtActressName.getText());

					if (!actress.getActress_name().isEmpty()) {

						actressService.updateActress(String.valueOf(actress.getActress_id()), actress);
						txtActressName.setText("");
						actress = null;
						uploadData(Optional.empty());
						btnSave.setText("Create");
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					if (txtActressName.getText().matches(".*[0-9].*")) {
						JOptionPane.showMessageDialog(null, "Name must be String");
					} else if (!txtActressName.getText().isEmpty()) {

						Actress actress = new Actress();
						actress.setActress_name(txtActressName.getText());
						actressService.createActress(actress);
						uploadData(Optional.empty());
						txtActressName.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Enter RequiredField");
					}
				}
			}
		});
	}

	private void uploadData(Optional<List<Actress>> optionalActress) {

		this.dtm = (DefaultTableModel) this.tblActress.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.actressList = this.actressService.findAllActress();
		this.filteredActressList = optionalActress.orElseGet(() -> this.actressList).stream()
				.collect(Collectors.toList());

		filteredActressList.forEach(e -> {
			Object[] row = new Object[2];
			row[0] = e.getActress_id();
			row[1] = e.getActress_name();
			dtm.addRow(row);
		});

		this.tblActress.setModel(dtm);
	}
}
