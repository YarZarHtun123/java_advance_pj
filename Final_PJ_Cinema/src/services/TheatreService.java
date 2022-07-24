package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import entities.Theatre;
import entities.Ticket;
import shared.mapper.Mapper;

public class TheatreService {

	private final Mapper theatreMapper;
	private final DBConfig dbConfig;
	
	public TheatreService() {
		// TODO Auto-generated constructor stub
		this.theatreMapper = new Mapper();
		this.dbConfig = new DBConfig();
	}
	
	
	public void createTheatre(Theatre theatre) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Insert into theatre (theatre_name,total_seat) values (?,?)");
			ps.setString(1, theatre.getTheatre_name());
			ps.setString(2, theatre.getTotal_seat());
			ps.executeUpdate();
            ps.close();
            
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Already Exists");
		}
		
	}
	
	public void updateTheatre(String id, Theatre theatre) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Update theatre set theatre_name=?,total_seat=? where theatre_id =?");

			ps.setString(1, theatre.getTheatre_name());
			ps.setString(2, theatre.getTotal_seat());
			ps.setString(3, id);

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// if(e instanceof MySQLIntegrityConstraintViolationException)
			JOptionPane.showMessageDialog(null, "Already Exits");
		}
	}
	
	public void deleteTheatre(String id) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("delete from theatre where theatre_id = '" + id + "'");

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "!!!!!");
		}
	}
	
	public List<Theatre> findAllTheatre() {

        List<Theatre> theatreList = new ArrayList<>();
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM theatre";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Theatre theatre = new Theatre();
                theatreList.add(this.theatreMapper.mapToTheatre(theatre, rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return theatreList;

    }

	 public Theatre findTheatreById(String id) {
	        Theatre theatre = new Theatre();

	        try (Statement st = this.dbConfig.getConnection().createStatement()) {

	            String query = "SELECT * FROM theatre WHERE theatre_id = " + id + "";

	            ResultSet rs = st.executeQuery(query);

	            while (rs.next()) {
	                this.theatreMapper.mapToTheatre(theatre, rs);
	            }
	            System.out.println(id);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return theatre;
	  }
	 
	 public Theatre findTheatreIDByName(String name) {
	        Theatre theatre = new Theatre();

	        try (Statement st = this.dbConfig.getConnection().createStatement()) {

	            String query = "SELECT * FROM theatre WHERE theatre_name = " + name + "";

	            ResultSet rs = st.executeQuery(query);

	            while (rs.next()) {
	                this.theatreMapper.mapToTheatre(theatre, rs);
	            }
	            //System.out.println(name);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return theatre;
	  }
	 
	

}
