package at.fhooe.im620.restaurantfinder.controller;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import at.fhooe.im620.restaurantfinder.bo.Tag;
import at.fhooe.im620.restaurantfinder.dao.GenericDAO;

public class TagController {

	private GenericDAO<Tag> tagDAO;

	/**
	 * the model for {@link Tag}
	 */
	private DataModel<Tag> tagModel;

	/**
	 * current tag
	 */
	private Tag tag;

	// // getter and setters

	public GenericDAO<Tag> getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(GenericDAO<Tag> tagDAO) {
		this.tagDAO = tagDAO;
	}

	public DataModel<Tag> getTagModel() {
		return tagModel;
	}

	public void setTagModel(DataModel<Tag> tagModel) {
		this.tagModel = tagModel;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag Tag) {
		this.tag = Tag;
	}

	// // restaurant methods

	public DataModel<Tag> getAllTags() {
		setTagModel(new ListDataModel<Tag>(getTagDAO().getAllEntities()));
		return getTagModel();
	}
	
	public String listTags(){
		return "listTags";
	}

	public String addTag() {
		setTag(new Tag());
		return "updateTag";
	}

	public String doUpdateTag() {
		getTagDAO().saveOrUpdateEntity(getTag());
		return "listTags"; // go back to index.xhtml
	}

	public String editTag() {
		setTag((Tag) getTagModel().getRowData()); // get selected element
		return "updateTag";
	}

	public String deleteTag() {
		setTag((Tag) getTagModel().getRowData()); // get selected element
		getTagDAO().deleteEntity(getTag());
		return "listTags";
	}

}
