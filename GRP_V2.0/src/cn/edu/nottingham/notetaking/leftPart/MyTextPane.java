package cn.edu.nottingham.notetaking.leftPart;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;

/**This is the class for textPane including saving and reading function
 *
 * @author Yufan SHAO
 */

/** Creates a new instance of JTextPanel_N */

public class MyTextPane extends JTextPane {
	
//	 private BufferedImage buffer = null;

    StringBuffer temp = new StringBuffer();
    javax.swing.text.html.HTMLDocument hd;
    int tempnum = 0;

    public MyTextPane() {
	super.setContentType("text/html");
	super.setEditable(false);
	hd = new HTMLDocument();
	hd = (HTMLDocument) super.getDocument();

    }

    
/** save file function */
    public void saveAsObj(String address) {

	try {
	    File writeF = new File(address);
	    if (!writeF.exists()) {
		writeF.createNewFile();
	    }
	    StyledDocument doc = (StyledDocument) this.getDocument();
	    FileOutputStream fos = new FileOutputStream(address);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(doc);
	    oos.flush();
	    oos.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

/** read file function
     * @param address */
    public void readFromObj(String address) {
	try {
	    File writeF = new File(address);
	    if (!writeF.exists()) {
		return;
	    }
	    FileInputStream fis = new FileInputStream(address);
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    StyledDocument doc = (StyledDocument) ois.readObject();
	    ois.close();
	    this.setStyledDocument(doc);
	    validate();
	} catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}

    }
    
//    @Override public void paintComponent(Graphics g) {
//        Component window = this.getTopLevelAncestor();
//        if (window instanceof Window && !((Window)window).isOpaque()) {
//            // This is a translucent window, so we need to draw to a buffer
//            // first to work around a bug in the DirectDraw rendering in Swing.
//            int w = this.getWidth();
//            int h = this.getHeight();
//            if (buffer == null || buffer.getWidth() != w || buffer.getHeight() != h) {
//                // Create a new buffer based on the current size.
//                GraphicsConfiguration gc = this.getGraphicsConfiguration();
//                buffer = gc.createCompatibleImage(w, h, BufferedImage.TRANSLUCENT);
//            }
//
//            // Use the super class's paintComponent implementation to draw to
//            // the buffer, then write that buffer to the original Graphics object.
//            Graphics bufferGraphics = buffer.createGraphics();
//            try {
//                super.paintComponent(bufferGraphics);
//            } finally {
//                bufferGraphics.dispose();
//            }
//            g.drawImage(buffer, 0, 0, w, h, 0, 0, w, h, null);
//        } else {
//            // This is not a translucent window, so we can call the super class
//            // implementation directly.
//            super.paintComponent(g);
//        }        
//    }

}