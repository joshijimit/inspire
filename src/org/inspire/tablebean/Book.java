package org.inspire.tablebean;

import java.util.Date;

public class Book {
	
	private int bid;
	private String bpath;
	private String btitle;
	private String bicon;
	private String bauthor;
	private String added_by;
	private Date added_date;
	private String approved;
	private String deleted;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBpath() {
		return bpath;
	}
	public void setBpath(String bpath) {
		this.bpath = bpath;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBicon() {
		return bicon;
	}
	public void setBicon(String bicon) {
		this.bicon = bicon;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
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
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
}
