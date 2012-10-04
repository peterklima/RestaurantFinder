package at.fhooe.im620.restaurantfinder.shared;

import java.util.List;

public class Restaurant {
	
	private Long id;
	
	private String name;
	
	private Address address;
	
	private Category category;
	
	private List<ContactInfo> contactInfos;
	
	private List<Tag> tags;
	
	private List<BusinessHours> hours;
	
	private List<DayRange> closedDays;

}
