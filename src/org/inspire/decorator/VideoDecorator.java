package org.inspire.decorator;

import org.displaytag.decorator.TableDecorator;
import org.inspire.tablebean.Video;

public class VideoDecorator extends TableDecorator{
	
	public String getVid()
	{
		Video video= (Video)getCurrentRowObject();        
		int id = video.getVid();
		return "<input type=\"checkbox\" id=\""+id+"\"/>";
	}

}
