package net.events.extensions;


import java.lang.reflect.Method;
import java.util.Enumeration;

import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

public class EVEOUtilities {
	
	public static NSArray attributesForEntityNameAndKey (String entityName, String key) {
		NSArray array = null;
		EOEntity e = EOUtilities.entityNamed(new EOEditingContext(), entityName);
		try {
			Class clazz = Class.forName(e.className());
			Method method = clazz.getDeclaredMethod(key, (Class[]) null);
			
			Object o = method.invoke(clazz, (Object[]) null);
			
			if (o != null && o instanceof NSArray) {
				array = (NSArray) o; 
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return array;
	}
	
	public static NSArray stringAttributesForEntityNameAndKey (String entityName, String key) {
		NSMutableArray tmp = new NSMutableArray (EVEOUtilities.attributesForEntityNameAndKey(entityName, key));
		NSArray attributes = EVEOUtilities.attributesForEntityNameAndKey(entityName, key);
		
		if (tmp != null && tmp.count() > 0) {
			EOEntity entity = EOUtilities.entityNamed(new EOEditingContext(), entityName);
			Enumeration enumeration = attributes.objectEnumerator();
			
			while (enumeration.hasMoreElements()) {
				String name = (String) enumeration.nextElement();
				
				if (!(entity.classDescriptionForInstances().classForAttributeKey(name).getName().equals(String.class.getName()))) {
					tmp.removeObject(name);
				}
			}
			return tmp.immutableClone();
		}
		return null;
	}

}
