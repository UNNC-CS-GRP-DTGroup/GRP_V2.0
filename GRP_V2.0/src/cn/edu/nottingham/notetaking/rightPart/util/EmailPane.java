package cn.edu.nottingham.notetaking.rightPart.util;

import javax.swing.JTabbedPane;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
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

public class EmailPane extends JTabbedPane implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame frame = new JFrame();
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	
	JLabel smtp = new JLabel("Smtp Server");
	JLabel username = new JLabel("Username");
	JLabel password = new JLabel("Password");
	JLabel from = new JLabel("From");
	JLabel to = new JLabel("To");
	JLabel cc = new JLabel("CC");
	JLabel subject = new JLabel("Subject");
	JLabel attachment = new JLabel("Attachment");
	
	MyJTextField smtp1 = new MyJTextField();
	MyJTextField username1 = new MyJTextField();
	MyJTextField password1 = new MyJTextField();
	MyJTextField from1 = new MyJTextField();
	MyJTextField to1 = new MyJTextField();
	MyJTextField cc1 = new MyJTextField();
	MyJTextField subject1 = new MyJTextField();
	MyJTextField attachment1 = new MyJTextField();
	MyJTextField content = new MyJTextField();

	
	String attach;
	
	JButton choose = new JButton("Choose");
	JButton send = new JButton("Send");
	JButton clear = new JButton("Clear");
	
	JFileChooser jfc = new JFileChooser();
	
	
	public EmailPane(){

		frame.getContentPane().setLayout(new GridLayout(4,1,5,5));
		jp1.setLayout(new GridLayout(7,2,5,5));
		jp1.add(smtp);
		jp1.add(smtp1);
		jp1.add(username);
		jp1.add(username1);
		jp1.add(password);
		jp1.add(password1);
		jp1.add(from);
		jp1.add(from1);
		jp1.add(to);
		jp1.add(to1);
		jp1.add(cc);
		jp1.add(cc1);
		jp1.add(subject);
		jp1.add(subject1);
		JScrollPane scroller = new JScrollPane(content);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		jp2.setLayout(new GridLayout(1,3,5,5));
		jp2.add(attachment,0);
		jp2.add(attachment1,1);
		jp2.add(choose,2);
		
		jp4.setLayout(new FlowLayout());
		jp4.add(send);
		jp4.add(clear);
		
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
		
		
		
	
		frame.getContentPane().add(jp1);
		frame.getContentPane().add(jp2);
		frame.getContentPane().add(scroller);
		frame.getContentPane().add(jp4);
		
		
		
		
		
		
		double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	            
	            attach = f.getAbsolutePath();
	        }
		
	}

}
