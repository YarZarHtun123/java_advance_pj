package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import entities.Actor;
import services.ActorService;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class ActorForm {   

	JFrame frameActor;
	private JTextField txtActorName;
	private JTable tblActor;
	private ActorService actorService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Actor> actorList = new ArrayList<>();
	private List<Actor> filteredActorList = new ArrayList<>();
	private Actor actor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActorForm window = new ActorForm();
					window.frameActor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ActorForm() {
		initialize();
		this.actorService = new ActorService();
		setTableDesign();
		uploadData(Optional.empty());

	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		this.tblActor.setModel(dtm);
	}

	private void initialize() {
		frameActor = new JFrame();
		frameActor.setTitle("Actor Form");
		frameActor.setBounds(100, 100, 373, 331);
		frameActor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameActor.getContentPane().setLayout(null);

		JLabel lblActorName = new JLabel("Actor Name");
		lblActorName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActorName.setBounds(23, 34, 107, 31);
		frameActor.getContentPane().add(lblActorName);

		txtActorName = new JTextField();
		txtActorName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtActorName.setBounds(112, 39, 126, 24);
		frameActor.getContentPane().add(txtActorName);
		txtActorName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 326, 148);
		frameActor.getContentPane().add(scrollPane);

		JButton btnSave = new JButton("Create");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != actor && actor.getActor_id() != 0) {

					actor.setActor_name(txtActorName.getText());

					if (!actor.getActor_name().isEmpty()) {

						actorService.updateActor(String.valueOf(actor.getActor_id()), actor);
						txtActorName.setText("");
						actor = null;
						uploadData(Optional.empty());
						btnSave.setText("Create");
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					if (txtActorName.getText().matches(".*[0-9].*")) {
						JOptionPane.showMessageDialog(null, "Name must be String");
					} else if (!txtActorName.getText().isEmpty()) {

						Actor actor = new Actor();
						actor.setActor_name(txtActorName.getText());
						actorService.createActor(actor);
						uploadData(Optional.empty());
						txtActorName.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Enter RequiredField");
					}
				}
			}
		});
		btnSave.setBounds(248, 40, 74, 23);
		frameActor.getContentPane().add(btnSave);

		tblActor = new JTable();
		tblActor.setBackground(UIManager.getColor("CheckBox.background"));
		scrollPane.setViewportView(tblActor);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actor = null;
				txtActorName.setText("");
				btnSave.setText("Create");
			}
		});
		btnCancel.setBounds(248, 68, 74, 23);
		frameActor.getContentPane().add(btnCancel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Create New Actor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 326, 90);
		frameActor.getContentPane().add(panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameActor.setVisible(false);
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
		btnBack.setBounds(267, 261, 69, 23);
		frameActor.getContentPane().add(btnBack);
		this.tblActor.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblActor.getSelectionModel().isSelectionEmpty()) {

				String id = tblActor.getValueAt(tblActor.getSelectedRow(), 0).toString();

				actor = actorService.findActorById(id);

				txtActorName.setText(actor.getActor_name());
				btnSave.setText("Edit");
			}
		});
	}

	private void uploadData(Optional<List<Actor>> optionalActor) {

		this.dtm = (DefaultTableModel) this.tblActor.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.actorList = this.actorService.findAllActor();
		this.filteredActorList = optionalActor.orElseGet(() -> this.actorList).stream().collect(Collectors.toList());

		filteredActorList.forEach(e -> {
			Object[] row = new Object[2];
			row[0] = e.getActor_id();
			row[1] = e.getActor_name();
			dtm.addRow(row);
		});

		this.tblActor.setModel(dtm);
	}
}
