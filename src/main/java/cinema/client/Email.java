package cinema.client;

import cinema.configuration.EmailConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class Email {

    private final EmailConfiguration session;
    final String from = "from";

    public void getEmail() throws MessagingException, IOException {
        Message message = new MimeMessage(session.getSession());
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("to"));
        message.setSubject("Mail Subject");
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File("C:\\XML/file.xml"));
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
