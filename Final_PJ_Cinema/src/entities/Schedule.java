package entities;

public class Schedule {

	private int schedule_id;
	private Theatre theatre;
	private String start_date;
	private String end_date;
	private Movie movie;
	private String num_of_tickets;
	
	public Schedule() {
		
	}

	public Schedule(int schedule_id, Theatre theatre, String start_date, String end_date, Movie movie,
			String num_of_tickets) {
		super();
		this.schedule_id = schedule_id;
		this.theatre = theatre;
		this.start_date = start_date;
		this.end_date = end_date;
		this.movie = movie;
		this.num_of_tickets = num_of_tickets;
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getNum_of_tickets() {
		return num_of_tickets;
	}

	public void setNum_of_tickets(String num_of_tickets) {
		this.num_of_tickets = num_of_tickets;
	}
	
	
}
