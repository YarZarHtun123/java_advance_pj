package entities;

public class Theatre {

	private Integer theatre_id;
	private String theatre_name, total_seat;

	public Theatre() {
		// TODO Auto-generated constructor stub
	}

	public Theatre(Integer theatre_id, String total_seat, String theatre_name) {
		super();
		this.theatre_id = theatre_id;
		this.total_seat = total_seat;
		this.theatre_name = theatre_name;
	}

	public Integer getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(Integer theatre_id) {
		this.theatre_id = theatre_id;
	}

	public String getTotal_seat() {
		return total_seat;
	}

	public void setTotal_seat(String total_seat) {
		this.total_seat = total_seat;
	}

	public String getTheatre_name() {
		return theatre_name;
	}

	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}

}
