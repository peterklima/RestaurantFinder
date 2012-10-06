package at.fhooe.im620.restaurantfinder.shared;

import java.util.Date;

public class DayRange {

	private Date startDay;

	private Date endDay;

	public DayRange(Date startDay, Date endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

}
