package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Seat;
import entities.SeatDetail;
import repositories.SeatDetailRepo;
import shared.mapper.Mapper;

public class SeatDetailService implements SeatDetailRepo{
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper seatDetailMapper;
	
	public SeatDetailService() {
		this.dbConfig=new DBConfig();
		this.seatDetailMapper=new Mapper();
	}
	
	public void createSeatDetail(SeatDetail seatDetail) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO seat_detail(seat_id,theatre_id)values(?,?);");
			 
			 ps.setInt(1,seatDetail.getSeat().getSeat_id());
			 ps.setInt(2,seatDetail.getTheatre().getTheatre_id());
			 ps.executeUpdate();
			 ps.close();
		 }catch(SQLException e) {
			 if(e instanceof SQLException) {
			 e.printStackTrace();
			 }
		 }
	}
	
	public List<SeatDetail> findAllSeat() {
	     List<SeatDetail> SeatList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM seat_detail order by seat_id inner join theatre on theatre.theatre_id=seat_detail.theatre_id "
	        		 +"inner join seat on seat.seat_id = seat_detail.seat_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 SeatDetail Seat = new SeatDetail();
	             SeatList.add(this.seatDetailMapper.mapToSeatDetail(Seat, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return SeatList;
	}
	
	public SeatDetail findById(String seatDetail_id) {
		SeatDetail SeatDetail = new SeatDetail();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM seat_detail inner join theatre on theatre.theatre_id=seat_detail.theatre_id "
		        		 +"inner join seat on seat.seat_id = seat_detail.seat_id WHERE seat_detail_id = " 
						+ seatDetail_id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.seatDetailMapper.mapToSeatDetail(SeatDetail, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SeatDetail;
	}
	
//	public void updateSeat(String id, Seat Seat) {
// 		try {
// 			PreparedStatement ps = this.dbConfig.getConnection()
//                 .prepareStatement("UPDATE seat SET seat_type=?,price=?,seat_name=? WHERE seat_id=?");
//
// 			ps.setString(1, Seat.getSeat_type());
// 			ps.setDouble(2, Seat.getPrice());
// 			ps.setString(3, Seat.getSeatName());
// 			ps.setString(4, id);
// 			ps.executeUpdate();
// 			ps.close();
// 		} catch (Exception e) {
//
// 			e.printStackTrace();
// 		}
// 	}

//	@Override
//	public void createSeat(SeatDetail seatDetail) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateSeat(String id, SeatDetail seatDetail) {
//		// TODO Auto-generated method stub
//		
//	}

	public SeatDetail findBySeatDetailBySeatTheatre(String seatId,String theatreId) {
		SeatDetail SeatDetail = new SeatDetail();
		System.out.println(seatId+theatreId);
			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM seat_detail  inner join theatre on theatre.theatre_id=seat_detail.theatre_id "+ 
		        		 "inner join seat on seat.seat_id = seat_detail.seat_id WHERE seat_detail.seat_id = "+ 
						seatId+" and seat_detail.theatre_id = "+theatreId+";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.seatDetailMapper.mapToSeatDetail(SeatDetail, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SeatDetail;
	}

}
