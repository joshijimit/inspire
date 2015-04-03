package org.inspire.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ContectBean extends ActionForm{

	private String name = null;
	private String email = null;
	private String subject = null;
	private String content = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors error = new ActionErrors();
		if(getName() == null || getName().equals(""))
		{
			error.add("Name.",new ActionMessage("name"));			
		}
		if(getEmail() == null || getEmail().equals(""))
		{
			error.add("Name.",new ActionMessage("email"));			
		}
		if(getSubject() == null || getSubject().equals(""))
		{
			error.add("Name.",new ActionMessage("subject"));			
		}
		if(getContent() == null || getContent().equals(""))
		{
			error.add("Name.",new ActionMessage("content"));			
		}
		return error;
	}
	
	
}
