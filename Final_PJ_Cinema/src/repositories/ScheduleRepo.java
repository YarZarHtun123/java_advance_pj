package repositories;

import java.util.List;

import entities.*;

public interface ScheduleRepo {
	List<Schedule> findScheduleByMovieId(String movieId);

    List<Schedule> findScheduleByTheatreId(String theatreId);

    void createSchedule(Schedule schedule);

    void updateSchedule(String id, Schedule schedule);

    void deleteSchedule(String id);

    Schedule findById(String schedule_Id);

    List<Schedule> findAllSchedule();
}
