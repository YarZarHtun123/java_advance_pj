package entities;

import java.sql.Time;

public class Section {
	private int section_id;
	private String start_time,end_time;
	
	public Section() {}
	
	public Section(int id,String sTime,String eTime) {
		this.section_id=id;
		this.start_time=sTime;
		this.end_time=eTime;
	}

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	
}
