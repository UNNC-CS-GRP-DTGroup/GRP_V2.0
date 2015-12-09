package cn.edu.nottingham.notetaking.leftPart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.xml.parsers.ParserConfigurationException;

import nl.dykema.jxmlnote.JXMLNoteEditor;
import nl.dykema.jxmlnote.document.DocumentAdminEvent;
import nl.dykema.jxmlnote.document.DocumentAdminListener;
import nl.dykema.jxmlnote.document.DocumentPreEvent;
import nl.dykema.jxmlnote.document.DocumentPreListener;
import nl.dykema.jxmlnote.document.XMLNoteDocument;
import nl.dykema.jxmlnote.document.XMLNoteImageIconSize;
import nl.dykema.jxmlnote.document.XMLNoteMark;
import nl.dykema.jxmlnote.document.XMLNoteMarkIdProvider;
import nl.dykema.jxmlnote.document.XMLNoteMarkListener;
import nl.dykema.jxmlnote.document.XMLNoteUndoable;
import nl.dykema.jxmlnote.exceptions.BadDocumentException;
import nl.dykema.jxmlnote.exceptions.BadMetaException;
import nl.dykema.jxmlnote.exceptions.BadOperationException;
import nl.dykema.jxmlnote.exceptions.BadStyleException;
import nl.dykema.jxmlnote.exceptions.DefaultXMLNoteErrorHandler;
import nl.dykema.jxmlnote.exceptions.MarkExistsException;
import nl.dykema.jxmlnote.exceptions.MarkNoExistException;
import nl.dykema.jxmlnote.exceptions.NoSelectionException;
import nl.dykema.jxmlnote.html.HtmlToXHtml;
import nl.dykema.jxmlnote.html.XHtmlToXMLNote;
import nl.dykema.jxmlnote.html.XMLNoteToHtml;
import nl.dykema.jxmlnote.interfaces.MarkMarkupProvider;
import nl.dykema.jxmlnote.interfaces.MarkMarkupProviderMaker;
import nl.dykema.jxmlnote.interfaces.XMLNotePreferences;
import nl.dykema.jxmlnote.interfaces.MarkMarkupProvider.MarkupType;
import nl.dykema.jxmlnote.internationalization.DefaultXMLNoteTranslator;
import nl.dykema.jxmlnote.internationalization.XMLNoteTranslator;
import nl.dykema.jxmlnote.report.ReportException;
import nl.dykema.jxmlnote.report.ReportProgressBar;
import nl.dykema.jxmlnote.report.XMLNoteToReport;
import nl.dykema.jxmlnote.report.ReportProgressBar.Progress;
import nl.dykema.jxmlnote.report.XMLNoteToReport.Moment;
import nl.dykema.jxmlnote.report.pdf.PdfReport;
import nl.dykema.jxmlnote.report.viewers.PdfViewer;
import nl.dykema.jxmlnote.styles.XMLNoteStyles;
import nl.dykema.jxmlnote.toolbar.JXMLNoteToolBar;
import nl.dykema.jxmlnote.utils.DPIAdjuster;
import nl.dykema.jxmlnote.widgets.JXMLNotePane;
import nl.dykema.jxmlnote.widgets.JXMLNoteStylePane;
import nl.dykema.jxmlnote.widgets.marks.DefaultMarkMarkupProvider;
import nl.dykema.jxmlnote.widgets.marks.MarkMouseListener;
import nl.dykema.jxmlnote.xml.XMLNoteUtils;

import org.xml.sax.SAXException;

public class JRichTextEditor2 extends JPanel{
	EditRun editRunInstance = new EditRun(this);
	
	public JRichTextEditor2() {
		// TODO Auto-generated constructor stub
		super();
		createEditor();
	}


	public void createEditor() {

		try {
			//System.setProperty("awt.useSystemAAFontSettings", "lcd_vbgr");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}	
		SwingUtilities.invokeLater(editRunInstance);
	}
	
	public EditRun getEditRun() {
		return editRunInstance;
	}

}
