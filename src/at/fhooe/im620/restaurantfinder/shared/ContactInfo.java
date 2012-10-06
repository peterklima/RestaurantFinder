package at.fhooe.im620.restaurantfinder.shared;

public class ContactInfo {

	private Long id;

	private String type;

	private String info;

	public ContactInfo(String type, String info) {
		this.type = type;
		this.info = info;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
