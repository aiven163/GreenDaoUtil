package com.aiven.main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.aiven.intef.XmlParseListener;
import com.aiven.main.xml.XmlAnalyUtil;
import com.aiven.mdl.Table;

public class Excutetor implements XmlParseListener {

	private static Excutetor excutetor;

//	/**
//	 * main
//	 * @param args
//	*/
//	public static void main(String[] args) {
//		excutetor = new Excutetor();
//		System.out.println("请输入XML配置文件路径：");
//		startCmd();
//	}

	private static void startCmd() {
		Scanner in = new Scanner(System.in, "UTF-8");
		String path = in.nextLine();
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("找不到指定的配置文件，请重新输入XML配置文件路径:");
			startCmd();
		} else {
			excutetor.startParamXml(path);
		}
		in.close();
	}

	private void startParamXml(String url) {
		try {
			new XmlAnalyUtil().startAnaly(url, excutetor);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("xml配置文件解析过程中出错" + e.getMessage());
		}
	}

	@Override
	public void xmlAnalyOver(int version, String outPath, String pkg, List<Table> tabs) {
		Creator creator = new Creator();
		try {
			clearPath(outPath);
			creator.generateMap(version, tabs, pkg, outPath);
			System.out.println("生成结束，目标地址："+outPath);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("生成Dao文件过程中出错" + e.getMessage());
		}
	}

	private void clearPath(String path)throws Exception{
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
}
