package org.inspire.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.ThoughtBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Language;
import org.inspire.tablebean.Thoughts;

import com.ibatis.sqlmap.client.SqlMapClient;

public class THShowThoughtsAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		ThoughtBean bean = (ThoughtBean) form;

		Integer intSize = null;
		try {

			String strAuth = bean.getAuther();
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();

			String method = bean.getMethod();
			if (method != null && method.equals("insert")) {
				String[] strUpList = null;
				if (bean.getUpdate() != null && !bean.getUpdate().equals("")) {
					strUpList = bean.getUpdate().split(",");

				}

				Thoughts t = new Thoughts();
				int id = 0;
				if (strUpList != null) {
					for (int i = 0; i < strUpList.length; i++) {
						id = Integer.parseInt(strUpList[i]);
						sqlMap.update("updateThoughts", id);
					}
					res.setAttribute("msg",
							"Approved Selected Records successfully.");

				} else {
					t.setApproved("0");
					t.setDeleted("0");
					t.setLastupdated(new Date());
					String s = null;
					String s1 = null;
					String language = null;

					s = bean.getTauther();
					s1 = bean.getTcontent();
					language = bean.getSel_SugLang_Id();

					t.setTauther(s);
					t.setTcontent(s1);
					t.setLanguage(language);

					sqlMap.insert("insertThoughts", t);
					res.setAttribute("msg",
							"You can see your Thought after being approved.");
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

			ArrayList<Thoughts> thought = null;
			int pageNo = 1;
			if (res.getParameter("d-1341821-p") != null) {
				pageNo = Integer.parseInt(res.getParameter("d-1341821-p")
						.toString());
			}
			String strCount = null;

			int size = 0;
			int startIndex = 0;
			if (strAuth != null && !strAuth.equals("")) {
				
				Map paramM = new HashMap();
				paramM.put("auth", "%" + strAuth + "%");
				paramM.put("language", bean.getSel_Lang_Id());
				
				thought = (ArrayList<Thoughts>) sqlMap.queryForList(
						"getAllThoughtsByAuther", paramM);
				strCount = sqlMap.queryForObject("getThoughtsByAutherCount",
						paramM).toString();
				size = Integer.parseInt(strCount);
				startIndex = (pageNo * 5) - 5;
				intSize = new Integer(size);
			} else {

				if (res.getSession().getAttribute("sessionName") != null
						&& res.getSession().getAttribute("sessionName").equals(
								"admin@inspire.com")) {

					strCount = sqlMap.queryForObject("getApproveThoughtsCount")
							.toString();
					size = Integer.parseInt(strCount);
					startIndex = (pageNo * 5) - 5;
					Map paramM = new HashMap();
					paramM.put("startIndex", startIndex);
					paramM.put("length", 5);
					thought = (ArrayList<Thoughts>) sqlMap.queryForList(
							"getAllThoughtsForApprove", paramM);

				} else {

					strCount = sqlMap.queryForObject("getThoughtsCount",
							bean.getSel_Lang_Id()).toString();
					size = Integer.parseInt(strCount);
					startIndex = (pageNo * 5) - 5;
					Map paramM = new HashMap();
					paramM.put("startIndex", startIndex);
					paramM.put("length", 5);
					paramM.put("language", bean.getSel_Lang_Id());
					thought = (ArrayList<Thoughts>) sqlMap.queryForList(
							"getAllThoughts", paramM);

				}
				intSize = new Integer(size);

			}

			res.setAttribute("size", intSize);
			res.setAttribute("result", thought);
			res
					.setAttribute("chkAll",
							"<input type=\"checkbox\" id=\"chkAll\" onclick=\"selectAll()\"/>");
		} catch (Exception e) {

			e.printStackTrace();
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
}
