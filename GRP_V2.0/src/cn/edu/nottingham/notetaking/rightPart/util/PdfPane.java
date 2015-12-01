package cn.edu.nottingham.notetaking.rightPart.util;


import java.awt.Component;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import cn.edu.nottingham.notetaking.rightPart.components.ButtonTabComponent;

/**
 * create a tabbedPane to support pdf format
 *
 * @author WENYU DU
 * @param pdfPane
 *            The pane itself
 * @param openPanel
 *            The starting pane
 * @param button
 *            The button to open a pdf file
 * @param jframe
 *            The frame which contains pdfPane
 */

public class PdfPane extends JTabbedPane {

    private static final long serialVersionUID = -5895534417361283788L;
    private JTabbedPane pdfPane;
    private JPanel openPanel;
    private JButton button;
    private JFrame jframe;

    /**
     * the constructor
     *
     * @author WENYU DU
     *
     * @param p
     *            The parent of pdfPane
     * @param filedialog
     *            The open dialog for users to choose files.
     * @param directory
     *            The directory of the selected file.
     * @param fileName
     *            The name of selected file.
     * @param path
     *            The path of the file.
     * @param pdfs
     *            The output of the file.
     * @param jta
     *            The textArea which displays the output.
     * @param jsp
     *            The scrollPane which contains jta.
     * @param jp
     *            The panel which contains jsp.
     */

    public PdfPane() {
	pdfPane = this;

	openPanel = new JPanel();
	button = new JButton("open");
	openPanel.add(button);
	addTab("open", openPanel);

	button.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		// get frame which contains pdfPane
		Component p = pdfPane.getParent();
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

		if (fileName == null || !fileName.endsWith(".pdf"))
		    return;

		String path = directory + fileName;

		// add one tab
		String pdfs = PdfPane.getText(path);
		JPanel jp = new JPanel();
		JTextArea jta = new JTextArea(pdfs);
		JScrollPane jsp = new JScrollPane(jta,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jp.setLayout(new GridLayout(1, 1));

		jp.add(jsp);

		pdfPane.addTab(fileName, jp);
		pdfPane.setTabComponentAt(pdfPane.getTabCount() - 1,
			new ButtonTabComponent(pdfPane));
	    }
	});
    }

    /**
     * get output from the pdf file
     *
     * @author WENYU DU
     * @return text or "" if exception occurs
     */

    public static String getText(String s) {
	File file = new File(s);
	boolean sort = false;
	int startPage = 1;
	int endPage = startPage;
	PDDocument document = null;

	// load file to stripper and return text of the file
	try {
	    try {
		document = PDDocument.load(file);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }
	    PDFTextStripper stripper = new PDFTextStripper();
	    endPage = stripper.getEndPage();

	    stripper.setSortByPosition(sort);
	    stripper.setStartPage(startPage);
	    stripper.setEndPage(endPage);
	    return stripper.getText(document);
	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	} finally {
	    if (document != null) {
		try {
		    document.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

}