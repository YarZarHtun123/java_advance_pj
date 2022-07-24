package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.*;
import repositories.SeatDetailRepo;
import shared.mapper.Mapper;

public class SeatService  {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper seatMapper;
	
	public SeatService() {
		this.dbConfig=new DBConfig();
		this.seatMapper=new Mapper();
	}
	
	public void createSeat(Seat Seat) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO seat(seat_type,price,seat_name)values(?,?,?);");
			 
			 ps.setString(1,Seat.getSeat_type());
			 ps.setDouble(2,Seat.getPrice());
			 ps.setString(3, Seat.getSeatName());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Seat> findAllSeat() {
	     List<Seat> SeatList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM seat order by seat_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 Seat Seat = new Seat();
	             SeatList.add(this.seatMapper.mapToSeat(Seat, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return SeatList;
	}
	
	public Seat findSeatById(String id) {
		 Seat Seat = new Seat();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM seat WHERE seat_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.seatMapper.mapToSeat(Seat, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Seat;
	}
	
	public void updateSeat(String id, Seat Seat) {
 		try {
 			PreparedStatement ps = this.dbConfig.getConnection()
                 .prepareStatement("UPDATE seat SET seat_type=?,price=?,seat_name=? WHERE seat_id=?");

 			ps.setString(1, Seat.getSeat_type());
 			ps.setDouble(2, Seat.getPrice());
 			ps.setString(3, Seat.getSeatName());
 			ps.setString(4, id);
 			ps.executeUpdate();
 			ps.close();
 		} catch (Exception e) {

 			e.printStackTrace();
 		}
 	}
	
	public Seat findSeatByName(String seatName) {
		 Seat seat = new Seat();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM seat WHERE seat_name = '" + seatName + "';";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.seatMapper.mapToSeat(seat, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return seat;
	}

}
