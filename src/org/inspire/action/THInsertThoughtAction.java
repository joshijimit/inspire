package org.inspire.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.ThoughtBean;
import org.inspire.bean.ThoughtINBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Thoughts;

import com.ibatis.sqlmap.client.SqlMapClient;

public class THInsertThoughtAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		ThoughtBean bean = (ThoughtBean) form;

		try {
			//PrintWriter pr = response.getWriter();

			String[] strUpList = null;
			if (res.getParameter("update") != null) {
				strUpList = res.getParameter("update").split(",");

			}
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			Thoughts t = new Thoughts();
			int id = 0;
			if (strUpList != null) {
				for (int i = 0; i < strUpList.length; i++) {
					id = Integer.parseInt(strUpList[i]);
					sqlMap.update("updateThoughts", id);
				}
				res.setAttribute("msg", "Approved Selected Records successfully. <a href=\"TH.do\">Back</a>");
				
			} else {
				t.setApproved("0");
				t.setDeleted("0");
				t.setLastupdated(new Date());
				String s = null;
				String s1 = null;

				s = bean.getTauther();
				s1 = bean.getTcontent();

				t.setTauther(s);
				t.setTcontent(s1);
				sqlMap.insert("insertThoughts", t);
				res.setAttribute("msg","You can see your Thought after being approved. <a href=\"TH.do\">Back</a>");
			}
			return mapping.findForward("success");

		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}

	}
}
