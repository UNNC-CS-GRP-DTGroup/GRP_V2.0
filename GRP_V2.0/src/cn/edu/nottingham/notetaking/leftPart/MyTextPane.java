package cn.edu.nottingham.notetaking.leftPart;

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

}