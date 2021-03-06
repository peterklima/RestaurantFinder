/**
 * 
 */
package at.fhooe.im620.restaurantfinder.controller;

import java.util.HashMap;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.Address;
import at.fhooe.im620.restaurantfinder.bo.BusinessHours;
import at.fhooe.im620.restaurantfinder.bo.Category;
import at.fhooe.im620.restaurantfinder.bo.ContactInfo;
import at.fhooe.im620.restaurantfinder.bo.DayRange;
import at.fhooe.im620.restaurantfinder.bo.Restaurant;
import at.fhooe.im620.restaurantfinder.bo.Tag;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

/**
 * @author Peter Klima
 * 
 */
public class RestaurantFinderController {

	private GenericDAO<Restaurant> restaurantDAO;
	private GenericDAO<Category> categoryDAO;
	private GenericDAO<BusinessHours> businessHourDAO;
	private GenericDAO<Address> addressDAO;
	private GenericDAO<Tag> tagDAO;

	/**
	 * the model for {@link Restaurant}
	 */
	private DataModel<Restaurant> restaurantModel;

	/**
	 * current Restaurant
	 */
	private Restaurant restaurant;

	/**
	 * current Address
	 */
	private Address address;

	/**
	 * the model for {@link ContactInfo}
	 */
	private DataModel<ContactInfo> contactInfoModel;

	/**
	 * current Contact info
	 */
	private ContactInfo contactInfo;

	// // getter and setters

	public GenericDAO<Restaurant> getRestaurantDAO() {
		return restaurantDAO;
	}

	public void setRestaurantDAO(GenericDAO<Restaurant> restaurantDAO) {
		this.restaurantDAO = restaurantDAO;
	}

	public GenericDAO<Category> getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(GenericDAO<Category> categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public GenericDAO<BusinessHours> getBusinessHourDAO() {
		return businessHourDAO;
	}

	public void setBusinessHourDAO(GenericDAO<BusinessHours> businessHourDAO) {
		this.businessHourDAO = businessHourDAO;
	}

	public GenericDAO<Address> getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(GenericDAO<Address> addressDAO) {
		this.addressDAO = addressDAO;
	}

	public GenericDAO<Tag> getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(GenericDAO<Tag> tagDAO) {
		this.tagDAO = tagDAO;
	}

	public DataModel<Restaurant> getRestaurantModel() {
		return restaurantModel;
	}

	public void setRestaurantModel(DataModel<Restaurant> restaurantModel) {
		this.restaurantModel = restaurantModel;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public DataModel<ContactInfo> getContactInfoModel() {
		return contactInfoModel;
	}

	public void setContactInfoModel(DataModel<ContactInfo> contactInfoModel) {
		this.contactInfoModel = contactInfoModel;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Long getCategoryId() {
		Restaurant restaurant = getRestaurant();
		if (restaurant == null) {
			return null;
		}
		Category category = restaurant.getCategory();
		if (category == null) {
			return null;
		}
		return category.getId();
	}

	public void setCategoryId(Long id) {
		getRestaurant().setCategory(getCategoryDAO().getEntityById(id));
	}

	// // restaurant methods

	public DataModel<Restaurant> getAllRestaurants() {
		setRestaurantModel(new ListDataModel<Restaurant>(getRestaurantDAO().getAllEntities()));
		return getRestaurantModel();
	}

	public String addRestaurant() {
		setRestaurant(new Restaurant());
		return "updateRestaurant"; // proceed to addRestaurant.xhtml
	}

	public String doUpdateRestaurant() {
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index"; // go back to index.xhtml
	}

	public String editRestaurant() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		return "updateRestaurant";
	}

	public String deleteRestaurant() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		getRestaurantDAO().deleteEntity(getRestaurant());
		return "index";
	}

	public String showRestaurant() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		return "showRestaurant";
	}

	public String saveRestaurant() {
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "showRestaurant";
	}

	// // address methods

	public String addAddress() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		setAddress(new Address());
		return "updateAddress"; // proceed to addAddress.xhtml
	}

	public String doUpdateAddress() {
		getAddressDAO().saveOrUpdateEntity(getAddress());
		getRestaurant().setAddress(getAddress());
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index"; // go back to index.xhtml
	}

	public String editAddress() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		setAddress(getRestaurant().getAddress());
		return "updateAddress";
	}

	// // contact info methods

	public DataModel<ContactInfo> getAllContactInfos() {
		setContactInfoModel(new ListDataModel<ContactInfo>(getRestaurant().getContactInfos()));
		return getContactInfoModel();
	}

	public String addContactInfo() {
		setContactInfo(new ContactInfo());
		getRestaurant().getContactInfos().add(getContactInfo());
		return "updateContactInfo"; // proceed to addContactInfo.xhtml
	}

	public String doUpdateContactInfo() {
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "showRestaurant"; // go back to index.xhtml
	}

	public String editContactInfo() {
		setContactInfo(getContactInfoModel().getRowData());
		return "updateContactInfo";
	}

	public String deleteContactInfo() {
		setContactInfo(getContactInfoModel().getRowData());
		getRestaurant().getContactInfos().remove(getContactInfo());
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "showRestaurant";
	}

	// // businessHours methods

	public String addBusinessHour() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		return "addBusinessHours"; // proceed to addBusinessHours.xhtml
	}

	public void addBusinessHourToRestaurant(BusinessHours businessHour) {
		Restaurant currentRestaurant = getRestaurant();
		currentRestaurant.getHours().add(businessHour);
		getRestaurantDAO().saveOrUpdateEntity(currentRestaurant);
	}

	public void removeBusinessHourFromRestaurant(BusinessHours businessHour) {
		Restaurant currentRestaurant = getRestaurant();
		currentRestaurant.getHours().remove(businessHour);
		getRestaurantDAO().saveOrUpdateEntity(currentRestaurant);
	}

	// // closedDay methods

	public String addClosedDays() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData());
		return "addClosedDays"; // proceed to addClosedDays.xhtml
	}

	public void addClosedDaysToRestaurant(DayRange closedDays) {
		Restaurant currentRestaurant = getRestaurant();
		currentRestaurant.getClosedDays().add(closedDays);
		getRestaurantDAO().saveOrUpdateEntity(currentRestaurant);
	}

	public void removeClosedDaysFromRestaurant(DayRange closedDays) {
		Restaurant currentRestaurant = getRestaurant();
		currentRestaurant.getClosedDays().remove(closedDays);
		getRestaurantDAO().saveOrUpdateEntity(currentRestaurant);
	}

	// // tag methods

	private HashMap<Long, Boolean> checkedTags = new HashMap<Long, Boolean>();

	public HashMap<Long, Boolean> getCheckedTags() {
		return checkedTags;
	}

	public void setCheckedTags(HashMap<Long, Boolean> checkedTags) {
		this.checkedTags = checkedTags;
	}

	public DataModel<Tag> getAllTags() {
		return new ListDataModel<Tag>(getRestaurant().getTags());
	}

	public String assignTags() {
		checkedTags.clear();
		for (Tag tag : restaurant.getTags()) {
			checkedTags.put(tag.getId(), true);
		}
		return "assignTags";
	}

	public String doAssignTags() {
		restaurant.getTags().clear();

		List<Tag> tags = tagDAO.getAllEntities();
		for (Tag tag : tags) {
			if (checkedTags.get(tag.getId())) {
				restaurant.getTags().add(tag);
				restaurantDAO.saveOrUpdateEntity(restaurant);
			}
		}
		return "showRestaurant";
	}

	public String getTagsForEntry() {
		List<Tag> tags = getRestaurantModel().getRowData().getTags();
		if (tags.isEmpty()) {
			return "";
		}
		String tagString = "";
		for (Tag tag : tags) {
			tagString += tag.getName() + ", ";
		}
		return tagString.substring(0, tagString.length() - 2);
	}
}
