package entities;

public class Customer {

	private int customer_id;
	private String customer_name;
	private String customer_phone;
	private String customer_email;
	private String customer_address;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int cus_id, String cus_name, String cus_phone, String cus_email, String cus_address) {
		super();
		this.customer_id = cus_id;
		this.customer_name = cus_name;
		this.customer_phone = cus_phone;
		this.customer_email = cus_email;
		this.customer_address = cus_address;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int cus_id) {
		this.customer_id = cus_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String cus_name) {
		this.customer_name = cus_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String cus_phone) {
		this.customer_phone = cus_phone;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String cus_email) {
		this.customer_email = cus_email;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String cus_address) {
		this.customer_address = cus_address;
	}

}
