package org.inspire.bean;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.inspire.tablebean.Language;

public class ThoughtBean extends ActionForm{
	
	private int tid;
	private String content;
	private String auther;
	private String tcontent;
	private String tauther;
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
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public String getTauther() {
		return tauther;
	}
	public void setTauther(String tauther) {
		this.tauther = tauther;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	
}
