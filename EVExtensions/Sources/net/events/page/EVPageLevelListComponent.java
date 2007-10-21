package net.events.page;



import com.webobjects.appserver.WOContext;

public abstract class EVPageLevelListComponent extends EVPageLevelComponent {

    // Standard Table content for list page
    private String tableContent;

	public EVPageLevelListComponent(WOContext aContext) {
		super(aContext);
	}


	/**
	 * @return the tableContent
	 */
	public String tableContent() {
		return this.tableContent;
	}

	/**
	 * @param tableContent the tableContent to set
	 */
	public void setTableContent(String tableContent) {
		this.tableContent = tableContent;
	}
	
}
