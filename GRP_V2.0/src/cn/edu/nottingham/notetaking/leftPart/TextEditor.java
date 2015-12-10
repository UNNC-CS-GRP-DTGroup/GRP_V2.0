package cn.edu.nottingham.notetaking.leftPart;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TextEditor extends JPanel{
//	static MyTextPane tp = new MyTextPane();
	
	TextEditor(MyTextPane tp)
    {
    	Font font = new Font("Arial", Font.PLAIN, 14); // set default font
    	tp.setFont(font);
    	tp.setForeground(Color.black);
    	
    	JPanel panel = new JPanel();
    	JMenu menu2 = new JMenu("Size");
    	JMenuItem item = new JMenuItem();
		item = new JMenuItem("8"); // set size to 8
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 8;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("9"); // set size to 9
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 9;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("10"); // set size to 10
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 10;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("12"); // set size to 12
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 12;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("14"); // set size to 14
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 14;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("16"); // set size to 16
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 16;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("18"); // set size to 18
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 18;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("20"); // set size to 20
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 20;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("24"); // set size to 24
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 24;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("28"); // set size to 28
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 28;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("32"); // set size to 32
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 32;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("48"); // set size to 48
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 48;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
		item = new JMenuItem("72"); // set size to 72
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Font font = tp.getFont();
				String a = font.getFamily();
				int b = font.getStyle();
				int c = 72;
				font = new Font(a,b,c);
				tp.setFont(font);
			}
		});
		menu2.add(item);
    	panel.add(menu2);
    	
    	// add the font family function
    			JMenu menu3 = new JMenu("Font");
    			item = new JMenuItem("Arial");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Arial";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Calibri");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Calibri";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Comic Sans MS");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Comic Sans MS";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Freestyle Script");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Freestyle Script";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Impact");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Impact";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Times New Roman");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Times New Roman";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Trebuchet MS");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Trebuchet MS";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			item = new JMenuItem("Verdana");
    			item.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					String a = "Verdana";
    					int b = font.getStyle();
    					int c = font.getSize();
    					font = new Font(a,b,c);
    					tp.setFont(font);
    				}
    			});
    			menu3.add(item);
    			panel.add(menu3);
    			
    			JPanel panelx = new JPanel(); // padding panel
    			panelx.setLayout(new FlowLayout());
    			panelx.setBackground(new Color(240,240,255));
    			JPanel panel1 = new JPanel(); // panel for the font color
    			panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    			panel1.setLayout(new GridLayout(2,1));
    			JLabel label = new JLabel("  Color");
    			panel1.add(label);
    			// add a color chooser
    				class ColorChooser extends AbstractAction {
    						ColorChooser(String str)
    						{
    							super(str);
    						}
    						public void actionPerformed(ActionEvent ee)
    						{
    							Color c = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
    							tp.setForeground(c); // apply the color chooser for the text area
    						}
    					} 
    			JButton button = new JButton(new ColorChooser("Text Color")); // add the color chooser as an item
    			panel1.add(button); // add the color chooser to the section panel
    			panelx.add(panel1); // add the section panel to the padding panel
    			panel.add(panelx); // add the padding + section panel to the main panel
    			
    			panelx = new JPanel(); // padding panel
    			panelx.setLayout(new FlowLayout());
    			panelx.setBackground(new Color(240,240,255));
    			panel1 = new JPanel(); // panel for editing the font style
    			panel1.setLayout(new GridLayout(2,1)); // panel to divide the label and the button
    			panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    			label = new JLabel("  Font Style"); // panel label
    			panel1.add(label); 
    			JPanel panel2 = new JPanel(); // panel for the buttons
    			panel2.setLayout(new GridLayout(1,3));
    			button = new JButton("Bold"); // set text to bold
    			button.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					tp.setFont(font.deriveFont(Font.BOLD));
    				}
    			});
    			panel2.add(button);
    			button = new JButton("Italic"); // set text to italic
    			button.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					tp.setFont(font.deriveFont(Font.ITALIC));
    				}
    			});
    			panel2.add(button);
    			button = new JButton("No Effects"); // set text to no effect
    			button.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e)
    				{
    					Font font = tp.getFont();
    					tp.setFont(font.deriveFont(Font.PLAIN));
    				}
    			});
    			panel2.add(button);
    			panel1.add(panel2); // add the buttons panel to the section panel
    			panelx.add(panel1); // add the section panel to the padding panel
    			panel.add(panelx); // add everything to the main panel
    }
	
//	public static void main(String args[])
//	{
//		TextEditor te = new TextEditor(tp);
//	}
}
