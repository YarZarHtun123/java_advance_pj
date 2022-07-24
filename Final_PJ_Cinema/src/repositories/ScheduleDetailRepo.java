package repositories;

import java.util.List;

import entities.*;

public interface ScheduleDetailRepo {
	 List<ScheduleDetail> findScheduleDetailByScheduleId(String scheduleId);

	    List<ScheduleDetail> findScheduleDetailBySectionId(String sectionId);

	    void createScheduleDetail(ScheduleDetail scheduleDetail);

	    void updateScheduleDetail(String id, ScheduleDetail scheduleDetail);

	    void deleteScheduleDetail(String id);

	    ScheduleDetail findById(String scheduledetail_Id);

	    List<ScheduleDetail> findAllScheduleDetail();
}
