package org.inspire.bean;

import org.apache.struts.action.ActionForm;

public class CommentBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comment_detail = null;
	private	String commented_by = null;
	private String commenter_name = null;
	private String commenter_website = null;
	private String commenter_email = null;
	private String blog_id = null;

	public String getComment_detail() {
		return comment_detail;
	}

	public void setComment_detail(String comment_detail) {
		this.comment_detail = comment_detail;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}

	public String getCommented_by() {
		return commented_by;
	}

	public void setCommented_by(String commented_by) {
		this.commented_by = commented_by;
	}

	public String getCommenter_name() {
		return commenter_name;
	}

	public void setCommenter_name(String commenter_name) {
		this.commenter_name = commenter_name;
	}

	public String getCommenter_website() {
		return commenter_website;
	}

	public void setCommenter_website(String commenter_website) {
		this.commenter_website = commenter_website;
	}

	public String getCommenter_email() {
		return commenter_email;
	}

	public void setCommenter_email(String commenter_email) {
		this.commenter_email = commenter_email;
	}

}
