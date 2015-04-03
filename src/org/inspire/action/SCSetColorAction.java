package org.inspire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SCSetColorAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest res,HttpServletResponse response){
		
		if(res.getParameter("color") != null && !res.getParameter("color").equals(""))
		{
			String color = res.getParameter("color");
			if(color.equals("Blue"))
			{
				res.getSession().setAttribute("backcolor", "menubodyBlue");
			}
			else if(color.equals("original")) {
				res.getSession().setAttribute("backcolor", "menubody");
			}
			
		}else
		{
			res.getSession().setAttribute("backcolor", "menubody");
		}
		return mapping.findForward("success");
	}
}
