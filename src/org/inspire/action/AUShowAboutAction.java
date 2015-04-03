package org.inspire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AUShowAboutAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest res,HttpServletResponse response){
		
		return mapping.findForward("success");
	}
}
