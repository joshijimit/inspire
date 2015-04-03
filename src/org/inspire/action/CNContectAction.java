package org.inspire.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.inspire.bean.ContectBean;
import org.inspire.logic.MSMailSenderLogic;

public class CNContectAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {
		
		ContectBean bean = (ContectBean) form;		
		
		String smtpServer = "smtp.gmail.com";
		String to = "joshijimit@gmail.com";
		String from = bean.getEmail();
		String subject = bean.getSubject();
		String name = bean.getName();		
		String body = bean.getContent();
		
		StringBuffer buffer = new StringBuffer(); 
		buffer.append("<html>");
		buffer.append("<body>");
		buffer.append("<table>");
		buffer.append("<tr>");
		buffer.append("<td>");
		buffer.append("<h2>"+name+"</h2>("+from+") has sent you mail. the Content of the mail is as below.");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td>");
		buffer.append(body);
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</body>");
		buffer.append("</html>");
		
		
		MSMailSenderLogic logic = new MSMailSenderLogic();		
		logic.send(smtpServer, to, from, subject, buffer.toString());
		res
		.setAttribute("msg",
				"You message has been sent successfully.");
		return mapping.findForward("success");
	}
}
