package cn.edu.nottingham.notetaking.mainFrame;

import java.awt.GridLayout;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import cn.edu.nottingham.notetaking.leftPart.LeftPanel;
import cn.edu.nottingham.notetaking.rightPart.RightPane;

/**
 * create the GUI for the app
 * 
 * @author RUNFA LV,WENYU DU
 * 
 */
public class MyFrame extends JFrame {
//	JPanel outPanel;
	JSplitPane splitPane;
	private static final long serialVersionUID = 66563949539476644L;

	public MyFrame() {
		super("NoteBook GUI");
		
		System.setProperty("sun.java2d.noddraw", "true");
		
//		outPanel = new JPanel();
//		outPanel.setLayout(new GridLayout(1, 2));
//		getContentPane().add(outPanel);
		
		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(910);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		splitPane.setRightComponent(new RightPane());
		splitPane.setLeftComponent(new LeftPanel().getLeft());
		getContentPane().add(splitPane);
		
//		add left and right parts
//		outPanel.add(new LeftPanel().getLeft());
//		outPanel.add(new RightPane());
		
		setSize(1380, 750);
		setVisible(true);
		System.out.println("set to true");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
