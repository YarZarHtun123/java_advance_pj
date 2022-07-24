package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.*;
import repositories.SeatDetailRepo;
import shared.mapper.Mapper;

public class SaleVoucherService {
	private final Mapper saleVoucherMapper;
	private final DBConfig dbConfig;
	private SeatDetailService seatDetailService;
	private ScheduleDetailService scheduleDetailService;
	
	public SaleVoucherService() {
		this.saleVoucherMapper = new Mapper();
		this.dbConfig = new DBConfig();
		this.scheduleDetailService=new ScheduleDetailService();
		//this.saleVoucherMapper.setSaleRepo(this);
		this.saleVoucherMapper.setScheduleDetailRepo(new ScheduleDetailService());
		this.seatDetailService = new SeatDetailService();
		this.saleVoucherMapper.setSeatDetailRepo(new SeatDetailService());
	}
	
	
public void createSaleVoucher(SaleVoucher saleVoucher) {
		
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO sale_voucher (customer_id,ticket_id,total_price,voucher_num) VALUES (?,?,?,?)");

			ps.setInt(1, saleVoucher.getCustomer().getCustomer_id());
			ps.setInt(2, saleVoucher.getTicket().getTicket_id());
			ps.setDouble(3,saleVoucher.getTotal_price());
			ps.setString(4,saleVoucher.getSaleVoucher());

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public List<SaleVoucher> findAllSale() {
    List<SaleVoucher> saleList = new ArrayList<>();	     
    try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

        String query = "SELECT * FROM sale_voucher inner join customer on customer.customer_id=sale_voucher.customer_id\n"+
        "inner join ticket on ticket.ticket_id=sale_voucher.ticket_id group by voucher_num;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
       	 SaleVoucher sale = new SaleVoucher();
            saleList.add(this.saleVoucherMapper.mapToSaleVoucher(sale, rs));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return saleList;
}

public SaleVoucher findVoucherById(String id) {
	SaleVoucher sale = new SaleVoucher();

		try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
		{
			String query = "SELECT * FROM sale_voucher inner join customer on customer.customer_id=sale_voucher.customer_id\n"+
			        "inner join ticket on ticket.ticket_id=sale_voucher.ticket_id where sale_voucher_id="+id;

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.saleVoucherMapper.mapToSaleVoucher(sale, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sale;
}

public List<SaleVoucher> getSeatNo(String voucherNo){
	List<SaleVoucher> saleList = new ArrayList<>();
    try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

        String query = "SELECT * FROM sale_voucher inner join customer on customer.customer_id=sale_voucher.customer_id\n"+
        "inner join ticket on ticket.ticket_id=sale_voucher.ticket_id where voucher_num = '"+voucherNo+"';";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
          	 SaleVoucher sale = new SaleVoucher();
             saleList.add(this.saleVoucherMapper.mapToSaleVoucher(sale, rs));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return saleList;   
}

public SaleVoucher findVoucherByVoucherNo(String voucher) {
	SaleVoucher sale = new SaleVoucher();

		try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
		{
			String query = "SELECT * FROM sale_voucher inner join customer on customer.customer_id=sale_voucher.customer_id\n"+
			        "inner join ticket on ticket.ticket_id=sale_voucher.ticket_id where sale_voucher_id="+voucher;

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.saleVoucherMapper.mapToSaleVoucher(sale, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sale;
}

}
