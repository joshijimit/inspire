package org.inspire.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

public class MyRequestProcessor extends RequestProcessor {

	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {

		if (request.getParameter("lang") != null
				&& !request.getParameter("lang").toString().equals("")) {
			String strLang = request.getParameter("lang").toString();
			if(strLang.equals("English"))
			{
			request.getSession().setAttribute(
					"org.apache.struts.action.LOCALE", new Locale("en","US"));
			}
			else if(strLang.equals("Japanese"))
			{
				request.getSession().setAttribute(
						"org.apache.struts.action.LOCALE", new Locale("ja","JP"));
				}
			else if(strLang.equals("Gujarati"))
			{
				request.getSession().setAttribute(
						"org.apache.struts.action.LOCALE", new Locale("gu","IN"));
				}
			else if(strLang.equals("Hindi"))
			{
				request.getSession().setAttribute(
						"org.apache.struts.action.LOCALE", new Locale("hn","IN"));
			}
			else if(strLang.equals("Marathi"))
			{
				request.getSession().setAttribute(
						"org.apache.struts.action.LOCALE", new Locale("mt","IN"));
			}
			
		}
		return true;
	}

}
