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
import org.inspire.bean.VideoBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Video;

import com.ibatis.sqlmap.client.SqlMapClient;

public class VDShowVideoAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		VideoBean bean = (VideoBean) form;
		Integer intSize = null;
		try {
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			String method = bean.getMethod();
			if (method != null && method.equals("insert")) {

				String[] strUpList = null;
				if (bean.getUpdate() != null && !bean.getUpdate().equals("")) {
					strUpList = bean.getUpdate().split(",");

				}

				Video t = new Video();
				int id = 0;
				if (strUpList != null) {
					for (int i = 0; i < strUpList.length; i++) {
						id = Integer.parseInt(strUpList[i]);
						sqlMap.update("updateVideos", id);
					}
					res.setAttribute("msg",
							"Approved Selected Records successfully.");

				} else {

					t.setApproved("0");
					t.setDeleted("0");
					t.setAdded_date(new Date());
					if (res.getSession().getAttribute("sessionName") != null)
						t.setAdded_by(res.getSession().getAttribute(
								"sessionName").toString());
					else
						t.setAdded_by("Anonymous");
					String s = null;
					String s1 = null;
					s = bean.getVtitle();
					s1 = bean.getVcontent();
					t.setVtitle(s);
					t.setVcontent(s1);

					sqlMap.insert("insertVideo", t);
					res.setAttribute("msg",
							"You can see your Video after being approved.");
				}
			}

			String strTitle = bean.getTitle();

			ArrayList<Video> video = null;
			int pageNo = 1;
			if (res.getParameter("d-49653-p") != null) {
				pageNo = Integer.parseInt(res.getParameter("d-49653-p")
						.toString());
			}
			String strCount = null;

			int size = 0;
			int startIndex = 0;

			if (res.getSession().getAttribute("sessionName") != null
					&& res.getSession().getAttribute("sessionName").equals(
							"admin@inspire.com")) {
				strCount = sqlMap.queryForObject("getAllApproveVideosCount")
						.toString();
				size = Integer.parseInt(strCount);
				startIndex = (pageNo * 2) - 2;
				intSize = new Integer(size);
				Map paramM = new HashMap();
				paramM.put("startIndex", startIndex);
				paramM.put("length", 2);
				video = (ArrayList<Video>) sqlMap.queryForList(
						"getAllApproveVideos", paramM);
			} else {
				if (strTitle != null && !strTitle.equals("")) {
					strCount = sqlMap.queryForObject(
							"getAllVideosByTitleCount", "%" + strTitle + "%")
							.toString();
					size = Integer.parseInt(strCount);
					startIndex = (pageNo * 2) - 2;
					intSize = new Integer(size);
					Map paramM = new HashMap();
					paramM.put("startIndex", startIndex);
					paramM.put("length", 2);
					paramM.put("vtitle", "%" + strTitle + "%");
					video = (ArrayList<Video>) sqlMap.queryForList(
							"getAllVideosByTitle", paramM);
				} else {
					strCount = sqlMap.queryForObject("getAllVideosCount")
							.toString();
					size = Integer.parseInt(strCount);
					startIndex = (pageNo * 2) - 2;
					intSize = new Integer(size);
					Map paramM = new HashMap();
					paramM.put("startIndex", startIndex);
					paramM.put("length", 2);
					video = (ArrayList<Video>) sqlMap.queryForList(
							"getAllVideos", paramM);
				}
			}
			intSize = new Integer(size);
			res.setAttribute("size", intSize);
			res.setAttribute("result", video);
			res
					.setAttribute("chkAll",
							"<input type=\"checkbox\" id=\"chkAll\" onclick=\"selectAll()\"/>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("success");
	}
}
