/*
 * Created on Sep 22, 2006
 *
 */
package org.objectstyle.wolips.eomodeler;

import java.util.*;

import com.webobjects.eoaccess.*;
import com.webobjects.foundation.*;

public class EOModelProcessor {

	public void processModel(EOModel _model, NSMutableArray _entities, NSMutableDictionary _flags) {
		fixAllowsNullOnSingleTableInheritance(_entities);
	}

	public void processSQL(StringBuffer _sqlBuffer, EOModel _model, NSMutableArray _entities, NSMutableDictionary _flags){
	}

	protected void fixAllowsNullOnSingleTableInheritance(NSArray _entities) {
		Enumeration entitiesEnum = (Enumeration) _entities.objectEnumerator();
		while (entitiesEnum.hasMoreElements()) {
			EOEntity entity = (EOEntity) entitiesEnum.nextElement();
			if (isSingleTableInheritance(entity)) {
				Enumeration attributeEnum = (Enumeration) entity.attributes().objectEnumerator();
				while (attributeEnum.hasMoreElements()) {
					EOAttribute attribute = (EOAttribute) attributeEnum.nextElement();
					if (!isInherited(attribute)) {
						attribute.setAllowsNull(true);
					}
				}
			}
		}
	}

	protected boolean isSingleTableInheritance(EOEntity _entity) {
		EOEntity parentEntity = _entity.parentEntity();
		return parentEntity != null && _entity.externalName() != null && _entity.externalName().equalsIgnoreCase(parentEntity.externalName());
	}

	protected boolean isInherited(EOAttribute _attribute) {
		boolean inherited = false;
		EOEntity parentEntity = _attribute.entity().parentEntity();
		while (parentEntity != null) {
			inherited = (parentEntity.attributeNamed(_attribute.name()) != null);
			parentEntity = parentEntity.parentEntity();
		}
		return inherited;
	}

}
