package cn.edu.nottingham.notetaking.mainFrame;


import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

/**
 * open the app
 * 
 * @author RUNFA LV,WENYU DU
 * 
 */
 


public class GUIClient {
	/**
	 * the main method to open the app
	 * 
	 * @author RUNFA LV, WENYU DU
	 * 
	 */
	
	/** UIManager中UI字体相关的key */
	public static String[] DEFAULT_FONT  = new String[]{
	    "Table.font"
	    ,"TableHeader.font"
	    ,"CheckBox.font"
	    ,"Tree.font"
	    ,"Viewport.font"
	    ,"ProgressBar.font"
	    ,"RadioButtonMenuItem.font"
	    ,"ToolBar.font"
	    ,"ColorChooser.font"
	    ,"ToggleButton.font"
	    ,"Panel.font"
	    ,"TextArea.font"
	    ,"Menu.font"
	    ,"TableHeader.font"
	    // ,"TextField.font"
	    ,"OptionPane.font"
	    ,"MenuBar.font"
	    ,"Button.font"
	    ,"Label.font"
	    ,"PasswordField.font"
	    ,"ScrollPane.font"
	    ,"MenuItem.font"
	    ,"ToolTip.font"
	    ,"List.font"
	    ,"EditorPane.font"
	    ,"Table.font"
	    ,"TabbedPane.font"
	    ,"RadioButton.font"
	    ,"CheckBoxMenuItem.font"
	    ,"TextPane.font"
	    ,"PopupMenu.font"
	    ,"TitledBorder.font"
	    ,"ComboBox.font"
	};
	// 调整默认字体
	
	public void changeFont() {
		for (int i = 0; i < DEFAULT_FONT.length; i++)
		    UIManager.put(DEFAULT_FONT[i],new Font("微软雅黑", Font.PLAIN,14));
	}
	
	public static void main(String[] args) {
//	    try
//	    {
//	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//	    }
//	    catch(Exception e)
//	    {
//	        //TODO exception
//	    }
//	    
//	    new MyFrame();
		
		//avoid unsafe of swing.
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("Start!");
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			    try
			    {
			        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//			        UIManager.put("RootPane.setupButtonVisible", false); // close "set up" button
			    }
			    catch(Exception e)
			    {
			        //TODO exception
			    }
//			    
			    // System.setProperty("sun.java2d.noddraw", "true");
				MyFrame.getInstance();
//			}
//		});
	}
	
	
}
