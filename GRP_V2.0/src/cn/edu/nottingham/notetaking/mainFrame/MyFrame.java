package cn.edu.nottingham.notetaking.mainFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.pilot.jword.ui.JWordController;
import com.pilot.kahve.GuiException;

import cn.edu.nottingham.notetaking.leftPart.DictionaryFrame;
import cn.edu.nottingham.notetaking.leftPart.LeftPanel;
import cn.edu.nottingham.notetaking.rightPart.RightPane;
import cn.edu.nottingham.notetaking.rightPart.util.EmailPane;

/**
 * create the GUI for the app
 * 
 * @author RUNFA LV,WENYU DU
 * 
 */
public class MyFrame extends JFrame {
//	JPanel outPanel;
	JSplitPane splitPane;
	JMenuBar menuBar;
	JMenuItem shareWithEmailMenuItem;
	JDialog emailDialog;;
	JMenu shareMenu;
	JMenuItem dictionaryMenuItem;
	JFrame dictionaryFrame;
	public static JWordController jWordCtrl;
	private static final long serialVersionUID = 66563949539476644L;

	public MyFrame() {
		super("NoteBook GUI");
		
		jWordCtrl = new JWordController();
		try {
			jWordCtrl.init(false);
		} catch (GuiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuBar = jWordCtrl.getFrame().getJMenuBar();
		shareMenu = new JMenu("Share");
		shareWithEmailMenuItem = new JMenuItem("Share with email");
		shareWithEmailMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				emailDialog = new EmailPane();
				emailDialog.setVisible(true);
			}
		});
		shareMenu.add(shareWithEmailMenuItem);
		menuBar.add(shareMenu);
		
		JMenu helpMenu = menuBar.getMenu(6);
		helpMenu.remove(0);
		
		dictionaryMenuItem = new JMenuItem("Dictionary");
		dictionaryMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dictionaryFrame = new DictionaryFrame();
				dictionaryFrame.setVisible(true);
			}
		});
		helpMenu.add(dictionaryMenuItem);
		
//		menuBar.remove(6); // remove the Help menu
		
		
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
		getContentPane().add(splitPane);
		
//		add left and right parts
//		outPanel.add(new LeftPanel().getLeft());
//		outPanel.add(new RightPane());
		
		setSize(1600, 1350);
		setVisible(true);
		setJMenuBar(menuBar);
		System.out.println("set to true");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
