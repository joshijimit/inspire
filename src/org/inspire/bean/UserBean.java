package org.inspire.bean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UserBean extends ActionForm{

	private String fname = null;
	private String mname = null;
	private String lname = null;
	private String email = null;
	private String website = null;
	private FormFile upic = null;
	private String oldPass = null;
	private String newPass = null;
	private String confirmPass = null;
	private String  currentImg = null;
	private FormFile backImg = null;

	public FormFile getBackImg() {
		return backImg;
	}

	public void setBackImg(FormFile backImg) {
		this.backImg = backImg;
	}
	
	public String getCurrentImg() {
		return currentImg;
	}
	public void setCurrentImg(String currentImg) {
		this.currentImg = currentImg;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public FormFile getUpic() {
		return upic;
	}
	public void setUpic(FormFile upic) {
		this.upic = upic;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
	
}
