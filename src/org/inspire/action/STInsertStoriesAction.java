package org.inspire.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.StoryBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Stories;

import com.ibatis.sqlmap.client.SqlMapClient;

public class STInsertStoriesAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		try {
			PrintWriter pr = response.getWriter();
			String[] strUpList = null;
			if (res.getParameter("update") != null) {
				strUpList = res.getParameter("update").split(",");

			}
			int id = 0;
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			if (strUpList != null) {
				for (int i = 0; i < strUpList.length; i++) {
					id = Integer.parseInt(strUpList[i]);
					sqlMap.update("updateStory", id);
				}

				pr
						.write("<div id=\"out\" style=\"color: RED;	font-size: 14px;"
								+ "font-weight: bold;\">Approved Selected Records successfully."
								+ "<a href=\"ST.do\"> Back</a></div>");
				pr.flush();
				pr.close();
			} else {
				Stories t = new Stories();
				t.setApproved("0");
				t.setDeleted("0");
				t.setLastupdated(new Date());
				String sTitle = null;
				String sContent = null;
				String language = null;
				
				StoryBean sbean = (StoryBean) form;
				try {
					sTitle = sbean.getStitle();
					sContent = sbean.getScontent();
					language = sbean.getSel_SugLang_Id();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t.setStitle(sTitle);
				t.setScontent(sContent);
				t.setLanguage(language);
				
				sqlMap.insert("insertStories", t);

				pr
						.write("<div id=\"out\" style=\"color: RED;	font-size: 14px;"
								+ "font-weight: bold;\">You can see your Story after being approved."
								+ "<a href=\"ST.do\"> Back</a></div>");
				pr.flush();
				pr.close();

			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}

	}
}
