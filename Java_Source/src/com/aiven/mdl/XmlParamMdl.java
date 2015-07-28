package com.aiven.mdl;

import java.util.ArrayList;
import java.util.List;

public class XmlParamMdl {

	private int version;
	private String outPath;
	private String outPackage;
	private List<Table> tabs;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public String getOutPackage() {
		return outPackage;
	}

	public void setOutPackage(String outPackage) {
		this.outPackage = outPackage;
	}

	public List<Table> getTabs() {
		return tabs;
	}

	public void addTable(Table tab) {
		if (tabs == null) {
			tabs = new ArrayList<>();
		}
		tabs.add(tab);
	}

}
