package com.aiven.main.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import com.aiven.intef.XmlParseListener;

public class XmlAnalyUtil extends DefaultHandler {

	public void startAnaly(String url, XmlParseListener listener) throws Exception {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		XmlHandler handler = new XmlHandler(listener);
		parser.parse(url, handler);
	}

}
