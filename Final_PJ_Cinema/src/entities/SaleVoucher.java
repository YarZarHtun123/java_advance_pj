package entities;

public class SaleVoucher {
	private int sale_id;
	private Customer customer;
	private Ticket ticket;
	
	private double total_price;
	private String saleVoucher;
	
	public SaleVoucher()
	{
		
	}
	
	public SaleVoucher(int sale_id,String saleVoucher, Customer customer, Ticket ticket, double total_price) {
		super();
		this.sale_id = sale_id;
		this.customer = customer;
		this.ticket = ticket;
		
		this.total_price = total_price;
		this.saleVoucher = saleVoucher;
	}



	public String getSaleVoucher() {
		return saleVoucher;
	}

	public void setSaleVoucher(String saleVoucher) {
		this.saleVoucher = saleVoucher;
	}

	public int getSale_id() {
		return sale_id;
	}



	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}


	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Ticket getTicket() {
		return ticket;
	}



	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
	
}
