package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Director;
import shared.mapper.Mapper;

public class DirectorService {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper directorMapper;
	
	public DirectorService() {
		this.dbConfig=new DBConfig();
		this.directorMapper=new Mapper();
	}
	
	public void createDirector(Director director) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO director(director_name)values(?);");
			 
			 ps.setString(1,director.getDirector_name());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Director> findAllDirector() {
	     List<Director> directorList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM Director order by Director_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 Director director = new Director();
	             directorList.add(this.directorMapper.mapToDirector(director, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return directorList;
	}
	
	public Director findDirectorById(String id) {
		 Director director = new Director();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM Director WHERE Director_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.directorMapper.mapToDirector(director, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return director;
	}
	
	public void updateDirector(String id, Director director) {
 		try {
 			PreparedStatement ps = this.dbConfig.getConnection()
                 .prepareStatement("UPDATE director SET director_name=? WHERE director_id=?");

 			ps.setString(1, director.getDirector_name());
 			ps.setString(2, id);
 			ps.executeUpdate();
 			ps.close();
 		} catch (Exception e) {

 			e.printStackTrace();
 		}
 	}
}
