package at.fhooe.im620.restaurantfinder.shared;

public class BusinessHour {

	private Weekday weekday;

	private Time start;

	private Time end;

	public BusinessHour(Weekday weekday, Time start, Time end) {
		this.weekday = weekday;
		this.start = start;
		this.end = end;
	}

	public Weekday getWeekday() {
		return weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	public Time getStartTime() {
		return start;
	}

	public void setStartTime(Time start) {
		this.start = start;
	}

	public Time getEndTime() {
		return end;
	}

	public void setEndTime(Time end) {
		this.end = end;
	}

}
