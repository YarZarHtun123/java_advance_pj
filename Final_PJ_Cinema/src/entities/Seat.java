package entities;

public class Seat {
	private int seat_id;
	private double price;
	private String seat_type,seatName;
	
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSeat_type() {
		return seat_type;
	}
	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public Seat(int seat_id, double price, String seat_type, String seatName) {
		super();
		this.seat_id = seat_id;
		this.price = price;
		this.seat_type = seat_type;
		this.seatName = seatName;
	}
	
	public Seat() {}

}
