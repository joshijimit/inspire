package org.inspire.decorator;

import org.displaytag.decorator.TableDecorator;
import org.inspire.tablebean.Stories;

public class StoryDecorator extends TableDecorator{
	
	public String getSid()
	{
		Stories thought= (Stories)getCurrentRowObject();        
		int id = thought.getSid();
		return "<input type=\"checkbox\" id=\""+id+"\"/>";
	}
	public String getScontent()
	{
		Stories thought= (Stories)getCurrentRowObject();        
		String title = thought.getStitle();
		String content = thought.getScontent();
		StringBuffer buf = new StringBuffer();
		buf.append("<table width=\"100%\">");
		buf.append("<tr>");
		buf.append("<td align=\"center\">");
		buf.append(title);
		buf.append("</td>");
		buf.append("</tr>");
		buf.append("<tr>");
		buf.append("<td align=\"left\">");
		buf.append(content);
		buf.append("</td>");
		buf.append("</tr>");
		buf.append("</table>");
		
		return buf.toString();
	}

}
