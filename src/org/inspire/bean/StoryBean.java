package org.inspire.bean;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.inspire.tablebean.Category;
import org.inspire.tablebean.Language;

public class StoryBean extends ActionForm {

	
	private String scontent;
	private String stitle;
	private String method;
	private String update;
	private String sel_Lang_Id;
	private List<Language> lagList;
	private String sel_SugLang_Id;
	private List<Language> sugLagList;
	
	public String getSel_SugLang_Id() {
		return sel_SugLang_Id;
	}
	public void setSel_SugLang_Id(String sel_SugLang_Id) {
		this.sel_SugLang_Id = sel_SugLang_Id;
	}
	public List<Language> getSugLagList() {
		return sugLagList;
	}
	public void setSugLagList(List<Language> sugLagList) {
		this.sugLagList = sugLagList;
	}
	public List<Language> getLagList() {
		return lagList;
	}
	public void setLagList(List<Language> lagList) {
		this.lagList = lagList;
	}
	public String getSel_Lang_Id() {
		return sel_Lang_Id;
	}
	public void setSel_Lang_Id(String sel_Lang_Id) {
		this.sel_Lang_Id = sel_Lang_Id;
	}
	
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

}
