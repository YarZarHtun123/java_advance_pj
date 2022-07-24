package repositories;

import java.util.List;

import entities.Seat;
import entities.SeatDetail;
import entities.Theatre;

public interface SeatDetailRepo {
	  //List<Seat> findSeatDetailBySeatId(String seatId);

	    //List<Theatre> findSeatDetailByTheatreId(String theatre);

	    //void createSeat(SeatDetail seatDetail);

	    //void updateSeat(String id, SeatDetail seatDetail);

	    //void deleteSeatDetail(String id);

	    SeatDetail findById(String seatDetail_id);

	    List<SeatDetail> findAllSeat();
}
