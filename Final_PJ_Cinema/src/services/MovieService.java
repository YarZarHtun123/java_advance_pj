package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.*;
import shared.mapper.Mapper;

public class MovieService {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper MovieMapper;
	
	public MovieService() {
		this.dbConfig=new DBConfig();
		this.MovieMapper=new Mapper();
	}
	
	public void createMovie(Movie Movie) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO movie(movie_name,director_id,actor_id,actress_id,movie_type_id,duration)values(?,?,?,?,?,?);");
			 
			 ps.setString(1,Movie.getMovie_name());
			 ps.setInt(2, Movie.getDirector().getDirector_id());
			 ps.setInt(3, Movie.getActor().getActor_id());
			 ps.setInt(4, Movie.getActress().getActress_id());
			 ps.setInt(5, Movie.getMovieType().getMovieType_id());
			 ps.setString(6, Movie.getDuration());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Movie> findAllMovie() {
	     List<Movie> MovieList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	    	 String query = "SELECT * FROM movie\n" +
	                    "INNER JOIN director\n" +
	                    "ON director.director_id = movie.director_id\n" +
	                    "INNER JOIN actor\n" +
	                    "ON actor.actor_id = movie.actor_id "+
	                    "INNER JOIN actress\n" +
	                    "ON actress.actress_id = movie.actress_id\n" +
	                    "INNER JOIN movie_type\n" +
	                    "ON movie_type.movie_type_id = movie.movie_type_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 Movie Movie = new Movie();
	             MovieList.add(this.MovieMapper.mapToMovie(Movie, rs));
	             
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	    
	     return MovieList;
	}
	
	public Movie findMovieById(String id) {
		 Movie Movie = new Movie();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM movie "+
						"INNER JOIN director\n" +
	                    "ON director.director_id = movie.director_id\n" +
	                    "INNER JOIN actor\n" +
	                    "ON actor.actor_id = movie.actor_id "+
	                    "INNER JOIN actress\n" +
	                    "ON actress.actress_id = movie.actress_id\n " +
	                    "INNER JOIN movie_type\n" +
	                    "ON movie_type.movie_type_id = movie.movie_type_id\n" +
	                    "WHERE movie_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.MovieMapper.mapToMovie(Movie, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Movie;
	}
	
	public void updateMovie(String id, Movie Movie) {
		try {

            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("UPDATE movie SET movie_name=?, director_id=?, actor_id=?, actress_id=?, movie_type_id=?, duration=?  WHERE movie_id = ?");

            ps.setString(1, Movie.getMovie_name());
            ps.setString(2, String.valueOf(Movie.getDirector().getDirector_id()));
            ps.setString(3, String.valueOf(Movie.getActor().getActor_id()));
            ps.setString(4, String.valueOf(Movie.getActress().getActress_id()));
            ps.setString(5, String.valueOf(Movie.getMovieType().getMovieType_id()));
            ps.setString(6, Movie.getDuration());
            
            ps.setString(7, String.valueOf(id));

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
 	}
	
	public List<Movie> findMovieByDirectorId(String directorId) {
        List<Movie> movieList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM movie\n" +
            		"INNER JOIN director\n" +
                    "ON director.director_id = movie.director_id\n" +
                    "INNER JOIN actor\n" +
                    "ON actor.actor_id = movie.actor_id"+
                    "INNER JOIN actress\n" +
                    "ON actress.actress_id = movie.actress_id\n" +
                    "INNER JOIN movie_type\n" +
                    "ON movie_type.movie_type_id = movie.movie_type_id\n" +
                    "WHERE movie.director_id = " + directorId + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	Movie movie = new Movie();
                movieList.add(this.MovieMapper.mapToMovie(movie, rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public List<Movie> findMovieByActorId(String actorId) {
        List<Movie> movieList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM movie\n" +
            		"INNER JOIN director\n" +
                    "ON director.director_id = movie.director_id\n" +
                    "INNER JOIN actor\n" +
                    "ON actor.actor_id = movie.actor_id"+
                    "INNER JOIN actress\n" +
                    "ON actress.actress_id = movie.actress_id\n" +
                    "INNER JOIN movie_type\n" +
                    "ON movie_type.movie_type_id = movie.movie_type_id\n" +
                    "WHERE movie.actor_id = " + actorId + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	Movie movie = new Movie();
                movieList.add(this.MovieMapper.mapToMovie(movie, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }
    
    public List<Movie> findMovieByActressId(String actressId) {
        List<Movie> movieList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM movie\n" +
            		"INNER JOIN director\n" +
                    "ON director.director_id = movie.director_id\n" +
                    "INNER JOIN actor\n" +
                    "ON actor.actor_id = movie.actor_id"+
                    "INNER JOIN actress\n" +
                    "ON actress.actress_id = movie.actress_id\n" +
                    "INNER JOIN movie_type\n" +
                    "ON movie_type.movie_type_id = movie.movie_type_id\n" +
                    "WHERE movie.actress_id = " + actressId + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	Movie movie = new Movie();
                movieList.add(this.MovieMapper.mapToMovie(movie, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }
    
    public List<Movie> findMovieByMovieTypeId(String movieTypeId) {
        List<Movie> movieList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM movie\n" +
            		"INNER JOIN director\n" +
                    "ON director.director_id = movie.director_id\n" +
                    "INNER JOIN actor\n" +
                    "ON actor.actor_id = movie.actor_id"+
                    "INNER JOIN actress\n" +
                    "ON actress.actress_id = movie.actress_id\n" +
                    "INNER JOIN movie_type\n" +
                    "ON movie_type.movie_type_id = movie.movie_type_id\n" +
                   "WHERE movie.movie_type_id = " + movieTypeId + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	Movie movie = new Movie();
                movieList.add(this.MovieMapper.mapToMovie(movie, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }
    
}
