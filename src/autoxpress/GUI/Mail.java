package autoxpress.GUI;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;

public class Mail {
    
    private static final String USERNAME = "autoxpresstn@gmail.com";
    private static final String PASSWORD = "vfhsaslvbnlyabfv";

    public static void envoyer(String email) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
             }
        });
        Message message = prepareMessage(session, email);
        Transport.send(message);
        System.out.println("Message envoyé avec succès.");
    }
    
    private static Message prepareMessage(Session session, String email){ 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("confiramation de reservation");                
            message.setText("Bonjour,\n\nConfirmation de l'identification du compte.\n\nCordialement,\nL'équipe de support");
            return message;
        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}