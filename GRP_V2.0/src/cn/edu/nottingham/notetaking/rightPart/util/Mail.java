package cn.edu.nottingham.notetaking.rightPart.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	private MimeMessage mimeMsg;
	private Session session;
	private Properties props;
	private String username;
	private String password;
	private Multipart mp;
	private String filename="";  
    private Vector<String> file = new Vector();
	public Mail(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}
	

	
	public void setSmtpHost(String hostName) {
		//System.out.println("设置系统属性：mail.smtp.host=" + hostName);
		if (props == null) {
			props = System.getProperties();
		}
		props.put("mail.smtp.host", hostName);
	}
	public boolean createMimeMessage() {
		try {
			//System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null);
		} catch (Exception e) {
			//System.out.println("获取邮件会话错误！" + e);
			return false;
		}
		//System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			//System.out.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/*定义SMTP是否需要验证*/
	public void setNeedAuth(boolean need) {
		//System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}
	
	public void addAttachfile(ArrayList<String> fname){  
		for(int i=0; i<fname.size(); i++){
			file.addElement(fname.get(i));
		}
    }  

	/*定义邮件主题*/
	public boolean setSubject(String mailSubject) {
		//System.out.println("定义邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			//System.err.println("定义邮件主题发生错误！");
			return false;
		}
	}

	/*定义邮件正文*/
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailBody, "text/html;charset=GBK");
			mp.addBodyPart(bp);
			if(!file.isEmpty()){//有附件  
                Enumeration efile=file.elements();  
                while(efile.hasMoreElements()){   
                    MimeBodyPart mbp=new MimeBodyPart();  
                    filename=efile.nextElement().toString(); //选择出每一个附件名  
                    FileDataSource fds=new FileDataSource(filename); //得到数据源 
                    //System.out.println(fds.toString());
                    mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
                    mbp.setFileName(fds.getName());  //得到文件名同样至入BodyPart  
                    mp.addBodyPart(mbp);  
                }    
                file.removeAllElements();      
            }   
			return true;
		} catch (Exception e) {
			//System.err.println("定义邮件正文时发生错误！" + e);
			return false;
		}
	}

	/*设置发信人*/
	public boolean setFrom(String from) {
		//System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); //发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*定义收信人*/
	public boolean setTo(String to) {
		if (to == null)
			return false;
		//System.out.println("定义收信人！");
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*定义抄送人*/
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress
					.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*发送邮件模块*/
	public boolean sendOut() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			//System.out.println("邮件发送中....");
			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username, password);
			transport.sendMessage(mimeMsg, mimeMsg
			.getRecipients(Message.RecipientType.TO));
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
			//System.out.println("发送成功！");
			transport.close();
			return true;
		} catch (Exception e) {
			//System.err.println("邮件失败！" + e);
			return false;
		}
	}

	/*调用sendOut方法完成发送*/
	public static boolean sendAndCc(Properties prop, String to, String copyto,
		String subject, String content,ArrayList<String> attach) {
		String smtp1 = prop.getProperty("mail.smtp.host");
		String username1 = prop.getProperty("mail.user");
		String password1 = prop.getProperty("mail.password");
		System.out.println(smtp1);
		Mail theMail = new Mail(smtp1);
		theMail.setNeedAuth(true); // 验证
		theMail.addAttachfile(attach);
		if (!theMail.setSubject(subject))
			return false;
		if (!theMail.setBody(content))
			return false;
		if (!theMail.setTo(to))
			return false;
		//if (!theMail.setCopyTo(copyto))
			//return false;
		if (!theMail.setFrom(username1))
			return false;
		theMail.setNamePass(username1, password1);
		if (!theMail.sendOut())
			return false;
		return true;
	}
}
