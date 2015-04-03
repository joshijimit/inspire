package org.inspire.bean;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.inspire.tablebean.Category;

public class NewBlogBean extends ActionForm {

	
	private String title = null;
	private String content = null;
	private List<Category> catList = null; 
	private String sel_Category_ID = null;
	private String postedDate = null;
	private String action = null;
	
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getSel_Category_ID() {
		return sel_Category_ID;
	}

	public void setSel_Category_ID(String sel_Category_ID) {
		this.sel_Category_ID = sel_Category_ID;
	}

	
	public List<Category> getCatList() {
		return catList;
	}

	public void setCatList(List<Category> catList) {
		this.catList = catList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	

}
