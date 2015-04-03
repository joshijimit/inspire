package org.inspire.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Ideals;
import org.inspire.tablebean.Stories;
import org.inspire.tablebean.Thoughts;
import org.inspire.tablebean.Video;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HMShowHomeAction extends Action {

	static Logger log = Logger.getLogger(
			HMShowHomeAction.class.getName()); 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		try {			
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			
			ArrayList<Thoughts> thought = (ArrayList<Thoughts>) sqlMap
											.queryForList("getThoughtsForHome");
			ArrayList<Stories> story = (ArrayList<Stories>) sqlMap
											.queryForList("getStoriesForHome");
			ArrayList<Video> video = (ArrayList<Video>) sqlMap
											.queryForList("getVideosForHome");
			ArrayList<Ideals> ideal = (ArrayList<Ideals>) sqlMap
											.queryForList("getIdealsForHome");
			
			for (Video vd : video) {
				vd.setVcontent(vd.getVcontent().replace("width=\"480\" height=\"385\"", ""));
			} 
			
			for (Stories str : story) {
				str.setScontent(str.getScontent());
			}
				
			
			res.setAttribute("resTH", thought);
			res.setAttribute("resST", story);
			res.setAttribute("resVD", video);
			res.setAttribute("resID", ideal);
			
		} catch (Exception e) {
			log.error("Error occured",e);
		}

		return mapping.findForward("success");
	}
}
