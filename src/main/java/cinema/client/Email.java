package cinema.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class Email {

    private final JavaMailSender emailSender;

    public void getEmail(String to) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("from");
        helper.setTo(to);
        helper.setSubject("Отчет");
        helper.setText("Отчет по фильмам");

        FileSystemResource file
                = new FileSystemResource(new File("C:\\XML/file.xml"));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);
    }
}
