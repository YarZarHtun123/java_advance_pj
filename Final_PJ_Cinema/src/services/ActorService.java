package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Actor;
import shared.mapper.Mapper;

public class ActorService {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper actorMapper;
	
	public ActorService() {
		this.dbConfig=new DBConfig();
		this.actorMapper=new Mapper();
	}
	
	public void createActor(Actor actor) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO actor(actor_name)values(?);");
			 
			 ps.setString(1,actor.getActor_name());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Actor> findAllActor() {
	     List<Actor> actorList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM actor order by actor_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 Actor actor = new Actor();
	             actorList.add(this.actorMapper.mapToActor(actor, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return actorList;
	}
	
	public Actor findActorById(String id) {
		 Actor actor = new Actor();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM actor WHERE actor_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.actorMapper.mapToActor(actor, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return actor;
	}
	
	public void updateActor(String id, Actor actor) {
 		try {
 			PreparedStatement ps = this.dbConfig.getConnection()
                 .prepareStatement("UPDATE actor SET actor_name=? WHERE actor_id=?");

 			ps.setString(1, actor.getActor_name());
 			ps.setString(2, id);
 			ps.executeUpdate();
 			ps.close();
 		} catch (Exception e) {

 			e.printStackTrace();
 		}
 	}
}
