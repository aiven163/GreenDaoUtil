package com.aiven.mdl;

import java.util.ArrayList;
import java.util.List;
/**
 * 表描述实体
 * @项目名称   你我金融 APP客户端(Android)
 * @类名称   Table  
 * @创建人   Aiven
 * @Email  aiven163@sina.com
 * @创建时间 2015-7-10 下午6:28:20 
 * @类描述   TODO
 */
public class Table {

	private String tabName;

	private List<DbProperty> propertys;

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public void addProperty(DbProperty property) {
		if (propertys == null) {
			propertys = new ArrayList<>();
		}
		propertys.add(property);
	}

	public List<DbProperty> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<DbProperty> propertys) {
		this.propertys = propertys;
	}

}
