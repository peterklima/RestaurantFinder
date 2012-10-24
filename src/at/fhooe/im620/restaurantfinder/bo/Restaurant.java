package at.fhooe.im620.restaurantfinder.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 281909316735095930L;

	private Long id;
	
	private String name;
	
	private String description;
	
	private Address address;
	
	private String imageUrl;
	
	private float pointsOutOfTen;
	
	private Category category;
	
	private List<ContactInfo> contactInfos;
	
	private Set<Tag> tags;
	
	private Set<BusinessHours> hours;
	
	private Set<DayRange> closedDays;
	
	public Restaurant(){
	}
	
	public Restaurant(Long id, String name, String description, Address address, Category category, String imageUrl, float pointsOutOfTen){
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.category = category;
		this.imageUrl = imageUrl;
		this.pointsOutOfTen = pointsOutOfTen;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getPointsOutOfTen() {
		return pointsOutOfTen;
	}

	public void setPointsOutOfTen(float pointsOutOfTen) {
		this.pointsOutOfTen = pointsOutOfTen;
	}

	@OneToOne
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<BusinessHours> getHours() {
		return hours;
	}

	public void setHours(Set<BusinessHours> hours) {
		this.hours = hours;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<DayRange> getClosedDays() {
		return closedDays;
	}

	public void setClosedDays(Set<DayRange> closedDays) {
		this.closedDays = closedDays;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + ", category=" + category + "]";
	}

}
