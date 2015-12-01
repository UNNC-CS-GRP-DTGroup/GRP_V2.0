package cn.edu.nottingham.notetaking.rightPart.util;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import cn.edu.nottingham.notetaking.rightPart.components.ButtonTabComponent;

/**
 * create a tabbedPane for imagePane
 *
 * @author WENYU DU
 */

public class ImagePane extends JTabbedPane {

    private static final long serialVersionUID = 7374219854264201102L;
    private JTabbedPane imagePane;
    private JPanel openPanel;
    private JButton button;
    private JFrame jframe;

    public ImagePane() {
	imagePane = this;

	openPanel = new JPanel();
	button = new JButton("open");
	openPanel.add(button);
	addTab("open", openPanel);

	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {

			// get frame which contains imagePane
			Component p = imagePane.getParent();

			while (p != null && !(p instanceof JFrame)) {
			    p = p.getParent();
			}
			if (p != null) {
			    jframe = (JFrame) p;
			}
			// open dialog
			FileDialog filedialog = new FileDialog(jframe, "open",
				FileDialog.LOAD);
			filedialog.setVisible(true);

			// get path
			String directory = filedialog.getDirectory();
			String fileName = filedialog.getFile();

			if (fileName == null)
			    return;

			String path = directory + fileName;

			// add one tab
			
			JWebBrowser webBrowser = new JWebBrowser();
			webBrowser.setBarsVisible(false);
			webBrowser.setStatusBarVisible(false);
			webBrowser.navigate(path);
			
			UIUtils.setPreferredLookAndFeel();
			NativeInterface.open();
			imagePane.addTab(fileName, webBrowser);
			imagePane.setTabComponentAt(imagePane.getTabCount() - 1,
				new ButtonTabComponent(imagePane));
			
		
		    }
		});

	    }
    }
