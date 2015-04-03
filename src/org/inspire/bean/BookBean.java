package org.inspire.bean;

import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BookBean extends ActionForm{
	
	private FormFile book = null;
	private String btitle;
	private FormFile bicon = null;
	private String bauthor;
	private String title;
	private String author;
	private String added_by;
	private Date added_date;
	private String method;
	private String update;
	private String action;
	
	public FormFile getBook() {
		return book;
	}
	public void setBook(FormFile book) {
		this.book = book;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public FormFile getBicon() {
		return bicon;
	}
	public void setBicon(FormFile bicon) {
		this.bicon = bicon;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdded_by() {
		return added_by;
	}
	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}
	public Date getAdded_date() {
		return added_date;
	}
	public void setAdded_date(Date added_date) {
		this.added_date = added_date;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
		
}
