package org.inspire.bean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class IdealBean extends ActionForm {

	private String iname;
	private String name;
	private String idesc;
	private FormFile ipics;
	private String action;
	private String update;

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public FormFile getIpics() {
		return ipics;
	}

	public void setIpics(FormFile ipics) {
		this.ipics = ipics;
	}

}
