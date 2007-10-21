package net.events.extensions;


import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.ERXStatelessComponent;

/**
 * This component displays a batch navigation inside a given container (wrap the compponent in whatever
 * container you like). The "back" and "forward" links float right, the rest is standard to the container.
 * 
 * @binding - displayGroup - the displaygroup to act on
 *  
 * @author cug
 */
public class EVBatchNavigationBar extends ERXStatelessComponent {

    private NSArray pageNumbers;
	private Integer selectedPage;

	public EVBatchNavigationBar(WOContext context) {
        super(context);
    }

    /**
     * the displaygroup, directly from the binding
     * 
     * @return - the displaygroup
     */
    public WODisplayGroup displayGroup () {
    	return (WODisplayGroup) this.valueForBinding("displayGroup");
    }
    
    /**
     * set the displaygroup
     * @param dg
     */
    public void setDisplayGroup (WODisplayGroup dg) {
    	this.setValueForBinding(dg, "displayGroup");
    }
 
    /**
     * determines whether we are displaying the last batch in a batching displaygroup
     * 
     * @return true, if we are on the last batch/page
     */
    public boolean isLastBatch () {
    	WODisplayGroup dg = (WODisplayGroup) parent().valueForKey ("displayGroup");
    	if ((dg.batchCount() - 1) * dg.numberOfObjectsPerBatch() > dg.indexOfFirstDisplayedObject())
    		return false;
    	else return true;
    }
    
    /**
     * determines whether we are on the first batch/page in a batching displaygroup
     * 
     * @return true, if we are on the first batch/page
     */
    public boolean isFirstBatch () {
    	WODisplayGroup dg = (WODisplayGroup) parent().valueForKey ("displayGroup");
    	if (dg.indexOfLastDisplayedObject() <= dg.numberOfObjectsPerBatch()) {
    		return true;
    	}
    	else return false; 
    }
    
    public void setShowPageNavigation (String b) {
    	this.setValueForBinding(b, "showPageNavigation");
    }
    
    public boolean showPageNavigation () {
    	return this.booleanValueForBinding("showPageNavigation") && this.displayGroup().batchCount() > 1;
    }
    
	public int currentPageBatch () {
		return (displayGroup().currentBatchIndex()-1) / 10;
	}
	
	/**
	 * @return Returns the pageNumbers.
	 */
	public NSArray getPageNumbers() {

		if (displayGroup() != null) {
			NSMutableArray temp = new NSMutableArray();
			
			for (int i = 0; i < displayGroup().batchCount() - (currentPageBatch() * 10); i++) {
				if (i == 10) 
					break;
				
				temp.addObject(new Integer(i+1 + (currentPageBatch() * 10)));
			}
			this.pageNumbers = temp;
		}
		else {
		}
		
		return this.pageNumbers;
	}
	/**
	 * @param pageNumbers The pageNumbers to set.
	 */
	public void setPageNumbers(NSArray pageNumbers) {
		this.pageNumbers = pageNumbers;
	}
	/**
	 * @return Returns the selectedPage.
	 */
	public Integer getSelectedPage() {
		return this.selectedPage;
	}
	/**
	 * @param selectedPage The selectedPage to set.
	 */
	public void setSelectedPage(Integer selectedPage) {
		this.selectedPage = selectedPage;
	}
	
	public WOComponent gotoNextPagebatch() {
		this.displayGroup().setCurrentBatchIndex((currentPageBatch() + 1) * 10 + 1);
		
		return this.context().page();
	}
	
	public WOComponent gotoPreviousPagebatch () {
		this.displayGroup().setCurrentBatchIndex((currentPageBatch() - 1) * 10 + 10);
		return null;
	}
	
	public WOComponent gotoSelectedPage () {
		displayGroup().setCurrentBatchIndex(selectedPage.intValue());
		return this.context().page();
	}
	
	public boolean showLinkForPageNumber () {
		return (selectedPage.intValue() == displayGroup().currentBatchIndex()); 
	}

	public boolean showBackForPageBatches () {
		return (!(currentPageBatch() == 0));
	}
	
	public boolean showForwardForPageBatches () {
		return (!(displayGroup().batchCount() / 10 == currentPageBatch())); 
	}
}