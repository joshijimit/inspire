package org.inspire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MNShowMenuAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest res,HttpServletResponse response){
		
		String str = res.getParameter("key");
		if(str != null && str.contains("subscribe"))
		{
			res.setAttribute("session", "session");
		}	
		return mapping.findForward("success");
	}
}
