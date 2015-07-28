package com.aiven.main.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.aiven.intef.XmlParseListener;
import com.aiven.mdl.DbProperty;
import com.aiven.mdl.Table;
import com.aiven.utils.KeyContains;

import de.greenrobot.daogenerator.PropertyType;

public class XmlHandler extends DefaultHandler {

	private String currentTag = null;
	private Table currentTable;
	private DbProperty currentProperty;
	private String version;
	private String outPath;
	private String outPackage;

	/**
	 * 当前值
	 */
	private String currentValue = null;
	private List<Table> tableList;
	private XmlParseListener mListener;

	public XmlHandler(XmlParseListener listener) {
		this.mListener = listener;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String value = new String(ch, start, length);
		if (value != null && !value.trim().equals("") && !value.trim().equals("\n")) {
			currentValue = value;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if (qName.toUpperCase().equals(KeyContains.version)) {
			version = currentValue;
		} else if (qName.toUpperCase().equals(KeyContains.outPath)) {
			outPath = currentValue;
		} else if (qName.toUpperCase().equals(KeyContains.outPackage)) {
			outPackage = currentValue;
		} else if (qName.toUpperCase().equals(KeyContains.table)) {
			if (tableList == null) {
				tableList = new ArrayList<>();
			}
			if (currentTable != null) {
				tableList.add(currentTable);
			}
		} else if (qName.toUpperCase().equals(KeyContains.tables)) {

		} else if (qName.toUpperCase().equals(KeyContains.colum)) {
			if (currentTable != null && currentProperty != null) {
				currentTable.addProperty(currentProperty);
			}
		} else if (qName.toUpperCase().equals(KeyContains.Db)) {
			if (mListener != null) {
				mListener.xmlAnalyOver(Integer.parseInt(version), outPath, outPackage, tableList);
			}
		} else {
			initProperty(qName.toUpperCase(), currentValue);
		}

		currentTag = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		tableList = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		currentTag = qName;
		if (currentTag.toUpperCase().equals(KeyContains.table)) {
			currentTable = new Table();
			currentTable.setTabName(attributes.getValue(0));
		} else if (currentTag.toUpperCase().equals(KeyContains.colum)) {
			currentProperty = new DbProperty();
			currentProperty.setName(attributes.getValue(attributes.getIndex(KeyContains.NAME)));
		}
	}

	private void initProperty(String tagValue, String currentValue) throws SAXException {
		if (tagValue.equals(KeyContains.type)) {
			currentProperty.setType(stringToType(currentValue));
		} else if (tagValue.equals(KeyContains.unique)) {
			currentProperty.setUnique(Boolean.parseBoolean(currentValue));
		} else if (tagValue.equals(KeyContains.notNull)) {
			currentProperty.setNotNull(Boolean.parseBoolean(currentValue));
		} else if (tagValue.equals(KeyContains.foreignKey)) {
			currentProperty.setForeignKey(Boolean.parseBoolean(currentValue));
		} else if (tagValue.equals(KeyContains.foreignKeyTable)) {
			currentProperty.setForeign_map_tabName(currentValue);
		} else if (tagValue.equals(KeyContains.foreignKeyColumn)) {
			currentProperty.setForegn_map_key(currentValue);
		} else if (tagValue.equals(KeyContains.autoIncreament)) {
			currentProperty.setAutoIncreament(Boolean.parseBoolean(currentValue));
		} else if (tagValue.equals(KeyContains.primaryKey)) {
			currentProperty.setPrimaryKey(Boolean.parseBoolean(currentValue));
		}
	}

	private PropertyType stringToType(String value) throws SAXException {
		if ("Integer".equals(value)) {
			return PropertyType.Int;
		} else if ("Boolean".equals(value)) {
			return PropertyType.Boolean;
		} else if ("Byte".equals(value)) {
			return PropertyType.Byte;
		} else if ("ByteArray".equals(value)) {
			return PropertyType.ByteArray;
		} else if ("Date".equals(value)) {
			return PropertyType.Date;
		} else if ("Double".equals(value)) {
			return PropertyType.Double;
		} else if ("Float".equals(value)) {
			return PropertyType.Float;
		} else if ("Long".equals(value)) {
			return PropertyType.Long;
		} else if ("Short".equals(value)) {
			return PropertyType.Short;
		} else if ("String".equals(value)) {
			return PropertyType.String;
		} else {
			return null;
		}
	}

}
