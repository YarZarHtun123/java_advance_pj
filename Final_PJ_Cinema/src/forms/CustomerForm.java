package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import config.DBConfig;
import entities.Customer;
import services.CustomerService;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CustomerForm {

	public JFrame frameCustomerRecordForm;
	private JTable tblCustomer;
	private Customer customer;
	private CustomerService customerService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private final DBConfig dbConfig = new DBConfig();
	private List<Customer> customerList = new ArrayList();
	private List<Customer> filterCustomerList = new ArrayList();
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerForm window = new CustomerForm();
					window.frameCustomerRecordForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 * @wbp.parser.entryPoint
	 */
	public CustomerForm() throws SQLException {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllCustomers(Optional.empty());
	}

	private void initializeDependency() {
			this.customerService = new CustomerService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Phone Number");
		dtm.addColumn("Email");
		dtm.addColumn("Address");
		this.tblCustomer.setModel(dtm);
	}

	private void loadAllCustomers(Optional<List<Customer>> optionalCustomer) {
		this.dtm = (DefaultTableModel) this.tblCustomer.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.customerList = this.customerService.findAllCustomers();

		this.filterCustomerList = optionalCustomer.orElseGet(() -> this.customerList).stream().collect(Collectors.toList());

		filterCustomerList.forEach(e -> {
			Object[] row = new Object[5];
			row[0] = e.getCustomer_id();
			row[1] = e.getCustomer_name();
			row[2] = e.getCustomer_phone();
			row[3] = e.getCustomer_email();
			row[4] = e.getCustomer_address();
			dtm.addRow(row);
		});

		this.tblCustomer.setModel(dtm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCustomerRecordForm = new JFrame();
		frameCustomerRecordForm.setResizable(false);
		frameCustomerRecordForm.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frameCustomerRecordForm.setTitle("Customer Record Form");
		frameCustomerRecordForm.setBounds(100, 100, 600, 515);
		frameCustomerRecordForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCustomerRecordForm.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 549, 345);
		frameCustomerRecordForm.getContentPane().add(scrollPane);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(199, 30, 91, 26);
		frameCustomerRecordForm.getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText();

				loadAllCustomers(Optional.of(customerList.stream()
						.filter(customer -> (customer.getCustomer_name().toLowerCase().startsWith(search.toLowerCase())) ||(customer.getCustomer_phone().equals(search)))
						.collect(Collectors.toList())));
			}
		});
		
		tblCustomer = new JTable();
		tblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblCustomer);
		this.tblCustomer.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblCustomer.getSelectionModel().isSelectionEmpty()) {
				String id = tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString();

				customer = customerService.findCustomerId(id);
			}
		});
		
		txtSearch = new JTextField();
		txtSearch.setBounds(25, 28, 162, 31);
		frameCustomerRecordForm.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllCustomers(Optional.empty());
			}
		});
		btnShowAll.setBounds(470, 30, 91, 26);
		frameCustomerRecordForm.getContentPane().add(btnShowAll);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 11, 295, 59);
		frameCustomerRecordForm.getContentPane().add(panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCustomerRecordForm.setVisible(false);
				
			}
		});
		btnBack.setBounds(470, 427, 91, 26);
		frameCustomerRecordForm.getContentPane().add(btnBack);
	}
}
