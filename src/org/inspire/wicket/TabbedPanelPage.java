package org.inspire.wicket;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class TabbedPanelPage extends WebPage
{
	public TabbedPanelPage()
	{
		List tabs = new ArrayList();
		tabs.add(new AbstractTab(new Model("Thought"))
		{
			public Panel getPanel(String panelId)
			{
				return new TabPanel1(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model("Story"))
		{
			public Panel getPanel(String panelId)
			{
				return new TabPanel2(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model("Idol"))
		{
			public Panel getPanel(String panelId)
			{
				return new TabPanel3(panelId);
			}
		});

		add(new AjaxTabbedPanel("tabs", tabs));
	}

	private static class TabPanel1 extends Panel
	{
		
		public TabPanel1(String id)
		{
			super(id);
		}
	};

	private static class TabPanel2 extends Panel
	{
		public TabPanel2(String id)
		{
			super(id);
		}
	};
	private static class TabPanel3 extends Panel
	{
		public TabPanel3(String id)
		{
			super(id);
		}
	};
}