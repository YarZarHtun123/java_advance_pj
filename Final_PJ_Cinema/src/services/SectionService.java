package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Section;
import shared.mapper.Mapper;

public class SectionService {
	DBConfig dbConfig;
	ResultSet rs;
	Connection con;
	Statement st;
	final Mapper sectionMapper;
	
	public SectionService() {
		this.dbConfig=new DBConfig();
		this.sectionMapper=new Mapper();
	}
	
	public void createSection(Section Section) {
		 try {
			 PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO section(start_time,end_time)values(?,?);");
			 
			 	ps.setString(1, Section.getStart_time());
	 			ps.setString(2, Section.getEnd_time());
//	 			ps.setTimestamp(1, Timestamp.valueOf(Section.getStart_time()));
//	 			ps.setTimestamp(2, Timestamp.valueOf(Section.getEnd_time()));
	 			ps.executeUpdate();
	 			ps.close();
		 }catch(Exception e) {
			 if(e instanceof SQLException) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Section> findAllSection() {
		
	     List<Section> SectionList = new ArrayList<>();	     
	     try (java.sql.Statement st = this.dbConfig.getConnection().createStatement()) {

	         String query = "SELECT * FROM section order by section_id;";
	         ResultSet rs = st.executeQuery(query);
	         while (rs.next()) {
	        	 Section Section = new Section();
	             SectionList.add(this.sectionMapper.mapToSection(Section, rs));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     //System.out.println(SectionList.isEmpty());
	     return SectionList;
	     
	}
	
	public Section findSectionById(String id) {
		 Section Section = new Section();

			try (java.sql.Statement st=this.dbConfig.getConnection().createStatement())
			{
				String query = "SELECT * FROM section WHERE section_id = " + id + ";";

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					this.sectionMapper.mapToSection(Section, rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Section;
	}
	
	public void updateSection(String id, Section Section) {
 		try {
 			PreparedStatement ps = this.dbConfig.getConnection()
                 .prepareStatement("UPDATE section SET start_time=?,end_time=? WHERE Section_id=?");

 			ps.setTimestamp(1, Timestamp.valueOf(Section.getStart_time()));
 			ps.setTimestamp(1, Timestamp.valueOf(Section.getEnd_time()));
 			ps.setString(3, id);
 			ps.executeUpdate();
 			ps.close();
 		} catch (Exception e) {

 			e.printStackTrace();
 		}
 	}
}
