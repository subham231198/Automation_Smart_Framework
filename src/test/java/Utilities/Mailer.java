package Utilities;

import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {
    static Properties prop = new Properties();
    public Mailer()
    {
        this.prop = prop;
    }
    public static void sendEmail(String subject, String body) throws IOException {
        try
        {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException("Requested file does not exist!");
        }

        String from = prop.getProperty("sender");
        String password = prop.getProperty("sender_mail_password");
        String to = prop.getProperty("recipient");
        prop.clear();
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        if(from!= null && password!=null && from.length()>0 && password.length()>0)
        {
            System.out.println("Sender: "+from+" Recipient: "+to);
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);

                MimeMultipart multipart = new MimeMultipart();

                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(body, "utf-8");
                multipart.addBodyPart(textPart);

                MimeBodyPart attachmentPart = new MimeBodyPart();
                String attachmentFilePath = "D:\\Automation Project\\HybridFramework_Selenium\\target\\cucumber-reports.html";
                attachmentPart.attachFile(attachmentFilePath);

                multipart.addBodyPart(attachmentPart);
                message.setContent(multipart);
                Transport.send(message);

                System.out.println("<!---Mail Sent Successfully---!>");
            } catch (MessagingException | IOException mex) {
                mex.printStackTrace();
            }
        } else {
            System.out.println("<!---Invalid mailer credentials given, could not send email---!>");
        }
    }
}

