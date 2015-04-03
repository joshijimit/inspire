package org.inspire.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Blog;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BGBlogListAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		try {

			if (res.getSession().getAttribute("sessionName") != null) {
				SqlMapClient sqlMap = DBProxy.getSqlMapInstance();

				if (res.getParameter("ID") != null
						&& !res.getParameter("ID").equals("")) {
					sqlMap.update("deleteBlog", res.getParameter("ID").toString());
				}
				ArrayList<Blog> blog = null;
				String sessionName = res.getSession().getAttribute(
						"sessionID").toString();
				blog = (ArrayList<Blog>) sqlMap.queryForList("getBlogList",
						sessionName);
				res.setAttribute("result", blog);
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("success");
	}
}
