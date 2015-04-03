package org.inspire.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.SignupBean;
import org.inspire.logic.Constant;
import org.inspire.logic.EnDecLogic;
import org.inspire.logic.MSMailSenderLogic;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Users;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SUSignupAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		SignupBean bean = (SignupBean) form;
		EnDecLogic enlogic = new EnDecLogic();
		SqlMapClient sqlMap = null;
		Users user = new Users();
		try {
			enlogic.setUp();
			sqlMap = DBProxy.getSqlMapInstance();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str = res.getParameter("activated1");

		if (str != null && !str.equals("")) {
			// bytes = strArr[1].getBytes();

			/*
			 * String decrypted = null; try { decrypted =
			 * enlogic.decrypt(bytes); } catch (InvalidKeyException e1) { //
			 * TODO Auto-generated catch block e1.printStackTrace(); } catch
			 * (BadPaddingException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); } catch (IllegalBlockSizeException e1) { //
			 * TODO Auto-generated catch block e1.printStackTrace(); } String[]
			 * values = decrypted.split("&");
			 */

			String act = res.getParameter("activated1");
			String email = res.getParameter("email1");
			Users us = new Users();
			us.setActivated("1");
			us.setEmail(email);
			us.setUFname(email.substring(0, email.indexOf('@')));
			if (act.equals("yes")) {
				try {
					sqlMap.update("updateUser", us);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			PrintWriter writer;
			try {
				writer = response.getWriter();

				writer.write("Your account is being activated. <a href="
						+ Constant.URL + ">Login</a>");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
		if (res.getParameter("email") != null) {

			String email = res.getParameter("email").toString();
			try {
				if (IsEmailExists(email, sqlMap)) {
					res
							.setAttribute("msg",
									"Email is already Registered. Please Enter different Email Address.");
					res.setAttribute("session", "session");
				} else {
					String smtpServer = "smtp.gmail.com";
					String to =email;
					String from = "joshijimit@gmail.com";
					String subject = "Welcome to Inspiration Temple.";
					String pass = getPassword(8);

					String body = "<html><body>Dear "
							+ email.substring(0,
									email.indexOf('@'))
							+ " <br><br>Welcome to Inspiration inc.<br>"
							+ " <br>Below are your Login Detail.<br><br>"
							+ "Email :"
							+ email
							+ "<br>"
							+ "Passward :"
							+ pass
							+ "<br><br>"
							+ "Please click below link to confirm your email<br>"
							+Constant.URL + res.getContextPath()
							+ "/SU.do?" + "email1=" + email
							+ "&activated1=yes"
							+ "<br><br>Regards<br>Jimit</body></html>";

					user.setActivated("0");
					user.setEmail(email);
					user.setPass(pass);
					user.setUFname(email.substring(0,
							email.indexOf('@')));
					sqlMap.insert("insertUser", user);
					MSMailSenderLogic logic = new MSMailSenderLogic();
					logic.send(smtpServer, to, from, subject, body);
					res.setAttribute("msg",
							"Please check your mail for Login Detail.");
					res.setAttribute("session", "session");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapping.findForward("success");
	}

	public static String getPassword(int n) {
		char[] pw = new char[n];
		int c = 'A';
		int r1 = 0;
		for (int i = 0; i < n; i++) {
			r1 = (int) (Math.random() * 3);
			switch (r1) {
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}

	private boolean IsEmailExists(String strEmail, SqlMapClient sqlMap)
			throws Exception {
		String strPass = (String) sqlMap.queryForObject("getPass", strEmail);
		if (strPass != null && !strPass.equals("")) {
			return true;
		}
		return false;
	}
}
