package cn.edu.nottingham.notetaking.rightPart.util;

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.edu.nottingham.notetaking.MyJTextField;

public class EmailPane extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JFrame tempFrame = null;
	private FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
	JPanel outPanel = new JPanel(new GridLayout(1,1));
	JPanel jp_smtp = new JPanel(new BorderLayout());
	JPanel jp_username = new JPanel(new BorderLayout());
	JPanel jp_password = new JPanel(new BorderLayout());
	JPanel jp_from = new JPanel(new BorderLayout());
	JPanel jp_to = new JPanel(new BorderLayout());
	JPanel jp_cc = new JPanel(new BorderLayout());
	JPanel jp_subject = new JPanel(new BorderLayout());
	JPanel jp_attachment = new JPanel(new BorderLayout());
	JPanel jp_content = new JPanel(new BorderLayout());
	JPanel jp_button = new JPanel(new GridLayout(1,2));
	
	JLabel smtp = new JLabel    (" Smtp Server  ");
	JLabel username = new JLabel(" Username     ");
	JLabel password = new JLabel(" Password     ");
	JLabel from = new     JLabel(" From         ");
	JLabel to = new JLabel      (" To           ");
	JLabel cc = new JLabel      (" CC           ");
	JLabel subject = new JLabel (" Subject      ");
	JLabel attachment = new JLabel(" Attachment   ");
	
	MyJTextField smtp1 = new MyJTextField(30);
	MyJTextField username1 = new MyJTextField(30);
	JPasswordField password1 = new JPasswordField(30);
	MyJTextField from1 = new MyJTextField(30);
	MyJTextField to1 = new MyJTextField(30);
	MyJTextField cc1 = new MyJTextField(30);
	MyJTextField subject1 = new MyJTextField(30);
	MyJTextField attachment1 = new MyJTextField(30);
	JTextArea content = new JTextArea(10,52);
	JScrollPane scroll_content = new JScrollPane(content);
	

	
	String attach;
	JButton choose = new JButton("Choose");
	JButton send = new JButton("Send");
	JButton clear = new JButton("Clear");
	
	JFileChooser jfc;
	
	private static int width = 450;

	private static int height = 595;

	private static int x = (int) Toolkit.getDefaultToolkit().getScreenSize()
	.getWidth()
	/ 2 - width / 2;

	private static int y = (int) Toolkit.getDefaultToolkit().getScreenSize()
	.getHeight()
	/ 2 - height / 2;
	
	
	public EmailPane(){
		super(tempFrame, "Email");
		
 		Thread emailThread = new Thread(new Runnable() {

			@Override
			public void run() {
				jfc = new JFileChooser();
			}
 		});
		
 		emailThread.start();
		this.add(outPanel);
		outPanel.setLayout(fl);
		
		jp_smtp.add(smtp,BorderLayout.WEST);
		jp_smtp.add(smtp1,BorderLayout.EAST);
		outPanel.add(jp_smtp);
		
		jp_username.add(username, BorderLayout.WEST);
		jp_username.add(username1,BorderLayout.EAST);
		outPanel.add(jp_username);
		
		jp_password.add(password, BorderLayout.WEST);
		jp_password.add(password1, BorderLayout.EAST);
		outPanel.add(jp_password);
		
		jp_from.add(from, BorderLayout.WEST);
		jp_from.add(from1, BorderLayout.EAST);
		outPanel.add(jp_from);
		
		jp_to.add(to, BorderLayout.WEST);
		jp_to.add(to1, BorderLayout.EAST);
		outPanel.add(jp_to);
		
		jp_cc.add(cc, BorderLayout.WEST);
		jp_cc.add(cc1, BorderLayout.EAST);
		outPanel.add(jp_cc);
		
		jp_subject.add(subject, BorderLayout.WEST);
		jp_subject.add(subject1, BorderLayout.EAST);
		outPanel.add(jp_subject);
		
		jp_attachment.add(attachment, BorderLayout.WEST);
		choose.setPreferredSize(new Dimension(190, 20));
		jp_attachment.add(choose, BorderLayout.EAST);
		outPanel.add(jp_attachment);
		
		jp_content.add(scroll_content, BorderLayout.CENTER);
		scroll_content.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_content.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.setLineWrap(true);
		outPanel.add(jp_content);
		
		send.setPreferredSize(new Dimension(190,40));
		clear.setPreferredSize(new Dimension(190,40));
		jp_button.add(send);
		jp_button.add(clear);
		outPanel.add(jp_button);
		
		
		
		

		
		
		
		
		clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				smtp1.setText(null);
				from1.setText(null);
				to1.setText(null);
				cc1.setText(null);
				username1.setText(null);
				password1.setText(null);
				subject1.setText(null);
				content.setText(null);
				attachment1.setText(null);
				
				jp_attachment.remove(attachment1);
	            jp_attachment.add(choose);
	            jp_attachment.setVisible(false);
	            jp_attachment.setVisible(true);
				
			}
			
			
		});
		choose.addActionListener(this);
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String smtp2 = smtp1.getText();
				System.out.println(smtp2);
				String from2 = from1.getText();
				System.out.println(from2);
				String to2 = to1.getText();
				System.out.println(to2);
				String cc2 = cc1.getText();
				System.out.println(cc2);
				String username2 = username1.getText();
				System.out.println(username2);
				String password2 = password1.getText();
				System.out.println(password2);
				String subject2 = subject1.getText();
				System.out.println(subject2);
				String text2 = content.getText();
				System.out.println(text2);
				try{
					Mail.sendAndCc(smtp2, from2, to2, cc2, subject2, text2, username2, password2,attach);
					JOptionPane.showMessageDialog(null, "发送成功！");
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null, "发送失败!\n"+e1.getMessage());
					e1.printStackTrace();
				}
			
			}
		});
		
		
		this.setLocation(x, y);
		this.setSize(width,height);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
			jfc.setFileSelectionMode(0);
	        int state=jfc.showOpenDialog(null);
	        if(state==1){
	            return;
	        }
	        else{
	            File f=jfc.getSelectedFile();//f为选择到的目录
	            attachment1.setText(f.getAbsolutePath());
	            attachment1.setEditable(false);
	            attach = f.getAbsolutePath();
	            jp_attachment.remove(choose);
	            jp_attachment.add(attachment1);
	            jp_attachment.setVisible(false);
	            jp_attachment.setVisible(true);
	            
	            
	        }
		
	}

}
