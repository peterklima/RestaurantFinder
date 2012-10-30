package at.fhooe.im620.restaurantfinder.controller;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.Category;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

public class CategoryController {

	private GenericDAO<Category> categoryDAO;

	/**
	 * the model for {@link Category}
	 */
	private DataModel<Category> categoryModel;

	/**
	 * current Category
	 */
	private Category category;

	// // getter and setters

	public GenericDAO<Category> getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(GenericDAO<Category> categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public DataModel<Category> getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(DataModel<Category> categoryModel) {
		this.categoryModel = categoryModel;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// // restaurant methods

	public DataModel<Category> getAllCategories() {
		setCategoryModel(new ListDataModel<Category>(getCategoryDAO().getAllEntities()));
		return getCategoryModel();
	}

	public List<Category> getParentCategories() {
		List<Category> categories = getCategoryDAO().getAllEntities();
		Category element = new Category();
		element.setId((long) 0);
		element.setName("none");
		element.setParentId((long) 0);
		categories.add(0, element);
		return categories;
	}

	public String addCategory() {
		setCategory(new Category());
		return "addCategory"; // proceed to addCategory.xhtml
	}

	public String doAddCategory() {
		getCategoryDAO().saveOrUpdateEntity(getCategory());
		return addCategory();
	}

	public String editCategory() {
		setCategory((Category) getCategoryModel().getRowData()); // get selected element
		return "addCategory";
	}

	public String doEditCategory() {
		getCategoryDAO().saveOrUpdateEntity(getCategory());
		return addCategory();
	}

	public String deleteCategory() {
		setCategory((Category) getCategoryModel().getRowData()); // get selected element
		getCategoryDAO().deleteEntity(getCategory());
		return "addCategory";
	}
}
