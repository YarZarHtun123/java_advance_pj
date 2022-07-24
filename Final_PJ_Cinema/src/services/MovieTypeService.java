package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.MovieType;
import shared.mapper.Mapper;

public class MovieTypeService {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper movieTypeMapper;
	
	public MovieTypeService() {
		this.dbConfig=new DBConfig();
		this.movieTypeMapper=new Mapper();
	}
	
	public void createMovieType(MovieType movieType) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO movie_type(movie_type_name)values(?);");
			 
			 ps.setString(1,movieType.getMovieType_name());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<MovieType> findAllMovieType() {
	     List<MovieType> movieTypeList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM movie_type order by movie_type_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 MovieType movieType = new MovieType();
	             movieTypeList.add(this.movieTypeMapper.mapToMovieType(movieType, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return movieTypeList;
	}
	
	public MovieType findMovieTypeById(String id) {
		 MovieType movieType = new MovieType();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM movie_type WHERE movie_type_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.movieTypeMapper.mapToMovieType(movieType, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return movieType;
	}
	
	public void updateMovieType(String id, MovieType movieType) {
 		try {
 			PreparedStatement ps = this.dbConfig.getConnection()
                 .prepareStatement("UPDATE movie_type SET movie_type_name=? WHERE movie_type_id=?");

 			ps.setString(1, movieType.getMovieType_name());
 			ps.setString(2, id);
 			ps.executeUpdate();
 			ps.close();
 		} catch (Exception e) {

 			e.printStackTrace();
 		}
 	}
}
