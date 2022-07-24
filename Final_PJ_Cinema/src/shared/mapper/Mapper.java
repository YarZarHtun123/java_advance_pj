package shared.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Actor;
import entities.Actress;
import entities.Customer;
import entities.Director;
import entities.Employee;
import entities.Movie;
import entities.MovieType;
import entities.SaleVoucher;
import entities.Schedule;
import entities.ScheduleDetail;
import entities.Seat;
import entities.SeatDetail;
import entities.Section;
import entities.Theatre;
import entities.Ticket;
import repositories.SaleRepo;
import repositories.ScheduleDetailRepo;
import repositories.ScheduleRepo;
import repositories.SeatDetailRepo;
import services.SaleVoucherService;

public class Mapper {

	private SeatDetailRepo seatDetailRepo;
	private ScheduleDetailRepo scheduleDetailRepo;
	//private SeatDetailRepo seatDetailRepo;
	private ScheduleRepo scheduleRepo;
//	private TheatreRepo theatreRepo;
//	private SeatRepo seatRepo;

	public void setSeatDetailRepo(SeatDetailRepo seatDetailRepo) {
		this.seatDetailRepo = seatDetailRepo;
	}
	
	public void setScheduleRepo(ScheduleRepo scheduleRepo) {
		this.scheduleRepo = scheduleRepo;
	}
	
	public void setScheduleDetailRepo(ScheduleDetailRepo scheduleDetailRepo) {
		this.scheduleDetailRepo = scheduleDetailRepo;
	}

	public Employee mapToEmployee(Employee employee, ResultSet rs) {
		try {
			employee.setId(rs.getInt("emp_id"));
			employee.setName(rs.getString("emp_name"));
			employee.setPhone(rs.getString("emp_phone"));
			employee.setEmail(rs.getString("emp_email"));
			employee.setAddress(rs.getString("emp_address"));
			employee.setUsername(rs.getString("username"));
			employee.setActive(rs.getBoolean("active"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public Theatre mapToTheatre(Theatre theatre, ResultSet rs) {
		try {
			theatre.setTheatre_id(rs.getInt("theatre_id"));
			theatre.setTheatre_name(rs.getString("theatre_name"));
			theatre.setTotal_seat(rs.getString("total_seat"));
			// System.out.println(theatre);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return theatre;
	}

	public Customer mapToCustomer(Customer customer, ResultSet rs) {
		try {
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setCustomer_name(rs.getString("customer_name"));
			customer.setCustomer_phone(rs.getString("customer_phone"));
			customer.setCustomer_email(rs.getString("customer_email"));
			customer.setCustomer_address(rs.getString("customer_address"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Actor mapToActor(Actor actor, ResultSet rs) {
		try {
			actor.setActor_id(rs.getInt("actor_id"));
			actor.setActor_name(rs.getString("actor_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actor;
	}

	public Actress mapToActress(Actress actress, ResultSet rs) {
		try {
			actress.setActress_id(rs.getInt("actress_id"));
			actress.setActress_name(rs.getString("actress_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actress;
	}

	public Director mapToDirector(Director director, ResultSet rs) {
		try {
			director.setDirector_id(rs.getInt("director_id"));
			director.setDirector_name(rs.getString("director_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return director;
	}

	public MovieType mapToMovieType(MovieType movieType, ResultSet rs) {
		try {
			movieType.setMovieType_id(rs.getInt("movie_type_id"));
			movieType.setMovieType_name(rs.getString("movie_type_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieType;
	}

	public Seat mapToSeat(Seat seat, ResultSet rs) {
		try {
			seat.setSeat_id(rs.getInt("seat_id"));
			seat.setSeat_type(rs.getString("seat_type"));
			seat.setPrice(rs.getDouble("price"));
			seat.setSeatName(rs.getString("seat_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seat;
	}

	public Section mapToSection(Section section, ResultSet rs) {
		try {
			section.setSection_id(rs.getInt("section_id"));
			section.setStart_time(rs.getString("start_time"));
			section.setEnd_time(rs.getString("end_time"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public SaleVoucher mapToSaleVoucher(SaleVoucher saleVoucher, ResultSet rs) {
		try {
			saleVoucher.setSale_id(rs.getInt("sale_voucher_id"));
			saleVoucher.setSaleVoucher(rs.getString("voucher_num"));
			
			Customer customer = new Customer();
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setCustomer_name(rs.getString("customer_name"));
			customer.setCustomer_phone(rs.getString("customer_phone"));
			customer.setCustomer_address(rs.getString("customer_address"));
			customer.setCustomer_email(rs.getString("customer_email"));

			Ticket ticket = new Ticket();
			ticket.setScheduleDetail(this.scheduleDetailRepo.findById(String.valueOf(rs.getInt("schedule_detail_id"))));
			ticket.setSeatDetail(this.seatDetailRepo.findById(String.valueOf(rs.getInt("seat_detail_id"))));
			ticket.setStatus(rs.getString("status"));
			ticket.setDate(rs.getString("date"));

			saleVoucher.setCustomer(customer);
			saleVoucher.setTicket(ticket);
			
			saleVoucher.setTotal_price(Double.parseDouble(rs.getString("total_price")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleVoucher;
	}

	public Movie mapToMovie(Movie movie, ResultSet rs) {
		try {
			movie.setMovie_id(rs.getInt("movie_id"));
			movie.setMovie_name(rs.getString("movie_name"));

			Director director = new Director();
			director.setDirector_id(rs.getInt("director_id"));
			director.setDirector_name(rs.getString("director_name"));

			Actor actor = new Actor();
			actor.setActor_id(rs.getInt("actor_id"));
			actor.setActor_name(rs.getString("actor_name"));

			Actress actress = new Actress();
			actress.setActress_id(rs.getInt("actress_id"));
			actress.setActress_name(rs.getString("actress_name"));

			MovieType movieType = new MovieType();
			movieType.setMovieType_id(rs.getInt("movie_type_id"));
			movieType.setMovieType_name(rs.getString("movie_type_name"));

			movie.setDirector(director);
			movie.setActor(actor);
			movie.setActress(actress);
			movie.setMovieType(movieType);

			movie.setDuration(rs.getString("duration"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;
	}

	public Ticket mapToTicket(Ticket ticket, ResultSet rs) {
		try {
			ticket.setTicket_id(rs.getInt("ticket_id"));
			ticket.setDate(rs.getString("date"));
			ticket.setStatus(rs.getString("status"));
			
//			SeatDetail seatDetail = new SeatDetail();
//			seatDetail.setSeat(null);
//			seatDetail.setTheatre(null);
//			seatDetail.setSeat_detail_id(rs.getInt("seat_detail_id"));
//			
//			ticket.setSeatDetail(seatDetail);
			ticket.setSeatDetail(this.seatDetailRepo.findById(String.valueOf(rs.getInt("seat_detail_id"))));
			ticket.setScheduleDetail(this.scheduleDetailRepo.findById(String.valueOf(rs.getInt("schedule_detail_id"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public ScheduleDetail mapToScheduleDetail(ScheduleDetail scheduleDetail, ResultSet rs) {
		try {
			scheduleDetail.setSchedule_detail_id(rs.getInt("schedule_detail_id"));

			Section section = new Section();
			section.setSection_id(rs.getInt("section_id"));
			section.setStart_time(rs.getString("start_time"));
			section.setEnd_time(rs.getString("end_time"));
			
			scheduleDetail.setSection(section);
			scheduleDetail.setSchedule(this.scheduleRepo.findById(String.valueOf(rs.getInt("schedule_id"))));
			scheduleDetail.setschedule_date(rs.getString("schedule_detail_date"));
			// scheduleDetail.setSchedule(schedule);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleDetail;
	}
	
	public Schedule mapToSchedule(Schedule schedule, ResultSet rs) {
		try {
			schedule.setSchedule_id(rs.getInt("schedule_id"));

			Theatre theatre = new Theatre();
			theatre.setTheatre_id(rs.getInt("theatre_id"));
			theatre.setTheatre_name(rs.getString("theatre_name"));

			schedule.setTheatre(theatre);

			schedule.setStart_date(rs.getString("start_date"));
			schedule.setEnd_date(rs.getString("end_date"));

			Movie movie = new Movie();
			movie.setMovie_id(rs.getInt("movie_id"));
			movie.setMovie_name(rs.getString("movie_name"));
			schedule.setMovie(movie);

			schedule.setNum_of_tickets(rs.getString("num_of_tickets"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedule;
	}

	public SeatDetail mapToSeatDetail(SeatDetail seatDetail,ResultSet rs) {
		
		try {
			seatDetail.setSeat_detail_id(rs.getInt("seat_detail_id"));
			
			Theatre theatre = new Theatre();
			theatre.setTheatre_id(rs.getInt("theatre_id"));
			theatre.setTheatre_name(rs.getString("theatre_name"));

			seatDetail.setTheatre(theatre);
			
			Seat seat = new Seat();
			seat.setSeat_id(rs.getInt("seat_id"));
			seat.setSeatName(rs.getString("seat_name"));
			seat.setSeat_type(rs.getString("seat_type"));
			seat.setPrice(rs.getDouble("price"));
			
			seatDetail.setSeat(seat);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return seatDetail;
		
	}

}
