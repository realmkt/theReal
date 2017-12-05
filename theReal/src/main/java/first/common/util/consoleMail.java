package first.common.util;

import java.util.Properties;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/*import com.top.common.util.StringUtil;
import com.top.eng.entity.Entity;
*/


import org.apache.poi.util.StringUtil;

public class consoleMail {
	 private static final String SMTP_HOST_NAME = "smtp.naver.com";    //HOST
	 private static final String SMTP_AUTH_USER = "rhkdhlgkrtod@naver.com";              //ID
	 private static final String SMTP_AUTH_PWD = "wkdtk9769";                  //PASSWORD
	 
	 
	  public void postMail(String recipients[], String subject, String message, String from, String userNm) throws Exception {
		  System.out.println("console1");
		  boolean debug = false;
		  
		  try {
		   System.out.println("console2");
		  //Set the host smtp address
		  Properties props = new Properties();
		  props.put("mail.transport.protocol", "smtp");
		  props.put("mail.smtp.starttls.enable","true");
		  props.put("mail.smtp.host", SMTP_HOST_NAME);
		  props.put("mail.smtp.auth", "true");
		 
		  Authenticator auth = new SMTPAuthenticator();
		  Session session = Session.getDefaultInstance(props, auth);
		 
		  session.setDebug(debug);
		 
		  // create a message
		  Message msg = new MimeMessage(session);
		 
		  // set the from and to address
		  InternetAddress addressFrom = new InternetAddress(from);
		  msg.setFrom(addressFrom);
		 
		  InternetAddress[] addressTo = new InternetAddress[recipients.length];
		  for (int i = 0; i < recipients.length; i++) {
		   addressTo[i] = new InternetAddress(recipients[i]);
		  }
		  msg.setRecipients(Message.RecipientType.TO, addressTo);
		 
		  //메일 제목///
		  subject = "결제 영수증 입니당";/*StringUtil.getDate( StringUtil.getOffsetDateToDate(StringUtil.getToday("yyyyMMdd"), -1), "yyyy-MM-dd")+"일 정보입니다.";*/
		msg.setSubject(MimeUtility.encodeText(subject,"EUC-KR","B"));
		 
		  //메일 내용
		  message = "<html><body><font color=green>더리얼 결제영수증 입니다.</font></body></html>";
		  
		  MimeBodyPart mbp_cont = new MimeBodyPart();
		  mbp_cont.setContent(message, "text/html; charset=euc-kr");   //한글 과 html사용  
		  String fileaddress = "../test -TheReal 이메일영수증.html"; //파일이 있는 절대경로
		  MimeBodyPart mbp = new MimeBodyPart();
		  FileDataSource fds = new FileDataSource(fileaddress);
		  mbp.setDataHandler(new DataHandler(fds));
		  mbp.setFileName(MimeUtility.encodeText(fds.getName(),"EUC-KR","B"));   //한글파일네임을 적기 위해서
		 
		  Multipart mp = new MimeMultipart();
		  mp.addBodyPart(mbp_cont);
		  mp.addBodyPart(mbp);
		  msg.setContent(mp);
		

		 
		  Transport.send(msg);
		  } catch (UnsupportedEncodingException e) {
		   e.printStackTrace();
		  }
		 }
		 
		 /**
		  * SimpleAuthenticator is used to do simple authentication when the SMTP
		  * server requires it.
		  */
		 private class SMTPAuthenticator extends Authenticator {
		 
		  public PasswordAuthentication getPasswordAuthentication() {
		   String username = SMTP_AUTH_USER;
		   String password = SMTP_AUTH_PWD;
		   return new PasswordAuthentication(username, password);
		  }
		 }
}
