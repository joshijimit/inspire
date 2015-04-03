package org.inspire.logic;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

/**
 * A simple email sender class.
 */
public class MSMailSenderLogic {
	
	public void send(String smtpServer, String to, String from,
			String subject, String body) {
		try {
			
			Properties props = System.getProperties();
			// -- Attaching to default Session, or we could start a new one --
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.starttls.enable","true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
            
	        Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            
			// -- Create a new message --
			MimeMessage msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
					to, false));
			// -- We could include CC recipients too --
			// if (cc != null)
			// msg.setRecipients(Message.RecipientType.CC
			// ,InternetAddress.parse(cc, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);	
			msg.setText(body);
			msg.setContent(body, "text/html");
			
			// -- Set some other header information --
			msg.setHeader("X-Mailer", "LOTONtechEmail");
			msg.setSentDate(new Date());
			// -- Send the message --
			Transport.send(msg);
			System.out.println("Message sent OK.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        return new PasswordAuthentication("joshijimit@gmail.com", "jimit131611");
	    }
	}
}

