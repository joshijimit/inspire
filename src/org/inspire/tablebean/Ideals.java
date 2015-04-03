package org.inspire.tablebean;

import java.util.Date;


public class Ideals{
	
	private String iid;
	private String iname;
	private String idesc;
	private String ipicpath;
	private String added_by;
	private Date added_date;
	private String approved;
	private String deleted;
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getIpicpath() {
		return ipicpath;
	}
	public void setIpicpath(String ipicpath) {
		this.ipicpath = ipicpath;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getIdesc() {
		return idesc;
	}
	public void setIdesc(String idesc) {
		this.idesc = idesc;
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
