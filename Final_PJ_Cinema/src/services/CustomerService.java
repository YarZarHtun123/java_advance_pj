package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import entities.Customer;
import shared.mapper.Mapper;

public class CustomerService {

	private final Mapper customerMapper;
	private final DBConfig dbConfig;

	public CustomerService() {

		this.customerMapper = new Mapper();
		this.dbConfig = new DBConfig();

	}

	public void createCustomer(Customer cus) {
		
		try {

			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO customer (customer_name,customer_phone,customer_email,customer_address) VALUES (?,?,?,?)");

			ps.setString(1, cus.getCustomer_name());
			ps.setString(2, cus.getCustomer_phone());
			ps.setString(3, cus.getCustomer_email());
			ps.setString(4, cus.getCustomer_address());

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @param employee
	 */
	public void updateCustomer(String id, Customer cus) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"Update customer set customer_name=?,customer_phone=?,customer_email=?,customer_address=? where customer_id =?");

			ps.setString(1, cus.getCustomer_name());
			ps.setString(2, cus.getCustomer_phone());
			ps.setString(3, cus.getCustomer_email());
			ps.setString(4, cus.getCustomer_address());
			ps.setString(5, id);

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// if(e instanceof MySQLIntegrityConstraintViolationException)
			JOptionPane.showMessageDialog(null, "Already Exits");
		}
	}

	public void deleteCustomer(String id) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("delete from customer where customer_id = '" + id + "'");

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "!!!!!");
		}
	}

	public Customer findCustomerId(String id) {
		Customer customerModel = new Customer();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "Select * from customer where customer_id = " + id + ""; //
			//System.out.println(query);

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.customerMapper.mapToCustomer(customerModel, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerModel;
	}

	public List<Customer> findAllCustomers() {
		List<Customer> customerList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "select * from customer";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Customer customer = new Customer();
				customerList.add(this.customerMapper.mapToCustomer(customer, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	public int getNewCustomerId() {
		int id = 0;

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "Select customer_id from customer order by customer_id desc"; 

			ResultSet rs = st.executeQuery(query);
			rs.next();
			id=rs.getInt("customer_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
