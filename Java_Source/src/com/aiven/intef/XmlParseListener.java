package com.aiven.intef;

import com.aiven.mdl.Table;

import java.util.List;

public interface XmlParseListener {

	public void xmlAnalyOver(int version, String outPath, String pkg, List<Table> tabs);

}
