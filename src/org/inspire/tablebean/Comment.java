package org.inspire.tablebean;

public class Comment {
	
	public String comment_id = null;
	public String comment_detail = null;
	public String blog_id = null;
	public String blog_title = null;
	public String blog_content = null;
	public String commented_by = null;
	public String commented_date = null;
	public String commenter_name = null;
	public String commenter_website = null;
	public String commenter_email = null;
	public String upic = null;
	public String posted_by = null;
	public String posted_date = null;
	public String category_name = null;
	public String getPosted_by() {
		return posted_by;
	}
	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}
	public String getPosted_date() {
		return posted_date;
	}
	public void setPosted_date(String posted_date) {
		this.posted_date = posted_date;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getUpic() {
		return upic;
	}
	public void setUpic(String upic) {
		this.upic = upic;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
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
	public String del_flag = null;
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
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
	public String getCommented_date() {
		return commented_date;
	}
	public void setCommented_date(String commented_date) {
		this.commented_date = commented_date;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	
}
