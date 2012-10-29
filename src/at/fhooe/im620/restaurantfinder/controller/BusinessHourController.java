package at.fhooe.im620.restaurantfinder.controller;

import java.util.ArrayList;
import java.util.Set;

import javax.faces.convert.ConverterException;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.BusinessHours;
import at.fhooe.im620.restaurantfinder.bo.Time;
import at.fhooe.im620.restaurantfinder.bo.Weekday;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

public class BusinessHourController {

	private RestaurantFinderController restaurantFinderController;
	private GenericDAO<BusinessHours> businessHourDAO;
	private GenericDAO<Time> timeDAO;

	/**
	 * the model for {@link BusinessHour}
	 */
	private DataModel<BusinessHours> businessHourModel;

	/**
	 * current businessHour
	 */
	private BusinessHours businessHour = new BusinessHours();

	// // getter and setters

	public void setRestaurantFinderController(RestaurantFinderController restaurantFinderController) {
		this.restaurantFinderController = restaurantFinderController;
	}

	public RestaurantFinderController getRestaurantFinderController() {
		return this.restaurantFinderController;
	}
	
	public GenericDAO<BusinessHours> getBusinessHourDAO() {
		return businessHourDAO;
	}

	public void setBusinessHourDAO(GenericDAO<BusinessHours> businessHourDAO) {
		this.businessHourDAO = businessHourDAO;
	}

	private GenericDAO<Time> getTimeDAO() {
		return timeDAO;
	}

	public void setTimeDAO(GenericDAO<Time> timeDAO) {
		this.timeDAO = timeDAO;
	}

	public DataModel<BusinessHours> getBusinessHourModel() {
		return businessHourModel;
	}

	public void setBusinessHourModel(DataModel<BusinessHours> businessHourModel) {
		this.businessHourModel = businessHourModel;
	}

	public BusinessHours getBusinessHour() {
		return businessHour;
	}

	public void setBusinessHour(BusinessHours businessHour) {
		this.businessHour = businessHour;
	}

	public Weekday[] getWeekdayValues() {
		return Weekday.values();
	}

	public void setStartTime(String time) {
		String[] token = time.split(":");
		int hour = Integer.parseInt(token[0]);
		int minute = Integer.parseInt(token[1]);
		if (hour < 0 || hour > 24 || minute < 0 || minute > 60) {
			throw new ConverterException("Invalid value for opening time.");
		}
		getBusinessHour().setStart(new Time(hour, minute));
	}
	
	public String getStartTime() {
		return "7:00";
	}
	
	public void setEndTime(String time) {
		String[] token = time.split(":");
		int hour = Integer.parseInt(token[0]);
		int minute = Integer.parseInt(token[1]);
		if (hour < 0 || hour > 24 || minute < 0 || minute > 60) {
			throw new ConverterException("Invalid value for closing time.");
		}
		getBusinessHour().setEnd(new Time(hour, minute));
	}
	
	public String getEndTime() {
		return "12:00";
	}

	// // restaurant methods

	public DataModel<BusinessHours> getAllBusinessHours() {
		Set<BusinessHours> allEntities = getRestaurantFinderController().getRestaurant().getHours();
		setBusinessHourModel(new ListDataModel<BusinessHours>(new ArrayList<BusinessHours>(allEntities)));
		return getBusinessHourModel();
	}

	public String doAddBusinessHour() {
		getTimeDAO().saveOrUpdateEntity(getBusinessHour().getStart());
		getTimeDAO().saveOrUpdateEntity(getBusinessHour().getEnd());
		getBusinessHourDAO().saveOrUpdateEntity(getBusinessHour());
		getRestaurantFinderController().addBusinessHourToRestaurant(getBusinessHour());
		return "editBusinessHour";
	}

	public String editBusinessHour() {
		setBusinessHour((BusinessHours) getBusinessHourModel().getRowData()); // get selected element
		return "editBusinessHour";
	}

	public String doEditBusinessHour() {
		getBusinessHourDAO().saveOrUpdateEntity(getBusinessHour());
		return "editBusinessHour";
	}

	public String deleteBusinessHour() {
		setBusinessHour((BusinessHours) getBusinessHourModel().getRowData()); // get selected element
		getBusinessHourDAO().deleteEntity(getBusinessHour());
		return "index";
	}

	public String showBusinessHour() {
		setBusinessHour((BusinessHours) getBusinessHourModel().getRowData()); // get selected element
		return "editBusinessHour";
	}
}
