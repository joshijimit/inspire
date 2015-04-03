package org.inspire.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.StoryBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Language;
import org.inspire.tablebean.Stories;

import com.ibatis.sqlmap.client.SqlMapClient;

public class STShowStoriesAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		try {
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			StoryBean bean = (StoryBean) form;

			String method = bean.getMethod();
			if (method != null && method.equals("insert")) {
				String[] strUpList = null;
				if (bean.getUpdate() != null && !bean.getUpdate().equals("")) {
					strUpList = bean.getUpdate().split(",");

				}

				int id = 0;
				if (strUpList != null) {
					for (int i = 0; i < strUpList.length; i++) {
						id = Integer.parseInt(strUpList[i]);
						sqlMap.update("updateStory", id);
					}
					res.setAttribute("msg",
							"Approved Selected Records successfully.");

				} else {
					Stories t = new Stories();
					t.setApproved("0");
					t.setDeleted("0");
					t.setLastupdated(new Date());
					String sTitle = null;
					String sContent = null;
					String language = null;
					try {
						sTitle = bean.getStitle();
						sContent = bean.getScontent();
						language = bean.getSel_SugLang_Id();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t.setStitle(sTitle);
					t.setScontent(sContent);
					t.setLanguage(language);
					sqlMap.insert("insertStories", t);

					res.setAttribute("msg",
							"You can see your Story after being approved.");
				}
			}

			ArrayList<Language> language = new ArrayList<Language>();
			language = (ArrayList<Language>) sqlMap
					.queryForList("getLanguageList");
			if (language.size() != 0) {
				bean.setLagList(language);
				bean.setSel_SugLang_Id("1");
				bean.setSugLagList(language);
			}
			if (bean.getSel_Lang_Id() != null) {
				bean.setSel_Lang_Id(bean.getSel_Lang_Id());
			} else {
				bean.setSel_Lang_Id("1");
			}

			ArrayList<Stories> stories = new ArrayList<Stories>();
			if (res.getSession().getAttribute("sessionName") != null
					&& res.getSession().getAttribute("sessionName").equals(
							"admin@inspire.com")) {
				stories = (ArrayList<Stories>) sqlMap
						.queryForList("getAllStoriesForApprove");
			} else {
				if (res.getParameter("ID") != null
						&& !res.getParameter("ID").equals("")) {
					stories = (ArrayList<Stories>) sqlMap.queryForList(
							"getStoryByID", res.getParameter("ID").toString());
				} else {
					stories = (ArrayList<Stories>) sqlMap.queryForList(
							"getAllStories", bean.getSel_Lang_Id());
				}
			}
			if (stories.size() != 0) {
				int pageNo = 1;
				if (res.getParameter("d-49653-p") != null) {
					pageNo = Integer.parseInt(res.getParameter("d-49653-p")
							.toString());
				}
				Integer intSize = new Integer(stories.size());

				res.setAttribute("size", intSize);
				res.setAttribute("title", stories.get(pageNo - 1).getStitle());
				res.setAttribute("result", stories);
			}
			res
					.setAttribute("chkAll",
							"<input type=\"checkbox\" id=\"chkAll\" onclick=\"selectAll()\"/>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("success");
	}
}
