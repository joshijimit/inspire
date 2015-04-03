package org.inspire.tablebean;



public class Users {

	private int uid;
	private String email;
	private String pass;
	private String activated;
	private String uFname;
	private String uMname;
	private String uLname;
	private String uWebsite;
	private String uPic;
	
	public String getUMname() {
		return uMname;
	}

	public void setUMname(String mname) {
		uMname = mname;
	}

	public String getULname() {
		return uLname;
	}

	public void setULname(String lname) {
		uLname = lname;
	}

	public String getUWebsite() {
		return uWebsite;
	}

	public void setUWebsite(String website) {
		uWebsite = website;
	}

	public String getUPic() {
		return uPic;
	}

	public void setUPic(String pic) {
		uPic = pic;
	}

	public String getUFname() {
		return uFname;
	}

	public void setUFname(String fname) {
		uFname = fname;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

}
