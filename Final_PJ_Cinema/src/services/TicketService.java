package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Customer;
import entities.SeatDetail;
import entities.Ticket;
import shared.mapper.Mapper;

public class TicketService {
	
	private final Mapper ticketMapper;
	private final DBConfig dbConfig;
//	public SeatDetailService seatDetailService;
//	public ScheduleDetailService scheduleDetailService;
	
	public TicketService() {
		this.ticketMapper = new Mapper();
		this.ticketMapper.setScheduleDetailRepo(new ScheduleDetailService());
		this.ticketMapper.setSeatDetailRepo(new SeatDetailService());
		this.dbConfig = new DBConfig();
		//this.seatDeta
	}
	
	public List<Ticket> findAllTicket() {
		List<Ticket> ticketList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "select * from ticket inner join schedule_detail on schedule_detail.schedule_detail_id=ticket.schedule_detail_id "+
		"inner join seat_detail on seat_detail.seat_detail_id=ticket.seat_detail_id;";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Ticket ticket = new Ticket();
				ticketList.add(this.ticketMapper.mapToTicket(ticket, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketList;
	}
	
	public void createTicket(Ticket ticket) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO ticket(schedule_detail_id,seat_detail_id,status,date)values(?,?,?,?);");
			 
			 ps.setInt(1,ticket.getScheduleDetail().getSchedule_detail_id());
			 ps.setInt(2, ticket.getSeatDetail().getSeat_detail_id());
			 ps.setString(3, ticket.getStatus());
			 ps.setString(4, ticket.getDate());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Ticket> findTicketBySeat(String id) {
		List<Ticket> ticketList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "select * from ticket inner join schedule_detail on schedule_detail.schedule_detail_id=ticket.schedule_detail_id "+
		"inner join seat_detail.seat_detail_id=ticket.seat_detail_id;";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Ticket ticket = new Ticket();
				ticketList.add(this.ticketMapper.mapToTicket(ticket, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketList;
	}
	
	 public Ticket getlatestTicket() {
			Ticket ticket = new Ticket();
			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query =  "select * from ticket inner join schedule_detail on schedule_detail.schedule_detail_id=ticket.schedule_detail_id "+
						"inner join seat_detail on seat_detail.seat_detail_id=ticket.seat_detail_id ORDER BY ticket_id desc;";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.ticketMapper.mapToTicket(ticket, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ticket;
		}
}
