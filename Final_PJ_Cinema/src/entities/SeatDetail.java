package entities;

public class SeatDetail {
	int seat_detail_id;
	Seat seat;
	Theatre theatre;
	public SeatDetail(int seat_detail_id, Seat seat, Theatre theatre) {
		super();
		this.seat_detail_id = seat_detail_id;
		this.seat = seat;
		this.theatre = theatre;
	}
	public int getSeat_detail_id() {
		return seat_detail_id;
	}
	public void setSeat_detail_id(int seat_detail_id) {
		this.seat_detail_id = seat_detail_id;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public SeatDetail() {
	
	}
	
	
}
