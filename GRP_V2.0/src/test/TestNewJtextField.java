package test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class TestNewJtextField {

    public static class JTextField2 extends JTextField {
        private static final long serialVersionUID = 1L;
        private BufferedImage buffer = null;

        @Override public void paintComponent(Graphics g) {
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
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                final JFrame frame = new JFrame();
                frame.setUndecorated(true);
                frame.setBackground(new Color(96, 128, 160, 192));

                JTextField textField = new JTextField2();                
                JButton exitButton = new JButton("Exit");
                exitButton.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

                frame.add(exitButton, BorderLayout.PAGE_START);
                frame.add(textField, BorderLayout.PAGE_END);

                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}