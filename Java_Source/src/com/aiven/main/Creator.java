package com.aiven.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aiven.mdl.DbProperty;
import com.aiven.mdl.ForignKeyContains;
import com.aiven.mdl.TabEntity;
import com.aiven.mdl.Table;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.ToOne;
import de.greenrobot.daogenerator.Property.PropertyBuilder;
import de.greenrobot.daogenerator.PropertyType;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Dao层生成执行器
 * @项目名称   你我金融 APP客户端(Android)
 * @类名称   Creator  
 * @创建人   Aiven
 * @Email  aiven163@sina.com
 * @创建时间 2015-7-10 下午6:24:53 
 * @类描述   TODO
 */
public class Creator {

	private HashMap<String, TabEntity> tabMaps = new HashMap<>();

	private ArrayList<ForignKeyContains> foreignKeys = new ArrayList<>();

	public void generateMap(int version, List<Table> tabs, String outPkg, String outPath) throws Exception {
		if (tabs == null) {
			return;
		}
		Schema schema = new Schema(version, outPkg);
		for (int i = 0; i < tabs.size(); i++) {
			generateItemTab(schema, tabs.get(i));
		}
		generateForeignKeys();
		new DaoGenerator().generateAll(schema, outPath);
	}

	private void generateForeignKeys() {
		if (foreignKeys == null) {
			return;
		}
		for (int i = 0; i < foreignKeys.size(); i++) {
			generateForeignKeyItems(foreignKeys.get(i));
		}
	}

	private void generateItemTab(Schema schema, Table tab) {
		TabEntity tabEntity = new TabEntity();
		Entity itemEt = schema.addEntity(tab.getTabName());
		tab.setTabName(tab.getTabName() + "_TAB");
		List<DbProperty> prs = tab.getPropertys();
		if (prs != null) {
			for (int i = 0; i < prs.size(); i++) {
				Property itemProperty = addProperty(itemEt, prs.get(i));
				tabEntity.addProperty(itemProperty);
			}
		}
		tabEntity.setEntity(itemEt);
		tabMaps.put(itemEt.getClassName(), tabEntity);
	}

	/**
	 * 生成表
	 * addProperty
	 * @param tab
	 * @param property
	 */
	private Property addProperty(Entity tab, DbProperty property) {
		Property itemProperty = null;
		PropertyBuilder buider = null;
		if (property.getType() == PropertyType.Boolean) {
			buider = tab.addBooleanProperty(property.getName());
		} else if (property.getType() == PropertyType.Byte) {
			buider = tab.addByteProperty(property.getName());
		} else if (property.getType() == PropertyType.ByteArray) {
			buider = tab.addByteArrayProperty(property.getName());
		} else if (property.getType() == PropertyType.Date) {
			buider = tab.addDateProperty(property.getName());
		} else if (property.getType() == PropertyType.Double) {
			buider = tab.addDoubleProperty(property.getName());
		} else if (property.getType() == PropertyType.Float) {
			buider = tab.addFloatProperty(property.getName());
		} else if (property.getType() == PropertyType.Int) {
			buider = tab.addIntProperty(property.getName());
		} else if (property.getType() == PropertyType.Long) {
			buider = tab.addLongProperty(property.getName());
		} else if (property.getType() == PropertyType.Short) {
			buider = tab.addShortProperty(property.getName());
		} else if (property.getType() == PropertyType.String) {
			buider = tab.addStringProperty(property.getName());
		}

		if (property.isPrimaryKey()) {
			buider.primaryKey();
		}
		if (property.isAutoIncreament() && property.getType() == PropertyType.Long) {
			buider.autoincrement();
		}
		if (property.isUnique()) {
			buider.unique();
		}
		itemProperty = buider.getProperty();
		if (property.isForeignKey()) {
			ForignKeyContains forignKeyContains = new ForignKeyContains();
			forignKeyContains.setEntity(tab);
			forignKeyContains.setForeignKey(itemProperty);
			forignKeyContains.setMainKeyName(property.getForegn_map_key());
			forignKeyContains.setMainTabName(property.getForeign_map_tabName());
			if (foreignKeys == null) {
				foreignKeys = new ArrayList<>();
			}
			foreignKeys.add(forignKeyContains);
		}
		return itemProperty;
	}

	/**
	 * 生成主外键一对多和多对一关系映射
	 * generateForeignKeyItems
	 * @param forkey
	 */
	private void generateForeignKeyItems(ForignKeyContains forkey) {
		TabEntity mainMapEntity = tabMaps.get(forkey.getMainTabName());
		if (mainMapEntity != null) {
			ToOne toOne = forkey.getEntity().addToOne(mainMapEntity.getEntity(), forkey.getForeignKey());
			toOne.setName(mainMapEntity.getEntity().getClassName());
//			Property mainKeyPropery = mainMapEntity.getProperty(forkey.getMainKeyName());
			ToMany toMany = mainMapEntity.getEntity().addToMany(forkey.getEntity(), forkey.getForeignKey());
			toMany.setName(forkey.getEntity().getClassName() + "List");
		}
	}

}
