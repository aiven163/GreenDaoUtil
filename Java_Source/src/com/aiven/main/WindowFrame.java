package com.aiven.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import com.aiven.intef.XmlParseListener;
import com.aiven.main.xml.XmlAnalyUtil;
import com.aiven.mdl.Table;

public class WindowFrame extends JFrame implements ActionListener, XmlParseListener {

	/**   
	 * serialVersionUID:TODO  
	 */

	private static final long serialVersionUID = -3386011794627207460L;

	private JPanel mContainer;
	private JButton mButton, mSelectBtn;
	private JLabel mDefLab, mValueLab, mResultLab;
	private JFileChooser mChooser;
	private String mCurrentPath;

	public WindowFrame() {
		init();
	}

	public void display() {
		setVisible(true);
	}

	private void init() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(500, 200);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
				setVisible(true);
				initFrame();
			}
		});

	}

	private void initFrame() {

		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mContainer = new JPanel();
		mContainer.setLayout(null);
		mContainer.setSize(500, 200);
		mContainer.setBounds(0, 0, 500, 200);

		mDefLab = new JLabel();
		mDefLab.setText("请选择XML配置文件：");
		mDefLab.setSize(130, 30);
		mDefLab.setLocation(10, 30);
		mContainer.add(mDefLab);

		mValueLab = new JLabel();
		mValueLab.setText(" ");
		mValueLab.setSize(240, 30);
		mValueLab.setLocation(145, 30);
		mValueLab.setForeground(Color.gray);
		mValueLab.setOpaque(true);
		mValueLab.setBackground(Color.decode("#E0E0E0"));
		mContainer.add(mValueLab);

		mSelectBtn = new JButton();
		mSelectBtn.setText("选择");
		mSelectBtn.setSize(80, 30);
		mSelectBtn.setLocation(390, 30);
		mContainer.add(mSelectBtn);

		mButton = new JButton();
		mButton.setText("生成");
		mButton.setSize(200, 30);
		mButton.setLocation(150, 90);
		mButton.setEnabled(false);
		mContainer.add(mButton);

		mResultLab = new JLabel("", JLabel.CENTER);
		mResultLab.setLayout(new FlowLayout());
		mResultLab.setSize(400, 30);
		mResultLab.setLocation(50, 130);
		mResultLab.setForeground(Color.RED);
		mContainer.add(mResultLab);

		mSelectBtn.addActionListener(this);
		mButton.addActionListener(this);

		add(mContainer);
	}

	private void showSelectedDialog() {
		mChooser = new JFileChooser();
		mChooser.setCurrentDirectory(new File("D:\\"));  
		mChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		mChooser.setAcceptAllFileFilterUsed(false);
		mChooser.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return ".xml File";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory() || f.getName().endsWith(".xml")) {
					return true;
				}
				return false;
			}
		});
		mChooser.showDialog(new JLabel(), "选择");
		File file = mChooser.getSelectedFile();
		if (file != null && file.exists()) {
			mCurrentPath = file.getAbsolutePath();
			mValueLab.setText(" " + mCurrentPath);
			mButton.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mSelectBtn) {
			mResultLab.setText("");
			showSelectedDialog();
		} else if (e.getSource() == mButton) {
			startParamXml(mCurrentPath);
		}
	}

	private void startParamXml(String url) {
		try {
			new XmlAnalyUtil().startAnaly(url, this);
		} catch (Exception e) {
			e.printStackTrace();
			mResultLab.setForeground(Color.RED);
			mResultLab.setText("错误：xml配置文件解析过程中出错");
		}
	}

	@Override
	public void xmlAnalyOver(int version, String outPath, String pkg, List<Table> tabs) {
		Creator creator = new Creator();
		try {
			clearPath(outPath);
			creator.generateMap(version, tabs, pkg, outPath);
			mResultLab.setForeground(Color.GREEN);
			mResultLab.setText("生成结束，目标地址：" + outPath);
		} catch (Exception e) {
			e.printStackTrace();
			mResultLab.setForeground(Color.RED);
			mResultLab.setText("错误：生成Dao文件过程中出错");
		}
	}

	private void clearPath(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

}
