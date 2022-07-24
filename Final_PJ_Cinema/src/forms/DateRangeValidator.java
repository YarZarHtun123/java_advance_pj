package forms;
import java.util.Date;

public class DateRangeValidator {

	//test
    private final Date startDate;
    private final Date endDate;

    public DateRangeValidator(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinRange(Date testDate) {

        return testDate.getTime() >= startDate.getTime() &&
                testDate.getTime() <= endDate.getTime();
    }

}