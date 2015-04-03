package org.inspire.decorator;

import org.displaytag.decorator.TableDecorator;
import org.inspire.tablebean.Ideals;
import org.inspire.tablebean.Video;

public class IdealDecorator extends TableDecorator{

	public String getIpicpath()
	{
		Ideals ideal = (Ideals)getCurrentRowObject();        
		String spic = ideal.getIpicpath();
		String sName = ideal.getIname();
		return "<table><tr><td>" +
				"<img src=\""+spic+"\" alt=\"Preview Not Available\" width=\"100px\" height =\"100px\"/>" +
				"</td></tr>" +
				"<tr><td>" +
				sName +
				"</td></tr>" +
				"</table>";
	}
	
	public String getVid()
	{
		Ideals ideal= (Ideals)getCurrentRowObject();        
		String id = ideal.getIid();
		return "<input type=\"checkbox\" id=\""+id+"\"/>";
	}
}
