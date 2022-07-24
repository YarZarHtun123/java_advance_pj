package forms;

import entities.Employee;
import services.AuthService;
import services.EmployeeService;
import shared.utils.CurrentUserHolder;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

public class CredentialInfoForm {

	public JFrame frmLoginForm;
	private Employee employee;
	private EmployeeService employeeService;
	private AuthService authService;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	public JLabel lblLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CredentialInfoForm window = new CredentialInfoForm();
					window.frmLoginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CredentialInfoForm() {
		initialize();
		initializeDependency();
	}

	private void initializeDependency() {
		this.employeeService = new EmployeeService();
		this.authService = new AuthService();
	}

	public CredentialInfoForm(Employee employee) {
		this.employee = employee;
		initialize();
		initializeDependency();
		lblLogin.setText("Create New User");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginForm = new JFrame();
		frmLoginForm.setTitle("Login Form");
		frmLoginForm.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frmLoginForm.setBackground(SystemColor.inactiveCaptionBorder);
		frmLoginForm.setBounds(100, 100, 435, 327);
		frmLoginForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLoginForm.getContentPane().setLayout(null);

		JButton btnLogin = new JButton(employee != null ? "Create Account" : "Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != employee) {
					employee.setUsername(txtUsername.getText());
					employee.setPassword(String.valueOf(txtPassword.getPassword()));

					if (!employee.getUsername().isEmpty() && !employee.getPassword().isEmpty()) {
						employeeService.updateEmployee(String.valueOf(employee.getId()), employee);
						frmLoginForm.setVisible(false);
						EmployeeForm employeeForm = new EmployeeForm();
						employeeForm.frmEmployeeForm.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Fill required fields");
					}
				} else {
					String username = txtUsername.getText();
					String password = String.valueOf(txtPassword.getPassword());

					if (!username.isEmpty() && !password.isEmpty() ) {
						String loggedInUserId = authService.login(username, password);
						if (!loggedInUserId.isEmpty()) {
							Employee employee = employeeService.findEmployeeById(loggedInUserId);
							if(employee.isActive()) {
								CurrentUserHolder.setLoggedInUser(employee);
								JOptionPane.showMessageDialog(null, "Successfully Login");
								frmLoginForm.setVisible(false);
								CinemaForm cif;
								try {
									cif = new CinemaForm();
									cif.frameCinema.setVisible(true);
								} catch (Throwable e1) {
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(null, "You are Blocked!");
							}
			

							//frmLoginForm.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter required Fields");
					}
				}
			}
		});
		btnLogin.setBounds(138, 255, 155, 21);
		frmLoginForm.getContentPane().add(btnLogin);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 395, 235);
		frmLoginForm.getContentPane().add(panel);
		panel.setLayout(null);

		lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLogin.setBounds(22, 10, 346, 78);
		panel.add(lblLogin);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(42, 81, 85, 29);
		panel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsername.setColumns(10);
		txtUsername.setBounds(139, 81, 153, 29);
		panel.add(txtUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(42, 134, 85, 29);
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(139, 134, 153, 29);
		panel.add(txtPassword);

		JLabel lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMessage.setBounds(22, 173, 346, 29);
		panel.add(lblMessage);
	}
}
