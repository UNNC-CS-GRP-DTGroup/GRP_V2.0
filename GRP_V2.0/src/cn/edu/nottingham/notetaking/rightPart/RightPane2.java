package cn.edu.nottingham.notetaking.rightPart;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import cn.edu.nottingham.notetaking.rightPart.util.AudioPanel;
import cn.edu.nottingham.notetaking.rightPart.util.CameraPanel;
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
public class RightPane2 extends JTabbedPane {
    private static final long serialVersionUID = 483700995452230439L;

    private JPanel audioPanel;
    private JTabbedPane officePane;
    private JTabbedPane pdfPane;
    private JPanel browserPanel;
    private JTabbedPane imagePane;
    private JTabbedPane videopanel;
    private JPanel cameraPanel;
    

    /**
     * constructor
     *
     * @author PENGHAO LI,WENYU DU
     */
    public RightPane2() {
	imagePane = new ImagePane();
	videopanel = new VideoPanel();
	cameraPanel = new CameraPanel();
	
	addTab("Image", imagePane);
	addTab("Video", videopanel);
	addTab("Camera", cameraPanel);
    }
    
    
}
