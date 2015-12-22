package cn.edu.nottingham.notetaking.rightPart.util;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

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
import javax.swing.border.Border;

import com.edu.nottingham.notetaking.MyJTextField;

public class EmailPane extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JFrame tempFrame = null;
	private FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
	JPanel outPanel = new JPanel(new GridLayout(1,1));
	JPanel jp_to = new JPanel(new FlowLayout());
	JPanel jp_cc = new JPanel(new FlowLayout());
	JPanel jp_subject = new JPanel(new FlowLayout());
	JPanel jp_attachment = new JPanel(new FlowLayout());
	JPanel jp_content = new JPanel(new BorderLayout());
	JPanel jp_button = new JPanel(new FlowLayout());
	JPanel jp_aths = new JPanel();
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("Account");
	private JMenuItem menuItemSetting = new JMenuItem("Settings..");
	private ConfigUtility configUtil = new ConfigUtility();
	
	JLabel smtp = new JLabel    (" Smtp Server  ");
	JLabel username = new JLabel(" Username     ");
	JLabel password = new JLabel(" Password     ");
	JLabel from = new     JLabel(" From         ");
	JLabel to = new JLabel      (" To                ");
	JLabel cc = new JLabel      (" CC               ");
	JLabel subject = new JLabel (" Subject      ");
	JLabel attachment = new JLabel("  Attach       ");
	
	MyJTextField smtp1 = new MyJTextField(30);
	MyJTextField username1 = new MyJTextField(30);
	JPasswordField password1 = new JPasswordField(30);
	MyJTextField from1 = new MyJTextField(30);
	MyJTextField to1 = new MyJTextField(30);
	MyJTextField cc1 = new MyJTextField(30);
	MyJTextField subject1 = new MyJTextField(30);
	//MyJTextField attachment1 = new MyJTextField(30);
	JTextArea content = new JTextArea(10,37);
	JScrollPane scroll_content = new JScrollPane(content);
	

	ArrayList<String> attach = new ArrayList<String>();
	JButton choose = new JButton("Choose");
	JButton send = new JButton("Send");
	JButton clear = new JButton("Clear");
	JButton remove = new JButton("Remove");
	
	JFileChooser jfc;
	
	
	private static int width = 427;

	private static int height = 445;

	private static int x = (int) Toolkit.getDefaultToolkit().getScreenSize()
	.getWidth()
	/ 2 - width / 2;

	private static int y = (int) Toolkit.getDefaultToolkit().getScreenSize()
	.getHeight()
	/ 2 - height / 2;
	
	
	public EmailPane(){
		super(tempFrame, "Email");
		
//<<<<<<< HEAD
//		jfc = new JFileChooser();
//=======
		jp_aths.setLayout(new BoxLayout(jp_aths,BoxLayout.Y_AXIS));
		menuItemSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				SettingsDialog dialog = new SettingsDialog(EmailPane.this, configUtil);
				dialog.setVisible(true);
			}
		});
		menuFile.add(menuItemSetting);
		menuBar.add(menuFile);
		setJMenuBar(menuBar);
		
//>>>>>>> refs/remotes/origin/new_feature#4
		this.add(outPanel);
		outPanel.setLayout(fl);
		
		/*jp_smtp.add(smtp,BorderLayout.WEST);
		jp_smtp.add(smtp1,BorderLayout.EAST);
		outPanel.add(jp_smtp);
		
		jp_username.add(username, BorderLayout.WEST);
		jp_username.add(username1,BorderLayout.EAST);
		outPanel.add(jp_username);*/
		
		/*jp_password.add(password, BorderLayout.WEST);
		jp_password.add(password1, BorderLayout.EAST);
		outPanel.add(jp_password);
		*/
		/*jp_from.add(from, BorderLayout.WEST);
		jp_from.add(from1, BorderLayout.EAST);
		outPanel.add(jp_from);*/
		
		jp_to.add(to);
		jp_to.add(to1);
		outPanel.add(jp_to);
		
		jp_cc.add(cc);
		jp_cc.add(cc1);
		outPanel.add(jp_cc);
		
		jp_subject.add(subject);
		jp_subject.add(subject1);
		outPanel.add(jp_subject);
		
		jp_attachment.add(attachment);
		choose.setPreferredSize(new Dimension(160, 20));
		jp_attachment.add(choose);
		jp_attachment.add(Box.createHorizontalStrut(10));
		remove.setPreferredSize(new Dimension(152,20));
		jp_attachment.add(remove);
		outPanel.add(jp_attachment);
		
		outPanel.add(jp_aths);
		
		jp_content.add(scroll_content, BorderLayout.CENTER);
		scroll_content.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_content.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.setLineWrap(true);
		outPanel.add(jp_content);
		
		send.setPreferredSize(new Dimension(190,40));
		clear.setPreferredSize(new Dimension(190,40));
		jp_button.add(send);
		jp_button.add(Box.createHorizontalStrut(10));
		jp_button.add(clear);
		outPanel.add(jp_button);
		
		
		

		remove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jp_aths.removeAll();
				//jp_aths.invalidate();
				EmailPane.this.setSize(width,445);
				initHeight(445);
				jp_aths.repaint();
				attach.clear();
			}
			
		});

		
		
		
		
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
				jp_aths.removeAll();
				//jp_aths.invalidate();
				EmailPane.this.setSize(width,445);
				initHeight(445);
				jp_aths.repaint();
				attach.clear();
				//attachment1.setText(null);
				
				//jp_attachment.remove(attachment1);
	            //jp_attachment.add(choose);
	            jp_attachment.setVisible(false);
	            jp_attachment.setVisible(true);
				
			}
			
			
		});
		choose.addActionListener(this);
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*String smtp2 = smtp1.getText();
				System.out.println(smtp2);
				String from2 = from1.getText();
				System.out.println(from2);*/
				String to2 = to1.getText();
				System.out.println(to2);
				String cc2 = cc1.getText();
				System.out.println(cc2);
				/*String username2 = username1.getText();
				System.out.println(username2);
				String password2 = password1.getText();
				System.out.println(password2);*/
				String subject2 = subject1.getText();
				System.out.println(subject2);
				String text2 = content.getText();
				System.out.println(text2);
				try{
					Properties prop = configUtil.loadProperties();
					Mail.sendAndCc(prop, to2, cc2, subject2, text2,attach);
					JOptionPane.showMessageDialog(null, "Success!");
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null, "Failed!\n"+e1.getMessage());
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
	
	private void initHeight(int num) {
		// TODO Auto-generated method stub
		height = num;
	}

	public void actionPerformed(ActionEvent e){
		//jfc.setMultiSelectionEnabled(true);
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(0);
	        int state=jfc.showOpenDialog(null);
	        if(state==1){
	            return;
	        }
	        else{
	            File f=jfc.getSelectedFile();//f为选择到的目录
	            MyJTextField ath = new MyJTextField(50);
	            ath.setText(f.getAbsolutePath());
	            ath.setEditable(false);
	            attach.add(f.getAbsolutePath());
	    		//EmailPane.this.invalidate();
	            height = height+28;
	            EmailPane.this.setSize(width,height);
	            
	            EmailPane.this.repaint();
	            
	            
	            
	            //jp_attachment.add(choose, BorderLayout.EAST);
	            jp_aths.add(ath);
	            jp_attachment.setVisible(false);
	            jp_attachment.setVisible(true);
	            
	            
	        }
		
	}

}
