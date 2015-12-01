package cn.edu.nottingham.notetaking.rightPart.util.browser;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import cn.edu.nottingham.notetaking.csv.CsvBuilder;
import cn.edu.nottingham.notetaking.rightPart.components.ButtonTabComponent;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

/**
 * create a browser window as a tab
 * 
 * @author WENYU DU
 * 
 */
public class WindowsAsTabs extends JPanel {

    private static final long serialVersionUID = 2580832616738252274L;
    protected static final String LS = System.getProperty("line.separator");
    private static ArrayList<String> history = new ArrayList<String>();

    /**
     * constructor
     * 
     * @author WENYU DU
     * 
     */
    public WindowsAsTabs(String url) {
	super(new BorderLayout());
	JPanel jPanel = this;
	JTabbedPane tabbedPane = new JTabbedPane();
	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

	generateTabs(tabbedPane, url);
	jPanel.add(tabbedPane, BorderLayout.CENTER);

    }

    // tab with specific url
    public static void generateTabs(JTabbedPane tabbedPane, String url) {
	JWebBrowser webBrowser = new JWebBrowser();
	webBrowser.setBarsVisible(true);
	webBrowser.setStatusBarVisible(true);
	webBrowser.navigate(url);
	subGenerateTabs(tabbedPane, webBrowser);
    }

    // tab with defaul url
    public static void generateTabs(JTabbedPane tabbedPane) {
	String url = "www.baidu.com";
	generateTabs(tabbedPane, url);
    }

    public static void subGenerateTabs(JTabbedPane tabbedPane,
	    JWebBrowser webBrowser) {
	addWebBrowserListener(tabbedPane, webBrowser);
	tabbedPane.addTab("New Tab", webBrowser);
	ButtonTabComponent btc = new ButtonTabComponent(tabbedPane);
	tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, btc);
	btc.setBrowser(webBrowser);
    }

    // get history
    private static void loadUrl(String url) {
	String msg = "";
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");
	msg += "[" + sdf.format(date) + "]";

	if (history.isEmpty() || !history.get(history.size() - 1).equals(url)) {
	    history.add(url);
	    String log = url + " , " + msg;
	    CsvBuilder.writeCsv(log);

	}
    }

    private static void addWebBrowserListener(final JTabbedPane tabbedPane,
	    final JWebBrowser webBrowser) {
	webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
	    @Override
	    public void titleChanged(WebBrowserEvent e) {
		String a = e.getWebBrowser().getResourceLocation();
		loadUrl(a);

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
		    if (tabbedPane.getComponentAt(i) == webBrowser) {
			tabbedPane.setTitleAt(i, webBrowser.getPageTitle());
			break;
		    }
		}
	    }

	    @Override
	    public void windowWillOpen(WebBrowserWindowWillOpenEvent e) {
		JWebBrowser newWebBrowser = new JWebBrowser();
		subGenerateTabs(tabbedPane, newWebBrowser);
		e.setNewWebBrowser(newWebBrowser);
	    }

	    @Override
	    public void windowOpening(WebBrowserWindowOpeningEvent e) {
		e.getWebBrowser().setMenuBarVisible(false);
	    }
	});
    }
}
