package com.aiven.mdl;

import de.greenrobot.daogenerator.PropertyType;

/**
 * 字段属性
 * @项目名称   你我金融 APP客户端(Android)
 * @类名称   Property  
 * @创建人   Aiven
 * @Email  aiven163@sina.com
 * @创建时间 2015-7-10 下午4:45:40 
 * @类描述   TODO
 */
public class DbProperty {

	/**
	 * 字段名称
	 */
	public String name;

	/**
	 * 字段类型
	 */
	public PropertyType type;

	/**
	 * 不为空
	 */
	public boolean notNull;

	/**
	 * 是否是外键
	 */
	public boolean isForeignKey;

	/**
	 * 是否唯一
	 */
	public boolean isUnique;

	/**
	 * 外键对应表 
	 */
	public String foreign_map_tabName;

	/**
	 * 是否为主键
	 */
	public boolean primaryKey;

	/**
	 * 是否自增
	 */
	public boolean autoIncreament;

	/**
	 * 外键对应主表字段
	 */
	public String foregn_map_key;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public boolean isForeignKey() {
		return isForeignKey;
	}

	public void setForeignKey(boolean isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	public String getForeign_map_tabName() {
		return foreign_map_tabName;
	}

	public void setForeign_map_tabName(String foreign_map_tabName) {
		this.foreign_map_tabName = foreign_map_tabName;
	}

	public String getForegn_map_key() {
		return foregn_map_key;
	}

	public void setForegn_map_key(String foregn_map_key) {
		this.foregn_map_key = foregn_map_key;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isAutoIncreament() {
		return autoIncreament;
	}

	public void setAutoIncreament(boolean autoIncreament) {
		this.autoIncreament = autoIncreament;
	}

}
