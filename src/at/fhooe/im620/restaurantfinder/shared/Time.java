package at.fhooe.im620.restaurantfinder.shared;

public class Time {

	private int hour;

	private int minute;

	public Time() {
		hour = 0;
		minute = 0;
	}

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

}
