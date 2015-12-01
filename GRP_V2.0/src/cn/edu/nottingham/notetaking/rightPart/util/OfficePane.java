package cn.edu.nottingham.notetaking.rightPart.util;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import cn.edu.nottingham.notetaking.rightPart.components.ButtonTabComponent;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * create a tabbedPane for imagePane
 * 
 * @author WENYU DU
 * 
 */
public class OfficePane extends JTabbedPane {

    private static final long serialVersionUID = -3851277160004228690L;
    
    private static final int EXCEL = 44;
    private static final int WORD = 8;
    private static final int PPT = 22;

    private JTabbedPane officePane;
    private JPanel openPanel;
    private JButton button;
    private JFrame jframe;

    public OfficePane() {
	officePane = this;
	
	openPanel = new JPanel();
	button = new JButton("open");
	openPanel.add(button);
	addTab("open", openPanel);

	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ae) {

		//get frame which contains pdfPane
		Component p = officePane.getParent();
		while (p != null && !(p instanceof JFrame)) {
			p = p.getParent();
		}
		if (p != null) {
			jframe = (JFrame) p;
		}

		//open dialog
		FileDialog filedialog = new FileDialog(jframe, "open",
				FileDialog.LOAD);
		filedialog.setVisible(true);

		//get path
		String directory = filedialog.getDirectory();
		String fileName = filedialog.getFile();
		String filePath = directory+fileName;
		
		String destination = filePath + ".html";
		officeToHtml(filePath, destination);


		//delete the temp html files when close the project
		File html = new File(destination);
		html.deleteOnExit();
		
		deleteHtmlFiles(new File(filePath+".files"));
		new File(filePath+".files").deleteOnExit();
		
		
		//add one tab
		JWebBrowser webBrowser = new JWebBrowser();
		webBrowser.setBarsVisible(false);
		webBrowser.setStatusBarVisible(false);
		webBrowser.navigate(destination);
		
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		officePane.addTab(fileName, webBrowser);
		officePane.setTabComponentAt(officePane.getTabCount() - 1,
			new ButtonTabComponent(officePane));
	    }
	});

    }
    
    private static void deleteHtmlFiles(File dir){	
		if (dir.isDirectory()) {
		    String[] childFiles = dir.list();
		    for (String childFile : childFiles) {
			 deleteHtmlFiles(new File(dir, childFile));
		    }
		}
		dir.deleteOnExit();
    }
    
    

    /**
     * get the format of office and call toHtml
     * 
     * @author WENYU DU
     * 
     */
    public static void officeToHtml(String officePath, String htmlPath){

	if (officePath.endsWith(".xls") || officePath.endsWith(".xlsx")) {
	    toHtml(officePath, htmlPath, EXCEL, "Excel", "Workbooks");
	}

	if (officePath.endsWith(".doc") || officePath.endsWith(".docx")) {
	    toHtml(officePath, htmlPath, WORD, "Word", "Documents");
	}

	if (officePath.endsWith(".ppt") || officePath.endsWith(".pptx")) {
	    toHtml(officePath, htmlPath, PPT, "PowerPoint", "Presentations");
	}

    }

    /**
     * create the html file corresponding to the office file
     * 
     * @author WENYU DU
     * 
     */
    public static void toHtml(String officePath, String htmlPath, int FORMAT,
	    String component, String property){
	ActiveXComponent offCom = new ActiveXComponent(component
		+ ".Application");

	try {
            if(FORMAT==PPT){
                 offCom.setProperty("Visible", new Variant(true));
                 Dispatch ppts = offCom.getProperty("Presentations").toDispatch();

			Dispatch ppt = Dispatch.invoke(
					ppts,

					"Open",
					Dispatch.Method,
					new Object[] { officePath, new Variant(false),
							new Variant(false) }, new int[1]).toDispatch();

			Dispatch.invoke(ppt, "SaveAs", Dispatch.Method, new Object[] {

			htmlPath, new Variant(12) }, new int[1]);

			Variant f = new Variant(false);

			Dispatch.call(ppt, "Close");
                 
            }else{
                
            
	    offCom.setProperty("Visible", new Variant(false));
	    Dispatch formats = offCom.getProperty(property).toDispatch();
	    Dispatch format = Dispatch.invoke(
		    formats,
		    "Open",
		    Dispatch.Method,
		    new Object[] { officePath, new Variant(false),
			    new Variant(true) }, new int[1]).toDispatch();

	    Dispatch.invoke(format, "SaveAs", Dispatch.Method, new Object[] {

	    htmlPath, new Variant(FORMAT) }, new int[1]);

	    Variant f = new Variant(false);

	    Dispatch.call(format, "Close", f);
            }
	} catch (Exception e){
	    e.printStackTrace();
	} finally{
	    offCom.invoke("Quit", new Variant[] {});
	}
    }
}