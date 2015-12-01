package cn.edu.nottingham.notetaking.rightPart.util.browser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

/**
 * create a panel for browsing
 * 
 * @author WENYU DU
 * 
 */
public class BrowserPanel extends JPanel {

    private static final long serialVersionUID = 6442829790034923717L;
    private static String engine = "https://www.baidu.com/s?wd=";
    private static JTextField textField;

    /**
     * constructor
     * 
     * @author WENYU DU
     * 
     */
    public BrowserPanel() {
	super();
	setLayout(new BorderLayout());

	// add search bar
	JPanel top = new JPanel();
	add(top, BorderLayout.NORTH);
	textField = new JTextField("ad", 10);
	JComboBox<String> menu = new JComboBox<String>();
	JButton button = new JButton("search");
	menu.addItem("baidu");
	menu.addItem("bing");
	top.add(textField, BorderLayout.WEST);
	top.add(menu);
	top.add(button);

	menu.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (menu.getSelectedIndex() == 0) {
		    engine = "https://www.baidu.com/s?wd=";
		} else {
		    engine = "http://www.bing.com/search?q=";
		}
	    }
	});

	button.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		String url = engine + textField.getText();
		openActionPerformed(e, url);
	    }
	});

	// add two useful link:moodle and email
	JPanel center = new JPanel();
	JButton bt1 = new JButton("moodle");
	center.add(bt1);
	bt1.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String url = "https://moodle.nottingham.ac.uk/login/index.php";
		openActionPerformed(e, url);

	    }
	});
	JButton bt2 = new JButton("email");
	center.add(bt2);
	bt2.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String url = "https://owa.nottingham.edu.cn/owa";
		openActionPerformed(e, url);
	    }
	});
	add(center, BorderLayout.CENTER);

    }

    /**
     * add a method to open a browser with specified website.
     * 
     * @author WENYU DU
     * 
     */
    private void openActionPerformed(ActionEvent evt, String url) {
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		NativeInterface.open();
		UIUtils.setPreferredLookAndFeel();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			JFrame frame = new JFrame("NoteBook Browser");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(new WindowsAsTabs(url),
				BorderLayout.CENTER);
			frame.setSize(800, 600);
			frame.setLocationByPlatform(true);
			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		    }
		});
	    }
	}).start();
    }
}