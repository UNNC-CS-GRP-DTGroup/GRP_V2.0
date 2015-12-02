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

	private static final long serialVersionUID = 66563949539476644L;

	public MyFrame() {
		super("NoteBook GUI");
		
//	    try
//	    {
//	        //设置本属性将改变窗口边框样式定义
//	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
//	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//	    }
//	    catch(Exception e)
//	    {
//	        //TODO exception
//	    }
		getContentPane().setLayout(new GridLayout(1, 2));
		
		//add left and right parts
		getContentPane().add(new LeftPanel().getLeft());
		getContentPane().add(new RightPane());
		
		setSize(1200, 650);
		setVisible(true);
		System.out.println("set to true");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
