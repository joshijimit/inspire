package org.inspire.action;

import java.sql.SQLException;
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
import org.inspire.bean.CommentBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Blog;
import org.inspire.tablebean.Comment;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CMShowCommentsAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		CommentBean bean = (CommentBean) form;
		
		try {
			String name = bean.getCommenter_name();
			String email = bean.getCommenter_email();
			String website = bean.getCommenter_website();
			String detail = bean.getComment_detail();
			String blog_id = bean.getBlog_id();
			String ID = res.getParameter("ID");
			if(ID == null)
			{
				ID = res.getSession().getAttribute("CommentID").toString();
			}
			else
			{
				res.getSession().setAttribute("CommentID", ID);
			}
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date date = new java.util.Date();
			boolean isMissing = false;
			if(res.getParameter("insert")!= null)
			{
			List<String> err = new ArrayList<String>();
			
			if(name == null || name.trim().equals(""))
			{
				err.add("Please Enter your Name");
				isMissing = true;
			}
			if(email == null || email.trim().equals(""))
			{
				err.add("Please Enter your Email Address");
				isMissing = true;
			}
			if(detail == null || detail.trim().equals(""))
			{
				err.add("Please Enter your Comment.");
				isMissing = true;
			}
			if(isMissing)
			{
				res.setAttribute("error", err);
			}
			}
			if (name != null && !name.trim().equals("") && !isMissing) {
				Comment com = new Comment();
				com.setCommenter_name(name);
				com.setCommenter_email(email);
				com.setCommenter_website(website);
				com.setComment_detail(detail);
				com.setCommented_by("0");
				com.setBlog_id(ID);
				com.setDel_flag("0");
				com.setCommented_date(dateFormat.format(date));
				sqlMap.insert("insertComment", com);
				//res.setAttribute("msg", "Comment added successfully.");
			}
			
			ArrayList<Comment> comm = null;
			ArrayList<Blog> blog = null;
			
			if (ID != null && !ID.equals("")) {
				blog = (ArrayList<Blog>) sqlMap
						.queryForList("getBlogByID", ID);
				comm = (ArrayList<Comment>) sqlMap.queryForList(
						"getCommentsByBlogID", ID);
				res.setAttribute("resultCom", comm);
				res.setAttribute("result", blog);
			}
			if(email == null && res.getSession().getAttribute("sessionName") != null)
			{
				bean.setCommenter_email(res.getSession().getAttribute("sessionName").toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("failure");

		}
		return mapping.findForward("success");
	}
}
