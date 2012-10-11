/**
 * 
 */
package at.fhooe.im620.restaurantfinder.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.Restaurant;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

/**
 * @author Peter Klima
 *
 */
public class RestaurantFinderController {
	
	private GenericDAO<Restaurant> restaurantDAO;
	
	/**
	 * the model for {@link Restaurant}
	 */
	private DataModel<Restaurant> restaurantModel;

	/**
	 * current Restaurant
	 */
	private Restaurant restaurant;
	
	//// getter and setters

	public GenericDAO<Restaurant> getRestaurantDAO() {
		return restaurantDAO;
	}

	public void setRestaurantDAO(GenericDAO<Restaurant> restaurantDAO) {
		this.restaurantDAO = restaurantDAO;
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

	//// restaurant methods

	public DataModel<Restaurant> getAllRestaurants(){
		setRestaurantModel(new ListDataModel<Restaurant>(getRestaurantDAO().getAllEntities()));
		return getRestaurantModel();
	}

	public String addRestaurant(){
		setRestaurant(new Restaurant());
		return "addRestaurant"; // proceed to addRestaurant.xhtml
	}
	
	public String doAddRestaurant(){
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index"; // go back to index.xhtml
	}

	public String editRestaurant(){
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		return "editRestaurant";
	}
	
	public String doEditRestaurant(){
		getRestaurantDAO().saveOrUpdateEntity(getRestaurant());
		return "index";
	}

	public String deleteRestaurant(){
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		getRestaurantDAO().deleteEntity(getRestaurant());
		return "index";
	}
	
	public String showRestaurant(){
		setRestaurant((Restaurant) getRestaurantModel().getRowData()); // get selected element
		return "showRestaurant";
	}

	
		
}
