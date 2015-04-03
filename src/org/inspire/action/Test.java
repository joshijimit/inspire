package org.inspire.action;

import java.util.Calendar;

public class Test {
	
	public static void main(String[] args){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 7);
		System.out.println(c.getTime());
	}
}
