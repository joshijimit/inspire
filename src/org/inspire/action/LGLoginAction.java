package org.inspire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Users;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LGLoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		SqlMapClient sqlMap = null;
		String usr = res.getParameter("user");
		String pass = res.getParameter("pass");
		if (usr == null || pass == null || usr.equals("") || pass.equals("")) {
			res.setAttribute("error", "User or pass incorrect. Try again");
			return mapping.findForward("success");
		}
		try {
			sqlMap = DBProxy.getSqlMapInstance();
			Users user = new Users();
			user.setEmail(usr);
			user.setPass(pass);
			Users u = (Users) sqlMap.queryForObject("getUser", user);
			if (u != null) {
				if (u.getActivated().equals("1")) {
					res.getSession().setAttribute("sessionID", u.getUid());
					res.getSession().setAttribute("sessionName", u.getEmail());
					res.setAttribute("sessionName", u.getEmail());
				} else if (u.getActivated().equals("0")) {
					res.setAttribute("error", "Acc is not yet activated.Click on the Link sent in you by Mail.");
				} else {
					res.setAttribute("error",
							"User or pass incorrect. Try again");
				}
			}else{
				res.setAttribute("error",
				"User or pass incorrect. Try again");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return mapping.findForward("success");
	}
}
