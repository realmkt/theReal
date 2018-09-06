package first.common.util;

import java.io.UnsupportedEncodingException;
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

public class consoleMail
{
  private static final String SMTP_HOST_NAME = "smtp.gmail.com";
  private static final String SMTP_AUTH_USER = "RealMKTShop@gmail.com";
  private static final String SMTP_AUTH_PWD = "real7404!";

  public void postMail(String[] recipients, String subject, String content, String from, String userNm)
    throws Exception
  {
    boolean debug = false;
    try
    {
      Properties props = new Properties();

      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "587");

      Authenticator auth = new SMTPAuthenticator();
      Session session = Session.getDefaultInstance(props, auth);

      session.setDebug(debug);

      Message msg = new MimeMessage(session);

      InternetAddress addressFrom = new InternetAddress("thereal@realmkt.co.kr");
      msg.setFrom(addressFrom);

      InternetAddress[] addressTo = new InternetAddress[recipients.length];
      for (int i = 0; i < recipients.length; i++) {
        addressTo[i] = new InternetAddress(recipients[i]);
      }
      msg.setRecipients(Message.RecipientType.TO, addressTo);

      msg.setSubject(MimeUtility.encodeText(subject, "EUC-KR", "B"));

      MimeBodyPart mbp_cont = new MimeBodyPart();
      mbp_cont.setContent(content, "text/html; charset=UTF-8");
      String fileaddress = "../TheReal 이메일영수증.html";
      MimeBodyPart mbp = new MimeBodyPart();
      FileDataSource fds = new FileDataSource(fileaddress);
      mbp.setDataHandler(new DataHandler(fds));
      mbp.setFileName(MimeUtility.encodeText(fds.getName(), "EUC-KR", "B"));

      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp_cont);
      mp.addBodyPart(mbp);
      msg.setContent(mp);

      Transport.send(msg);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  private class SMTPAuthenticator extends Authenticator
  {
    private SMTPAuthenticator()
    {
    }

    public PasswordAuthentication getPasswordAuthentication() {
      String username = "RealMKTShop@gmail.com";
      String password = "real7404!";
      return new PasswordAuthentication(username, password);
    }
  }
}