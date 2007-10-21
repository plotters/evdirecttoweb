package net.events.extensions;


import com.webobjects.appserver.WOContext;

import er.extensions.ERXToOneRelationship;

/**
 * Using our own components where necessary for xhtml
 * 
 * @author cug
 *
 */

public class EVToOneRelationship extends ERXToOneRelationship {

    public EVToOneRelationship(WOContext context) {
        super(context);
    }


}