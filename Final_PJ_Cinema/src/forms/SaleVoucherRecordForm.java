package forms;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import entities.*;
import services.SaleVoucherService;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaleVoucherRecordForm {

	JFrame saleRecordframe;
	private JTable tblSaleVoucher;
	private JTextField txtSearch;
	private JButton btnBack;
	private JPanel panel;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<SaleVoucher> saleList = new ArrayList<>();
	private List<SaleVoucher> filteredSaleList = new ArrayList<>();
	private SaleVoucherService saleVoucherService;
	private SaleVoucher saleVoucher;
	List<String> voucherDetailList = new ArrayList<String>();
	String seatNum;
	long total;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleVoucherRecordForm window = new SaleVoucherRecordForm();
					window.saleRecordframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaleVoucherRecordForm() {
		initialize();
		setTableDesign();
		this.saleVoucherService = new SaleVoucherService();
		uploadData(Optional.empty());
	}

	public SaleVoucherRecordForm(SaleVoucher saleVoucher, long total) {
		// this.seatNum = seatNo;
		// System.out.println(seatNo);
		this.total = total;
		initialize();
		setTableDesign();
		this.saleVoucherService = new SaleVoucherService();
		uploadData(Optional.empty());
	}

	public void setTableDesign() {
		dtm.addColumn("Sale Voucher No.");
		dtm.addColumn("Customer Name");
		dtm.addColumn("Movie Name");
		dtm.addColumn("Date");
		dtm.addColumn("Movie Time");
		dtm.addColumn("Theatre");
		dtm.addColumn("Seat No.");
		dtm.addColumn("Total Price");
		this.tblSaleVoucher.setModel(dtm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		saleRecordframe = new JFrame();
		saleRecordframe.setTitle("Sale Voucher Record Form");
		saleRecordframe.setBounds(100, 100, 713, 463);
		saleRecordframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		saleRecordframe.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 677, 288);
		saleRecordframe.getContentPane().add(scrollPane);

		tblSaleVoucher = new JTable();
		scrollPane.setViewportView(tblSaleVoucher);
//		this.tblSaleVoucher.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
//			if (!tblSaleVoucher.getSelectionModel().isSelectionEmpty()) {
//
//				String id = tblSaleVoucher.getValueAt(tblSaleVoucher.getSelectedRow(), 0).toString();
//
//				saleVoucher = saleVoucherService.findVoucherById(id);
//
//				VoucherPrintForm print = new VoucherPrintForm(saleVoucher);
//				print.framePrint.setVisible(true);
//				
//			}
//		});

		this.tblSaleVoucher.setFocusable(false);
		this.tblSaleVoucher.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					for (int i = 0; i < tblSaleVoucher.getColumnCount(); i++) {
						voucherDetailList.add(tblSaleVoucher.getValueAt(tblSaleVoucher.getSelectedRow(), i).toString());
					}

					// saleVoucher = saleVoucherService.findVoucherById(id);
					// System.out.println(saleVoucher.getTotal_price());
					VoucherPrintForm print = new VoucherPrintForm(voucherDetailList);
					print.framePrint.setVisible(true);
					saleRecordframe.setVisible(false);
				}
			}
		});

		txtSearch = new JTextField();
		txtSearch.setBounds(33, 37, 115, 23);
		saleRecordframe.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtSearch.getText();
				if (key.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Voucher Number!");
				} else {
					String search = txtSearch.getText();

					uploadData(Optional.of(
							saleList.stream().filter(s -> (s.getSaleVoucher().equalsIgnoreCase(search.toLowerCase())))
									.collect(Collectors.toList())));
				}
			}
		});
		btnSearch.setBounds(169, 30, 89, 29);
		saleRecordframe.getContentPane().add(btnSearch);

		JLabel lblNewLabel = new JLabel("Sale Voucher Number");
		lblNewLabel.setBounds(23, 20, 125, 14);
		saleRecordframe.getContentPane().add(lblNewLabel);

		btnBack = new JButton("Back");
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
				saleRecordframe.setVisible(false);
			}
		});
		btnBack.setBounds(608, 377, 79, 37);
		saleRecordframe.getContentPane().add(btnBack);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 289, 62);
		saleRecordframe.getContentPane().add(panel);

		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadData(Optional.empty());
			}
		});
		btnShowAll.setBounds(598, 39, 89, 29);
		saleRecordframe.getContentPane().add(btnShowAll);
	}

	private void uploadData(Optional<List<SaleVoucher>> optionalSale) {

		this.dtm = (DefaultTableModel) this.tblSaleVoucher.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.saleList = this.saleVoucherService.findAllSale();
		this.filteredSaleList = optionalSale.orElseGet(() -> this.saleList).stream().collect(Collectors.toList());

		filteredSaleList.forEach(e -> {
			Object[] row = new Object[8];
			row[0] = e.getSaleVoucher();
			row[1] = e.getCustomer().getCustomer_name();
			row[2] = e.getTicket().getScheduleDetail().getSchedule().getMovie().getMovie_name();
			row[3] = e.getTicket().getDate();
			row[4] = e.getTicket().getScheduleDetail().getSection().getStart_time();
			row[5] = e.getTicket().getSeatDetail().getTheatre().getTheatre_name();
			row[6] = getSeatNum(e.getSaleVoucher());
			row[7] = total;

			dtm.addRow(row);
		});

		this.tblSaleVoucher.setModel(dtm);
	}

	public String getSeatNum(String voucherNo) {
		seatNum = "";
		total = 0;
		List<SaleVoucher> seatList = new ArrayList<>();
		int size = this.saleVoucherService.getSeatNo(voucherNo).size();
		seatList = this.saleVoucherService.getSeatNo(voucherNo);
		for (int s = 0; s < size; s++) {
			seatNum += seatList.get(s).getTicket().getSeatDetail().getSeat().getSeatName();
			total += seatList.get(s).getTotal_price();
			if (s != size - 1) {
				seatNum += ",";
			}
		}
		return seatNum;
	}
}
