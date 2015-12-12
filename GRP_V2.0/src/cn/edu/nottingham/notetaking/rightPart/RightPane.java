package cn.edu.nottingham.notetaking.rightPart;

import java.awt.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.event.*;
import javax.swing.ImageIcon;

import com.sun.prism.paint.Color;

import cn.edu.nottingham.notetaking.rightPart.util.AudioPanel;
import cn.edu.nottingham.notetaking.rightPart.util.CameraPanel;
import cn.edu.nottingham.notetaking.rightPart.util.EmailPane;
import cn.edu.nottingham.notetaking.rightPart.util.ImagePane;
import cn.edu.nottingham.notetaking.rightPart.util.OfficePane;
import cn.edu.nottingham.notetaking.rightPart.util.PdfPane;
import cn.edu.nottingham.notetaking.rightPart.util.VideoPanel;
import cn.edu.nottingham.notetaking.rightPart.util.browser.BrowserPanel;

/**
 * create a panel to contain right part
 *
 * @author PENGHAO LI,WENYU DU
 */
public class RightPane extends JTabbedPane {
    private static final long serialVersionUID = 483700995452230439L;

    private JPanel audioPanel;
    private JTabbedPane officePane;
    private JTabbedPane pdfPane;
    private JPanel browserPanel;
    private JTabbedPane imagePane;
    private JTabbedPane videopanel;
    private JPanel cameraPanel;
    private JTabbedPane emailPane;
    private JButton button;

    /**
     * constructor
     *
     * @author PENGHAO LI,WENYU DU
     */
    public RightPane() {
	audioPanel = new AudioPanel();
	officePane = new OfficePane();
	pdfPane = new PdfPane();
	browserPanel = new BrowserPanel();
	imagePane = new ImagePane();
	videopanel = new VideoPanel();
	cameraPanel = new CameraPanel();
    //emailPane = new EmailPane();
	
	addTab(" Audio", new ImageIcon("icons/audio.png"), audioPanel);
	addTab(" Office", new ImageIcon("icons/office.png"), officePane);
	addTab(" Pdf", new ImageIcon("icons/pdf.png"), pdfPane);
	addTab(" Browser", new ImageIcon("icons/browser.png"), browserPanel);
	addTab(" Image", new ImageIcon("icons/images.png"), imagePane);
	addTab(" Video", new ImageIcon("icons/video 2.png"), videopanel);
	addTab(" Camera", new ImageIcon("icons/camera.png"), cameraPanel);
	addTab(" Email", new ImageIcon("icons/email.png"), emailPane);

    }
}
