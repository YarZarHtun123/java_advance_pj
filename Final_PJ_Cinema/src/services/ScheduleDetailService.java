package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.ScheduleDetail;
import repositories.ScheduleDetailRepo;
import shared.mapper.Mapper;

public class ScheduleDetailService implements ScheduleDetailRepo {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper cinemaMapper;
	private ScheduleService scheduleService;

	public ScheduleDetailService() {
		this.dbConfig = new DBConfig();
		this.cinemaMapper = new Mapper();
		this.scheduleService=new ScheduleService();
		this.cinemaMapper.setScheduleRepo(new ScheduleService());
	}
	
	public void createScheduleDetail(ScheduleDetail scheduleDetail) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO schedule_detail(schedule_id,section_id,schedule_detail_date)values(?,?,?);");

			ps.setInt(1, scheduleDetail.getSchedule().getSchedule_id());
			ps.setInt(2, scheduleDetail.getSection().getSection_id());
			ps.setString(3, scheduleDetail.getschedule_date());

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			if (e instanceof SQLException) {
				e.printStackTrace();
			}
		}
	}

	public List<ScheduleDetail> findAllScheduleDetail() {
		List<ScheduleDetail> scheduleDetailList = new ArrayList<>();
		try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM schedule_detail\n" + "INNER JOIN schedule\n"
					+ "ON schedule_detail.schedule_id = schedule.schedule_id\n" + "INNER JOIN section\n"
					+ "ON schedule_detail.section_id = section.section_id\n" + "INNER JOIN movie\n"
					+ "ON movie.movie_id = schedule.movie_id\n" + "INNER JOIN theatre\n"
					+ "ON theatre.theatre_id = schedule.theatre_id ORDER BY schedule_detail.schedule_detail_id;";
			
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				ScheduleDetail scheduleDetail = new ScheduleDetail();
				scheduleDetailList.add(this.cinemaMapper.mapToScheduleDetail(scheduleDetail, rs));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scheduleDetailList;
	}
	
	public List<ScheduleDetail> findCinemaByTheatreName(String theatreName) {
        List<ScheduleDetail> scheduleDetailList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

        	String query = "SELECT * FROM theatre\n"
					+ "WHERE theatre.theatre_name = " + theatreName + ";";
        	System.out.println(theatreName);
        	

			/*
			 * ResultSet rs = st.executeQuery(query);
			 * 
			 * while (rs.next()) { ScheduleDetail scheduleDetail = new ScheduleDetail();
			 * scheduleDetailList.add(this.cinemaMapper.mapToScheduleDetail(scheduleDetail,
			 * rs)); }
			 */

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scheduleDetailList;
    }

	@Override
	public List<ScheduleDetail> findScheduleDetailByScheduleId(String scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleDetail> findScheduleDetailBySectionId(String sectionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateScheduleDetail(String id, ScheduleDetail scheduleDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteScheduleDetail(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScheduleDetail findById(String scheduledetail_Id) {
		ScheduleDetail scheduleDetail = new ScheduleDetail();
		try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM schedule_detail\n" + "INNER JOIN schedule\n"
					+ "ON schedule_detail.schedule_id = schedule.schedule_id\n" + "INNER JOIN section\n"
					+ "ON schedule_detail.section_id = section.section_id\n" + " where schedule_detail_id="+scheduledetail_Id+";";
			
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				this.cinemaMapper.mapToScheduleDetail(scheduleDetail, rs);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scheduleDetail;
	}
}
