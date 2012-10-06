package at.fhooe.im620.restaurantfinder.shared;

import java.util.List;

public class Restaurant {

	private Long id;

	private String name;

	private Address address;

	private Category category;

	private List<ContactInfo> contactInfos;

	private List<Tag> tags;

	private List<BusinessHour> hours;

	private List<DayRange> closedDays;

	public Restaurant(String name, Address address, Category category) {
		this.name = name;
		this.address = address;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<BusinessHour> getHours() {
		return hours;
	}

	public void setHours(List<BusinessHour> hours) {
		this.hours = hours;
	}

	public List<DayRange> getClosedDays() {
		return closedDays;
	}

	public void setClosedDays(List<DayRange> closedDays) {
		this.closedDays = closedDays;
	}

}
