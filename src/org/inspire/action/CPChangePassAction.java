package org.inspire.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.UserBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Users;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CPChangePassAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		try {
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			UserBean bean =(UserBean)form;
			String email = null;
			String oldPass = null;
			String newPass = null;
			String confirmPass = null;
			String userPass = null;
			Users user = new Users();

			if(res.getParameter("action") != null)
			{
				return mapping.findForward("success");
			}
			
			
			if (res.getSession().getAttribute("sessionName") != null) {
				
				email = res.getSession().getAttribute("sessionName").toString();
				oldPass = bean.getOldPass();
				newPass = bean.getNewPass();
				confirmPass = bean.getConfirmPass();
				Users us = new Users();
				us.setEmail(res.getSession().getAttribute("sessionName").toString());
				userPass = (String)sqlMap.queryForObject("getPass",
						us);
				
				List<String> error = new ArrayList<String>();
				boolean missing = false;
				if (oldPass.trim().equals("")) {
					error.add("Please Enter Old Password");
					missing = true;
				}else{
					bean.setOldPass(oldPass);
				}
				if (newPass.trim().equals("")) {
					error.add("Please Enter New Password");
					missing = true;
				}else{
					bean.setNewPass(newPass);
				}
				if (confirmPass.trim().equals("")) {
					error.add("Please Enter Confirm Password");
					missing = true;
				}else{
					bean.setConfirmPass(confirmPass);
				}
				if (missing) {
					res.setAttribute("error", error);
					return mapping.findForward("success");
				}
				if (oldPass.equals(userPass)) {
					if (newPass.endsWith(confirmPass)) {
						user.setEmail(email);
						user.setPass(newPass);
						sqlMap.update("changePass", user);
					} else {
						error.add("Your New Password not match with Confirm Password.");
						res.setAttribute("error", error);
						return mapping.findForward("success");
					}
				} else {

					error.add("Please enter correct old password.");
					res.setAttribute("error", error);
					return mapping.findForward("success");
				}
			}
			res.setAttribute("msg", "Password successfully changed");
			return mapping.findForward("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("failure");

		}

	}
}
