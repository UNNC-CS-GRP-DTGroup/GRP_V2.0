package cn.edu.nottingham.notetaking.mainFrame;

import java.awt.Component;
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
	private static JSplitPane splitPane;
	private static final long serialVersionUID = 66563949539476644L;
	private static MyFrame uniqueInstance;
	
	private MyFrame() {
		super("NoteBook GUI");
	}

	public static MyFrame getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new MyFrame();
			System.setProperty("sun.java2d.noddraw", "true");
			
	//		outPanel = new JPanel();
	//		outPanel.setLayout(new GridLayout(1, 2));
	//		getContentPane().add(outPanel);
			
			splitPane = new JSplitPane();
			splitPane.setOneTouchExpandable(true);
			splitPane.setContinuousLayout(true);
			splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			
			splitPane.setRightComponent(new RightPane());
			splitPane.setLeftComponent(new LeftPanel().getLeft());
			uniqueInstance.getContentPane().add(splitPane);
			
	//		add left and right parts
	//		outPanel.add(new LeftPanel().getLeft());
	//		outPanel.add(new RightPane());
			
			uniqueInstance.setSize(1200, 650);
			uniqueInstance.setVisible(true);
			System.out.println("set to true");
			uniqueInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		return uniqueInstance;

	}
}
