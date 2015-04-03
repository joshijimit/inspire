package org.inspire.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.NewBlogBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Blog;
import org.inspire.tablebean.Category;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BGInsertBlogAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		NewBlogBean bean = (NewBlogBean) form;

		if (res.getParameter("isNew") != null) {
			res.getSession().setAttribute("blogID", "");
		}
		ArrayList<Category> category = new ArrayList<Category>();

		try {

			if (res.getSession().getAttribute("sessionName") != null) {
				SqlMapClient sqlMap = DBProxy.getSqlMapInstance();

				category = (ArrayList<Category>) sqlMap
						.queryForList("getCategory");
				if (category.size() == 0) {
					Category c = new Category();
					c.setCategory_ID("0");
					c.setCategory_Name("--Select--");
					category.add(c);
					bean.setCatList(category);
				} else {
					bean.setCatList(category);
				}
				if (res.getParameter("ID") != null
						&& !res.getParameter("ID").equals("") 
						&& bean.getAction()==null) {

					ArrayList<Blog> blog = new ArrayList<Blog>();
					blog = (ArrayList<Blog>) sqlMap.queryForList("getBlogByID",
							res.getParameter("ID").toString());
					if (blog.size() > 0) {
						Blog blogTableBean = blog.get(0);
						bean.setTitle(blogTableBean.getBlog_title());
						bean
								.setContent(rteSafe(blogTableBean
										.getBlog_content()));
						bean.setSel_Category_ID(blogTableBean.getCategory_id());
						res.getSession().setAttribute("blogID",
								blogTableBean.getBlog_id());
					}

				} else {

					String strCont = "";
					String strTitle = "";
					if (bean.getTitle() != null && !bean.getTitle().equals("")) {

						String str = (String) res.getSession().getAttribute(
								"blogID");
						String strCat = bean.getSel_Category_ID();
						strCont = bean.getContent();
						strTitle = bean.getTitle();
						if (bean.getAction().equals("submit")
								|| bean.getAction().equals("publish")) {
							List<String> error = new ArrayList<String>();
							boolean missing = false;
							if (strCont.trim().equals("")) {
								error.add("Please Select Content");
								missing = true;
							}
							if (missing) {
								res.setAttribute("error", error);
								return mapping.findForward("success");
							}
						}
						if (str != null && !str.equals("")) {

							String ID = str;
							Blog blog = new Blog();
							blog.setBlog_title(strTitle);
							blog.setBlog_content(strCont);
							blog.setCategory_id(strCat);
							blog.setBlog_id(ID);
							if (bean.getAction().equals("publish")) {
								blog.setIs_published("1");
							} else {
								blog.setIs_published("0");
							}
							sqlMap.update("updateBlog", blog);

						} else {

							String strPostedBy = res.getSession().getAttribute(
									"sessionID").toString();

							DateFormat dateFormat = new SimpleDateFormat(
									"yyyy/MM/dd HH:mm:ss");
							java.util.Date date = new java.util.Date();
							Blog blog = new Blog();
							blog.setBlog_title(strTitle);
							blog.setBlog_content(strCont);
							blog.setCategory_id(strCat);
							blog.setPosted_by(strPostedBy);
							blog.setPosted_date(dateFormat.format(date));
							if (bean.getAction().equals("publish"))
								blog.setIs_published("1");
							else
								blog.setIs_published("0");
							blog.setDel_flag("0");
							// Hashtable ht = new Hashtable();
							// ht.put("index","blog_id");

							int intBlogID = (Integer) (sqlMap
									.queryForObject("getBlogID"));

							res.getSession().setAttribute("blogID",
									Integer.toString(intBlogID + 1));
							sqlMap.insert("insertBlog", blog);

						}
						if (bean.getAction() != null && bean.getAction().equals("publish"))
							res.setAttribute("msg",
									"Blog Successfully published");
						else
							res.setAttribute("msg", "Blog Successfully Saved");
					} else {
						if (bean.getAction() != null && (bean.getAction().equals("submit")
								|| bean.getAction().equals("publish"))) {
							List<String> error = new ArrayList<String>();
							boolean missing = false;
							if (strTitle.trim().equals("")) {
								error.add("Please Select Title");
								missing = true;
							}
							if (strCont.trim().equals("")) {
								error.add("Please Select Content");
								missing = true;
							}
							if (missing) {
								res.setAttribute("error", error);
								return mapping.findForward("success");
							}
						}
						Category objCat = (Category) category.get(0);
						bean.setSel_Category_ID(objCat.getCategory_ID());
						res.getSession().setAttribute("blogID", null);
					}

					bean.setContent(rteSafe(strCont));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	public static String rteSafe(String strText) {
		// returns safe code for preloading in the RTE
		String tmpString = strText;

		// convert all types of single quotes
		tmpString = tmpString.replace((char) 145, (char) 39);
		tmpString = tmpString.replace((char) 146, (char) 39);
		tmpString = tmpString.replace("'", "&#39;");

		// convert all types of double quotes
		tmpString = tmpString.replace((char) 147, (char) 34);
		tmpString = tmpString.replace((char) 148, (char) 34);
		// tmpString = tmpString.replace("\"", "\"");

		// replace carriage returns & line feeds
		tmpString = tmpString.replace((char) 10, (char) 32);
		tmpString = tmpString.replace((char) 13, (char) 32);

		return tmpString;
	}
}
