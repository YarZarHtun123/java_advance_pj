package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Actress;
import shared.mapper.Mapper;

public class ActressService {
	
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper actressMapper;
	
	public ActressService() {
		this.dbConfig=new DBConfig();
		this.actressMapper=new Mapper();
	}
	
	public void createActress(Actress actress) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO actress(actress_name)values(?);");
			 
			 ps.setString(1,actress.getActress_name());
			 ps.executeUpdate();
			 ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Actress> findAllActress() {
	     List<Actress> actressList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM actress order by actress_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 Actress actress = new Actress();
	             actressList.add(this.actressMapper.mapToActress(actress, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return actressList;
	}
	
	public Actress findActressById(String id) {
		 Actress actress = new Actress();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM actress WHERE actress_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.actressMapper.mapToActress(actress, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return actress;
	}
	
	public void updateActress(String id, Actress actress) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
                .prepareStatement("UPDATE actress SET actress_name=? WHERE actress_id=?");

			ps.setString(1, actress.getActress_name());
			ps.setString(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
