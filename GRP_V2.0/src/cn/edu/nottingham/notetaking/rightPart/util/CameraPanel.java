package cn.edu.nottingham.notetaking.rightPart.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import cn.edu.nottingham.notetaking.mainFrame.MyFrame;

/**
 * @author Melissa Lan
 *
 */

public class CameraPanel extends JPanel{
	
	private static final long serialVersionUID = 3088759888647458280L;
	private JPanel cameraAccess;
    private JButton button;
    
    public CameraPanel() {
    	super();
    	
    	setLayout(new BorderLayout());
    	
    	//initialising all the buttons and panel
    	cameraAccess = new JPanel();
    	add(cameraAccess, BorderLayout.NORTH);
    	button = new JButton("Launch Camera");
        button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
    	cameraAccess.add(button);

    	//Upon clicking the open button
    	button.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent ae) {
    			Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Camera.main(null);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
    			
    			
    			t.start();
    					
    				}
    		
    	
    			});
    			
    			
  
    	
    }
    
   
}
