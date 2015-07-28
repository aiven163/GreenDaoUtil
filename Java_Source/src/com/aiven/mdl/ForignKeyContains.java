package com.aiven.mdl;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
/**
 * 外键关联实体
 * @项目名称   你我金融 APP客户端(Android)
 * @类名称   ForignKeyContains  
 * @创建人   Aiven
 * @Email  aiven163@sina.com
 * @创建时间 2015-7-10 下午6:27:52 
 * @类描述   TODO
 */
public class ForignKeyContains {

	/**
	 * 当前表实体
	 */
	private Entity entity;

	/***
	 * 外键属性
	 */
	private Property foreignKey;

	/**
	 * 主表名称
	 */
	private String mainTabName;

	/**
	 * 主表字段
	 */
	private String mainKeyName;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Property getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(Property foreignKey) {
		this.foreignKey = foreignKey;
	}

	public String getMainTabName() {
		return mainTabName;
	}

	public void setMainTabName(String mainTabName) {
		this.mainTabName = mainTabName;
	}

	public String getMainKeyName() {
		return mainKeyName;
	}

	public void setMainKeyName(String mainKeyName) {
		this.mainKeyName = mainKeyName;
	}
	
	
	

}
