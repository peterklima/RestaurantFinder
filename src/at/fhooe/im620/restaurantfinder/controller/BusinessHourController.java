package at.fhooe.im620.restaurantfinder.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.faces.convert.ConverterException;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.BusinessHours;
import at.fhooe.im620.restaurantfinder.bo.Time;
import at.fhooe.im620.restaurantfinder.bo.Weekday;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

public class BusinessHourController {

	private static final BusinessHourComparator businessHourComparator = new BusinessHourComparator();

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
		return getBusinessHour().getStart().toString();
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
		return getBusinessHour().getEnd().toString();
	}

	// // restaurant methods

	public DataModel<BusinessHours> getAllBusinessHours() {
		List<BusinessHours> businessHourList = getRestaurantFinderController().getRestaurant().getHours();
		java.util.Collections.sort(businessHourList, businessHourComparator);
		setBusinessHourModel(new ListDataModel<BusinessHours>(businessHourList));
		return getBusinessHourModel();
	}

	public String doAddBusinessHour() {
		BusinessHours currentBusinessHour = getBusinessHour();
		getTimeDAO().saveOrUpdateEntity(currentBusinessHour.getStart());
		getTimeDAO().saveOrUpdateEntity(currentBusinessHour.getEnd());
		getBusinessHourDAO().saveOrUpdateEntity(currentBusinessHour);
		getRestaurantFinderController().addBusinessHourToRestaurant(currentBusinessHour);
		BusinessHours newBusinessHour = new BusinessHours();
		newBusinessHour.setWeekday(currentBusinessHour.getWeekday());
		setBusinessHour(newBusinessHour);
		return "addBusinessHours";
	}

	public String editBusinessHour() {
		setBusinessHour((BusinessHours) getBusinessHourModel().getRowData()); // get selected element
		return "editBusinessHours";
	}

	public String doEditBusinessHour() {
		BusinessHours currentBusinessHour = getBusinessHour();
		getTimeDAO().saveOrUpdateEntity(currentBusinessHour.getStart());
		getTimeDAO().saveOrUpdateEntity(currentBusinessHour.getEnd());
		getBusinessHourDAO().saveOrUpdateEntity(currentBusinessHour);
		setBusinessHour(new BusinessHours());
		return "addBusinessHours";
	}

	public String cancelEditBusinessHour() {
		setBusinessHour(new BusinessHours());
		return "addBusinessHours";
	}

	public String deleteBusinessHour() {
		setBusinessHour((BusinessHours) getBusinessHourModel().getRowData()); // get selected element
		BusinessHours currentBusinessHour = getBusinessHour();
		getRestaurantFinderController().removeBusinessHourFromRestaurant(currentBusinessHour);
		getBusinessHourDAO().deleteEntity(currentBusinessHour);
		getTimeDAO().deleteEntity(currentBusinessHour.getStart());
		getTimeDAO().deleteEntity(currentBusinessHour.getEnd());
		return "addBusinessHours";
	}

	static class BusinessHourComparator implements Comparator<BusinessHours> {
		@Override
		public int compare(BusinessHours hourA, BusinessHours hourB) {
			int minutesA = toMinutesFromWeekStart(hourA);
			int minutesB = toMinutesFromWeekStart(hourB);
			return minutesA - minutesB;
		}

		private int toMinutesFromWeekStart(BusinessHours hour) {
			Time start = hour.getStart();
			return hour.getWeekday().ordinal() * 60 * 24 + start.getHour() * 60 + start.getMinute();
		}
	}
}