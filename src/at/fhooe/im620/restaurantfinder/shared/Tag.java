package at.fhooe.im620.restaurantfinder.shared;

public class Tag {

	private Long id;

	private String name;

	public Tag(String name) {
		this.name = name;
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

}
