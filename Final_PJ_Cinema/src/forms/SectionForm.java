package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import entities.Section;
import services.SectionService;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SectionForm {

	public JFrame frameSectionForm;
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTable tblSection;
	private SectionService sectionService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Section> sectionList = new ArrayList<>();
	private List<Section> filteredSectionList = new ArrayList<>();
	private Section section;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SectionForm window = new SectionForm();
					window.frameSectionForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Start Time");
		dtm.addColumn("End Time");
		this.tblSection.setModel(dtm);
	}

	public SectionForm() {
		initialize();
		setTableDesign();
		sectionService = new SectionService();
		uploadData(Optional.empty());
	}

	private void initialize() {
		frameSectionForm = new JFrame();
		frameSectionForm.setTitle("Section Form");
		frameSectionForm.setBounds(100, 100, 372, 343);
		frameSectionForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSectionForm.getContentPane().setLayout(null);

		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStartTime.setBounds(39, 38, 71, 25);
		frameSectionForm.getContentPane().add(lblStartTime);

		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEndTime.setBounds(39, 73, 71, 25);
		frameSectionForm.getContentPane().add(lblEndTime);

		txtStartTime = new JTextField();
		txtStartTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtStartTime.setToolTipText("(Sample Format : 10:30)");
		txtStartTime.setBounds(120, 38, 108, 25);
		frameSectionForm.getContentPane().add(txtStartTime);
		txtStartTime.setColumns(10);

		txtEndTime = new JTextField();
		txtEndTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEndTime.setToolTipText("(Sample Format : 10:30)");
		txtEndTime.setColumns(10);
		txtEndTime.setBounds(120, 73, 108, 25);
		frameSectionForm.getContentPane().add(txtEndTime);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Create section", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 330, 99);
		frameSectionForm.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(227, 31, 83, 21);
		panel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				section = null;
				resetformdata();
				btnSave.setText("Create");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(227, 68, 83, 21);
		panel.add(btnCancel);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != section && section.getSection_id() != 0) {

					section.setStart_time(txtStartTime.getText());
					section.setEnd_time(txtEndTime.getText());

					if (!section.getStart_time().isEmpty() || !section.getEnd_time().isEmpty()) {

						sectionService.updateSection(String.valueOf(section.getStart_time()), section);
						resetformdata();
						section = null;
						uploadData(Optional.empty());
						btnSave.setText("Create");
					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					if (txtStartTime.getText().matches(".*[aA-zZ].*") || txtEndTime.getText().matches(".*[aA-zZ].*")) {
						JOptionPane.showMessageDialog(null, "Time format must be hr:min");
					} else {

						Section section = new Section();
						section.setStart_time(txtStartTime.getText());
						section.setEnd_time(txtEndTime.getText());
						// System.out.println(section.getEnd_time());
						if (!section.getStart_time().isEmpty() || !section.getEnd_time().isEmpty()) {

							sectionService.createSection(section);
							uploadData(Optional.empty());
							resetformdata();
						} else {
							JOptionPane.showMessageDialog(null, "Enter RequiredField");
						}
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 122, 330, 129);
		frameSectionForm.getContentPane().add(scrollPane);

		tblSection = new JTable();
		scrollPane.setViewportView(tblSection);
		this.tblSection.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblSection.getSelectionModel().isSelectionEmpty()) {

				String id = tblSection.getValueAt(tblSection.getSelectedRow(), 0).toString();

				section = sectionService.findSectionById(id);

				txtStartTime.setText(section.getStart_time());
				txtEndTime.setText(section.getEnd_time());
				btnSave.setText("Edit");
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CinemaForm cinema;
				try {
					cinema = new CinemaForm();
					cinema.frameCinema.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameSectionForm.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(259, 259, 83, 31);
		frameSectionForm.getContentPane().add(btnBack);
	}

	public void resetformdata() {
		txtStartTime.setText("");
		txtEndTime.setText("");
	}

	private void uploadData(Optional<List<Section>> optionalSection) {

		this.dtm = (DefaultTableModel) this.tblSection.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.sectionList = this.sectionService.findAllSection();
		this.filteredSectionList = optionalSection.orElseGet(() -> this.sectionList).stream()
				.collect(Collectors.toList());

		filteredSectionList.forEach(e -> {
			Object[] row = new Object[3];
			row[0] = e.getSection_id();
			row[1] = e.getStart_time();
			row[2] = e.getEnd_time();
			dtm.addRow(row);
		});

		this.tblSection.setModel(dtm);
		// System.out.println(filteredSectionList);
	}
}
