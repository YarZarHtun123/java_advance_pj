package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Movie;
import entities.Schedule;
import repositories.ScheduleRepo;
import shared.mapper.Mapper;

public class ScheduleService implements ScheduleRepo {

	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper scheduleMapper;

	public ScheduleService() {
		this.dbConfig = new DBConfig();
		this.scheduleMapper = new Mapper();
	}

	public void createSchedule(Schedule schedule) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO schedule(theatre_id,start_date,end_date,movie_id,num_of_tickets)values(?,?,?,?,?);");

			ps.setInt(1, schedule.getTheatre().getTheatre_id());
			ps.setString(2, schedule.getStart_date());
			ps.setString(3, schedule.getEnd_date());
			ps.setInt(4, schedule.getMovie().getMovie_id());
			ps.setString(5, schedule.getNum_of_tickets());

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			if (e instanceof SQLException) {
				e.printStackTrace();
			}
		}
	}


	public List<Schedule> findAllSchedule() {
		List<Schedule> scheduleList = new ArrayList<>();
		try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM schedule\n" + "INNER JOIN theatre\n"
					+ "ON theatre.theatre_id = schedule.theatre_id\n" + "INNER JOIN movie\n"
					+ "ON movie.movie_id = schedule.movie_id;";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Schedule schedule = new Schedule();
				scheduleList.add(this.scheduleMapper.mapToSchedule(schedule, rs));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scheduleList;
	}

	public Schedule findById(String id) {
		Schedule schedule = new Schedule();

		try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "SELECT * FROM schedule " + "INNER JOIN theatre\n"
					+ "ON theatre.theatre_id = schedule.theatre_id\n" + "INNER JOIN movie\n"
					+ "ON movie.movie_id = schedule.movie_id " + "WHERE schedule_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.scheduleMapper.mapToSchedule(schedule, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedule;
	}
	
	   public int getLatestScheduleId() {
	        int id = 0;
	        try (Statement st = this.dbConfig.getConnection().createStatement()) {

	            String query = "SELECT schedule_id FROM schedule ORDER BY schedule_id DESC";

	            ResultSet rs = st.executeQuery(query);
	            rs.next();
	            id = rs.getInt("schedule_id");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return id;
	    }

	public void updateSchedule(String id, Schedule schedule) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"UPDATE schedule SET theatre_id=?, start_date=?, end_date=?, movie_id=?, num_of_tickets=?  WHERE schedule_id = ?");

			ps.setString(1, String.valueOf(schedule.getTheatre().getTheatre_id()));
			ps.setString(2, schedule.getStart_date());
			ps.setString(3, schedule.getEnd_date());
			ps.setString(4, String.valueOf(schedule.getMovie().getMovie_id()));
			ps.setString(5, String.valueOf(schedule.getNum_of_tickets()));

			ps.setString(6, String.valueOf(id));

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Schedule> findScheduleByTheatreId(String theatreId) {
		List<Schedule> scheduleList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM schedule\n" + "INNER JOIN theatre\n"
					+ "ON theatre.theatre_id = schedule.theatre_id\n" + "INNER JOIN movie\n"
					+ "ON movie.movie_id = theatre.theatre_id" + "WHERE schedule.theatre_id = " + theatreId + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Schedule schedule = new Schedule();
				scheduleList.add(this.scheduleMapper.mapToSchedule(schedule, rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return scheduleList;
	}

	public List<Schedule> findScheduleByMovieId(String movieId) {
		List<Schedule> scheduleList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM schedule\n" + "INNER JOIN theatre\n"
					+ "ON theatre.theatre_id = schedule.theatre_id\n" + "INNER JOIN movie\n"
					+ "ON movie.movie_id = theatre.theatre_id" + "WHERE schedule.movie_id = " + movieId + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Schedule schedule = new Schedule();
				scheduleList.add(this.scheduleMapper.mapToSchedule(schedule, rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return scheduleList;
	}
	
	public int getNewScheduleId() {
		int id = 0;
		try (Statement st = this.dbConfig.getConnection().createStatement()){
			String query = "Select schedule_id from schedule ORDER BY schedule_id DESC";
			
			ResultSet rs = st.executeQuery(query);
			rs.next();
			id = rs.getInt("schedule_id");
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(id);
		return id;
	}

	@Override
	public void deleteSchedule(String id) {
		// TODO Auto-generated method stub
		
	}

}
