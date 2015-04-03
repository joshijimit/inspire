package org.inspire.bean;

import org.apache.struts.action.ActionForm;

public class ThoughtINBean extends ActionForm{
	
	private int tid;
	private String content;
	private String auther;
	private String tcontent;
	private String tauther;
	
	
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
