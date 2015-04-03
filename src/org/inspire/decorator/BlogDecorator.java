package org.inspire.decorator;

import org.displaytag.decorator.TableDecorator;
import org.inspire.tablebean.Blog;

public class BlogDecorator extends TableDecorator {

	public String getBlog_id() {
		Blog blog = (Blog) getCurrentRowObject();
		String id = blog.getBlog_id();
		return "<a href=\"BGI.do?ID=" + id + "\">Edit</a>";
	}
	
	public String getPosted_by() {
		Blog blog = (Blog) getCurrentRowObject();
		String id = blog.getBlog_id();
		return "<a href=\"BGL.do?ID=" + id + "\">Delete</a>";
	}

}
