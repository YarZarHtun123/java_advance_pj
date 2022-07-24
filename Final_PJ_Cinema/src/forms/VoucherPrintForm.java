package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

import entities.SaleVoucher;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VoucherPrintForm {

	public JFrame framePrint;
	SaleVoucher saleVoucher;
	JLabel lblCustomerName, lblName, lblTheatreName, lblSeat, lblTime, lblPrice, lblVouchernum, lblDate;
	List<String> voucherDetailList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoucherPrintForm window = new VoucherPrintForm();
					window.framePrint.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VoucherPrintForm() {
		initialize();

	}

	public VoucherPrintForm(List<String> detailList) {
		this.voucherDetailList = detailList;
		initialize();
		uploadData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePrint = new JFrame();
		framePrint.setResizable(false);
		framePrint.setBounds(100, 100, 525, 515);
		framePrint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrint.getContentPane().setLayout(null);

		JLabel lblCusstomer = new JLabel("Customer Name");
		lblCusstomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCusstomer.setBounds(32, 73, 127, 23);
		framePrint.getContentPane().add(lblCusstomer);

		JLabel lblVoucherNo = new JLabel("Voucher No.");
		lblVoucherNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVoucherNo.setBounds(32, 41, 127, 23);
		framePrint.getContentPane().add(lblVoucherNo);

		JLabel lblDate1 = new JLabel("Date :");
		lblDate1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate1.setBounds(296, 11, 59, 23);
		framePrint.getContentPane().add(lblDate1);

		lblDate = new JLabel("");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(358, 11, 145, 23);
		framePrint.getContentPane().add(lblDate);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Print Successfull!!!");
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPrint.setBounds(423, 442, 78, 23);
		framePrint.getContentPane().add(btnPrint);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleVoucherRecordForm saleVoucherRecordForm = new SaleVoucherRecordForm();
				saleVoucherRecordForm.saleRecordframe.setVisible(true);
				framePrint.setVisible(false);
			}
		});
		btnBack.setBounds(335, 442, 78, 23);
		framePrint.getContentPane().add(btnBack);

		JLabel lblMovieName = new JLabel("Movie Name");
		lblMovieName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMovieName.setBounds(32, 166, 167, 23);
		framePrint.getContentPane().add(lblMovieName);

		JLabel lblTheatre = new JLabel("Theatre");
		lblTheatre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatre.setBounds(32, 209, 167, 23);
		framePrint.getContentPane().add(lblTheatre);

		JLabel lblSeatNo = new JLabel("Seat NO.");
		lblSeatNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeatNo.setBounds(32, 249, 167, 23);
		framePrint.getContentPane().add(lblSeatNo);

		JLabel lblMovieTime = new JLabel("Movie Time");
		lblMovieTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMovieTime.setBounds(32, 293, 167, 23);
		framePrint.getContentPane().add(lblMovieTime);

		JLabel lblTheatre_1 = new JLabel(":");
		lblTheatre_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatre_1.setBounds(209, 166, 11, 23);
		framePrint.getContentPane().add(lblTheatre_1);

		JLabel lblTheatre_1_1 = new JLabel(":");
		lblTheatre_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatre_1_1.setBounds(209, 209, 11, 23);
		framePrint.getContentPane().add(lblTheatre_1_1);

		JLabel lblTheatre_1_1_1 = new JLabel(":");
		lblTheatre_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatre_1_1_1.setBounds(209, 249, 11, 23);
		framePrint.getContentPane().add(lblTheatre_1_1_1);

		JLabel lblTheatre_1_1_1_1 = new JLabel(":");
		lblTheatre_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatre_1_1_1_1.setBounds(209, 293, 11, 23);
		framePrint.getContentPane().add(lblTheatre_1_1_1_1);

		lblName = new JLabel("");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(242, 166, 167, 23);
		framePrint.getContentPane().add(lblName);

		lblTheatreName = new JLabel("");
		lblTheatreName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatreName.setBounds(242, 209, 167, 23);
		framePrint.getContentPane().add(lblTheatreName);

		lblSeat = new JLabel("");
		lblSeat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeat.setBounds(242, 249, 167, 23);
		framePrint.getContentPane().add(lblSeat);

		lblTime = new JLabel("");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTime.setBounds(242, 293, 167, 23);
		framePrint.getContentPane().add(lblTime);

		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalPrice.setBounds(32, 334, 167, 23);
		framePrint.getContentPane().add(lblTotalPrice);

		JLabel lblTheatre_1_1_1_1_1 = new JLabel(":");
		lblTheatre_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheatre_1_1_1_1_1.setBounds(209, 334, 11, 23);
		framePrint.getContentPane().add(lblTheatre_1_1_1_1_1);

		lblPrice = new JLabel("");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(242, 334, 167, 23);
		framePrint.getContentPane().add(lblPrice);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(10, 121, 490, 276);
		framePrint.getContentPane().add(panel);

		lblCustomerName = new JLabel("");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomerName.setBounds(228, 73, 127, 23);
		framePrint.getContentPane().add(lblCustomerName);

		lblVouchernum = new JLabel("");
		lblVouchernum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVouchernum.setBounds(228, 41, 127, 23);
		framePrint.getContentPane().add(lblVouchernum);
	}

	public void uploadData() {
		// System.out.println(saleVoucher.getTotal_price());
		lblVouchernum.setText(voucherDetailList.get(0));
		lblCustomerName.setText(voucherDetailList.get(1));
		lblName.setText(voucherDetailList.get(2));
		lblDate.setText(voucherDetailList.get(3));
		lblTime.setText(voucherDetailList.get(4));
		lblTheatreName.setText(voucherDetailList.get(5));
		lblSeat.setText(voucherDetailList.get(6));
		lblPrice.setText(voucherDetailList.get(7));

	}
}
