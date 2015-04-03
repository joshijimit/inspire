package org.inspire.tablebean;


public class Blog {

	private String blog_id;
	private String blog_title;
	private String blog_content;
	private String category_id;
	private String category_name;
	private String comment_Count;
	private String posted_by;
	private String posted_date;
	private String del_flag;
	private String is_published;

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_Name) {
		this.category_name = category_Name;
	}

	public String getComment_Count() {
		return comment_Count;
	}

	public void setComment_Count(String comment_Count) {
		this.comment_Count = comment_Count;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getIs_published() {
		return is_published;
	}

	public void setIs_published(String is_published) {
		this.is_published = is_published;
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

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

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
}
