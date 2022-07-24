package forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.Customer;
import entities.Movie;
import entities.SaleVoucher;
import entities.Theatre;
import entities.Ticket;
import services.CustomerService;
import services.MovieService;
import services.SaleVoucherService;
import services.TheatreService;

public class SaleVoucherForm {

	public JFrame frameVoucher;
	private Customer customer;
	private JTextField txtCusName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	CustomerService customerService;
	TheatreService theatreService;
	MovieService movieService;
	JLabel lblSaleDate;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Theatre> theatreList=new ArrayList<>();
	private List<Movie> movieList=new ArrayList<>();
	private SaleVoucher saleVoucher;
	private SaleVoucherService saleVoucherService;
	 private List<Ticket> ticketList = new ArrayList<>();
	private JTable tblSaleVoucher;
	private long total;
	String seatNo="";
	int i;
	//Ticket ticket;
	List<String> seat=new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleVoucherForm window = new SaleVoucherForm();
					window.frameVoucher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	

	public SaleVoucherForm() {
		initialize();
		this.customerService = new CustomerService();
		this.theatreService = new TheatreService();
		this.saleVoucherService = new SaleVoucherService();
		//setSaleDate();
		setTableDesign();
		loadAllSaleVoucher();
	}
	
	public SaleVoucherForm(List<String> seat,List<Ticket> ticket) {
		this.seat = seat;
		this.ticketList = ticket;
		setSeat();
		initialize();
		this.customerService = new CustomerService();
		this.theatreService = new TheatreService();
		this.saleVoucherService = new SaleVoucherService();
		//setSaleDate();
		setTableDesign();
		loadAllSaleVoucher();
	}
	
	public void setSeat() {
		for(int i=0;i<seat.size();i++) {
			seatNo+=seat.get(i);
			if(i!=seat.size()-1) {
				seatNo+=",";
			}
		}
		//System.out.println(seatNo);
	}

	
	private void resetFormData() {
		txtCusName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
	}

	private void initialize() {
		frameVoucher = new JFrame();
		frameVoucher.setTitle("Voucher");
		frameVoucher.setBounds(100, 100, 686, 503);
		frameVoucher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVoucher.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Sale Voucher", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 0, 648, 431);
		frameVoucher.getContentPane().add(panel);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustomerName.setBounds(24, 63, 102, 13);
		panel.add(lblCustomerName);

		JLabel lblDate = new JLabel("Date - ");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(459, 12, 36, 13);
		panel.add(lblDate);

		lblSaleDate = new JLabel("");
		lblSaleDate.setBounds(500, 12, 94, 13);
		panel.add(lblSaleDate);
		lblSaleDate.setText(ticketList.get(0).getDate());
		
		txtCusName = new JTextField();
		txtCusName.setColumns(10);
		txtCusName.setBounds(120, 59, 118, 19);
		panel.add(txtCusName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhone.setBounds(24, 90, 102, 13);
		panel.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(120, 86, 118, 19);
		panel.add(txtPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(264, 63, 53, 13);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(321, 59, 118, 19);
		panel.add(txtEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(264, 89, 53, 13);
		panel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(321, 86, 118, 19);
		panel.add(txtAddress);
		
		JButton btnCustomerCreate = new JButton("Create");
		btnCustomerCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCustomer();
				txtCusName.setEnabled(false);
				txtAddress.setEnabled(false);
				txtPhone.setEnabled(false);
				txtEmail.setEnabled(false);
			}
		});
		btnCustomerCreate.setBounds(331, 115, 91, 21);
		panel.add(btnCustomerCreate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Creating Customer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 46, 469, 104);
		panel.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 628, 269);
		panel.add(scrollPane);
		
		tblSaleVoucher = new JTable();
		scrollPane.setViewportView(tblSaleVoucher);
		tblSaleVoucher.setDefaultRenderer(Object.class, new MyTableRenderer());
		
		JLabel lblNewLabel = new JLabel("Voucher No.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(24, 21, 91, 14);
		panel.add(lblNewLabel);
		
		JLabel lblVoucherNo = new JLabel("");
		lblVoucherNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVoucherNo.setBounds(120, 21, 133, 19);
		panel.add(lblVoucherNo);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVoucher.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.setBounds(554, 432, 91, 21);
		frameVoucher.getContentPane().add(btnClose);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//customer = customerService.findCustomerId(customerService.getNewCustomerId()+"");
				if (!txtCusName.getText().isEmpty() && 
						!txtAddress.getText().isEmpty() &&
						!txtPhone.getText().isEmpty() &&
						!txtEmail.getText().isEmpty()) {
					  String voucherNum="INV-00";
						ticketList.forEach(t -> {
							 SaleVoucher saleVoucher = new SaleVoucher();
							 saleVoucher.setCustomer(customerService.findCustomerId(customerService.getNewCustomerId()+""));
							 saleVoucher.setSaleVoucher(voucherNum+ customerService.getNewCustomerId());
							 saleVoucher.setTicket(t);
							 saleVoucher.setTotal_price(t.getSeatDetail().getSeat().getPrice());
							 saleVoucherService.createSaleVoucher(saleVoucher);
							 
						});
						
						//saleVoucher = saleVoucherService.findVoucherByVoucherNo(voucherNum)
						//System.out.println(saleVoucher.getCustomer().getCustomer_id()+"id");
						SaleVoucherRecordForm record = new SaleVoucherRecordForm(saleVoucher,total);
						
						record.saleRecordframe.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Enter Customer");
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(444, 432, 91, 21);
		frameVoucher.getContentPane().add(btnSave);
	}
	
	
	public void saveCustomer() {
		customer = new Customer();
		customer.setCustomer_name(txtCusName.getText());
		customer.setCustomer_phone(txtPhone.getText());
		customer.setCustomer_email(txtEmail.getText());
		customer.setCustomer_address(txtAddress.getText());
    	if (txtCusName.getText().matches(".*[0-9].*")) {
			JOptionPane.showMessageDialog(null, "Name must be String");
		} else if (!customer.getCustomer_name().isEmpty() && !customer.getCustomer_phone().isEmpty() && !customer.getCustomer_email().isEmpty() && !customer.getCustomer_address().isEmpty()) {
			customerService.createCustomer(customer);
			JOptionPane.showMessageDialog(null, "Save Successfully!!!");		
		} else {
			JOptionPane.showMessageDialog(null, "Enter RequiredField");
		}
	}
	public void setTableDesign() {
		dtm.addColumn("Movie Date");
		dtm.addColumn("Movie Time");
		dtm.addColumn("Movie Name");
		dtm.addColumn("Theatre");
		dtm.addColumn("Seat");
		dtm.addColumn("Price");
		
		this.tblSaleVoucher.setModel(dtm);
	}
	
    private void loadAllSaleVoucher() {
        this.dtm = (DefaultTableModel) this.tblSaleVoucher.getModel();
        this.dtm.getDataVector().removeAllElements();
        this.dtm.fireTableDataChanged();
        int row_count=0;
        this.ticketList.forEach(pd -> {
            Object[] row = new Object[6];
            row[2] = pd.getScheduleDetail().getSchedule().getMovie().getMovie_name();
            row[4] = pd.getSeatDetail().getSeat().getSeatName();
            row[3] = pd.getSeatDetail().getTheatre().getTheatre_name();
            row[5] = pd.getSeatDetail().getSeat().getPrice();
            row[0] = pd.getDate();
            row[1] = pd.getScheduleDetail().getSection().getStart_time()+":"+pd.getScheduleDetail().getSection().getEnd_time();
            dtm.addRow(row);
        });
        
        dtm.addRow(new Object[]{ "", "","","","---------Total-------", calculateTotal() + " MMK"});
        System.out.println(row_count);

        this.tblSaleVoucher.setModel(dtm);
    }
    
    private long calculateTotal() {
        total = 0;
        this.ticketList.forEach(pd -> {
            total += (long) pd.getSeatDetail().getSeat().getPrice();
        });
        return total;
    }  
}


class MyTableRenderer extends DefaultTableCellRenderer {
	 
    // You should override getTableCellRendererComponent
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
 
        Component c = super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
        //c.setForeground(Color.WHITE);
 
        if (row % 2 == 0) {
            c.setBackground(Color.MAGENTA);
        }
        //System.out.println(row);
     
        return c;
    }
}
