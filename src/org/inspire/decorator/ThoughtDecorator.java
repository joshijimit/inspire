package org.inspire.decorator;

import org.displaytag.decorator.TableDecorator;
import org.inspire.tablebean.Thoughts;

public class ThoughtDecorator extends TableDecorator {

	public String getTid() {
		Thoughts thought = (Thoughts) getCurrentRowObject();
		int id = thought.getTid();
		return "<input type=\"checkbox\" id=\"" + id + "\"/>";
	}

	public String getTcontent() {

		Thoughts thought = (Thoughts) getCurrentRowObject();
		String tAuth = thought.getTauther();
		String tCont = thought.getTcontent();
		StringBuffer strTable = new StringBuffer();
		strTable.append("<table width=\"85%\" align=\"center\">");
		strTable.append("<tr>");
		strTable.append("<td align=\"center\">");
		strTable.append(tCont);
		strTable.append("</td>");
		strTable.append("</tr>");
		strTable.append("<tr>");
		strTable.append("<td align=\"right\">");
		strTable.append(" - "+tAuth);
		strTable.append("</td>");
		strTable.append("</tr>");
		strTable.append("</table>");

		return strTable.toString();
	}

}
