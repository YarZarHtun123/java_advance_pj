package forms;

import entities.Employee;
import services.EmployeeService;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.border.TitledBorder;

public class EmployeeForm {

	public JFrame frmEmployeeForm;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTable tblEmployee;
	private EmployeeService employeeService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private Employee employee;
	private List<Employee> originalEmployeeList = new ArrayList<>();
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnSearch;
	private JButton btnBlock;
	private JButton btnExit;
	private JButton btnShowAll;
	private JTextField txtSearch;
	private JPanel panel_1;
	private JLabel lblCreateUser;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm window = new EmployeeForm();
					window.frmEmployeeForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeForm() {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllEmployees(Optional.empty());
	}

	private void initializeDependency() {
		this.employeeService = new EmployeeService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Phone");
		dtm.addColumn("Email");
		dtm.addColumn("Address");
		dtm.addColumn("Username");
		dtm.addColumn("Status");
		this.tblEmployee.setModel(dtm);
	}

	private void loadAllEmployees(Optional<List<Employee>> optionalEmployees) {
		this.dtm = (DefaultTableModel) this.tblEmployee.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.originalEmployeeList = this.employeeService.findAllEmployees();
		List<Employee> employeeList = optionalEmployees.orElseGet(() -> originalEmployeeList).stream()
				.sorted((a, b) -> Boolean.compare(b.isActive(), a.isActive())).collect(Collectors.toList());

		employeeList.forEach(e -> {
			Object[] row = new Object[7];
			row[0] = e.getId();
			row[1] = e.getName();
			row[2] = e.getPhone();
			row[3] = e.getEmail();
			row[4] = e.getAddress();
			row[5] = e.getUsername();
			row[6] = e.isActive() ? "Active" : "Inactive";
			dtm.addRow(row);
		});

		this.tblEmployee.setModel(dtm);
	}

	private void resetFormData() {
		txtName.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeForm = new JFrame();
		frmEmployeeForm.setType(Type.POPUP);
		frmEmployeeForm.setTitle("Employee Form");
		frmEmployeeForm.setBounds(100, 100, 703, 583);
		frmEmployeeForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeForm.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBackground(Color.RED);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(64, 32, 85, 29);
		frmEmployeeForm.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtName.setColumns(10);
		txtName.setBounds(161, 33, 193, 29);
		frmEmployeeForm.getContentPane().add(txtName);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPhone.setBounds(64, 71, 85, 29);
		frmEmployeeForm.getContentPane().add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPhone.setColumns(10);
		txtPhone.setBounds(161, 72, 193, 29);
		frmEmployeeForm.getContentPane().add(txtPhone);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(161, 111, 193, 29);
		frmEmployeeForm.getContentPane().add(txtEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(64, 110, 85, 29);
		frmEmployeeForm.getContentPane().add(lblEmail);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddress.setBounds(64, 149, 85, 29);
		frmEmployeeForm.getContentPane().add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAddress.setColumns(10);
		txtAddress.setBounds(161, 150, 193, 29);
		frmEmployeeForm.getContentPane().add(txtAddress);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 201, 643, 266);
		frmEmployeeForm.getContentPane().add(scrollPane);

		tblEmployee = new JTable();
		tblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblEmployee);
		this.tblEmployee.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblEmployee.getSelectionModel().isSelectionEmpty()) {

				String id = tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString();

				employee = employeeService.findEmployeeById(id);
				txtName.setText(employee.getName());
				txtPhone.setText(employee.getPhone());
				txtEmail.setText(employee.getEmail());
				txtAddress.setText(employee.getAddress());
				btnSave.setText("Edit");
			}
		});

		btnSave = new JButton("Create");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != employee && employee.getId() != 0) {

					employee.setName(txtName.getText());
					employee.setPhone(txtPhone.getText());
					employee.setEmail(txtEmail.getText());
					employee.setAddress(txtAddress.getText());

					if (!employee.getName().isEmpty() && !employee.getPhone().isEmpty()
							&& !employee.getEmail().isEmpty() && !employee.getAddress().isEmpty()) {

						employeeService.updateEmployee(String.valueOf(employee.getId()), employee);
						resetFormData();
						loadAllEmployees(Optional.empty());
						employee = null;
						btnSave.setText("Save");
					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				} else {
					employee = new Employee();
					employee.setName(txtName.getText());
					employee.setPhone(txtPhone.getText());
					employee.setEmail(txtEmail.getText());
					employee.setAddress(txtAddress.getText());

					if (!employee.getName().isEmpty() && !employee.getPhone().isEmpty()
							&& !employee.getEmail().isEmpty() && !employee.getAddress().isEmpty()) {

						employeeService.createEmployee(employee);
						resetFormData();
						loadAllEmployees(Optional.empty());

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				}
			}
		});

		btnSave.setBounds(397, 48, 103, 29);
		frmEmployeeForm.getContentPane().add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
				employee = null;
				btnSave.setText("Create");
			}
		});
		btnCancel.setBounds(397, 126, 103, 29);
		frmEmployeeForm.getContentPane().add(btnCancel);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(243, 502, 91, 21);
		frmEmployeeForm.getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String keyword = txtSearch.getText();

				loadAllEmployees(
						Optional.of(
								originalEmployeeList.stream()
										.filter(employee -> employee.getName().toLowerCase(Locale.ROOT)
												.startsWith(keyword.toLowerCase(Locale.ROOT)))
										.collect(Collectors.toList())));

			}
		});

		btnBlock = new JButton("Block");
		btnBlock.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBlock.setBounds(397, 87, 103, 29);
		frmEmployeeForm.getContentPane().add(btnBlock);
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != employee) {
					employeeService.blockEmployee(String.valueOf(employee.getId()));
					resetFormData();
					loadAllEmployees(Optional.empty());
					employee = null;
				} else {
					JOptionPane.showMessageDialog(null, "Choose Employee");
				}
			}
		});

		btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmployeeForm.setVisible(false);
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
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(471, 502, 91, 21);
		frmEmployeeForm.getContentPane().add(btnExit);

		btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllEmployees(Optional.empty());
			}
		});
		btnShowAll.setBounds(574, 502, 91, 21);
		frmEmployeeForm.getContentPane().add(btnShowAll);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setBounds(78, 498, 153, 29);
		frmEmployeeForm.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 476, 332, 68);
		frmEmployeeForm.getContentPane().add(panel_1);

		lblCreateUser = new JLabel("**Create New User Account");
		lblCreateUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCreateUser.setBounds(394, 165, 187, 13);
		frmEmployeeForm.getContentPane().add(lblCreateUser);
		lblCreateUser.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (null != employee ) {
					if(employee.getUsername().equals(null)) {
						frmEmployeeForm.setVisible(false);
						CredentialInfoForm credentialInfoForm = new CredentialInfoForm(employee);
						credentialInfoForm.frmLoginForm.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "This Employee already have user account!");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Choose Employee");
				}
			}

			public void mouseEntered(MouseEvent e) {
				lblCreateUser.setText("<html><a href=''>" + lblCreateUser.getText() + "</a></html>");
			}

		});

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Create New Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 10, 643, 188);
		frmEmployeeForm.getContentPane().add(panel);
	}

}
