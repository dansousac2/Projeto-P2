import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Avisar {
	private String email;
	private String NomeLivro;

	public Avisar(String Nome, String NomeL) throws AddressException, MessagingException {
		email = Nome;
		NomeLivro = NomeL;
		verificar();
	}
	public void verificar() throws AddressException, MessagingException {
		 Properties props = new Properties();
			props.put("mail.smtp.user", "estanteonlineifpb@gmail.com"); 
	        props.put("mail.smtp.host", "smtp.gmail.com"); 
	        props.put("mail.smtp.port", "25"); 
	        props.put("mail.debug", "true"); 
	        props.put("mail.smtp.auth", "true"); 
	        props.put("mail.smtp.starttls.enable","true"); 
	        props.put("mail.smtp.EnableSSL.enable","true");
	        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
	        props.setProperty("mail.smtp.port", "465");   
	        props.setProperty("mail.smtp.socketFactory.port", "465");
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        	protected PasswordAuthentication getPasswordAuthentication() 
	        	{
	        		return new PasswordAuthentication("estanteonlineifpb@gmail.com", "estante123");
	        	}
	        });
	        session.setDebug(true);
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("estanteonlineifpb@gmail.com"));
	        Address[] toUser = InternetAddress.parse(email);
	        message.setRecipients(Message.RecipientType.TO, toUser);
	        message.setSubject("Livraria Stile");
	        message.setText("O Livro "+NomeLivro+" já está disponivel em nossa loja! venha conferir!");
	        Transport.send(message);
	}
}
