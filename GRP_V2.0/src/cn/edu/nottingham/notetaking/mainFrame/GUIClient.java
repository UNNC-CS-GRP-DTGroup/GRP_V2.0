package cn.edu.nottingham.notetaking.mainFrame;


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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("Start!");
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
			    }
			    catch(Exception e)
			    {
			        //TODO exception
			    }
//			    
				
				new MyFrame();
			}
		});
	} 
}
