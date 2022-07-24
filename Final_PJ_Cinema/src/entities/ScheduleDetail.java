package entities;

public class ScheduleDetail {
	private int schedule_detail_id;
	private Schedule schedule;
	private Section section;
	private String schedule_date;
	
	public ScheduleDetail() {
		
	}

	public ScheduleDetail(int schedule_detail_id, Schedule schedule, Section section,String schedule_date) {
		super();
		this.schedule_detail_id = schedule_detail_id;
		this.schedule = schedule;
		this.section = section;
		this.schedule_date = schedule_date;
	}

	public int getSchedule_detail_id() {
		return schedule_detail_id;
	}

	public void setSchedule_detail_id(int schedule_detail_id) {
		this.schedule_detail_id = schedule_detail_id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getschedule_date() {
		return schedule_date;
	}

	public void setschedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}
	
	

}
