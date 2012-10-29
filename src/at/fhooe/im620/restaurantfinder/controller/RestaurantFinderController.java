/**
 * 
 */
package at.fhooe.im620.restaurantfinder.controller;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.Address;
import at.fhooe.im620.restaurantfinder.bo.BusinessHours;
import at.fhooe.im620.restaurantfinder.bo.Category;
import at.fhooe.im620.restaurantfinder.bo.ContactInfo;
import at.fhooe.im620.restaurantfinder.bo.Restaurant;
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
		return "addRestaurant"; // proceed to addRestaurant.xhtml
	}

	public String doAddRestaurant() {
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index"; // go back to index.xhtml
	}

	public String editRestaurant() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		return "editRestaurant";
	}

	public String doEditRestaurant() {
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index";
	}

	public String deleteRestaurant() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		getRestaurantDAO().deleteEntity(getRestaurant());
		return "index";
	}

	public String showRestaurant() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		return "showRestaurant";
	}

	// // address methods

	public String addAddress() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		setAddress(new Address());
		return "addAddress"; // proceed to addAddress.xhtml
	}

	public String doAddAddress() {
		getAddressDAO().saveOrUpdateEntity(getAddress());
		getRestaurant().setAddress(getAddress());
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index"; // go back to index.xhtml
	}

	public String editAddress() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		setAddress(getRestaurant().getAddress());
		return "editAddress";
	}

	public String doEditAddress() {
		getAddressDAO().saveOrUpdateEntity(getAddress());
		return "index";
	}

	// // contact info methods

	public DataModel<ContactInfo> getAllContactInfos() {
		setContactInfoModel(new ListDataModel<ContactInfo>(getRestaurant().getContactInfos()));
		return getContactInfoModel();
	}
	
	// // businessHours methods

	public String addBusinessHour() {
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		return "addBusinessHours"; // proceed to addBusinessHours.xhtml
	}

	public void addBusinessHourToRestaurant(BusinessHours businessHour) {
		Restaurant currentRestaurant = getRestaurant();
		currentRestaurant.getHours().add(businessHour);
		getRestaurantDAO().saveOrUpdateEntity(currentRestaurant);		
	}
}
