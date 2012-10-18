package at.fhooe.im620.restaurantfinder.controller;

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

	public String addCategory() {
		setCategory(new Category());
		return "addCategory"; // proceed to addCategory.xhtml
	}

	public String doAddCategory() {
		getCategoryDAO().saveOrUpdateEntity(getCategory());
		return "index"; // go back to index.xhtml
	}

	public String editCategory() {
		setCategory((Category) getCategoryModel().getRowData()); // get selected element
		return "editCategory";
	}

	public String doEditCategory() {
		getCategoryDAO().saveOrUpdateEntity(getCategory());
		return "index";
	}

	public String deleteCategory() {
		setCategory((Category) getCategoryModel().getRowData()); // get selected element
		getCategoryDAO().deleteEntity(getCategory());
		return "index";
	}

	public String showCategory() {
		setCategory((Category) getCategoryModel().getRowData()); // get selected element
		return "showCategory";
	}

}
