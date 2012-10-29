package at.fhooe.im620.restaurantfinder.controller;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.DayRange;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

public class ClosedDayController {

	private RestaurantFinderController restaurantFinderController;
	private GenericDAO<DayRange> closedDayDAO;

	private DataModel<DayRange> closedDayModel;
	private DayRange closedDays = new DayRange();

	// // getter and setters

	public void setRestaurantFinderController(RestaurantFinderController restaurantFinderController) {
		this.restaurantFinderController = restaurantFinderController;
	}

	public RestaurantFinderController getRestaurantFinderController() {
		return this.restaurantFinderController;
	}

	public GenericDAO<DayRange> getClosedDayDAO() {
		return closedDayDAO;
	}

	public void setClosedDayDAO(GenericDAO<DayRange> closedDayDAO) {
		this.closedDayDAO = closedDayDAO;
	}

	public DataModel<DayRange> getClosedDayModel() {
		return closedDayModel;
	}

	public void setClosedDayModel(DataModel<DayRange> closedDayModel) {
		this.closedDayModel = closedDayModel;
	}

	public DayRange getClosedDays() {
		return closedDays;
	}

	public void setClosedDays(DayRange closedDays) {
		this.closedDays = closedDays;
	}

	// // closed day methods

	public DataModel<DayRange> getAllClosedDays() {
		setClosedDayModel(new ListDataModel<DayRange>(getClosedDayDAO().getAllEntities()));
		return getClosedDayModel();
	}

	public String doAddClosedDays() {
		getClosedDayDAO().saveOrUpdateEntity(getClosedDays());
		getRestaurantFinderController().addClosedDaysToRestaurant(getClosedDays());
		return "editClosedDays";
	}

	public String editClosedDays() {
		return "editClosedDays";
	}

	public String deleteClosedDays() {
		return "editClosedDays";
	}

}
