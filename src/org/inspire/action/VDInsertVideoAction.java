package org.inspire.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Video;

import com.ibatis.sqlmap.client.SqlMapClient;

public class VDInsertVideoAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest res,HttpServletResponse response){
		
		
			try {
				Video t = new Video();
				t.setApproved("0");
				t.setDeleted("0");
				t.setAdded_date(new Date());
				if(res.getSession().getAttribute("sessionName") != null)
					t.setAdded_by(res.getSession().getAttribute("sessionName").toString());
				else
					t.setAdded_by("Anonymous");
				String s = null;
				String s1 = null;
				try {
					s = new String(res.getParameter("title").getBytes(),"UTF-8");
					s1 = new String(res.getParameter("cont").getBytes(),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t.setVtitle(s);
				t.setVcontent(s1);
				
				SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
				sqlMap.insert("insertVideo",t);			
				return null;
			}catch(Exception e){
				e.printStackTrace();
				return mapping.findForward("failure");
			}
		
		
		
	}
}
