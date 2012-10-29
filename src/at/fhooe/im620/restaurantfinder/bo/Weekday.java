package at.fhooe.im620.restaurantfinder.bo;

public enum Weekday {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

	private final String label;

	private Weekday() {
		label = name().toLowerCase();
	}

	public String getLabel() {
		return label;
	}
}
