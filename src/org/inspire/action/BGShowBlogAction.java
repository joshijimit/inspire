package org.inspire.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.ShowBlogBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Blog;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BGShowBlogAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		ShowBlogBean bean = (ShowBlogBean) form;
		try {
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			ArrayList<Blog> blog = null;

			blog = (ArrayList<Blog>) sqlMap.queryForList("getAllBlogs");
			if(blog.size() == 0)
			{
				res.setAttribute("msg", "No Blog Found to Display.");
			}else
			{
				res.setAttribute("result", blog);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("failure");

		}
		return mapping.findForward("success");
	}
}
