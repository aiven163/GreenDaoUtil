package com.aiven.mdl;

import java.util.HashMap;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
/**
 * 表实体
 * @项目名称   你我金融 APP客户端(Android)
 * @类名称   TabEntity  
 * @创建人   Aiven
 * @Email  aiven163@sina.com
 * @创建时间 2015-7-10 下午6:28:09 
 * @类描述   TODO
 */
public class TabEntity {

	private Entity entity;
	private HashMap<String, Property> propertyMap;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void addProperty(Property property) {
		if (this.propertyMap == null) {
			propertyMap = new HashMap<>();
		}
		propertyMap.put(property.getPropertyName(), property);
	}

	public Property getProperty(String name) {
		return propertyMap.get(name);
	}

}
