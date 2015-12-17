package com.edu.nottingham.notetaking;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


/**
 * A modified JTextField which overrides paintComponent to fix a bug
 * 
 * @author Yichen PAN
 * 
 */

public class MyJTextField extends JTextField implements MouseListener {
    private static final long serialVersionUID = 1L;
    private BufferedImage buffer = null;
    
    public MyJTextField(){init();}

    public MyJTextField(int columns) {
    	super(null, null, columns);
    	init();
	}
    
    @Override 
    public void paintComponent(Graphics g) {
        Component window = this.getTopLevelAncestor();
        if (window instanceof Window && !((Window)window).isOpaque()) {
            // This is a translucent window, so we need to draw to a buffer
            // first to work around a bug in the DirectDraw rendering in Swing.
            int w = this.getWidth();
            int h = this.getHeight();
            if (buffer == null || buffer.getWidth() != w || buffer.getHeight() != h) {
                // Create a new buffer based on the current size.
                GraphicsConfiguration gc = this.getGraphicsConfiguration();
                buffer = gc.createCompatibleImage(w, h, BufferedImage.TRANSLUCENT);
            }

            // Use the super class's paintComponent implementation to draw to
            // the buffer, then write that buffer to the original Graphics object.
            Graphics bufferGraphics = buffer.createGraphics();
            try {
                super.paintComponent(bufferGraphics);
            } finally {
                bufferGraphics.dispose();
            }
            g.drawImage(buffer, 0, 0, w, h, 0, 0, w, h, null);
        } else {
            // This is not a translucent window, so we can call the super class
            // implementation directly.
            super.paintComponent(g);
        }        
    }
    
    private JPopupMenu pop = null; // 弹出菜单  
    
    private JMenuItem copy = null, paste = null, cut = null; // 三个功能菜单  
    
    private void init() {  
        this.addMouseListener(this);  
        pop = new JPopupMenu();  
        pop.add(copy = new JMenuItem("Copy"));  
        pop.add(paste = new JMenuItem("Paste"));  
        pop.add(cut = new JMenuItem("Cut"));  
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));  
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));  
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));  
        copy.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                action(e);  
            }  
        });  
        paste.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                action(e);  
            }  
        });  
        cut.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                action(e);  
            }  
        });  
        this.add(pop);  
    }  
    
    public void action(ActionEvent e) {  
        String str = e.getActionCommand();  
        if (str.equals(copy.getText())) { // 复制  
            this.copy();  
        } else if (str.equals(paste.getText())) { // 粘贴  
            this.paste();  
        } else if (str.equals(cut.getText())) { // 剪切  
            this.cut();  
        }  
    }  
  
    public JPopupMenu getPop() {  
        return pop;  
    }  
  
    public void setPop(JPopupMenu pop) {  
        this.pop = pop;  
    }  
  
    /** 
     * 剪切板中是否有文本数据可供粘贴 
     *  
     * @return true为有文本数据 
     */  
    public boolean isClipboardString() {  
        boolean b = false;  
        Clipboard clipboard = this.getToolkit().getSystemClipboard();  
        Transferable content = clipboard.getContents(this);  
        try {  
            if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {  
                b = true;  
            }  
        } catch (Exception e) {  
        }  
        return b;  
    }  
  
    /** 
     * 文本组件中是否具备复制的条件 
     *  
     * @return true为具备 
     */  
    public boolean isCanCopy() {  
        boolean b = false;  
        int start = this.getSelectionStart();  
        int end = this.getSelectionEnd();  
        if (start != end)  
            b = true;  
        return b;  
    }  

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {  
            copy.setEnabled(isCanCopy());  
            paste.setEnabled(isClipboardString());  
            cut.setEnabled(isCanCopy());  
            pop.show(this, e.getX(), e.getY());  
        }  
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}