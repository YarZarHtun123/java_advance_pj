package entities;

public class Ticket {
	private int ticket_id;
	private String status,date;
	ScheduleDetail scheduleDetail;
	SeatDetail seatDetail;
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Ticket() {
		
	}
	public Ticket(int ticket_id, String status, String date, ScheduleDetail schedule, SeatDetail seatDetail) {
		super();
		this.ticket_id = ticket_id;
		this.status = status;
		this.date = date;
		this.scheduleDetail = schedule;
		this.seatDetail = seatDetail;
	}
	public ScheduleDetail getScheduleDetail() {
		return scheduleDetail;
	}
	public void setScheduleDetail(ScheduleDetail scheduleDetail) {
		this.scheduleDetail = scheduleDetail;
	}
	public SeatDetail getSeatDetail() {
		return seatDetail;
	}
	public void setSeatDetail(SeatDetail seatDetail) {
		this.seatDetail = seatDetail;
	}
	
	

}
